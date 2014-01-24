/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity.space.station;

import com.eu.evaluation.model.NameCodeEntity;
import javax.persistence.Column;
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
@Table(name = "t_spc_station_org")
@XmlRootElement
public class StationOrg extends NameCodeEntity implements IStation {
//、

    private String address;
    private String area;
    private String areaId;
    private String dispatchUnit;
    private Integer have2ac;
    private Integer have2dc;
    private Integer haveCommuntcation;
    private Integer havesdh;
    private Double latitude;
    private Double longitude;
    private String notHave2acCause;
    private String notHave2dcCause;
    private String stationType;//
    private String stationTypeAlong;
    private String stdName;

    @Override
    public void setCode(String code) {
        super.setCode(code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @XmlElement(name="stationCode")
    @Transient
    public String getCode() {
        return super.getCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @XmlElement(name="stationName")
    @Transient
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "address")
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "area")
    @Override
    public String getArea() {
        return area;
    }

    @Override
    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "areaId")
    @Override
    public String getAreaId() {
        return areaId;
    }

    @Override
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Column(name = "dispatchUnit")
    @Override
    public String getDispatchUnit() {
        return dispatchUnit;
    }

    @Override
    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
    }

    @Column(name = "have2ac")
    @Override
    public Integer getHave2ac() {
        return have2ac;
    }

    @Override
    public void setHave2ac(Integer have2ac) {
        this.have2ac = have2ac;
    }

    @Column(name = "have2dc")
    @Override
    public Integer getHave2dc() {
        return have2dc;
    }

    @Override
    public void setHave2dc(Integer have2dc) {
        this.have2dc = have2dc;
    }

    @Column(name = "haveCommuntcation")
    @Override
    public Integer getHaveCommuntcation() {
        return haveCommuntcation;
    }

    @Override
    public void setHaveCommuntcation(Integer haveCommuntcation) {
        this.haveCommuntcation = haveCommuntcation;
    }

    @Column(name = "havesdh")
    @Override
    public Integer getHavesdh() {
        return havesdh;
    }

    @Override
    public void setHavesdh(Integer havesdh) {
        this.havesdh = havesdh;
    }

    @Column(name = "latitude")
    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "notHave2acCause")
    @Override
    public String getNotHave2acCause() {
        return notHave2acCause;
    }

    @Override
    public void setNotHave2acCause(String notHave2acCause) {
        this.notHave2acCause = notHave2acCause;
    }

    @Column(name = "notHave2dcCause")
    @Override
    public String getNotHave2dcCause() {
        return notHave2dcCause;
    }

    @Override
    public void setNotHave2dcCause(String notHave2dcCause) {
        this.notHave2dcCause = notHave2dcCause;
    }

    @Column(name = "stationType")
    @Override
    public String getStationType() {
        return stationType;
    }

    @Override
    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    @Column(name = "stationTypeAlong")
    @Override
    public String getStationTypeAlong() {
        return stationTypeAlong;
    }

    @Override
    public void setStationTypeAlong(String stationTypeAlong) {
        this.stationTypeAlong = stationTypeAlong;
    }

    @Column(name = "stdName")
    @Override
    public String getStdName() {
        return stdName;
    }

    @Override
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

}
