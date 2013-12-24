/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.UniqueEvaluateItem;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.UniqueEvaluateItemHistory;
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
public class UniqueEvaluate implements Evaluating<UniqueEvaluateItem , UniqueEvaluateItemHistory>{
    
    protected Log logger = LogFactory.getLog(this.getClass());
    
    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;
    
    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        String debugInfo = "开始执行唯一性评测，实体 = {0} , 实体ID = {1} , 评测历史项目ID = {2}";
        logger.debug(MessageFormat.format(debugInfo , new Object[]{instanceClass , instanceID , evaluateItemHistoryID}));
        
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);
        
        Object entity = defaultDAO.findEvaluateData(instanceClass, instanceID , ev.getEvaluateVersion().getId());//获取被评测数据
        
        logger.debug("被评测数据ID ： " + ((EvaluatedData)entity).getId());
        
        String field = ev.getFieldDictionary().getPropertyName();
        Object fieldValue = BeanUtils.getProperty(entity, field);
        
        String jpql = "select count(*) from {0} t where t.{1} = :value and t.evaluateVersion.id = :evID";
        jpql = MessageFormat.format(jpql, new Object[]{instanceClass , field});
        
        MapSqlParameterSource params = new MapSqlParameterSource("value", fieldValue);
        params.addValue("evID", ev.getEvaluateVersion().getId());
        
        long count = defaultDAO.executeCount(jpql, params);
        
        debugInfo = "完成实体唯一性评测，实体 = {0} ，实体ID = {1} , 评测历史项目ID = {2}";
        logger.debug(MessageFormat.format(debugInfo , new Object[]{instanceClass , instanceID , evaluateItemHistoryID}));
        return count == 1;
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);
        
        String reason = "{0}的{1}属性，唯一性评测未通过！";

        reason = MessageFormat.format(reason, new Object[]{ev.getObjectDictionary().getDisplayname(), ev.getFieldDictionary().getDisplayname()});

        return new String(reason.getBytes() , "GBK");
    }

    public void supplementHistory(UniqueEvaluateItem item, UniqueEvaluateItemHistory itemHistory) {
        //无额外信息
    }

}
