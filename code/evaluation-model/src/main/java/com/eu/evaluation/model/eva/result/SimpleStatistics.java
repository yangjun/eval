/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.result;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name="t_res_SimpleStatistics")
public class SimpleStatistics extends BaseEntity{
    private static final long serialVersionUID = 7295573750771899784L;
    private EvaluateVersion evaluateVersion;
    
    private EvaluateTypeEnum evaluateTypeEnum;
    
    private int instanceType;
    
    private String instanceClass;
    
    private long successCount = 0;
    
    private long failCount = 0;
    
    private long total = 0;

    public SimpleStatistics() {
    }

    public SimpleStatistics(int instanceType , String instanceClass , EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
        this.instanceType = instanceType;
        this.instanceClass = instanceClass;
    }

    public SimpleStatistics(EvaluateTypeEnum evaluateTypeEnum, int instanceType, String instanceClass , long failCount) {
        this.evaluateTypeEnum = evaluateTypeEnum;
        this.instanceType = instanceType;
        this.instanceClass = instanceClass;
        this.failCount = failCount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluateVersion_id" , referencedColumnName = "id")
    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name="evaluate_Type")
    public EvaluateTypeEnum getEvaluateTypeEnum() {
        return evaluateTypeEnum;
    }

    public void setEvaluateTypeEnum(EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
    }

    @Column(name="instanceType")
    public int getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(int instanceType) {
        this.instanceType = instanceType;
    }

    @Column(name="instanceClass")
    public String getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    @Column(name="successCount")
    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    @Column(name="failCount")
    public long getFailCount() {
        return failCount;
    }

    public void setFailCount(long failCount) {
        this.failCount = failCount;
    }

    @Column(name="total")
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
    
}
