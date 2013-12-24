/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;

/**
 * 评测逻辑接口
 * @author dell
 */
public interface Evaluating<T extends EvaluateItem , V extends EvaluateItemHistory> {
    /**
     * 评测逻辑
     * @param evaluateItemHistoryID 评测项目历史记录ID
     * @param instanceClass 被评测数据Class
     * @param instanceType 被评测数据类型
     * @param instanceID 被评测数据ID
     * @return 评测是否通过
     * @throws Exception 
     */
    public boolean evaluate(String evaluateItemHistoryID , String instanceClass , int instanceType , String instanceID) throws Exception;
    
    /**
     * 评测不通过时组合描述信息
     * @param evaluateItemHistoryID 评测项目历史记录ID
     * @param instanceClass 被评测数据Class
     * @param instanceType 被评测数据类型
     * @param instanceID 被评测数据ID
     * @return 评测不通过的原因
     * @throws Exception 
     */
    public String notPassedReason(String evaluateItemHistoryID , String instanceClass , int instanceType , String instanceID) throws Exception;
    
    /**
     * 完善评测历史记录信息，将评测项目中的额外信息复制到评测历史项目中。
     * EvaluateItem子类的属性都属于额外信息
     * @param item 评测项目
     * @param itemHistory 评测历史记录
     */
    public void supplementHistory(T item , V itemHistory);
}
