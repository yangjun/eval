/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.sys.AccessSystem;
import java.util.List;

/**
 *
 * @author dell
 */
public interface SystemService {
    public List<AccessSystem> findAllAccessSystem();
    
    public List<AccessSystem> findByEvaluateVersion(String evid);
    
    public AccessSystem findAccessSystemByCode(String systemCode);
    
    public List<AccessSystem> findAllVaildAccessSystem();
}
