/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva.history;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.sys.AccessSystem;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 被评地区局
 * @author dell
 */
@Entity
@Table(name="t_res_evaluate_system")
public class EvaluateSystem extends BaseEntity{
    private static final long serialVersionUID = -6151435521594995464L;
    private EvaluateVersion evaluateVersion;
    
    private AccessSystem accessSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluateVersion_id" , referencedColumnName = "id")
    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessSystem_id" , referencedColumnName = "id")
    public AccessSystem getAccessSystem() {
        return accessSystem;
    }

    public void setAccessSystem(AccessSystem accessSystem) {
        this.accessSystem = accessSystem;
    }
    
    
}
