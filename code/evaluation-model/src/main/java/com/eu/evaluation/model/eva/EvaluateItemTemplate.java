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
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评测项目模板（配置信息）
 * @author dell
 */
@Entity
@Table(name="t_eva_template")
public class EvaluateItemTemplate extends BaseEntity{
    private static final long serialVersionUID = 6875081233884526742L;
    
    private String evaluateClass;//评测逻辑实现类，填实现类声明的spring托管bean的名称，不是类对的全路径名
    
    private EvaluateTypeEnum evaluateTypeEnum;//评测类型。每种类型至少有一条记录，记录默认的评测逻辑实现类
    
    private ObjectDictionary objectDictionary;//如果某个对象或字段需要使用特殊的评测逻辑实现类，则增加一条数据，并填写相关的字段
    
    private FieldDictionary fieldDictionary;
    
    private String describetion;
    
    private String evaluateItemBuilder;//评测项目生成器。指明了如何从评测模板生成评测项目,填实现类声明的spring托管bean的名称，不是类对的全路径名

    @Column(name = "evaluateItemBuilder")
    public String getEvaluateItemBuilder() {
        return evaluateItemBuilder;
    }

    public void setEvaluateItemBuilder(String evaluateItemBuilder) {
        this.evaluateItemBuilder = evaluateItemBuilder;
    }

    @Column(name = "evaluateTypeEnum")
    @Enumerated(value = EnumType.STRING)
    public EvaluateTypeEnum getEvaluateTypeEnum() {
        return evaluateTypeEnum;
    }

    public void setEvaluateTypeEnum(EvaluateTypeEnum evaluateTypeEnum) {
        this.evaluateTypeEnum = evaluateTypeEnum;
    }

    @Column(name = "evaluateClass")
    public String getEvaluateClass() {
        return evaluateClass;
    }

    public void setEvaluateClass(String evaluateClass) {
        this.evaluateClass = evaluateClass;
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

    @Column(name = "describetion")
    public String getDescribetion() {
        return describetion;
    }

    public void setDescribetion(String describetion) {
        this.describetion = describetion;
    }
    
    
}
