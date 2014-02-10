/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.model.eva;

import com.eu.evaluation.model.eva.history.NotNullEvaluateItemHistory;
import com.eu.evaluation.model.eva.history.UniqueEvaluateItemHistory;
import com.eu.evaluation.model.eva.history.UpAndDownEvlauateItemHistory;

/**
 * 评测类型
 * @author dell
 */
public enum EvaluateTypeEnum {
    
    NOT_NULL("完整性评测" , 1 , NotNullEvaluateItem.class , NotNullEvaluateItemHistory.class) , 
    UNIQUE("唯一性评测" , 2 , UniqueEvaluateItem.class , UniqueEvaluateItemHistory.class),
    UP_AND_DOWN("上下级关系评测" , 3 , UpAndDownEvlauateItem.class , UpAndDownEvlauateItemHistory.class);
    
    private String name;
    
    private int type;
    
    private Class evaClass;
    
    private Class evaHistoryClass;
    
    private EvaluateTypeEnum(String name , int type , Class evaClass , Class evaHistoryClass){
        this.name = name;
        this.type = type;
        this.evaClass = evaClass;
        this.evaHistoryClass = evaHistoryClass;
    }
    
    public static String getName(int index) {  
        for (EvaluateTypeEnum c : EvaluateTypeEnum.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    
    public static EvaluateTypeEnum getByType(int type) {  
        for (EvaluateTypeEnum c : EvaluateTypeEnum.values()) {  
            if (c.getIndex() == type) {  
                return c;  
            }  
        }  
        return null;  
    }  
    
    public static Class getEvaHistoryClass(Class evaClass){
        for (EvaluateTypeEnum c : EvaluateTypeEnum.values()) {  
            if (c.getEvaClass()== evaClass) {  
                return c.evaHistoryClass; 
            }  
        }  
        throw new RuntimeException("评测项目：" + evaClass.getName() + "没定义对应的评测历史项目类！");
    }
    
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return type;  
    }  
    public void setIndex(int index) {  
        this.type = index;  
    }  

    public Class getEvaClass() {
        return evaClass;
    }

    public void setEvaClass(Class evaClass) {
        this.evaClass = evaClass;
    }

    public Class getEvaHistoryClass() {
        return evaHistoryClass;
    }

    public void setEvaHistoryClass(Class evaHistoryClass) {
        this.evaHistoryClass = evaHistoryClass;
    }
    
    
}
