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
import com.eu.evaluation.model.eva.UniqueEvaluateItem;
import com.eu.evaluation.model.eva.UpAndDownEvlauateItem;
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
public class UpAndDownEvaluateItemDAO extends AbstractDAO<UpAndDownEvlauateItem> implements EvaluateItemBuilder<UpAndDownEvlauateItem>{

    public void buildEvaluateItem(UpAndDownEvlauateItem evaluateItem, EvaluateItemTemplate itemTemplate , Map<String, Object> otherMap) {
        evaluateItem.setEditable(true);
        
        evaluateItem.setUpEntity(getObjectDictionary((String) otherMap.get(UpAndDownEvlauateItem.MAP_KEY_UP_ENTITY)));
        evaluateItem.setDownEntity(getObjectDictionary((String) otherMap.get(UpAndDownEvlauateItem.MAP_KEY_DOWN_ENTITY)));
    }

    public EvaluateItem findTheMatching(ObjectDictionary od, FieldDictionary fd , Map<String , Object> otherMap) {
        String jpql = "select t from UpAndDownEvlauateItem t where t.objectDictionary.id = :odID";
        MapSqlParameterSource params = new MapSqlParameterSource("odID", od.getId());
        
        String upEntityID = (String)otherMap.get(UpAndDownEvlauateItem.MAP_KEY_UP_ENTITY);
        if (upEntityID != null){
            jpql += " and t.upEntity.id = :upID";
            params.addValue("upID", upEntityID);
        }
        
        String downEntityID = (String)otherMap.get(UpAndDownEvlauateItem.MAP_KEY_DOWN_ENTITY);
        if (downEntityID != null){
            jpql += " and t.downEntity.id = :downID";
            params.addValue("downID", downEntityID);
        }
        
        List<UpAndDownEvlauateItem> result = this.query(jpql, params);
        if (result.isEmpty()) {
            return null;
        } else if (result.size() == 1) {
            return result.get(0);
        } else {
            ObjectDictionary upEntity = getObjectDictionary(upEntityID);
            ObjectDictionary downEntity = getObjectDictionary(downEntityID);
            throw new RuntimeException("为对象：" + fd.getObjectDictionary().getDisplayname() + " 定义了多条上下级关系评测。上级=" + (upEntity == null ? "null" : upEntity.getDisplayname()) + " ; 下级=" + (downEntity == null ? "null" : downEntity.getDisplayname()));
        }
    }
    
    private ObjectDictionary getObjectDictionary(String id){
        try{
            return this.get(id, ObjectDictionary.class);
        }catch(IllegalArgumentException e){
            return null;
        }
    }
    
}
