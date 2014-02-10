/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluate.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dell
 */
public class BeanUtils {

    public static Method getReadMethod(Class<?> cls, Field field) throws NoSuchMethodException{
        String firstPart = field.getName().substring(0 , 1).toUpperCase();
        String lastPart = field.getName().substring(1);
        String methodName = "";
        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            methodName = "Is" + firstPart + lastPart;
        } else {
            methodName = "get" + firstPart + lastPart;
        }
        
        Class<?> tmpClass = cls;
        while(true){
            try {
                return tmpClass.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException ex) {
                tmpClass = tmpClass.getSuperclass();
                if (tmpClass == null){
                    throw ex;
                }
            }
        }
    }
    
    public static Field[] getDeclaredFields(Class<?> cls , Class<?> endClass){
        List<Field> fieldList = new ArrayList<Field>();
        Class<?> tmpClass = cls;
        while(tmpClass != null){
            Field[] fields = tmpClass.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            if (tmpClass == endClass){
                break;
            }else{
                tmpClass = tmpClass.getSuperclass();
            }
        }
        return fieldList.toArray(new Field[]{});
    }
    
    public static boolean isSimpleTypeField(Field f){
        Class<?> type = f.getType();
        if (type == String.class){
            return true;
        }else if (boolean.class == type || Boolean.class == type){
            return true;
        }else if (Number.class.isAssignableFrom(type)){
            return true;
        }else return int.class == type || double.class == type || long.class == type || float.class == type;
    }
}
