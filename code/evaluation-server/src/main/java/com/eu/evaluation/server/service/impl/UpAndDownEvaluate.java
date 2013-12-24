/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.NameEvaluatedData;
import com.eu.evaluation.model.dictionary.ObjectRelation;
import com.eu.evaluation.model.eva.UpAndDownEvlauateItem;
import com.eu.evaluation.model.eva.history.UpAndDownEvlauateItemHistory;
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
public class UpAndDownEvaluate implements Evaluating<UpAndDownEvlauateItem , UpAndDownEvlauateItemHistory> {

    protected Log logger = LogFactory.getLog(this.getClass());
    
    private String notPassMessage =  "";

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;

    @Autowired
    private ObjectRelationDAO objectRelationDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        String debugInfo = "开始执行上下级关系评测，实体 = {0} , 实体ID = {1} , 评测历史项目ID = {2}";
        logger.debug(MessageFormat.format(debugInfo, new Object[]{instanceClass, instanceID, evaluateItemHistoryID}));

        UpAndDownEvlauateItemHistory ev = (UpAndDownEvlauateItemHistory) evaluateItemHistoryDAO.get(evaluateItemHistoryID);

        Long upCount = 0L;
        if (ev.getUpEntity() != null) {//查找当前对象是否具有指定的父级对象
            upCount = countUp(ev, instanceID);
        } 
        
        Long downCount = 0L;
        if (downCount ==0 && ev.getDownEntity() != null) {//查找当前资源是否具有指定的子集对象
            downCount = countDown(ev, instanceID);
        }
        
        notPassMessage = getErrorMsg(ev, instanceClass, instanceType, instanceID, upCount, downCount);

        debugInfo = "完成实体唯一性评测，实体 = {0} ，实体ID = {1} , 评测历史项目ID = {2}";
        logger.debug(MessageFormat.format(debugInfo, new Object[]{instanceClass, instanceID, evaluateItemHistoryID}));
        return (upCount == 1 && downCount > 0);
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        return notPassMessage;
    }
    
    private String getErrorMsg(UpAndDownEvlauateItemHistory ev , String instanceClass, int instanceType, String instanceID , Long upCount , Long downCount){
        if (upCount != 1 || downCount <=0){
            Object entity = defaultDAO.findEvaluateData(instanceClass, instanceID , ev.getEvaluateVersion().getId());//获取被评测数据
            String name = ((EvaluatedData)entity).getId();
            if (entity instanceof NameEvaluatedData){
                name = ((NameEvaluatedData)entity).getName();
            }
            
            if (upCount != 1 && downCount <=0){
                String message = "{0} {1} 的上下级关系评测未通过。原因：不属于任何{3}；不包含任何{4}！";
                return MessageFormat.format(message , new Object[]{ev.getObjectDictionary().getDisplayname() , name , ev.getUpEntity().getDisplayname() , ev.getDownEntity().getDisplayname()});
            }else if (upCount != 1){
                String message = "{0} {1} 的上下级关系评测未通过。原因：不属于任何{3}！";
                return MessageFormat.format(message , new Object[]{ev.getObjectDictionary().getDisplayname() , name , ev.getUpEntity().getDisplayname()});
            }else{
                String message = "{0} {1} 的上下级关系评测未通过。原因：不包含任何{4}！";
                return MessageFormat.format(message , new Object[]{ev.getObjectDictionary().getDisplayname() , name , ev.getDownEntity().getDisplayname()});
            }
        }else{
            return "";
        }
    }

    /**
     * 查找当前对象是否具有指定的父级对象
     * @param ev
     * @param instanceID 当前资源ID
     * @return 
     */
    private Long countUp(UpAndDownEvlauateItemHistory ev, String instanceID) {
        //查找对象父子关系，注意得到的结果，or.self = 当前被评对象，or.parent = 父级对象
        ObjectRelation or = objectRelationDAO.findByParent(ev.getObjectDictionary().getId(), ev.getUpEntity().getId());

        if (or == null) {
            throw new RuntimeException("上下级关系评测时产生错误！原因：对象关系配置错误，本体 = " + ev.getObjectDictionary().getDisplayname() + " ; 父级 = " + ev.getUpEntity().getDisplayname() + "的对象关系不存在");
        }

        //构建sql，查询父级是否存在
        String jpql = "select count(*) from {0} t where t.id = :id and exists (select 1 from {1} p where p.id = t.{2}.id)";
        jpql = MessageFormat.format(jpql, new Object[]{or.getSelf().getInstanceClass(), or.getParent().getInstanceClass(), or.getRelationField().getPropertyName()});
        MapSqlParameterSource params = new MapSqlParameterSource("id", instanceID);

        logger.debug("评测父级sql" + jpql + "\n; 参数： = " + instanceID);
        return defaultDAO.findOnylFirst(jpql, params);
    }

    /**
     * 查找当前资源是否具有指定的子集对象
     * @param ev
     * @param instanceID 当前资源ID
     * @return 
     */
    private Long countDown(UpAndDownEvlauateItemHistory ev, String instanceID) {
        //查找对象父子关系，注意得到的结果，or.self = 下级对象，or.parent = 当前被评对象
        ObjectRelation or = objectRelationDAO.findByParent(ev.getDownEntity().getId(), ev.getObjectDictionary().getId());
        if (or == null) {
            throw new RuntimeException("上下级关系评测时产生错误！原因：对象关系配置错误，本体 = " + ev.getDownEntity().getDisplayname() + " ; 父级 = " + ev.getObjectDictionary().getDisplayname() + "的对象关系不存在");
        }

        //构建sql，查询父级是否存在
        String jpql = "select count(*) from {0} t where exists (select 1 from {1} p where p.id = t.{2}.id and p.id = :id)";
        jpql = MessageFormat.format(jpql, new Object[]{or.getSelf().getInstanceClass(), or.getParent().getInstanceClass(), or.getRelationField().getPropertyName()});
        MapSqlParameterSource params = new MapSqlParameterSource("id", instanceID);
        return defaultDAO.findOnylFirst(jpql, params);
    }

    public void supplementHistory(UpAndDownEvlauateItem item, UpAndDownEvlauateItemHistory itemHistory) {
        itemHistory.setDownEntity(item.getDownEntity());
        itemHistory.setUpEntity(item.getUpEntity());
    }
}
