/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.SystemService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:databaseContext-jpa-all.xml" , "classpath:spring-mvc.xml"})
public class EvaluateExcutorTest extends AbstractJUnit4SpringContextTests{
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private EvaluateService evaluateService;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private EvaluateExcutor evaluateExcutor;
    
    public EvaluateExcutorTest() {
    }

    /**
     * Test of execute method, of class EvaluateExcutor.
     */
//    @Test
    public void testExecute() throws Exception {
        //新建一个评测版本
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EvaluateVersion ev = new EvaluateVersion();
        ev.setName("test：" + sf.format(Calendar.getInstance().getTime()));
        ev = evaluateService.saveEvaluateVersion(ev);
        List<AccessSystem> systems = systemService.findAllAccessSystem();
        evaluateService.saveEvaluateSystem(ev.getId(), systems);
        
        //评测当前版本
        evaluateExcutor.execute(ev);
    }
    
    @Test
    public void test(){
        double d = 0.987;
        logger.info("String.format = " + String.format("%.2f", d));
        DecimalFormat df = new DecimalFormat("*.00");
        logger.info("DecimalFormat = " + df.format(d));
        
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        logger.info("NumberFormat = " + nf.format(d));
        
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        logger.info("DecimalFormat = " +(formater.format(d)));
    }
    
}
