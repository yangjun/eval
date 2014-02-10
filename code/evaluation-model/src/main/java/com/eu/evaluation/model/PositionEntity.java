/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author dell
 */
@MappedSuperclass
public class PositionEntity extends BaseEntity implements IPositionEntity{
    private static final long serialVersionUID = -288917218649019012L;
    private String position;

    @Column(name="position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
