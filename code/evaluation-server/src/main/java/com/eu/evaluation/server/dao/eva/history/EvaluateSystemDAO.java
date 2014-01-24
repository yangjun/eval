/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.eva.history.EvaluateSystem;
import com.eu.evaluation.server.dao.AbstractDAO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class EvaluateSystemDAO extends AbstractDAO<EvaluateSystem>{
    
    
    public int removeByEvaluateVersion(String evid){
        String jpql = "delete from EvaluateSystem t where t.evaluateVersion.id = :evid";
        MapSqlParameterSource params = new MapSqlParameterSource("evid", evid);
        return this.createQuery(jpql, params).executeUpdate();
    }
}
