/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.sys;

import com.eu.evaluation.model.NameEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name="t_sys_params")
public class SystemParams extends NameEntity{
    private static final long serialVersionUID = 5428864436796640751L;
    private String paramValue;

    @Column(name="paramValue")
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    
    
}
