/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.sys.AccessSystemDAO;
import com.eu.evaluation.server.service.SystemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class SystemServiceImpl implements SystemService{

    @Autowired
    private AccessSystemDAO accessSystemDAO;
    
    public List<AccessSystem> findAllAccessSystem() {
        return accessSystemDAO.findAll();
    }

    public List<AccessSystem> findByEvaluateVersion(String evid) {
        return accessSystemDAO.findByEvaluateVersion(evid);
    }

    public AccessSystem findAccessSystemByCode(String systemCode) {
        return accessSystemDAO.findByCode(systemCode);
    }

    public List<AccessSystem> findAllVaildAccessSystem() {
        return accessSystemDAO.findVaildAccessSystem();
    }
    
}
