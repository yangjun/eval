/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.NameCodeEvaluatedData;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_spc_area")
public class Area extends NameCodeEvaluatedData implements Serializable {
    private static final long serialVersionUID = -4372212372820098432L;
    
    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.AREA;
    }
}
