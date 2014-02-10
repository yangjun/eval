/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.model.sys.ImportDataConfig;
import com.eu.evaluation.server.ApplicationContext;
import com.eu.evaluation.server.service.ImportDataActuator;
import com.eu.evaluation.server.service.ImportDataService;
import com.eu.evaluation.server.service.SystemService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据导入执行器
 *
 * @author dell
 */
@Component
public class ImportDataExcutor {

    @Autowired
    private ImportDataService importDataService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private ApplicationContext applicationContext;
    
    public void excuteInThread(){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    excute();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ImportDataExcutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void excute() throws ClassNotFoundException {
        //验证当前状态，如果当前正在导入或评测中，则不处理
        if (applicationContext.isEvaluating() || applicationContext.isImporting()) {
            return;
        } else {
            applicationContext.setImporting(true);
        }

        List<AccessSystem> systems = systemService.findAllVaildAccessSystem();
        for (AccessSystem accessSystem : systems) {
            try {
                excute(accessSystem);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ImportDataExcutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        applicationContext.setImporting(false);//数据导入完成，改变状态
    }

    private void excute(AccessSystem accessSystem) throws ClassNotFoundException {
        List<ImportDataConfig> configs = importDataService.findAllImportDataConfig();
        for (ImportDataConfig config : configs) {
            ImportDataActuator actuator = ImportDataActuatorFactory.getImportDataExcutor(config.getImportClass());
            actuator.importData(accessSystem, config);
        }
    }
}
