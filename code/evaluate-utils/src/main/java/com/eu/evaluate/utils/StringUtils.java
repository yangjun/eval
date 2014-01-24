/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluate.utils;

/**
 *
 * @author dell
 */
public class StringUtils {
    public static boolean isBlank(Object obj){
        if (obj == null){
            return true;
        }else if ("".equals(obj.toString())){
            return true;
        }else{
            return false;
        }
    }
    
    public static String lowerFirstChar(String str){
        String tmp = new String(str);
        String firstPart = tmp.substring(0 , 1);
        String lastPart = tmp.substring(1);
        return firstPart.toLowerCase() + lastPart;
    }
}
