/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class EvaluateItemHistoryDAO extends AbstractDAO<EvaluateItemHistory>{
    
    /**
     * 根据版本查找评测项目
     * @param ev
     * @return 
     */
    public List<EvaluateItemHistory> find(EvaluateVersion ev){
        String jpql = "select t from EvaluateItemHistory t where t.evaluateVersion.id = :evID";
        MapSqlParameterSource params = new MapSqlParameterSource("evID", ev.getId());
        return this.query(jpql , params);
    }
}
