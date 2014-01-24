/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.eva;

import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.NameEvaluatedData;
import com.eu.evaluation.model.eva.UniqueEvaluateItem;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.UniqueEvaluateItemHistory;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.service.Evaluating;
import java.text.MessageFormat;
import org.apache.commons.beanutils.BeanUtils;
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
public class UniqueEvaluate implements Evaluating<UniqueEvaluateItem, UniqueEvaluateItemHistory> {

    protected Log logger = LogFactory.getLog(this.getClass());

    private String notPassMessage = "";

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, AccessSystem accessSystem, String instanceClass, int instanceType, String instanceID) throws Exception {
        String debugInfo = "{0} 执行唯一性评测，所属地区局 = {1} , 被评测对象类型 = {2} , 被评测字段 = {3} , 被评测对象实体ID = {4}";
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);//查找评测项目
        logger.debug(MessageFormat.format(debugInfo , new Object[]{"开始" , accessSystem.getName() , ev.getObjectDictionary().getDisplayname() , ev.getFieldDictionary().getDisplayname() , instanceID}));
        
        Object entity = defaultDAO.findEvaluateData(instanceClass, instanceID, ev.getEvaluateVersion().getId(), accessSystem);//获取被评测数据
        if (entity == null) {
            notPassMessage = "{0} 的 {1} 唯一性评测不通过，因为在地区局 {2} 中不存在版本为 {3} , ID为 {4} 的 {5}";
            notPassMessage = MessageFormat.format(notPassMessage, new Object[]{ev.getObjectDictionary().getDisplayname(),
                ev.getFieldDictionary().getDisplayname(),
                accessSystem.getName(),
                ev.getEvaluateVersion().getName(),
                instanceID,
                ev.getObjectDictionary().getDisplayname()});
            return false;
        }
        

        //得到被评字段名及其字段值
        String field = ev.getFieldDictionary().getPropertyName();
        Object fieldValue = BeanUtils.getProperty(entity, field);

        //构造一个sql，统计某个对象，某个字段，特定值的个数
        String jpql = "select count(*) from {0} t where t.{1} = :value and t.evaluateVersion.id = :evID and t.position = :position";
        jpql = MessageFormat.format(jpql, new Object[]{instanceClass, field});

        MapSqlParameterSource params = new MapSqlParameterSource("value", fieldValue);
        params.addValue("evID", ev.getEvaluateVersion().getId());
        params.addValue("position", accessSystem.getCode());

        long count = defaultDAO.executeCount(jpql, params);

        if (count > 1) {//大于1，表示这个字段不唯一
            notPassMessage = getErrorMsg(ev, entity , accessSystem);
        }

        logger.debug(MessageFormat.format(debugInfo , new Object[]{"完成" , accessSystem.getName() , ev.getObjectDictionary().getDisplayname() , ev.getFieldDictionary().getDisplayname() , instanceID}));
        return count <= 1;
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        return notPassMessage;
    }

    public void supplementHistory(UniqueEvaluateItem item, UniqueEvaluateItemHistory itemHistory) {
        //无额外信息
    }

    private String getErrorMsg(EvaluateItemHistory ev, Object entity, AccessSystem accessSystem) {
        notPassMessage = "{0} 的 {1} “{2}” 的 “{3}” 属性，非空评测未通过";
        String name = ((EvaluatedData) entity).getId();
        if (entity instanceof NameEvaluatedData) {
            name = ((NameEvaluatedData) entity).getName();
        }
        return MessageFormat.format(notPassMessage, new Object[]{accessSystem.getName(),
            ev.getObjectDictionary().getDisplayname(),
            name,
            ev.getFieldDictionary().getDisplayname()});
    }
}
