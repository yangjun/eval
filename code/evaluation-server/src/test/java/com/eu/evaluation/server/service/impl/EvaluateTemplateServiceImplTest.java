/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.UpAndDownEvlauateItem;
import com.eu.evaluation.server.service.DictionaryService;
import com.eu.evaluation.server.service.EvaluateTemplateService;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author dell
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application.xml", "classpath:databaseContext-jpa-all.xml" , "classpath:spring-mvc.xml"})
public class EvaluateTemplateServiceImplTest /*extends AbstractJUnit4SpringContextTests*/ {

    @Autowired
    private EvaluateTemplateService templateService;

    @Autowired
    private DictionaryService dictionaryService;

    public EvaluateTemplateServiceImplTest() {
    }

    @Test
    public void test(){
        
    }
    /**
     * Test of createOrReplaceEvaluateItem method, of class
     * EvaluateTemplateServiceImpl.
     */
//    @Test
    public void testCreateOrReplaceEvaluateItem() {
        //区域【编码】字段非空评测
        EvaluateTypeEnum type = EvaluateTypeEnum.NOT_NULL;
        ObjectDictionary od = dictionaryService.findObjectDictionaryByName("区域");
        FieldDictionary fd = dictionaryService.findFielddDictionaryByName("区域" , "编码");
        Map<String , Object> otherMap = new HashMap<String, Object>();
        EvaluateItem result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        
        //站点【名称】字段非空评测
        type = EvaluateTypeEnum.NOT_NULL;
        od = dictionaryService.findObjectDictionaryByName("站点");
        fd = dictionaryService.findFielddDictionaryByName("站点" , "名称");
        otherMap = new HashMap<String, Object>();
        result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        
        //站点【调管单位】字段非空评测
        type = EvaluateTypeEnum.NOT_NULL;
        od = dictionaryService.findObjectDictionaryByName("站点");
        fd = dictionaryService.findFielddDictionaryByName("站点" , "调管单位");
        otherMap = new HashMap<String, Object>();
        result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        
        //站点【编码】字段唯一性评测
        type = EvaluateTypeEnum.UNIQUE;
        od = dictionaryService.findObjectDictionaryByName("站点");
        fd = dictionaryService.findFielddDictionaryByName("站点" , "编码");
        otherMap = new HashMap<String, Object>();
        result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        
        //站点【名称】字段唯一性评测
        type = EvaluateTypeEnum.UNIQUE;
        od = dictionaryService.findObjectDictionaryByName("站点");
        fd = dictionaryService.findFielddDictionaryByName("站点" , "名称");
        otherMap = new HashMap<String, Object>();
        result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        
        //站点上下级关系评测
        type = EvaluateTypeEnum.UP_AND_DOWN;
        od = dictionaryService.findObjectDictionaryByName("站点");
        fd = null;
        ObjectDictionary upOd = dictionaryService.findObjectDictionaryByName("区域");
        ObjectDictionary downOd = dictionaryService.findObjectDictionaryByName("机房");
        otherMap = new HashMap<String, Object>();
        otherMap.put(UpAndDownEvlauateItem.MAP_KEY_UP_ENTITY, upOd);
        otherMap.put(UpAndDownEvlauateItem.MAP_KEY_DOWN_ENTITY, downOd);
        result = templateService.createOrReplaceEvaluateItem(type, od.getId(), fd.getId() , otherMap);
        assertNotNull(result.getId());
    }

}
