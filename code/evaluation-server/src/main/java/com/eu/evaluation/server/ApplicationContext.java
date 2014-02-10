/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ApplicationContext {
    private boolean evaluating = false;//是否正在评测
    
    private boolean importing = false;//是否正在导入数据

    public boolean isEvaluating() {
        return evaluating;
    }

    public void setEvaluating(boolean evaluating) {
        this.evaluating = evaluating;
    }

    public boolean isImporting() {
        return importing;
    }

    public void setImporting(boolean importing) {
        this.importing = importing;
    }
    
    
}
