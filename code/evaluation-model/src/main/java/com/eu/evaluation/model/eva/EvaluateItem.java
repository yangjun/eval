/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评测项目
 * @author dell
 */
@Entity
@Table(name="t_eva_item")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eva_type" , discriminatorType = DiscriminatorType.STRING)
public class EvaluateItem extends BaseEntity{
    
    private static final long serialVersionUID = -160862979545966008L;
    
    private boolean editable;
    
    private FieldDictionary fieldDictionary;
    
    private ObjectDictionary objectDictionary;
    
    private String evaluateSQL;
    
    private String evaluateClass;
    
    private String describetion;
    
    private EvaluateTypeEnum evaluateTypeEnum;//评测类型，多种评测类型通过继承的方式共用一张表，通过eva_type进行区分，但eva_type的取值不能是枚举，不方便查询，所以增加该字段

    @Enumerated(value = EnumType.STRING)
    @Column(name="evaluateTypeEnum")
    public EvaluateTypeEnum getEvaluateTypeEnum() {
        return evaluateTypeEnum;
    }

    public void setEvaluateTypeEnum(EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
    }
    
    @Column(name="editable")
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="objectDictionary_id" , referencedColumnName = "id")
    public ObjectDictionary getObjectDictionary() {
        return objectDictionary;
    }

    public void setObjectDictionary(ObjectDictionary objectDictionary) {
        this.objectDictionary = objectDictionary;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fieldDictionary_id" , referencedColumnName = "id")
    public FieldDictionary getFieldDictionary() {
        return fieldDictionary;
    }

    public void setFieldDictionary(FieldDictionary fieldDictionary) {
        this.fieldDictionary = fieldDictionary;
    }

    @Column(name="evaluateSQL")
    public String getEvaluateSQL() {
        return evaluateSQL;
    }

    public void setEvaluateSQL(String evaluateSQL) {
        this.evaluateSQL = evaluateSQL;
    }

    @Column(name="evaluateClass")
    public String getEvaluateClass() {
        return evaluateClass;
    }

    public void setEvaluateClass(String evaluateClass) {
        this.evaluateClass = evaluateClass;
    }

    @Column(name="describetion")
    public String getDescribetion() {
        return describetion;
    }

    public void setDescribetion(String describion) {
        this.describetion = describion;
    }
    
    
}
