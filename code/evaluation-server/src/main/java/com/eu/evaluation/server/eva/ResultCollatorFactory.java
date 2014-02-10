/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.server.ApplicationContextUtils;
import com.eu.evaluation.server.service.ResultCollator;

/**
 * 评测结果整理器，负责从评测结果中抽取数据，整理后存储到各个表中，方便后续的查询分析
 * @author dell
 */
public class ResultCollatorFactory {
    
    public static ResultCollator getResultCollator(String resultCollatorName){
        ResultCollator collator = (ResultCollator) ApplicationContextUtils.getBean(resultCollatorName);
        return collator;
    }
}
