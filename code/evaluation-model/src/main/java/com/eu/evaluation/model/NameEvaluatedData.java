/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import com.eu.evaluation.anno.Dictinary;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author dell
 */
@MappedSuperclass
public class NameEvaluatedData extends EvaluatedData{
    private static final long serialVersionUID = 1601296602654889935L;
    private String name;

    @Column(name = "name")
    @Dictinary(displayname = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
