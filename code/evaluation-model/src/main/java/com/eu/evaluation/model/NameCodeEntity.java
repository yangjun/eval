/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author dell
 */
@MappedSuperclass
public class NameCodeEntity extends NameEntity{
    private static final long serialVersionUID = 6088579298970256906L;
    private String code;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
