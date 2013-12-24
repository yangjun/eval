/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.history.Result;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class ResultDAO extends AbstractDAO<Result>{
    
    /**
     * 根据版本查询未评测的项目
     * @param ev
     * @return 
     */
    public List<Result> findUnFinished(EvaluateVersion ev){
        String jpql = "select t from Result t where t.status = :status and t.evaluateVersion.id = :evID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("status", Result.STATUS_UNEVALUATE);
        params.addValue("evID", ev.getId());
        return this.query(jpql , params);
    }
}
