/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva;

import com.eu.evaluation.model.eva.EvaluateItem;
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
public class EvaluateItemDAO extends AbstractDAO<EvaluateItem>{
    
    /**
     * 查找未拷贝到历史记录的评测项目
     * @param ev
     * @return 
     */
    public List<EvaluateItem> findUnCopied(EvaluateVersion ev){
        String jpql = "select t from EvaluateItem t "
                + "where not exists (select 1 from EvaluateItemHistory h where t.id = h.orgEvaluateItemId"
                + " and h.evaluateVersion.id = :evid)";
        MapSqlParameterSource params = new MapSqlParameterSource("evid", ev.getId());
        return this.query(jpql, params);
    }
}
