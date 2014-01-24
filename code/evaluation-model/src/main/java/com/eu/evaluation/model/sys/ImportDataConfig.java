/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.sys;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.NameEntity;
import com.eu.evaluation.model.entity.ListResponse;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dell
 */
@Entity
@Table(name="t_sys_import_data_config")
public class ImportDataConfig extends NameEntity{
    private int instanceType;//导入的实体类型
    
    private String url;//资源系统提供的资源查询接口
    
    private String importClass;//导入逻辑的实现类（spring的bean名称）
    
    private String ListResponseClassName;//资源系统接口list数据相应的封装类。资源系统接口提供的被评数据是list形式的xml，需要一个封装类才能转换到java对象
    
    private String orgInstanceClassName;//被评数据原始实体对象
    
    private Class<? extends ListResponse<? extends BaseEntity>> listResponse;
    
    private Class<?> orgInstanceClass;

    @Column(name="instanceType")
    public int getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(int instanceType) {
        this.instanceType = instanceType;
    }

    @Column(name="url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="importClass")
    public String getImportClass() {
        return importClass;
    }

    public void setImportClass(String importClass) {
        this.importClass = importClass;
    }

    @Column(name="ListResponseClass")
    public String getListResponseClassName() {
        return ListResponseClassName;
    }

    public void setListResponseClassName(String ListResponseClassName) {
        this.ListResponseClassName = ListResponseClassName;
    }

    @Column(name="orgInstanceClass")
    public String getOrgInstanceClassName() {
        return orgInstanceClassName;
    }

    public void setOrgInstanceClassName(String orgInstanceClassName) {
        this.orgInstanceClassName = orgInstanceClassName;
    }

    @Transient
    public Class<? extends ListResponse<? extends BaseEntity>> getListResponse() throws ClassNotFoundException {
        if (listResponse == null){
            listResponse = (Class<? extends ListResponse<? extends BaseEntity>>) Class.forName(getListResponseClassName());
        }
        return listResponse;
    }

    public void setListResponse(Class<? extends ListResponse<? extends BaseEntity>> listResponse) {
        this.listResponse = listResponse;
    }

    @Transient
    public Class<?> getOrgInstanceClass() throws ClassNotFoundException {
        if (orgInstanceClass == null){
            orgInstanceClass =  Class.forName(getOrgInstanceClassName());
        }
        return orgInstanceClass;
    }

    public void setOrgInstanceClass(Class<?> orgInstanceClass) {
        this.orgInstanceClass = orgInstanceClass;
    }
    
    
}
