/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.dictionary;

import com.eu.evaluation.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典--资源对象
 * @author dell
 */
@Entity
@Table(name="t_dic_object")
public class ObjectDictionary extends BaseEntity{
    private static final long serialVersionUID = 1396433438291949651L;
    
    private int instanceType;
    
    private String instanceClass;
    
    private String tableName;
    
    private String displayname;
    
    private boolean valid;//是否有效，数据字典不删除，只设置为是否有效
    
    private int serial;//序号

    @Column(name="serial")
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
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

    @Column(name="tableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name="displayname")
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Column(name="valid")
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
}
