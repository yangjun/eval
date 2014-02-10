/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.eva;

import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.NameEvaluatedData;
import com.eu.evaluation.model.dictionary.ObjectRelation;
import com.eu.evaluation.model.eva.UpAndDownEvlauateItem;
import com.eu.evaluation.model.eva.history.UpAndDownEvlauateItemHistory;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.dictionary.ObjectRelationDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.service.Evaluating;
import java.text.MessageFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class UpAndDownEvaluate implements Evaluating<UpAndDownEvlauateItem, UpAndDownEvlauateItemHistory> {

    protected Log logger = LogFactory.getLog(this.getClass());

    private String notPassMessage = "";

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;

    @Autowired
    private ObjectRelationDAO objectRelationDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, AccessSystem accessSystem, String instanceClass, int instanceType, String instanceID) throws Exception {
        String debugInfo = "{0} 执行上下级关系评测，所属地区局 = {1} , 被评测对象类型 = {2} , 被评测对象实体ID = {3}";
        UpAndDownEvlauateItemHistory ev = (UpAndDownEvlauateItemHistory) evaluateItemHistoryDAO.get(evaluateItemHistoryID);
        logger.debug(MessageFormat.format(debugInfo, new Object[]{"开始", accessSystem.getName(), ev.getObjectDictionary().getDisplayname(), instanceID}));

        Long upCount = 1L;
        if (ev.getUpEntity() != null) {//查找当前对象是否具有指定的父级对象
            upCount = countUp(ev, accessSystem, instanceID);
        }

        Long downCount = 1L;
        if (ev.getDownEntity() != null) {//查找当前资源是否具有指定的子集对象
            downCount = countDown(ev, accessSystem, instanceID);
        }

        notPassMessage = getErrorMsg(ev, accessSystem, instanceClass, instanceType, instanceID, upCount, downCount);

        logger.debug(MessageFormat.format(debugInfo, new Object[]{"完成", accessSystem.getName(), ev.getObjectDictionary().getDisplayname(), instanceID}));
        return (upCount == 1 && downCount > 0);
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        return notPassMessage;
    }

    private String getErrorMsg(UpAndDownEvlauateItemHistory ev, AccessSystem accessSystem, String instanceClass, int instanceType, String instanceID, Long upCount, Long downCount) {
        if (upCount != 1 || downCount <= 0) {
            Object entity = defaultDAO.findEvaluateData(instanceClass, instanceID, ev.getEvaluateVersion().getId(), accessSystem);//获取被评测数据
            String name = ((EvaluatedData) entity).getId();
            if (entity instanceof NameEvaluatedData) {
                name = ((NameEvaluatedData) entity).getName();
            }

            if (upCount != 1 && downCount <= 0) {
                String message = "{0} “{1}” 的上下级关系评测未通过。原因：不属于任何{2}；不包含任何{3}！";
                return MessageFormat.format(message, new Object[]{ev.getObjectDictionary().getDisplayname(), name, ev.getUpEntity().getDisplayname(), ev.getDownEntity().getDisplayname()});
            } else if (upCount != 1) {
                String message = "{0} “{1}” 的上下级关系评测未通过。原因：不属于任何{2}！";
                return MessageFormat.format(message, new Object[]{ev.getObjectDictionary().getDisplayname(), name, ev.getUpEntity().getDisplayname()});
            } else {
                String message = "{0} “{1}” 的上下级关系评测未通过。原因：不包含任何{2}！";
                return MessageFormat.format(message, new Object[]{ev.getObjectDictionary().getDisplayname(), name, ev.getDownEntity().getDisplayname()});
            }
        } else {
            return "";
        }
    }

    /**
     * 查找当前对象是否具有指定的父级对象
     *
     * @param ev
     * @param instanceID 当前资源ID
     * @return
     */
    private Long countUp(UpAndDownEvlauateItemHistory ev, AccessSystem accessSystem, String instanceID) {
        //查找对象父子关系，注意得到的结果，or.self = 当前被评对象，or.parent = 父级对象
        ObjectRelation or = objectRelationDAO.findByParent(ev.getObjectDictionary().getInstanceClass(), ev.getUpEntity().getInstanceClass());

        if (or == null) {
            throw new RuntimeException("上下级关系评测时产生错误！原因：对象关系配置错误，本体 = " + ev.getObjectDictionary().getDisplayname() + " ; 父级 = " + ev.getUpEntity().getDisplayname() + "的对象关系不存在");
        }

        //构建sql，查询父级是否存在
        String jpql = "select count(*) from {0} t where t.id = :id and t.evaluateVersion.id = :evid and t.position = :position and exists (select 1 from {1} p where p.evaluateVersion.id = t.evaluateVersion.id and p.position = t.position and p.id = t.{2}";
        if (or.isSimpleProperty()) {//如果关联属性是简单对象，直接采用p.id = t.field的模式
            jpql += ")";
        } else {//如果关联属性不是简单对象，则采用p.id = t.field.id的模式
            jpql += ".id)";
        }
        jpql = MessageFormat.format(jpql, new Object[]{or.getSelfClass(), or.getRelationClass(), or.getPropertyName()});
        MapSqlParameterSource params = new MapSqlParameterSource("id", instanceID);
        params.addValue("evid", ev.getEvaluateVersion().getId());
        params.addValue("position", accessSystem.getCode());

        logger.debug("评测父级sql" + jpql + "\n; 参数： = " + instanceID);
        return defaultDAO.executeCount(jpql, params);
    }

    /**
     * 查找当前资源是否具有指定的子集对象
     *
     * @param ev
     * @param instanceID 当前资源ID
     * @return
     */
    private Long countDown(UpAndDownEvlauateItemHistory ev, AccessSystem accessSystem, String instanceID) {
        //查找对象父子关系，注意得到的结果，or.self = 下级对象，or.parent = 当前被评对象
        ObjectRelation or = objectRelationDAO.findByParent(ev.getDownEntity().getInstanceClass(), ev.getObjectDictionary().getInstanceClass());
        if (or == null) {
            throw new RuntimeException("上下级关系评测时产生错误！原因：对象关系配置错误，本体 = " + ev.getDownEntity().getDisplayname() + " ; 父级 = " + ev.getObjectDictionary().getDisplayname() + "的对象关系不存在");
        }

        //构建sql，查询父级是否存在
        String jpql = "select count(*) from {0} t where t.evaluateVersion.id = :evid and t.position = :position and exists (select 1 from {1} p where p.evaluateVersion.id = t.evaluateVersion.id and p.position = t.position and p.id = :id and p.id = t.{2}";
        if (or.isSimpleProperty()) {//如果关联属性是简单对象，直接采用p.id = t.field的模式
            jpql += ")";
        } else {//如果关联属性不是简单对象，则采用p.id = t.field.id的模式
            jpql += ".id)";
        }
        jpql = MessageFormat.format(jpql, new Object[]{or.getSelfClass(), or.getRelationClass(), or.getPropertyName()});
        MapSqlParameterSource params = new MapSqlParameterSource("id", instanceID);
        params.addValue("evid", ev.getEvaluateVersion().getId());
        params.addValue("position", accessSystem.getCode());
        return defaultDAO.findOnylFirst(jpql, params);
    }

    public void supplementHistory(UpAndDownEvlauateItem item, UpAndDownEvlauateItemHistory itemHistory) {
        itemHistory.setDownEntity(item.getDownEntity());
        itemHistory.setUpEntity(item.getUpEntity());
    }
}
