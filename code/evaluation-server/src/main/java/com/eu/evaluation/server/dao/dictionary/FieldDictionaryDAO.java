/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.dao.dictionary;

import com.eu.evaluation.model.dictionary.FieldDictionary;
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
public class FieldDictionaryDAO extends AbstractDAO<FieldDictionary> {

    public List<String> findByObject(String objectID) {
        String jpql = "select t.fieldName from FieldDictionary t where t.objectDictionary.id = :oid and t.valid =:valid";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("oid", objectID);
        params.addValue("valid", true);
        return this.createQuery(jpql, params).getResultList();
    }

    /**
     * 作废所有的数据字典
     */
    public void invalidateAll() {
        String jpql = "update FieldDictionary t set t.valid = :valid";
        MapSqlParameterSource params = new MapSqlParameterSource("valid", false);
        this.createQuery(jpql, params).executeUpdate();
    }

    public FieldDictionary findByObjectAndProperty(String objectID, String property) {
        String jpql = "select t from FieldDictionary t where t.objectDictionary.id = :oid and t.propertyName = :property";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("oid", objectID);
        params.addValue("property", property);
        try {
            return (FieldDictionary) this.createQuery(jpql, params).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public FieldDictionary findByObjectNameAndProperty(String objectDisplayname, String propertyDisplayname) {
        String jpql = "select t from FieldDictionary t where t.objectDictionary.displayname = :displayname and t.displayname = :property and t.valid = :valid";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("displayname", objectDisplayname);
        params.addValue("property", propertyDisplayname);
        params.addValue("valid", true);
        List<FieldDictionary> result = this.query(jpql, params);
        if (result.isEmpty()){
            return null;
        }else if (result.size() == 1){
            return result.get(0);
        }else{
            throw new RuntimeException("数据库字典定义错误，对象 " + objectDisplayname + " 的定义的有效属性 " + propertyDisplayname + " 不唯一");
        }
    }
    
    public List<FieldDictionary> findByInstanceType(int instanceType){
        String jpql = "select t from FieldDictionary t where t.objectDictionary.instanceType = :instanceType";
        MapSqlParameterSource params = new MapSqlParameterSource("instanceType", instanceType);
        return this.query(jpql, params);
    }
}
