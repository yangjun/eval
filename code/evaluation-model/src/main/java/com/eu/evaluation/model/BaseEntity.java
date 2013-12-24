/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import com.eu.server.utils.CommonUtils;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

/**
 *
 * @author dell
 */
@MappedSuperclass
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -540232181953635561L;
    
    
    private String id;
    
    private int optlock = 0;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Version
    @Column(name = "optlock")
    public int getOptlock() {
        return optlock;
    }

    public void setOptlock(int optlock) {
        this.optlock = optlock;
    }
    
    @PrePersist
    protected void initPK(){
        if (CommonUtils.isBlank(getId())){
            setId(UUID.randomUUID().toString());
        }
    }
}
