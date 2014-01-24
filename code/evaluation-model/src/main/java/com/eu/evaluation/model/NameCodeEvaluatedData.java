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
public class NameCodeEvaluatedData extends NameEvaluatedData{
    private static final long serialVersionUID = -7018409170724595233L;
    private String code;

    @Column(name = "code")
    @Dictinary(displayname = "编码")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
