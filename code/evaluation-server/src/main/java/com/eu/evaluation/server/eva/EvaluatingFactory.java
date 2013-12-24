/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.server.ApplicationContextUtils;
import com.eu.evaluation.server.service.Evaluating;

/**
 *
 * @author dell
 */
public class EvaluatingFactory {
    
    /**
     * 根据评测项目模板中记录的评测逻辑类bean名称，从spring容器中获取实例化的评测逻辑类
     * @param evaluatingClass
     * @return 
     */
    public static Evaluating getEvaluating(String evaluatingClass){
        Evaluating evaluating = (Evaluating) ApplicationContextUtils.getBean(evaluatingClass);
        return evaluating;
    }
}
