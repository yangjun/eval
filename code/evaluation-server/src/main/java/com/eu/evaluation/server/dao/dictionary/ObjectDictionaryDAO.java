/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.dictionary;

import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class ObjectDictionaryDAO extends AbstractDAO<ObjectDictionary>{
    
    public List<ObjectDictionary> findAndOrder(){
        String jpql = "select t from ObjectDictionary t order by t.serial";
        return this.query(jpql);
    }
}
