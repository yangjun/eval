/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.sys;

import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class AccessSystemDAO extends AbstractDAO<AccessSystem>{
    public AccessSystem findByCode(String code){
        String jpql = "select t from AccessSystem t where t.code = :code";
        MapSqlParameterSource params = new MapSqlParameterSource("code", code);
        try {
            return (AccessSystem) this.createQuery(jpql, params).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<AccessSystem> findByEvaluateVersion(String evid){
        String jpql = "select t.accessSystem from EvaluateSystem t where t.evaluateVersion.id = :evid";
        MapSqlParameterSource params = new MapSqlParameterSource("evid", evid);
        return this.query(jpql, params);
    }
    
    public List<AccessSystem> findVaildAccessSystem(){
        String jpql = "select t from AccessSystem t where t.vaild = :vaild";
        MapSqlParameterSource params = new MapSqlParameterSource("vaild", true);
        return this.query(jpql, params);
    }
}
