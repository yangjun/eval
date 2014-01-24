/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva;

import com.eu.evaluation.model.dictionary.ObjectDictionary;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 上下级关系评测
 * @author dell
 */
@Entity
@DiscriminatorValue(value = "UpAndDownEvlauateItem")
public class UpAndDownEvlauateItem extends EvaluateItem{
    private static final long serialVersionUID = 9131407011287293072L;
    
    public static final String MAP_KEY_UP_ENTITY = "MAP_KEY_UP_ENTITY";
    public static final String MAP_KEY_DOWN_ENTITY = "MAP_KEY_DOWN_ENTITY";
    
    private ObjectDictionary upEntity;
    
    private ObjectDictionary downEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="upEntity_id" , referencedColumnName = "id")
    public ObjectDictionary getUpEntity() {
        return upEntity;
    }

    public void setUpEntity(ObjectDictionary upEntity) {
        this.upEntity = upEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="downEntity_id" , referencedColumnName = "id")
    public ObjectDictionary getDownEntity() {
        return downEntity;
    }

    public void setDownEntity(ObjectDictionary downEntity) {
        this.downEntity = downEntity;
    }
    
    
}
