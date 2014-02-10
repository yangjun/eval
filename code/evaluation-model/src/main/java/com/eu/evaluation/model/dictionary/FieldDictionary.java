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
 * 数据字典--资源属性
 * @author dell
 */
@Entity
@Table(name="t_dic_field")
public class FieldDictionary extends BaseEntity{
    private static final long serialVersionUID = 8708286336698826977L;
    
    private ObjectDictionary objectDictionary;
    
    private String fieldName;//数据库字典名称
    
    private String propertyName;//java类属性名称
    
    private String displayname;//显示名称
    
    private boolean visible = true;//是否可见。用于指标管理中，对用户因此一些不必须要的属性。如ID，optlock
    
    private boolean valid = true;//是否有效，数据字典不删除，只设置为是否有效
    
    private boolean simpleProperty = true;//propertyName对应的属性是否是一个简单类型（如String , Interger等）

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objectDictionary_id" , referencedColumnName = "id")
    public ObjectDictionary getObjectDictionary() {
        return objectDictionary;
    }

    public void setObjectDictionary(ObjectDictionary objectDictionary) {
        this.objectDictionary = objectDictionary;
    }

    @Column(name="fieldName")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Column(name="displayname")
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Column(name="visible")
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Column(name="propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Column(name="valid")
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Column(name="simpleProperty")
    public boolean isSimpleProperty() {
        return simpleProperty;
    }

    public void setSimpleProperty(boolean simpleProperty) {
        this.simpleProperty = simpleProperty;
    }
    
    
}
