/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import com.eu.evaluate.utils.StringUtils;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author dell
 */
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class BaseEntity implements IBaseEntity , Serializable{
    private static final long serialVersionUID = -540232181953635561L;
    
    private String id;
    
    private int optlock = 0;
    
    private String position;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Version
    @Column(name = "optlock")
    @Override
    public int getOptlock() {
        return optlock;
    }

    @Override
    public void setOptlock(int optlock) {
        this.optlock = optlock;
    }

    @Column(name="position")
    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }
    
    
    
    @PrePersist
    protected void initPK(){
        if (StringUtils.isBlank(getId())){
            setId(UUID.randomUUID().toString());
        }
    }
}
