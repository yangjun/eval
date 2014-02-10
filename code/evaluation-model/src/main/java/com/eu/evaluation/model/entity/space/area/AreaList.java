/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.area;

import com.eu.evaluation.model.entity.ListResponse;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@XmlRootElement(name = "areas")
public class AreaList implements ListResponse<AreaOrg>{
    private List<AreaOrg> areas;

    @XmlElement(name = "area")
    public List<AreaOrg> getRows() {
        return areas;
    }

    public void setRows(List<AreaOrg> areas) {
        this.areas = areas;
    }
    
    
}
