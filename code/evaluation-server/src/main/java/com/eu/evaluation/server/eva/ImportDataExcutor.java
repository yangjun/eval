/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.model.sys.ImportDataConfig;
import com.eu.evaluation.server.service.ImportDataActuator;
import com.eu.evaluation.server.service.ImportDataService;
import com.eu.evaluation.server.service.SystemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据导入执行器
 * @author dell
 */
@Component
public class ImportDataExcutor {
    
    @Autowired
    private ImportDataService importDataService;
    
    @Autowired
    private SystemService systemService;
    
    public void excute(String systemCode) throws ClassNotFoundException{
        AccessSystem accessSystem = systemService.findAccessSystemByCode(systemCode);
        List<ImportDataConfig> configs = importDataService.findAllImportDataConfig();
        for(ImportDataConfig config : configs){
            ImportDataActuator actuator = ImportDataActuatorFactory.getImportDataExcutor(config.getImportClass());
            actuator.importData(accessSystem, config);
        }
        
    }
}
