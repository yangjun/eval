/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
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
public class SimpleStatisticsDAO extends AbstractDAO<SimpleStatistics>{
    
   
    
    /**
     * 根据版本、地区局删除评测统计结果
     * @param ev
     * @param system
     * @return 
     */
    public int delete(EvaluateVersion ev , AccessSystem system){
        String jpql = "delete from SimpleStatistics t where t.evaluateVersion.id = :evid and t.position = :position";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", ev.getId());
        params.addValue("position", system.getCode());
        return this.createQuery(jpql, params).executeUpdate();
    }
    
    /**
     * 根据版本、所属地区局、资源类型查询统计结果
     * @param evaulateVersionID
     * @param position
     * @param entityEnum
     * @return 
     */
    public List<SimpleStatistics> find(String evaulateVersionID , String position , EntityEnum entityEnum){
        String jpql = "select t from SimpleStatistics t where t.evaluateVersion.id = :evid and t.position = :position and t.instanceType = :instanceType";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", evaulateVersionID);
        params.addValue("position", position);
        params.addValue("instanceType", entityEnum.getInstanceType());
        return this.query(jpql, params);
    }
    
    /**
     * 根据版本、所属地区局、评测类型查询统计结果
     * @param evaulateVersionID
     * @param position
     * @param evaluateTypeEnum
     * @return 
     */
    public List<SimpleStatistics> find(String evaulateVersionID , String position , EvaluateTypeEnum evaluateTypeEnum){
        String jpql = "select t from SimpleStatistics t where t.evaluateVersion.id = :evid and t.position = :position and t.evaluateTypeEnum = :evaluateTypeEnum";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", evaulateVersionID);
        params.addValue("position", position);
        params.addValue("evaluateTypeEnum", evaluateTypeEnum);
        return this.query(jpql, params);
    }
}
