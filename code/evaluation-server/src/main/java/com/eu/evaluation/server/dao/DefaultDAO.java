/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.sys.AccessSystem;
import java.text.MessageFormat;
import java.util.List;
import javax.persistence.Query;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class DefaultDAO extends AbstractDAO{
    /**
     * 执行JPQL语句
     * @param <T>
     * @param jpql
     * @param params
     * @return 
     */
    public <T> List<T> find(String jpql , MapSqlParameterSource params){
        return this.query(jpql , params);
    }
    
    /**
     * 查找评测数据
     * @param <T>
     * @param className 评测数据类名
     * @param id 评测数据ID
     * @param evaluateVersionID 评测数据版本
     * @return 
     */
    public <T> T findEvaluateData(String className , String id , String evaluateVersionID , AccessSystem accessSystem){
        String jpql = "select t from {0} t where t.id = :id and t.evaluateVersion.id = :evID and t.position = :position";
        jpql = MessageFormat.format(jpql, new Object[]{className});
        MapSqlParameterSource params = new MapSqlParameterSource("id" , id);
        params.addValue("evID", evaluateVersionID);
        params.addValue("position", accessSystem.getCode());
        List<T> result = find(jpql, params);
        if (result.isEmpty()){
            String msg = "未找到被评数据，被评数据类型：{0} ， 被评数据ID {1} ， 被评数据版本 {2} , 被评测数据所属地区局 {3}";
            throw new RuntimeException(MessageFormat.format(msg, new Object[]{className , id , evaluateVersionID , accessSystem.getName()}));
        }else if (result.size() == 1){
            return result.get(0);
        }else{
            String msg = "被评数据有重复，被评数据类型：{0} ， 被评数据ID {1} ， 被评数据版本 {2} , 被评测数据所属地区局 {3}";
            throw new RuntimeException(MessageFormat.format(msg, new Object[]{className , id , evaluateVersionID , accessSystem.getName()}));
        }
    }
    
    /**
     * 执行原生的Insert语句
     * @param sql
     * @param params 
     */
    public void executeNativeInsert(String sql , MapSqlParameterSource params){
        this.updateNative(sql, params);
    }
    
    /**
     * 执行count语句
     * @param jpql
     * @param params
     * @return 
     */
    public Long executeCount(String jpql , MapSqlParameterSource params){
//        List rr = this.query(jpql , params);
//        return Long.valueOf(rr.size());
        
        Long count = (Long) this.createQuery(jpql, params).getSingleResult();
        return count;
    }
    
    /**
     * 只返回第一个数据
     * @param jpql
     * @param params
     * @return 
     */
    public <T> T findOnylFirst(String jpql , MapSqlParameterSource params){
        Query query = this.createQuery(jpql, params);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<T> result = query.getResultList();
        if (result.isEmpty()){
            return null;
        }else{
            return result.get(0);
        }
    }
    
    /**
     * 按所属地区局删除指定的数据。被删除的数据对象必须包含position字段
     * @param entityEnum
     * @param position
     * @return 
     */
    public int deleteData(Class<?> orgDataClass , String position){
        String jpql = "delete from {0} t where t.position = :position";
        jpql = MessageFormat.format(jpql, orgDataClass.getName());
        MapSqlParameterSource params = new MapSqlParameterSource("position", position);
        return this.createQuery(jpql, params).executeUpdate();
    }
}
