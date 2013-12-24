/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.dictionary;

import com.eu.evaluation.model.dictionary.ObjectRelation;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class ObjectRelationDAO extends AbstractDAO<ObjectRelation>{
    
    /**
     * 查询实体之间的关系，父关系
     * @param selfClassID
     * @param parentClassID
     * @return 
     */
    public ObjectRelation findByParent(String selfClassID , String parentClassID){
        String jpql = "select t from ObjectRelation t where t.self.id = :selfID and t.parent.id = :parentID";
        MapSqlParameterSource params = new MapSqlParameterSource("selfID" , selfClassID);
        params.addValue("parentID", parentClassID);
        List<ObjectRelation> result = this.query(jpql , params);
        if (result.isEmpty()){
            return null;
        }else if (result.size() == 1){
            return result.get(0);
        }else{
            throw new RuntimeException("对象关系配置错误！本体ID = " + selfClassID + " ; 父级ID = " + parentClassID + "的对象关系只应该有一条，但数据库中存在多条！");
        }
    }
    
    /**
     * 查询实体之间的关系，子关系
     * @param selfClassID
     * @return 
     */
    public List<ObjectRelation> findChild(String selfClassID){
        String jpql = "select t from ObjectRelation t where t.parent.id = :slefID";
        MapSqlParameterSource params = new MapSqlParameterSource("slefID", selfClassID);
        return this.query(jpql, params);
    }
}
