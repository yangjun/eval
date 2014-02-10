/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.anno;

/**
 *
 * @author dell
 */
public @interface EntityDefine {

    public boolean visible() default true;

    public boolean valid() default true;
}
