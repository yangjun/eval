/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.PageData;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.Result;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.model.eva.result.SimpleStatisticsTemp;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class ResultDAO extends AbstractDAO<Result> {

    /**
     * 根据版本查询未评测的项目
     *
     * @param ev
     * @return
     */
    public List<Result> findUnFinished(EvaluateVersion ev , AccessSystem system) {
        String jpql = "select t from Result t where t.status = :status and t.evaluateVersion.id = :evID and t.position = :position";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("status", Result.STATUS_UNEVALUATE);
        params.addValue("evID", ev.getId());
        params.addValue("position", system.getCode());
        return this.query(jpql, params);
    }
    
    public PageData<Result> findUnPassed(EvaluateVersion ev , AccessSystem system , int pageNo , int pageSize){
        String jpql = "select r from Result r "
                + "where r.evaluateVersion.id = :evid "
                + "and r.position = :position "
                + "and r.status =:status "
                + "order by r.instanceType , r.instanceId , r.itemHistory.evaluateTypeEnum";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", ev.getId());
        params.addValue("position", system.getCode());
        params.addValue("status", Result.STATUS_FAILURE);
        return this.query(jpql, params, pageNo, pageSize);
    }
    
    /**
     * 根据版本、地区局查询被评测的资源类型，按照资源类型、评测类型分组统计到SimpleStatistics
     * @param ev
     * @param system
     * @return 
     */
    public List<SimpleStatistics> groupToSimpleStatistics(EvaluateVersion ev , AccessSystem system){
        String jpql = "select new SimpleStatistics(t.instanceType , t.instanceClass , h.evaluateTypeEnum)"
                + " from Result t , EvaluateItemHistory h "
                + "where t.itemHistory.id = h.id and t.evaluateVersion.id = :evid and t.position = :position "
                + "group by t.instanceType , t.instanceClass , h.evaluateTypeEnum";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", ev.getId());
        params.addValue("position", system.getCode());
        return this.createQuery(jpql, params).getResultList();
    }
    
    
}
