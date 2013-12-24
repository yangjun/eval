/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

/**
 *
 * @author dell
 */
public enum EntityEnum {
    UNKNOWN("未定义" , -1) ,AREA("区域" , 2) , STATION("站点" , 4) , DEVICEROOM("机房" , 5) ;
    
    private String name;
    
    private int index;
    
    private EntityEnum(String name , int index){
        this.name = name;
        this.index = index;
    }
    
    public static String getName(int index) {  
        for (EntityEnum c : EntityEnum.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
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
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
}
