/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.dictionary;

import com.eu.evaluation.model.BaseEntity;
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
    
    private ObjectDictionary self;//自身
    
    private FieldDictionary relationField;//关联字段
    
    private ObjectDictionary parent;//关联到的父对象

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="self_id" , referencedColumnName = "id")
    public ObjectDictionary getSelf() {
        return self;
    }

    public void setSelf(ObjectDictionary self) {
        this.self = self;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id" , referencedColumnName = "id")
    public ObjectDictionary getParent() {
        return parent;
    }

    public void setParent(ObjectDictionary parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="relationField_id" , referencedColumnName = "id")
    public FieldDictionary getRelationField() {
        return relationField;
    }

    public void setRelationField(FieldDictionary relationField) {
        this.relationField = relationField;
    }
    
    
}
