/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author dell
 */
@MappedSuperclass
public class EvaluatedData implements Serializable {

    private static final long serialVersionUID = 8711629870623528792L;
    private EvaluatedDataPK pk;
    private String id;
    private EvaluateVersion evaluateVersion;

    private int optlock;

    private EntityEnum entityType = EntityEnum.UNKNOWN;

    @Column(name = "id", insertable = false , updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @EmbeddedId
    public EvaluatedDataPK getPk() {
        return pk;
    }

    public void setPk(EvaluatedDataPK pk) {
        this.pk = pk;
    }

    @Transient
    public EntityEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityEnum entityType) {
        this.entityType = entityType;
    }

    @Version
    @Column(name = "optlock")
    public int getOptlock() {
        return optlock;
    }

    public void setOptlock(int optlock) {
        this.optlock = optlock;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "evaluateVersion_id", referencedColumnName = "id", insertable = false, updatable = false)
    public EvaluateVersion getEvaluateVersion() {
        return evaluateVersion;
    }

    public void setEvaluateVersion(EvaluateVersion evaluateVersion) {
        this.evaluateVersion = evaluateVersion;
    }

}
