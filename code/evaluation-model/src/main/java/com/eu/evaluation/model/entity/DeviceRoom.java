/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.NameCodeEvaluatedData;
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
@Table(name = "t_spc_deviceroom")
public class DeviceRoom extends NameCodeEvaluatedData {

    private static final long serialVersionUID = -8561514945614851426L;

    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "station_id", referencedColumnName = "id", insertable = false , updatable = false),
            @JoinColumn(name = "evaluateVersion_id", referencedColumnName = "evaluateVersion_id" , insertable = false , updatable = false)
    })
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.DEVICEROOM;
    }
}
