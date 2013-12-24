/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.eva.EvaluateItem;
import com.eu.evaluation.model.eva.NotNullEvaluateItem;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.NotNullEvaluateItemHistory;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.service.Evaluating;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 字段非空评测实现逻辑 注意，实现接口Evaluating时，声明为spring的托管bean
 *
 * @author dell
 */
@Component
public class NotNullEvaluate implements Evaluating<NotNullEvaluateItem , NotNullEvaluateItemHistory> {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;
    
    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, String instanceClass , int instanceType , String instanceID) throws Exception {
        String debugInfo = "开始执行实体字段非空评测，实体 = {0} ， 实体ID = {1} , 评测历史项目ID = {2}";
        logger.debug(MessageFormat.format(debugInfo , new Object[]{instanceClass , instanceID , evaluateItemHistoryID}));
        
        debugInfo = "实体{0}的字段非空评测：当前对象 = {1} ； 字段 = {2} ； 值 = {3}";
        
        String endDebugInfo = "完成实体唯一性评测，实体 = {0} ， 实体ID = {1} , 评测历史项目ID = {2}";
        
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);//查找评测项目
        
        Object obj = defaultDAO.findEvaluateData(instanceClass, instanceID , ev.getEvaluateVersion().getId());//获取被评测数据
        

        String[] names = ev.getFieldDictionary().getPropertyName().split("\\.");
        for (String propertyName : names) {
            String firstLetter = propertyName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + propertyName.substring(1);//得到get方法名称
            Method getMethod = obj.getClass().getMethod(getMethodName, new Class[]{});//得到get方法
            Object invokedValue = getMethod.invoke(obj, new Object[]{});//调用get方法，得到值
            
            //logger.debug(MessageFormat.format(debugInfo, new Object[]{instanceClass , obj.getClass().getName() , propertyName , invokedValue}));
            
            if (invokedValue == null) {//getMethod返回空值，则初始化这个字段
                logger.debug(MessageFormat.format(endDebugInfo , new Object[]{instanceClass , instanceID , evaluateItemHistoryID}));
                return false;
            }
            obj = invokedValue;//为obj赋新值
        }

        logger.debug(MessageFormat.format(endDebugInfo , new Object[]{instanceClass , instanceID , evaluateItemHistoryID}));
        return true;
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass , int instanceType , String instanceID) throws Exception {
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);
        
        String reason = "{0}的{1}属性，非空评测未通过！";

        reason = MessageFormat.format(reason, new Object[]{ev.getObjectDictionary().getDisplayname(), ev.getFieldDictionary().getDisplayname()});

        return new String(reason.getBytes() , "GBK");
    }

    /**
     * 得到类中的一个字段，即使是父类的字段也可获取
     *
     * @param aClass 需要获取字段的类
     * @param name 需要获取的字段名称
     * @throws java.lang.NoSuchFieldException
     * @return 获取的字段
     */
    private Field getFieldWithSupperClass(Class aClass, String name) throws NoSuchFieldException {
        Field field = null;
        try {
            field = aClass.getDeclaredField(name);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            if (aClass.getSuperclass() != null) {
                field = getFieldWithSupperClass(aClass.getSuperclass(), name);
            } else {
                throw ex;
            }
        }
        return field;
    }

    public void supplementHistory(NotNullEvaluateItem item, NotNullEvaluateItemHistory itemHistory) {
        //无额外信息
    }

}
