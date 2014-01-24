/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.result;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 评测结果
 * @author dell
 */
@Entity
@Table(name="t_res_result")
public class Result extends BaseEntity{
    private static final long serialVersionUID = -8638419517310706654L;
    
    public final static int STATUS_UNEVALUATE = 0;//未评测
    
    public final static int STATUS_PASSED = 1;//评测通过
    
    public final static int STATUS_FAILURE = 2;//评测未通过
    
    private EvaluateVersion evaluateVersion;
    
    private String instanceId;
    
    private int instanceType;
    
    private String instanceClass;
    
    private EvaluateItemHistory itemHistory;
    
    private Calendar completedDate;
    
    private int status = 0;//状态，0=未评测，1=评测通过，2=评测未通过
    
    private String describetion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluateVersion_id" , referencedColumnName = "id")
    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }

    @Column(name = "instanceId")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Column(name = "instanceType")
    public int getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(int instanceType) {
        this.instanceType = instanceType;
    }

    @Column(name = "instanceClass")
    public String getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="evaluateItemHistory_id" , referencedColumnName = "id")
    public EvaluateItemHistory getItemHistory() {
        return itemHistory;
    }

    public void setItemHistory(EvaluateItemHistory itemHistory) {
        this.itemHistory = itemHistory;
    }

    @Column(name = "completedDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Calendar getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Calendar completedDate) {
        this.completedDate = completedDate;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "describetion")
    public String getDescribetion() {
        return describetion;
    }

    public void setDescribetion(String describetion) {
        this.describetion = describetion;
    }
    
    
}
