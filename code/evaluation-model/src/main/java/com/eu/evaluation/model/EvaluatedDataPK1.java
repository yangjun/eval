/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dell
 */
public class EvaluatedDataPK1 implements Serializable{
    private static final long serialVersionUID = -4641396372978004222L;
    
    private String id;
    
    private EvaluateVersion evaluateVersion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }

    @Override
    public String toString() {
        return id + " ; " + evaluateVersion.getId();
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
        
        final EvaluatedDataPK1 other = (EvaluatedDataPK1)obj;
        if (id == null){
            if (other.getId() != null){
                return false;
            }
        }else if (!id.equals(other.getId())){
            return false;
        }
        
        if (evaluateVersion == null){
            if (other.evaluateVersion.getId()!= null){
                return false;
            }
        }else if (!evaluateVersion.getId().equals(other.evaluateVersion.getId())){
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        result = PRIME * result + ((evaluateVersion.getId() == null) ? 0 : evaluateVersion.getId().hashCode());
        return result;
    }
    
    
}
