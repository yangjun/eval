/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.eva.ResultCollatorConfig;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.Result;
import com.eu.evaluation.model.sys.AccessSystem;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author dell
 */
public interface EvaluateService {
    
    /**
     * 复制评测项目到历史记录
     * @param ev 
     */
    public void copyEvaluateItemToHistory(EvaluateVersion ev)  throws InstantiationException , IllegalAccessException;
    
    /**
     * 查找未完成的评测项目
     * @param ev
     * @return 
     */
    public List<Result> findUnFinishedEvaluate(EvaluateVersion ev , AccessSystem system);
    
    /**
     * 根据评测项目中定义的数据字典查询被评测数据
     * @param <T>
     * @param itemHistory
     * @return 
     */
    public <T> List<T> findEvaluatedEntity(EvaluateItemHistory itemHistory , AccessSystem system);
    
    /**
     * 保存评测结果
     * @param result
     * @return 
     */
    public Result saveResult(Result result);
    
    /**
     * 根据版本查找评测项目
     * @param ev
     * @return 
     */
    public List<EvaluateItemHistory> findEvaluateItemHistoryByVersion(EvaluateVersion ev);
    
    
    /**
     * 根据ID查询版本
     * @param id
     * @return 
     */
    public EvaluateVersion findEvaluateVersionByID(String id);
    
    /**
     * 查找EvaluateVersion
     * @param ev
     * @return 
     */
    public List<EvaluateVersion> findEvaluateVersion(EvaluateVersion ev, Calendar startDate , Calendar endDate);
    
    /**
     * 保存版本
     * @param ev
     * @return 
     */
    public EvaluateVersion saveEvaluateVersion(EvaluateVersion ev);
    
    /**
     * 查询所有评测结果整理器
     * @return 
     */
    public List<ResultCollatorConfig> findAllResultCollatorConfig();
    
    public void saveEvaluateSystem(String evid , List<AccessSystem> accessSystems);
    
    /**
     * 查找最新的评测版本
     * @return 
     */
    public EvaluateVersion findLastEvaluateVersion();
}
