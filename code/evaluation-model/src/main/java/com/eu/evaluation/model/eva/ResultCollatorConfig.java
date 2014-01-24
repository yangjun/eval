/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva;

import com.eu.evaluation.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name="t_eva_result_collator")
public class ResultCollatorConfig extends BaseEntity{
    private static final long serialVersionUID = 7025323136966833651L;
    private String collatorClass;
    
    private int serial;

    @Column(name="collatorClass")
    public String getCollatorClass() {
        return collatorClass;
    }

    public void setCollatorClass(String collatorClass) {
        this.collatorClass = collatorClass;
    }

    @Column(name="serial")
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    
    
}
