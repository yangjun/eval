/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dell
 */
@Embeddable
public class EvaluatedDataPK implements Serializable{
    private static final long serialVersionUID = -4641396372978004222L;
    
    private String id;
    
    private String evaluateVersionId;
    
    private String position;

    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="evaluateVersion_id")
    public String getEvaluateVersionId() {
        return evaluateVersionId;
    }

    public void setEvaluateVersionId(String evaluateVersion) {
        this.evaluateVersionId = evaluateVersion;
    }

    @Column(name="position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return id + " ; " + evaluateVersionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }else if (obj == null){
            return false;
        }else if (getClass() != obj.getClass()){
            return false;
        }
        
        final EvaluatedDataPK other = (EvaluatedDataPK)obj;
        if (id == null){
            if (other.getId() != null){
                return false;
            }
        }else if (!id.equals(other.getId())){
            return false;
        }
        
        if (evaluateVersionId == null){
            if (other.getEvaluateVersionId() != null){
                return false;
            }
        }else if (!evaluateVersionId.equals(other.getEvaluateVersionId())){
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((evaluateVersionId == null) ? 0 : evaluateVersionId.hashCode());
        return result;
    }
    
    
}
