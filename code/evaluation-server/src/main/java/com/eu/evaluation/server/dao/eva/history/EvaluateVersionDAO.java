/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.dao.eva.history;

import com.eu.evaluate.utils.StringUtils;
import com.eu.evaluation.model.PageData;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class EvaluateVersionDAO extends AbstractDAO<EvaluateVersion> {

    public List<EvaluateVersion> find(EvaluateVersion ev, Calendar startDate, Calendar endDate) {
        String jpql = "select t from EvaluateVersion t where 1=1";
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (!StringUtils.isBlank(ev.getId())) {
            jpql += " and t.id = :id";
            params.addValue("id", ev.getId());
        }

        if (!StringUtils.isBlank(ev.getName())) {
            jpql += " and t.name like :name";
            params.addValue("name", "'" + ev.getName() + "'");
        }

        if (startDate != null) {
            jpql += " and t.createDate >= :startDate";
            params.addValue("startDate", startDate);
        }

        if (endDate != null) {
            jpql += " and t.createDate <= :endDate";
            params.addValue("endDate", endDate);
        }
        
        return this.query(jpql, params);
    }
    
    public EvaluateVersion findTheLast(){
        String jpql = "select t from EvaluateVersion e order by e.createDate desc";
        PageData<EvaluateVersion> pageData = this.query(jpql, null, 1, 1);
        if (pageData.getRows().isEmpty()){
            return null;
        }else{
            return pageData.getRows().get(0);
        }
    }
}
