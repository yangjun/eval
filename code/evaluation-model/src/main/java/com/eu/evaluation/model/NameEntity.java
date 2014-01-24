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
public class NameEntity extends BaseEntity implements INameEntity{
    private static final long serialVersionUID = -6675119591041067587L;
    private String name;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
