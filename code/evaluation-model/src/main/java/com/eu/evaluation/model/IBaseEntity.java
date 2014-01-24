/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author dell
 */
public interface IBaseEntity {

    @Id
    String getId();

    @Version
    @Column(name = "optlock")
    int getOptlock();

    @Column(name = "position")
    String getPosition();

    void setId(String id);

    void setOptlock(int optlock);

    void setPosition(String position);
    
}
