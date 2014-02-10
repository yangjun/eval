/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva;

import com.eu.evaluation.model.eva.ResultCollatorConfig;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class ResultCollatorConfigDAO extends AbstractDAO<ResultCollatorConfig>{
    @Override
    public List<ResultCollatorConfig> findAll(){
        String jpql = "select t from ResultCollatorConfig t order by t.serial";
        return this.query(jpql);
    }
}
