/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.deviceRoom;

import com.eu.evaluation.model.entity.ListResponse;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@XmlRootElement(name="rooms")
public class DeviceRoomList implements ListResponse<DeviceRoomOrg>{
    private List<DeviceRoomOrg> deviceRooms;

    @XmlElement(name="room")
    public List<DeviceRoomOrg> getRows() {
        return deviceRooms;
    }

    public void setRows(List<DeviceRoomOrg> deviceRooms) {
        this.deviceRooms = deviceRooms;
    }
    
    
}
