/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.server.eva.ImportDataExcutor;
import com.eu.evaluation.server.service.ImportDataService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author dell
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application.xml", "classpath:databaseContext-jpa-all.xml" , "classpath:spring-mvc.xml"})
public class ImportDataServiceImplTest /*extends AbstractJUnit4SpringContextTests*/{
    
    @Autowired
    private ImportDataExcutor importDataExcutor;
    
    public ImportDataServiceImplTest() {
    }
    
    @Test
    public void test(){
        
    }

    /**
     * Test of importData method, of class ImportDataServiceImpl.
     */
//    @Test
    public void testImportData() throws Exception {
        importDataExcutor.excute();
//        assertNotNull(logger);
    }
    
}
