/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 字段非空评测
 * @author dell
 */
@Entity
@DiscriminatorValue(value = "NotNullEvaluateItem")
public class NotNullEvaluateItem extends EvaluateItem{
    
}
