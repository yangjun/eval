/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.exception;

/**
 *
 * @author dell
 */
public class NotUniqueException extends RuntimeException{
    private String errorMsg = "{0}对象的属性{1}不唯一！";

    public NotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
