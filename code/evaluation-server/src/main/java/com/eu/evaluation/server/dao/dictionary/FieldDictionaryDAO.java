/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.dictionary;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class FieldDictionaryDAO extends AbstractDAO<FieldDictionary>{
    
    public List<String> findByObject(String objectID){
        String jpql = "select t.fieldName from FieldDictionary t where t.objectDictionary.id = :oid";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("oid", objectID);
        return this.createQuery(jpql, params).getResultList();
    }
}
