/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.history;

import com.eu.evaluation.model.dictionary.ObjectDictionary;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author dell
 */
@Entity
@DiscriminatorValue(value = "UpAndDownEvlauateItemHistory")
public class UpAndDownEvlauateItemHistory extends EvaluateItemHistory{
    private static final long serialVersionUID = -2270355134324364765L;
    
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
