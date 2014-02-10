/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.web.controller;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.server.service.DictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据字典Controller
 * @author dell
 */
@Component
@RequestMapping(value = "/rest/dictinary")
public class DictinaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 更新数据库字典
     */
    @ResponseBody
    @RequestMapping(value = "/refreshDictinary", method = RequestMethod.POST)
    public void refreshDictinary() {
        dictionaryService.initDirectionary();
    }
    
    /**
     * 
     * @param instanceType
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/fieldDictionary/{instanceType}")
    public List<FieldDictionary> findFieldDictionaryByInstanceType(@PathVariable(value = "instanceType") int instanceType){
        return dictionaryService.findFieldDictionaryByInstanceType(instanceType);
    }
}
