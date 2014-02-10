/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity.space.area;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.NameCodeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_spc_area_org")
@XmlRootElement(name = "area")
public class AreaOrg extends NameCodeEntity implements IArea {

    private static final long serialVersionUID = -4297557937021548197L;
    private String areaLevel;
    
    private String parentId;

    @Override
    @Column(name = "areaLevel")
    public String getAreaLevel() {
        return areaLevel;
    }

    @Override
    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    @Override
    @Column(name = "parentId")
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
