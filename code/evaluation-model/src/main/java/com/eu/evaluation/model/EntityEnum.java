/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model;

import com.eu.evaluation.model.entity.space.deviceRoom.DeviceRoom;
import com.eu.evaluation.model.entity.space.area.Area;
import com.eu.evaluation.model.entity.space.station.Station;

/**
 *
 * @author dell
 */
public enum EntityEnum {

    UNKNOWN("未定义", -1, null),
    AREA("区域", 2, Area.class),
    STATION("站点", 4, Station.class),
    DEVICEROOM("机房", 5, DeviceRoom.class);

    private String name;//资源对象中文名

    private int instanceType;//资源对象类型常量

    private Class<?> entityClass;//资源对象被评数据实体类

    private EntityEnum(String name, int index, Class<?> entityClass) {
        this.name = name;
        this.instanceType = index;
        this.entityClass = entityClass;
    }

    public static String getByName(int instanceType) {
        for (EntityEnum c : EntityEnum.values()) {
            if (c.getInstanceType() == instanceType) {
                return c.name;
            }
        }
        return null;
    }
    
    public static EntityEnum getByEntityClass(Class<?> intanceClass){
        for (EntityEnum c : EntityEnum.values()) {
            if (c.getEntityClass()== intanceClass) {
                return c;
            }
        }
        return null;
    }
    
    public static EntityEnum getByInstanceType(int instanceType){
        for (EntityEnum c : EntityEnum.values()) {
            if (c.getInstanceType()== instanceType) {
                return c;
            }
        }
        return null;
    }

    // get set 方法  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(int index) {
        this.instanceType = index;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    
}
