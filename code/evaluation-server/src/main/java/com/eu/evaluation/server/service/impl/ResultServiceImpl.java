/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.server.dao.eva.history.SimpleStatisticsDAO;
import com.eu.evaluation.server.service.ResultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author dell
 */
@Component
public class ResultServiceImpl implements ResultService{
    
    @Autowired
    private SimpleStatisticsDAO simpleStatisticsDAO;
    
    public List<SimpleStatistics> findSimpleStatistics(String evaulateVersionID , String position , EntityEnum entityEnum){
        return simpleStatisticsDAO.find(evaulateVersionID, position, entityEnum);
    }

    public List<SimpleStatistics> findSimpleStatistics(String evaulateVersionID, String position, EvaluateTypeEnum evaluateTypeEnum) {
        return simpleStatisticsDAO.find(evaulateVersionID, position, evaluateTypeEnum);
    }
}
