/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.server.ApplicationContextUtils;
import com.eu.evaluation.server.service.ImportDataActuator;

/**
 *
 * @author dell
 */
public class ImportDataActuatorFactory {
    
    public static ImportDataActuator getImportDataExcutor(String excutorName){
        ImportDataActuator importData = (ImportDataActuator) ApplicationContextUtils.getBean(excutorName);
        return importData;
    }
}
