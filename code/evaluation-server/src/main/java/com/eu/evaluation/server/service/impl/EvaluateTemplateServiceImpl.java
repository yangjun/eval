/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateItemTemplate;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.server.dao.eva.EvaluateItemDAO;
import com.eu.evaluation.server.dao.eva.EvaluateItemTemplateDAO;
import com.eu.evaluation.server.eva.EvaluateItemBuilder;
import com.eu.evaluation.server.eva.EvaluateItemBuilderFactory;
import com.eu.evaluation.server.service.EvaluateTemplateService;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class EvaluateTemplateServiceImpl implements EvaluateTemplateService {

    @Autowired
    private EvaluateItemDAO evaluateItemDAO;

    @Autowired
    private EvaluateItemTemplateDAO evaluateItemTemplateDAO;

    public EvaluateItem createOrReplaceEvaluateItem(EvaluateTypeEnum type, ObjectDictionary od, FieldDictionary fd , Map<String , Object> otherMap) {
        EvaluateItemTemplate template = evaluateItemTemplateDAO.findTheMatching(type, od, fd);//根据评测类型查找评测项目模板
        
        EvaluateItemBuilder builder = EvaluateItemBuilderFactory.getBuilder(template.getEvaluateItemBuilder());//实例化评测项目生成器
        
        EvaluateItem item = builder.findTheMatching(od, fd , otherMap);//查找已存在的评测项目
        
        if (item == null){
            try {
                item = (EvaluateItem) type.getEvaClass().newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
        
        //更新评测项目信息
        item.setEvaluateClass(template.getEvaluateClass());
        item.setFieldDictionary(fd);
        item.setObjectDictionary(od);
        item.setDescribetion(template.getDescribetion());
        
        builder.buildEvaluateItem(item , template , otherMap);//补充评测项目的个性化信息
        
        item = evaluateItemDAO.save(item);
        return item;
    }
}
