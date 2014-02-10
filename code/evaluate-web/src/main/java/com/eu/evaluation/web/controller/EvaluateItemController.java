/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.web.controller;

import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.server.service.DictionaryService;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.EvaluateTemplateService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ����ָ��Controller
 * @author dell
 */
@Controller
@RequestMapping(value="/rest/evaluateItem")
public class EvaluateItemController {
    
    protected Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    public EvaluateService evaluateService;
    
    @Autowired
    public EvaluateTemplateService evaluateTemplateService;
    
    @Autowired
    public DictionaryService dictionaryService;
    
    /**
     * �����������͡�����ʵ�����Ͳ�ѯ����ָ��
     * @param evaluateType
     * @param instanceType
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/evaluateItem/{evaluateType}/{instanceType}" , method = RequestMethod.GET)
    public <T extends EvaluateItem> List<T> findEvaluateItem(@PathVariable("evaluateType")EvaluateTypeEnum evaluateType , 
            @PathVariable("instanceType")int instanceType){
        
        return evaluateTemplateService.findEvaluateItem(evaluateType, instanceType);
    }
    
    /**
     * ��������ָ��
     * @param evaluateType ��������
     * @param objectDictionaryID ����ʵ�����ݿ��ֵ�ID
     * @param fieldDictionaryID �����ֶ����ݿ��ֵ�ID
     * @param request ��¼����ָ���������Ϣ
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/evaluateItem" , method = RequestMethod.POST , consumes = {"application/json"})
    public EvaluateItem saveEvaluateItem(@RequestParam("evaluateType") EvaluateTypeEnum evaluateType ,
            @RequestParam("objectDictionaryID") String objectDictionaryID , 
            @RequestParam("fieldDictionaryID") String fieldDictionaryID , 
            ServletRequest request){
        
        
        Map<String , Object> otherMap = new HashMap<String, Object>();
        for(Object key : request.getParameterMap().keySet()){
            otherMap.put((String) key, request.getParameter((String) key));
        }
        
        return evaluateTemplateService.createOrReplaceEvaluateItem(EvaluateTypeEnum.NOT_NULL, objectDictionaryID, fieldDictionaryID,otherMap);
    }
}
