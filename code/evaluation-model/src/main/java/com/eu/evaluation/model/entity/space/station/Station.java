/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.entity.space.station;

import com.eu.evaluation.anno.Dictinary;
import com.eu.evaluation.anno.ForeignKey;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.NameCodeEvaluatedData;
import com.eu.evaluation.model.entity.space.area.Area;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_spc_station")
public class Station extends NameCodeEvaluatedData implements IStation{
    private static final long serialVersionUID = 5883411103958952393L;

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

    @Transient
    @Override
    public EntityEnum getEntityType() {
        return EntityEnum.STATION;
    }

    @Column(name="address")
    @Dictinary(displayname = "地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="area")
    @Dictinary(displayname = "区域名称" , visible = false)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name="areaId")
    @Dictinary(displayname = "所属区域" , visible = true , foreignKey = @ForeignKey(foreignClass = Area.class))
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Column(name="dispatchUnit")
    @Dictinary(displayname = "调管单位")
    public String getDispatchUnit() {
        return dispatchUnit;
    }

    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
    }

    @Column(name="have2ac")
    @Dictinary(displayname = "通信交流电源是否满足双电源")
    public Integer getHave2ac() {
        return have2ac;
    }

    public void setHave2ac(Integer have2ac) {
        this.have2ac = have2ac;
    }

    @Column(name="have2dc")
    @Dictinary(displayname = "通信行信直流电源是否满足双电源")
    public Integer getHave2dc() {
        return have2dc;
    }

    public void setHave2dc(Integer have2dc) {
        this.have2dc = have2dc;
    }

    @Column(name="haveCommuntcation")
    @Dictinary(displayname = "是否有独立通信机房")
    public Integer getHaveCommuntcation() {
        return haveCommuntcation;
    }

    public void setHaveCommuntcation(Integer haveCommuntcation) {
        this.haveCommuntcation = haveCommuntcation;
    }

    @Column(name="havesdh")
    @Dictinary(displayname = "是否光通信覆盖")
    public Integer getHavesdh() {
        return havesdh;
    }

    public void setHavesdh(Integer havesdh) {
        this.havesdh = havesdh;
    }

    @Column(name="latitude")
    @Dictinary(displayname = "纬度")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name="longitude")
    @Dictinary(displayname = "经度")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name="notHave2acCause")
    @Dictinary(displayname = "通信交流电源不满足双电源原因")
    public String getNotHave2acCause() {
        return notHave2acCause;
    }

    public void setNotHave2acCause(String notHave2acCause) {
        this.notHave2acCause = notHave2acCause;
    }

    @Column(name="notHave2dcCause")
    @Dictinary(displayname = "通信直流电源不满足双电源原因")
    public String getNotHave2dcCause() {
        return notHave2dcCause;
    }

    public void setNotHave2dcCause(String notHave2dcCause) {
        this.notHave2dcCause = notHave2dcCause;
    }

    @Column(name="stationType")
    @Dictinary(displayname = "站点类型")
    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    @Column(name="stationTypeAlong")
    @Dictinary(displayname = "站点附属类型")
    public String getStationTypeAlong() {
        return stationTypeAlong;
    }

    public void setStationTypeAlong(String stationTypeAlong) {
        this.stationTypeAlong = stationTypeAlong;
    }

    @Column(name="stdName")
    @Dictinary(displayname = "标准名称")
    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    
    
}
