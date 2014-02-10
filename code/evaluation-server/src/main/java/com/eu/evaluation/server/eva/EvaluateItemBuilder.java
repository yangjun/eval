/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateItemTemplate;
import java.util.Map;

/**
 * 评测项目生成器
 * @author dell
 */
public interface EvaluateItemBuilder<T extends EvaluateItem>{

    /**
     * 根据模板创建评测项目。
     * 
     * @param itemTemplate
     * @param od
     * @param fd
     * @return 
     */
    public void buildEvaluateItem(T evaluateItem , EvaluateItemTemplate itemTemplate , Map<String , Object> otherMap);
    
    /**
     * 根据数据字典，查找评测项目
     * @param od
     * @param fd
     * @return 
     */
    public EvaluateItem findTheMatching(ObjectDictionary od, FieldDictionary fd , Map<String , Object> otherMap);
    
    
}
