/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.dictionary;

import com.eu.evaluation.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 实体之间的关系
 * @author dell
 */
@Entity
@Table(name="t_dic_relation")
public class ObjectRelation extends BaseEntity{
    private static final long serialVersionUID = 2118876504346430675L;
    
    private String selfClass;//自身
    
    private String propertyName;//关联字段
    
    private String relationClass;//关联到的对象
    
    private boolean simpleProperty;//关联字段propertyName是否是简单类型

    @Column(name = "selfClass")
    public String getSelfClass() {
        return selfClass;
    }

    public void setSelfClass(String selfClass) {
        this.selfClass = selfClass;
    }

    @Column(name = "propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Column(name = "relationClass")
    public String getRelationClass() {
        return relationClass;
    }

    public void setRelationClass(String relationClass) {
        this.relationClass = relationClass;
    }

    @Column(name = "simpleProperty")
    public boolean isSimpleProperty() {
        return simpleProperty;
    }

    public void setSimpleProperty(boolean simpleProperty) {
        this.simpleProperty = simpleProperty;
    }
    
    
}
