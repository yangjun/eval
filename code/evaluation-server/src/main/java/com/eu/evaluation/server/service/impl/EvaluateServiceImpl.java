/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.history.Result;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.EvaluateItemDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateVersionDAO;
import com.eu.evaluation.server.dao.eva.history.ResultDAO;
import com.eu.evaluation.server.eva.EvaluatingFactory;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.Evaluating;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateItemDAO evaluateItemDAO;

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    @Autowired
    private ResultDAO resultDAO;

    @Autowired
    private EvaluateVersionDAO evaluateVersionDAO;

    public void copyEvaluateItemToHistory(EvaluateVersion ev) throws InstantiationException , IllegalAccessException{
        List<EvaluateItem> items = evaluateItemDAO.findUnCopied(ev);
        List<EvaluateItemHistory> itemHistorys = new ArrayList<EvaluateItemHistory>();

        for (EvaluateItem item : items) {
            //实例化评测历史项目
            Class evaHistoryClass = EvaluateTypeEnum.getEvaHistoryClass(item.getClass());
            EvaluateItemHistory itemHistory = (EvaluateItemHistory) evaHistoryClass.newInstance();
            
            //复制评测项目信息到评测历史项目中
            itemHistory.setDescribetion(item.getDescribetion());
            itemHistory.setEvaluateClass(item.getEvaluateClass());
            itemHistory.setEvaluateSQL(item.getEvaluateSQL());
            itemHistory.setFieldDictionary(item.getFieldDictionary());
            itemHistory.setObjectDictionary(item.getObjectDictionary());
            itemHistory.setEvaluateVersion(ev);
            itemHistory.setOrgEvaluateItemId(item.getId());
            
            //实例化评测逻辑类，复制额外的信息到历史项目中
            Evaluating evaluating = EvaluatingFactory.getEvaluating(item.getEvaluateClass());
            evaluating.supplementHistory(item, itemHistory);
            
            itemHistorys.add(itemHistory);
        }
        evaluateItemHistoryDAO.save(itemHistorys);
    }

    public List<Result> findUnFinishedEvaluate(EvaluateVersion ev) {
        return resultDAO.findUnFinished(ev);
    }

    public <T> List<T> findEvaluatedEntity(EvaluateItemHistory itemHistory) {
        itemHistory = evaluateItemHistoryDAO.get(itemHistory.getId());

        String jpql = "select t from {0} t where t.evaluateVersion.id = :evid and not exists (select 1 from Result r where r.itemHistory.id = :hid and r.evaluateVersion.id = :evid and r.instanceId = t.id)";

        jpql = MessageFormat.format(jpql, new Object[]{itemHistory.getObjectDictionary().getInstanceClass()});
        MapSqlParameterSource params = new MapSqlParameterSource("evid", itemHistory.getEvaluateVersion().getId());
        params.addValue("hid", itemHistory.getId());

        return defaultDAO.find(jpql, params);
    }

    public Result saveResult(Result result) {
        return resultDAO.save(result);
    }

    /**
     * 根据版本查找评测项目
     *
     * @param ev
     * @return
     */
    public List<EvaluateItemHistory> findEvaluateItemHistoryByVersion(EvaluateVersion ev) {
        return evaluateItemHistoryDAO.find(ev);
    }

    public EvaluateVersion findEvaluateVersionByID(String id) {
        return evaluateVersionDAO.get(id);
    }

    public EvaluateVersion saveEvaluateVersion(EvaluateVersion ev) {
        return evaluateVersionDAO.save(ev);
    }
}
