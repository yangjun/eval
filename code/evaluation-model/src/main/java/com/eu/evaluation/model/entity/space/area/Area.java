/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.area;

import com.eu.evaluation.anno.Dictinary;
import com.eu.evaluation.anno.ForeignKey;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.NameCodeEvaluatedData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_spc_area")
public class Area extends NameCodeEvaluatedData implements IArea {
    private static final long serialVersionUID = -4372212372820098432L;
    
    private String areaLevel;
    
    private String parentId;
    
    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.AREA;
    }

    @Column(name = "areaLevel")
    @Dictinary(displayname = "等级")
    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    
    @Column(name = "parentId")
    @Dictinary(displayname = "父区域标示" , foreignKey = @ForeignKey(foreignClass = Area.class))
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    
}
