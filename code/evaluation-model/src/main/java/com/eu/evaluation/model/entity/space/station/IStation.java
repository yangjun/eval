/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.station;

import javax.persistence.Column;

/**
 *
 * @author dell
 */
public interface IStation {

    @Column(name = "address")
    String getAddress();

    @Column(name = "area")
    String getArea();

    @Column(name = "areaId")
    String getAreaId();

    @Column(name = "dispatchUnit")
    String getDispatchUnit();

    @Column(name = "have2ac")
    Integer getHave2ac();

    @Column(name = "have2dc")
    Integer getHave2dc();

    @Column(name = "haveCommuntcation")
    Integer getHaveCommuntcation();

    @Column(name = "havesdh")
    Integer getHavesdh();

    @Column(name = "latitude")
    Double getLatitude();

    @Column(name = "longitude")
    Double getLongitude();

    @Column(name = "notHave2acCause")
    String getNotHave2acCause();

    @Column(name = "notHave2dcCause")
    String getNotHave2dcCause();

    @Column(name = "stationType")
    String getStationType();

    @Column(name = "stationTypeAlong")
    String getStationTypeAlong();

    @Column(name = "stdName")
    String getStdName();

    void setAddress(String address);

    void setArea(String area);

    void setAreaId(String areaId);

    void setDispatchUnit(String dispatchUnit);

    void setHave2ac(Integer have2ac);

    void setHave2dc(Integer have2dc);

    void setHaveCommuntcation(Integer haveCommuntcation);

    void setHavesdh(Integer havesdh);

    void setLatitude(Double latitude);

    void setLongitude(Double longitude);

    void setNotHave2acCause(String notHave2acCause);

    void setNotHave2dcCause(String notHave2dcCause);

    void setStationType(String stationType);

    void setStationTypeAlong(String stationTypeAlong);

    void setStdName(String stdName);
    
}
