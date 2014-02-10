/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.web.controller;

import com.eu.evaluation.web.controller.pojo.KeyValue;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础数据Controller
 * @author dell
 */
@Controller
@RequestMapping("/rest/basicData")
public class BasicDataController {
    

    /**
     * 返回所有实体类型
     * @return 
     */
    @ResponseBody
    @RequestMapping(value = "/entityEnum" , method = RequestMethod.GET)
    public List<KeyValue> findAllEntityEnum(){
        List<KeyValue> result = new ArrayList<KeyValue>();
        for(EntityEnum ee : EntityEnum.values()){
            if (ee == EntityEnum.UNKNOWN){
                continue;
            }
            KeyValue kv = new KeyValue(ee.getInstanceType(), ee.getName());
            result.add(kv);
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/evaluateTypeEnum" , method = RequestMethod.GET)
    public List<KeyValue> findAllEvaluateTypeEnum(){
        List<KeyValue> result = new ArrayList<KeyValue>();
        for(EvaluateTypeEnum ee : EvaluateTypeEnum.values()){
            KeyValue kv = new KeyValue(ee, ee.getName());
            result.add(kv);
        }
        return result;
    }
    
    
}
