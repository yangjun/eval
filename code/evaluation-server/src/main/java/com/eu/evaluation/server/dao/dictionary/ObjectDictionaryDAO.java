/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.dictionary;

import com.eu.evaluation.model.dictionary.ObjectDictionary;
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
public class ObjectDictionaryDAO extends AbstractDAO<ObjectDictionary>{
    
    public List<ObjectDictionary> findAndOrder(){
        String jpql = "select t from ObjectDictionary t where t.valid = :valid order by t.serial";
        MapSqlParameterSource params = new MapSqlParameterSource("valid", true);
        return this.query(jpql , params);
    }
    
    public ObjectDictionary findByInstanceType(int instanceType){
        String jpql = "select t from ObjectDictionary t where t.instanceType = :type";
        MapSqlParameterSource params = new MapSqlParameterSource("type", instanceType);
        try {
            return (ObjectDictionary) this.createQuery(jpql, params).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
    }
    
    /**
     * 作废所有的数据字典
     */
    public void invalidateAll(){
        String jpql = "update ObjectDictionary t set t.valid = :valid";
        MapSqlParameterSource params = new MapSqlParameterSource("valid", false);
        this.createQuery(jpql, params).executeUpdate();
    }
    
    public ObjectDictionary findByDisplayName(String displayName){
        String jpql = "select t from ObjectDictionary t where t.displayname = :displayname and t.valid = :valid";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("displayname", displayName);
        params.addValue("valid", true);
        List<ObjectDictionary> result = this.query(jpql , params);
        if (result.isEmpty()){
            return null;
        }else if (result.size() == 1){
            return result.get(0);
        }else{
            throw new RuntimeException("名称为 " + displayName + " ，且valid属性为1的数据库对象应该只有一个，但实际存在多个");
        }
    }
}
