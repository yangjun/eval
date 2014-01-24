/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.eva;

import com.eu.evaluate.utils.StringUtils;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.NameEvaluatedData;
import com.eu.evaluation.model.eva.NotNullEvaluateItem;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.NotNullEvaluateItemHistory;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.history.EvaluateItemHistoryDAO;
import com.eu.evaluation.server.service.Evaluating;
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
public class NotNullEvaluate implements Evaluating<NotNullEvaluateItem, NotNullEvaluateItemHistory> {

    protected Log logger = LogFactory.getLog(this.getClass());

    private String notPassMessage = "";

    @Autowired
    private EvaluateItemHistoryDAO evaluateItemHistoryDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    public boolean evaluate(String evaluateItemHistoryID, AccessSystem accessSystem, String instanceClass, int instanceType, String instanceID) throws Exception {

        String debugInfo = "{0} 执行实体字段非空评测，所属地区局 = {1} , 被评测对象类型 = {2} , 被评测字段 = {3} , 被评测对象实体ID = {4}";
        EvaluateItemHistory ev = evaluateItemHistoryDAO.get(evaluateItemHistoryID);//查找评测项目
        logger.debug(MessageFormat.format(debugInfo, new Object[]{"开始", accessSystem.getName(), ev.getObjectDictionary().getDisplayname(), ev.getFieldDictionary().getDisplayname(), instanceID}));

        Object entity = defaultDAO.findEvaluateData(instanceClass, instanceID, ev.getEvaluateVersion().getId(), accessSystem);//获取被评测数据
        if (entity == null) {
            notPassMessage = "{0} 的 {1} 非空评测不通过，因为在地区局 {2} 中不存在版本为 {3} , ID为 {4} 的 {5}";
            notPassMessage = MessageFormat.format(notPassMessage, new Object[]{ev.getObjectDictionary().getDisplayname(),
                ev.getFieldDictionary().getDisplayname(),
                accessSystem.getName(),
                ev.getEvaluateVersion().getName(),
                instanceID,
                ev.getObjectDictionary().getDisplayname()});
            return false;
        }

        //通过反射验证属性值是否为空
        Object obj = entity;
        String[] names = ev.getFieldDictionary().getPropertyName().split("\\.");
        for (String propertyName : names) {
            String firstLetter = propertyName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + propertyName.substring(1);//得到get方法名称
            Method getMethod = obj.getClass().getMethod(getMethodName, new Class[]{});//得到get方法
            Object invokedValue = getMethod.invoke(obj, new Object[]{});//调用get方法，得到值

            if (StringUtils.isBlank(invokedValue)) {//getMethod返回空值，评测不通过
                logger.debug(MessageFormat.format(debugInfo, new Object[]{"完成", accessSystem.getName(), ev.getObjectDictionary().getDisplayname(), ev.getFieldDictionary().getDisplayname(), instanceID}));
                notPassMessage = getErrorMsg(ev, entity , accessSystem);
                return false;
            }
            obj = invokedValue;//为obj赋新值
        }

        logger.debug(MessageFormat.format(debugInfo, new Object[]{"完成", accessSystem.getName(), ev.getObjectDictionary().getDisplayname(), ev.getFieldDictionary().getDisplayname(), instanceID}));
        return true;
    }

    public String notPassedReason(String evaluateItemHistoryID, String instanceClass, int instanceType, String instanceID) throws Exception {
        return notPassMessage;
    }

    public void supplementHistory(NotNullEvaluateItem item, NotNullEvaluateItemHistory itemHistory) {
        //无额外信息
    }

    private String getErrorMsg(EvaluateItemHistory ev, Object entity, AccessSystem accessSystem) {
        notPassMessage = "{0} 的 {1} “{2}” 的 “{3}” 属性，非空评测未通过";
        String name = ((EvaluatedData) entity).getId();
        if (entity instanceof NameEvaluatedData) {
            name = ((NameEvaluatedData) entity).getName();
        }
        return MessageFormat.format(notPassMessage, new Object[]{accessSystem.getName(),
            ev.getObjectDictionary().getDisplayname(),
            name,
            ev.getFieldDictionary().getDisplayname()});
    }
}
