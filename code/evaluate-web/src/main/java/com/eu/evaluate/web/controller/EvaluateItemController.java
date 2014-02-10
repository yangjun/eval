/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluate.web.controller;

import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.server.service.DictionaryService;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.EvaluateTemplateService;
import java.util.List;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dell
 */
@Controller
@RequestMapping(value="/rest/eva/item")
public class EvaluateItemController {
    
    @Autowired
    public EvaluateService evaluateService;
    
    @Autowired
    public EvaluateTemplateService evaluateTemplateService;
    
    @Autowired
    public DictionaryService dictionaryService;
    
    /**
     * 根据评测类型、被评实体类型查询评测指标
     * @param evaluateType
     * @param instanceType
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/evaluateItem/{evaluateType}/{instanceType}" , method = RequestMethod.GET)
    public <T extends EvaluateItem> List<T> findEvaluateItem(int evaluateType , int instanceType){
        return evaluateTemplateService.findEvaluateItem(evaluateType, instanceType);
    }
    
    /**
     * 保存评测指标
     * @param evaluateType 评测类型
     * @param objectDictionaryID 被评实体数据库字典ID
     * @param fieldDictionaryID 被评字段数据库字典ID
     * @param request 记录评测指标的其他信息
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/evaluateItem" , method = RequestMethod.POST)
    public EvaluateItem saveEvaluateItem(@RequestParam(value = "evaluateType") int evaluateType , 
            @RequestParam(value="objectDictionaryID") String objectDictionaryID , 
            @RequestParam(value="fieldDictionaryID") String fieldDictionaryID , 
            ServletRequest request){
        return evaluateTemplateService.createOrReplaceEvaluateItem(EvaluateTypeEnum.getByType(evaluateType), objectDictionaryID, fieldDictionaryID, request.getParameterMap());
    }
}
