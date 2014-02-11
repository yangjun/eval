/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluate.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    
    /**
     * 格式化double
     * @param value 需要格式化的值
     * @param digits 需要保留的小数位数
     * @param mode 保留方式
     * @return 
     */
    public static String formatDouble(double value , int digits , RoundingMode mode){
        //定义格式化模式，保留2位小数
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(digits);
        formater.setGroupingSize(0);
        formater.setRoundingMode(mode);
        
        return formater.format(value);
    }
    
    /**
     * 保留2位小数，不进位
     * @param value
     * @return 
     */
    public static String formatDouble_2_floor(double value){
        return formatDouble(value , 2 , RoundingMode.FLOOR);
    }
}
