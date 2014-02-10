/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.sys.ImportDataConfig;
import com.eu.evaluation.server.dao.sys.ImportDataConfigDAO;
import com.eu.evaluation.server.service.ImportDataService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ImportDataServiceImpl implements ImportDataService {

    protected Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private ImportDataConfigDAO importDataConfigDAO;

    public List<ImportDataConfig> findAllImportDataConfig() {
        return importDataConfigDAO.findAll();
    }
}
