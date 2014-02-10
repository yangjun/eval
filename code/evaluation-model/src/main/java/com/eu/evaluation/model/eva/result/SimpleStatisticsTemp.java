/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.result;

import com.eu.evaluation.model.eva.EvaluateTypeEnum;

/**
 *
 * @author dell
 */
public class SimpleStatisticsTemp {
    private EvaluateTypeEnum evaluateTypeEnum;
    
    private int instanceType;
    
    private int status;
    
    private Long total;

    public SimpleStatisticsTemp(int instanceType , EvaluateTypeEnum evaluateTypeEnum, int status, Long total) {
        this.evaluateTypeEnum = evaluateTypeEnum;
        this.instanceType = instanceType;
        this.status = status;
        this.total = total;
    }

    public EvaluateTypeEnum getEvaluateTypeEnum() {
        return evaluateTypeEnum;
    }

    public void setEvaluateTypeEnum(EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
    }

    public int getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(int instanceType) {
        this.instanceType = instanceType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
    
}
