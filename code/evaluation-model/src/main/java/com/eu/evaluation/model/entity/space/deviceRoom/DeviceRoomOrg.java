/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.deviceRoom;

import com.eu.evaluation.model.NameCodeEntity;
import com.eu.evaluation.model.NameCodePositionEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name="t_spc_deviceroom_org")
@XmlRootElement(name="room")
public class DeviceRoomOrg extends NameCodePositionEntity implements IDeviceRoom{
    private static final long serialVersionUID = -3645436483923262736L;
    private String floor;
    
    private Double height;
    
    private Double longness;
    
    private Double width;
    
    private String roomNo;
    
    private String roomType;
    
    private String station;
    
    private String stationID;

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public Double getLongness() {
        return longness;
    }

    @Override
    public void setLongness(Double longness) {
        this.longness = longness;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public String getRoomNo() {
        return roomNo;
    }

    @Override
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String getRoomType() {
        return roomType;
    }

    @Override
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String getStation() {
        return station;
    }

    @Override
    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String getStationID() {
        return stationID;
    }

    @Override
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    @Override
    public void setCode(String code) {
        super.setCode(code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @XmlElement(name="roomCode")
    @Transient
    public String getCode() {
        return super.getCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @XmlElement(name="roomName")
    @Transient
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
