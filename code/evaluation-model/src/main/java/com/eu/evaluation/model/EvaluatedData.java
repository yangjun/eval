/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model;

import com.eu.evaluation.anno.Dictinary;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
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
public class EvaluatedData implements IBaseEntity {

    private static final long serialVersionUID = 8711629870623528792L;
    private EvaluatedDataPK pk;
    private String id;
    private String position;
    private EvaluateVersion evaluateVersion;

    private int optlock = 0;

    private EntityEnum entityType = EntityEnum.UNKNOWN;

    @Column(name = "id", insertable = false , updatable = false)
    @Dictinary(displayname = "ID" , visible = false)
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
    @Dictinary(displayname = "optlock" , visible = false)
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

    @Column(name = "position", insertable = false , updatable = false)
    @Dictinary(displayname = "position" , visible = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    
}
