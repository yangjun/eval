/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity.space.deviceRoom;

import com.eu.evaluation.anno.Dictinary;
import com.eu.evaluation.anno.ForeignKey;
import com.eu.evaluation.model.entity.space.station.Station;
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
public class DeviceRoom extends NameCodeEvaluatedData implements IDeviceRoom{

    private static final long serialVersionUID = -8561514945614851426L;

    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.DEVICEROOM;
    }
    
    private String floor;
    
    private Double height;
    
    private Double longness;
    
    private Double width;
    
    private String roomNo;
    
    private String roomType;
    
    private String station;
    
    private String stationID;

    @Override
    @Dictinary(displayname = "楼层")
    public String getFloor() {
        return floor;
    }

    @Override
    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    @Dictinary(displayname = "高（米）")
    public Double getHeight() {
        return height;
    }

    @Override
    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    @Dictinary(displayname = "长（米）")
    public Double getLongness() {
        return longness;
    }

    @Override
    public void setLongness(Double longness) {
        this.longness = longness;
    }

    @Override
    @Dictinary(displayname = "宽（米）")
    public Double getWidth() {
        return width;
    }

    @Override
    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    @Dictinary(displayname = "房间号")
    public String getRoomNo() {
        return roomNo;
    }

    @Override
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    @Dictinary(displayname = "机房类型")
    public String getRoomType() {
        return roomType;
    }

    @Override
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    @Dictinary(displayname = "所在站点名" , visible = false)
    public String getStation() {
        return station;
    }

    @Override
    public void setStation(String station) {
        this.station = station;
    }

    @Override
    @Dictinary(displayname = "所属站点" , foreignKey = @ForeignKey(foreignClass = Station.class))
    public String getStationID() {
        return stationID;
    }

    @Override
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }
}
