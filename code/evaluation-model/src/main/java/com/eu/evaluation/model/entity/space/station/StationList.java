/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.station;

import com.eu.evaluation.model.entity.ListResponse;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@XmlRootElement(name="stations")
public class StationList implements ListResponse<StationOrg>{
    private List<StationOrg> stations;

    @XmlElement(name="station")
    public List<StationOrg> getRows() {
        return stations;
    }

    public void setRows(List<StationOrg> stations) {
        this.stations = stations;
    }
    
    
}
