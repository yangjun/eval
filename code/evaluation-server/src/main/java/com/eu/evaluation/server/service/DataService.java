/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.sys.AccessSystem;

/**
 *
 * @author dell
 */
public interface DataService {
    public void copyData(EvaluateVersion ev , AccessSystem system);
}
