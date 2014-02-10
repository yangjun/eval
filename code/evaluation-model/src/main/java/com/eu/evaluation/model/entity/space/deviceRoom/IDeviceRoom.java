/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.deviceRoom;

import com.eu.evaluation.model.IPositionEntity;

/**
 *
 * @author dell
 */
public interface IDeviceRoom extends IPositionEntity{

    String getFloor();

    Double getHeight();

    Double getLongness();

    String getRoomNo();

    String getRoomType();

    String getStation();

    String getStationID();

    Double getWidth();

    void setFloor(String floor);

    void setHeight(Double height);

    void setLongness(Double longness);

    void setRoomNo(String roomNo);

    void setRoomType(String roomType);

    void setStation(String station);

    void setStationID(String stationID);

    void setWidth(Double width);
    
}
