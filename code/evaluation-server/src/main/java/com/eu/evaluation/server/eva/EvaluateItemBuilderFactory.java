/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.server.ApplicationContextUtils;

/**
 * 
 * @author dell
 */
public class EvaluateItemBuilderFactory {
    /**
     * 根据评测项目模板中记录的评测项目生成器bean名称，从spring容器中获取实例化的评测项目生成器
     * @param builderBeanName
     * @return 
     */
    public static EvaluateItemBuilder getBuilder(String builderBeanName){
        EvaluateItemBuilder builder = (EvaluateItemBuilder) ApplicationContextUtils.getBean(builderBeanName);
        return builder;
    }
}
