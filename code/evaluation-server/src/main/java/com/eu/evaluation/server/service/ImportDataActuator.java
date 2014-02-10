/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.entity.ListResponse;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.model.sys.ImportDataConfig;

/**
 * 从资源系统接口导入被评原始数据
 * @author dell
 */
public interface ImportDataActuator {
    
    /**
     * 
     * @param <T>
     * @param <V>
     * @param accessSystem
     * @param entityEnum
     * @param config
     * @throws ClassNotFoundException 
     */
    public <T extends ListResponse<V>, V extends BaseEntity> void importData(AccessSystem accessSystem , ImportDataConfig config)  throws ClassNotFoundException;
}
