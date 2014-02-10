/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author dell
 */
public class ApplicationContextUtils {

    private static ApplicationContext applicationContext;

    private static Log logger = LogFactory.getLog(ApplicationContextUtils.class);

    public static void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ApplicationContextUtils.class) {
            logger.debug("setApplicationContext, notifyAll");
            ApplicationContextUtils.applicationContext = applicationContext;
            ApplicationContextUtils.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        synchronized (ApplicationContextUtils.class) {
            while (applicationContext == null) {
                try {
                    logger.debug("getApplicationContext, wait...");
                    ApplicationContextUtils.class.wait(60000);
                    if (applicationContext == null) {
                        logger.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
                    }
                } catch (InterruptedException ex) {
                    logger.debug("getApplicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
}
