/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.result;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.PositionEntity;
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
 * 未通过评测的数据
 * @author dell
 */
@Entity
@Table(name="t_res_unpassed_evaluated_data")
public class UnPassedEvaluatedData extends PositionEntity{
    private static final long serialVersionUID = 8090880743473028013L;
    private int instanceType;
    
    private String instanceClass;
    
    private String instanceId;
    
    private EvaluateTypeEnum evaluateTypeEnum;
    
    private String desciption;
    
    private EvaluateVersion evaluateVersion;
    
    public UnPassedEvaluatedData(){
        
    }

    public UnPassedEvaluatedData(int instanceType, String instanceId,String instanceClass, EvaluateTypeEnum evaluateTypeEnum, String desciption, EvaluateVersion evaluateVersion) {
        this.instanceType = instanceType;
        this.instanceId = instanceId;
        this.instanceClass = instanceClass;
        this.evaluateTypeEnum = evaluateTypeEnum;
        this.desciption = desciption;
        this.evaluateVersion = evaluateVersion;
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

    @Column(name="instanceId")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name="evaluateTypeEnum")
    public EvaluateTypeEnum getEvaluateTypeEnum() {
        return evaluateTypeEnum;
    }

    public void setEvaluateTypeEnum(EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
    }

    @Column(name="desciption")
    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluateVersion_id" , referencedColumnName = "id")
    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }
    
    
}
