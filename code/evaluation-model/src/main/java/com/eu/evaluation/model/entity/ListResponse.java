/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.entity;

import com.eu.evaluation.model.BaseEntity;
import java.util.List;

/**
 *
 * @author dell
 */
public interface ListResponse<T extends BaseEntity> {
    
    public abstract List<T> getRows();
    
    public void setRows(List<T> areas);
}
