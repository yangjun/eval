/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.ResultCollatorConfig;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateSystem;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.Result;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.EvaluateItemDAO;
import com.eu.evaluation.server.dao.eva.ResultCollatorConfigDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateSystemDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateVersionDAO;
import com.eu.evaluation.server.dao.eva.history.ResultDAO;
import com.eu.evaluation.server.eva.EvaluatingFactory;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.Evaluating;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    @Autowired
    private ResultCollatorConfigDAO resultCollatorConfigDAO;

    @Autowired
    private EvaluateSystemDAO evaluateSystemDAO;

    public void copyEvaluateItemToHistory(EvaluateVersion ev) throws InstantiationException, IllegalAccessException {
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
            itemHistory.setEvaluateTypeEnum(item.getEvaluateTypeEnum());

            //实例化评测逻辑类，复制额外的信息到历史项目中
            Evaluating evaluating = EvaluatingFactory.getEvaluating(item.getEvaluateClass());
            evaluating.supplementHistory(item, itemHistory);

            itemHistorys.add(itemHistory);
        }
        evaluateItemHistoryDAO.save(itemHistorys);
    }

    public List<Result> findUnFinishedEvaluate(EvaluateVersion ev, AccessSystem system) {
        return resultDAO.findUnFinished(ev, system);
    }

    public <T> List<T> findEvaluatedEntity(EvaluateItemHistory itemHistory, AccessSystem system) {
        itemHistory = evaluateItemHistoryDAO.get(itemHistory.getId());

        String jpql = "select t from {0} t "
                + "where t.evaluateVersion.id = :evid "
                + "and t.position = :position "
                + "and not exists "
                + " (select 1 from Result r "
                + " where r.itemHistory.id = :hid "
                + " and r.evaluateVersion.id = :evid "
                + " and r.instanceId = t.id"
                + " and r.position = :position)";

        jpql = MessageFormat.format(jpql, new Object[]{itemHistory.getObjectDictionary().getInstanceClass()});
        MapSqlParameterSource params = new MapSqlParameterSource("evid", itemHistory.getEvaluateVersion().getId());
        params.addValue("hid", itemHistory.getId());
        params.addValue("position", system.getCode());

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

    public List<ResultCollatorConfig> findAllResultCollatorConfig() {
        return resultCollatorConfigDAO.findAll();
    }

    public void saveEvaluateSystem(String evid, List<AccessSystem> accessSystems) {
        evaluateSystemDAO.removeByEvaluateVersion(evid);
        EvaluateVersion version = evaluateVersionDAO.get(evid);
        List<EvaluateSystem> list = new ArrayList<EvaluateSystem>();
        for (AccessSystem system : accessSystems) {
            EvaluateSystem es = new EvaluateSystem();
            es.setAccessSystem(system);
            es.setEvaluateVersion(version);
            list.add(es);
        }
        evaluateSystemDAO.save(list);
    }

    public List<EvaluateVersion> findEvaluateVersion(EvaluateVersion ev , Calendar startDate , Calendar endDate) {
        return evaluateVersionDAO.find(ev , startDate , endDate);
    }

    public EvaluateVersion findLastEvaluateVersion() {
        return evaluateVersionDAO.findTheLast();
    }
}
