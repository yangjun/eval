/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.sys;

import com.eu.evaluation.model.NameCodeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接入的系统
 * @author dell
 */
@Entity
@Table(name="t_sys_access_system")
public class AccessSystem extends NameCodeEntity{
    private String path;//被评系统数据查询接口
    
    private boolean vaild;//是否有效

    @Column(name="path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name="vaild")
    public boolean isVaild() {
        return vaild;
    }

    public void setVaild(boolean vaild) {
        this.vaild = vaild;
    }
    
    
}
