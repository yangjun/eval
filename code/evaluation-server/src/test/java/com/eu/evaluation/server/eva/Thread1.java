/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author dell
 */
public class Thread1 implements Runnable{

    private String threadName;
    
    public Thread1(String name){
        this.threadName = name;
    }
    
    protected Log logger = LogFactory.getLog(getClass());
    public void run() {
        try {
            double t = Math.random();
            long sleep = (long) (t * 10000);
            Thread.sleep(sleep);
            logger.debug("这是线程 : " + threadName + " ，休眠了 " + sleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
