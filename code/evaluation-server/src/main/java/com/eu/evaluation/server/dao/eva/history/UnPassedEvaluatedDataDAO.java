/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.model.eva.result.UnPassedEvaluatedData;
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
public class UnPassedEvaluatedDataDAO extends AbstractDAO<UnPassedEvaluatedData>{
    
    /**
     * 根据版本和地区局删除数据
     * @param ev
     * @param system
     * @return 
     */
    public int deleteByVersionAndPosition(EvaluateVersion ev , AccessSystem system){
        String jpql = "delete from UnPassedEvaluatedData t where t.evaluateVersion.id = :evid and t.position = :position";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", ev.getId());
        params.addValue("position", system.getCode());
        return this.createQuery(jpql, params).executeUpdate();
    }
    
    
    /**
     * 根据版本和地区局统计数据到SimpleStatistics
     * @param ev
     * @param system
     * @return 
     */
    public List<SimpleStatistics> countToSimpleStatistics(EvaluateVersion ev , AccessSystem system){
        String jpql = "select new SimpleStatistics(t.evaluateTypeEnum , t.instanceType , t.instanceClass , count(t.instanceType)) "
                + "from UnPassedEvaluatedData t "
                + "where t.evaluateVersion.id = :evid and t.position = :position "
                + "group by t.evaluateTypeEnum , t.instanceType , t.instanceClass";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evid", ev.getId());
        params.addValue("position", system.getCode());
        return this.createQuery(jpql, params).getResultList();
    }
}
