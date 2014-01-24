/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluate.web.controller;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.server.service.DictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dell
 */
@Controller
@RequestMapping("/rest/basicData")
public class BasicDataController {
    

    
    @ResponseBody
    @RequestMapping(value = "/entityEnum" , method = RequestMethod.GET)
    public EntityEnum[] findAllEntityEnum(){
        return EntityEnum.values();
    }
    
    @ResponseBody
    @RequestMapping(value = "/evaluateTypeEnum" , method = RequestMethod.GET)
    public EvaluateTypeEnum[] findAllEvaluateTypeEnum(){
        return EvaluateTypeEnum.values();
    }
    
    
}
