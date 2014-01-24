/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao.entity.space.deviceroom;

import com.eu.evaluation.model.entity.space.deviceRoom.DeviceRoomOrg;
import com.eu.evaluation.server.dao.AbstractDAO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class DeviceroomOrgDAO extends AbstractDAO<DeviceRoomOrg>{
    public int removeByPosition(String sysKey){
        String sql = "delete from DeviceRoomOrg t where t.position = :position";
        MapSqlParameterSource params = new MapSqlParameterSource("position", sysKey);
        return this.remove(sql, params);
    }
}
