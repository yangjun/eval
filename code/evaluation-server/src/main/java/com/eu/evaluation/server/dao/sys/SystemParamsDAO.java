/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.sys;

import com.eu.evaluation.model.sys.SystemParams;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class SystemParamsDAO extends AbstractDAO<SystemParams>{
    
    public SystemParams findByName(String name){
        String jpql = "select t from SystemParams t where t.name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource("name", name);
        List<SystemParams> result = this.query(jpql , params);
        if (result.isEmpty()){
            return null;
        }else if (result.size() == 1){
            return result.get(0);
        }else{
            throw new RuntimeException("参数名为 " + name + " 的系统参数不唯一！");
        }
    }
    
    
}
