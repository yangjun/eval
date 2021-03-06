/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.eva;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateItemTemplate;
import com.eu.evaluation.model.eva.NotNullEvaluateItem;
import com.eu.evaluation.model.eva.UniqueEvaluateItem;
import com.eu.evaluation.server.dao.AbstractDAO;
import com.eu.evaluation.server.eva.EvaluateItemBuilder;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class UniqueEvaluateItemDAO extends AbstractDAO<UniqueEvaluateItem> implements EvaluateItemBuilder<UniqueEvaluateItem>{

    public void buildEvaluateItem(UniqueEvaluateItem evaluateItem , EvaluateItemTemplate itemTemplate , Map<String , Object> otherMap) {
        evaluateItem.setEditable(true);
    }

    public UniqueEvaluateItem findTheMatching(ObjectDictionary od, FieldDictionary fd , Map<String , Object> otherMap) {
        String jpql = "select t from UniqueEvaluateItem t where t.fieldDictionary.id = :fdID ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fdID", fd.getId());
        List<UniqueEvaluateItem> result = this.query(jpql, params);
        if (result.isEmpty()) {
            return null;
        } else if (result.size() == 1) {
            return result.get(0);
        } else {
            throw new RuntimeException("为对象：" + fd.getObjectDictionary().getDisplayname() + " , 属性：" + fd.getDisplayname() + " 定义了多条唯一性评测项目！");
        }
    }
    
}
