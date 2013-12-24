/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.server.service.EvaluateService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author dell
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:databaseContext-jpa-all.xml"})
public class EvaluateExcutorTest extends AbstractJUnit4SpringContextTests{
    
    @Autowired
    private EvaluateService evaluateService;
    
    @Autowired
    private EvaluateExcutor evaluateExcutor;
    
    public EvaluateExcutorTest() {
    }

    /**
     * Test of execute method, of class EvaluateExcutor.
     */
    @Test
    public void testExecute() throws Exception {
        //新建一个评测版本
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EvaluateVersion ev = new EvaluateVersion();
        ev.setName("test：" + sf.format(Calendar.getInstance().getTime()));
        ev = evaluateService.saveEvaluateVersion(ev);
        
        //评测当前版本
        evaluateExcutor.execute(ev);
    }
    
}
