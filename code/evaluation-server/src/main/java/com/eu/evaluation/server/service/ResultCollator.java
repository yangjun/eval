/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.eva.history.EvaluateVersion;

/**
 *
 * @author dell
 */
public interface ResultCollator {
    public void collate(EvaluateVersion ev);
}
