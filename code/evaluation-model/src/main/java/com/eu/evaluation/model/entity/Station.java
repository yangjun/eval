/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.NameCodeEvaluatedData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_spc_station")
public class Station extends NameCodeEvaluatedData {
    private static final long serialVersionUID = 5883411103958952393L;

    private String dispatchUnit;
    
    private Area area;

    @Column(name = "dispatchUnit")
    public String getDispatchUnit() {
        return dispatchUnit;
    }

    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "area_id", referencedColumnName = "id", insertable = false , updatable = false),
            @JoinColumn(name = "evaluateVersion_id", referencedColumnName = "evaluateVersion_id", insertable = false , updatable = false)
    })
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.STATION;
    }

    
}
