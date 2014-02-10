/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import java.util.List;
import java.util.Map;
/**
 *
 * @author dell
 */
public interface EvaluateTemplateService {
    
    /**
     * 根据评测类型、数据库字典生成评测项目
     * @param type 评测类型
     * @param objectDictionaryID 评测对象ID
     * @param fieldDictionaryID 评测字段ID
     * @param otherMap 其他信息，如长度有效性评测，评测项目中需要记录最大和最小长度，则将这两个值存入otherMap，长度有效性评测的EvaluateItemBuilder.buildEvaluateItem方法中从otherMap取值，并填入长度有效性评测项目中
     * @return 
     */
    public EvaluateItem createOrReplaceEvaluateItem(EvaluateTypeEnum type , String objectDictionaryID, String fieldDictionaryID , Map<String , Object> otherMap);
    
    /**
     * 根据评测类型、数据库字典生成评测项目
     * @param type 评测类型
     * @param objectDictionaryID 评测对象ID
     * @param fieldDictionaryID 评测字段ID
     * @param otherMap 其他信息，如长度有效性评测，评测项目中需要记录最大和最小长度，则将这两个值存入otherMap，长度有效性评测的EvaluateItemBuilder.buildEvaluateItem方法中从otherMap取值，并填入长度有效性评测项目中
     * @return 
     */
    //public EvaluateItem createOrReplaceEvaluateItem1(EvaluateTypeEnum type , String objectDictionaryID, String fieldDictionaryID , Map<String , Object> otherMap);

    /**
     * 根据评测类型、被评实体类型查询评测指标
     * @param <T>
     * @param evaluateType
     * @param instanceType
     * @return 
     */
    public <T extends EvaluateItem> List<T> findEvaluateItem(EvaluateTypeEnum evaluateType , int instanceType);
}
