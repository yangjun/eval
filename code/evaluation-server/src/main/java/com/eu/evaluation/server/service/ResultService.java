/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import java.util.List;

/**
 *
 * @author dell
 */
public interface ResultService {
    
    /**
     * 根据版本、所属地区局、资源类型查询统计结果
     * @param evaulateVersionID
     * @param position
     * @param entityEnum
     * @return 
     */
    public List<SimpleStatistics> findSimpleStatistics(String evaulateVersionID , String position , EntityEnum entityEnum);
    
    /**
     * 根据版本、所属地区局、评测类型查询统计结果
     * @param evaulateVersionID
     * @param position
     * @param entityEnum
     * @return 
     */
    public List<SimpleStatistics> findSimpleStatistics(String evaulateVersionID , String position , EvaluateTypeEnum evaluateTypeEnum);
}
