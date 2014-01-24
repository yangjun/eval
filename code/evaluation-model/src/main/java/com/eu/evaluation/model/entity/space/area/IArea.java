/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity.space.area;

import com.eu.evaluation.model.IBaseEntity;

/**
 *
 * @author dell
 */
public interface IArea extends IBaseEntity{

    public String getAreaLevel();

    public String getCode();

    public String getParentId();
    
    public String getPosition();

    public void setAreaLevel(String areaLevel);

    public void setCode(String code);

    public void setParentId(String parentId);
    
    public void setPosition(String position);
}
