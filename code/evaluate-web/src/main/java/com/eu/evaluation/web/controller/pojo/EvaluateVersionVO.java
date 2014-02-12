/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.web.controller.pojo;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 为了适用前台的格式要求，增加tokens字段
 * @author dell
 */
public class EvaluateVersionVO extends EvaluateVersion{
    private static final long serialVersionUID = 2497142288954677271L;
    
    private String[] tokens;
    
     private String value;
    public String[] getTokens(){
        return tokens;
    }
    
    public void setTokens(String[] tokens){
        this.tokens = tokens;
    }
    
    public static EvaluateVersionVO cloneWith(EvaluateVersion ev){
        EvaluateVersionVO vo = new EvaluateVersionVO();
        try {
            BeanUtils.copyProperties(vo, ev);
            vo.setTokens(new String[]{ev.getName()});
            vo.setValue(ev.getName());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EvaluateVersionVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EvaluateVersionVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vo;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
