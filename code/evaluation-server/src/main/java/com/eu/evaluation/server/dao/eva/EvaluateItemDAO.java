/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.text.MessageFormat;
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
    
    /**
     * 根据评测类型、被评实体类型查询评测指标
     * @param <T>
     * @param evaluateTypeEnum
     * @param entityEnum
     * @return 
     */
    public <T extends EvaluateItem> List<T> find(EvaluateTypeEnum evaluateTypeEnum , EntityEnum entityEnum){
        String jpql = "select t from {0} t where t.objectDictionary.instanceType = :instanceType";
        jpql = MessageFormat.format(jpql, evaluateTypeEnum.getEvaClass().getName());
        MapSqlParameterSource params = new MapSqlParameterSource("instanceType", entityEnum.getInstanceType());
        return this.createQuery(jpql, params).getResultList();
    }
}
