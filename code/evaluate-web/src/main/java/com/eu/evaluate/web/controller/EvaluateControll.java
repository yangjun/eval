/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluate.web.controller;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.eva.EvaluateExcutor;
import com.eu.evaluation.server.eva.ImportDataExcutor;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.SystemService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dell
 */
@Controller
@RequestMapping("/rest/evaluate")
public class EvaluateControll {

    @Autowired
    private EvaluateService evaluateService;
    
    @Autowired
    private EvaluateExcutor evaluateExcutor;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private ImportDataExcutor importDataExcutor;

    /**
     * ��ѯ����汾
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/evaluateVersion", method = RequestMethod.GET)
    public List<EvaluateVersion> findEvaluateVersion(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "startDate", required = false) Calendar startDate,
            @RequestParam(value = "endDate", required = false) Calendar endDate) {
        EvaluateVersion ev = new EvaluateVersion();
        ev.setName(name);
        return evaluateService.findEvaluateVersion(ev, startDate, endDate);
    }
    
    /**
     * �½�һ������汾������ʼ�������н���ĵ�����
     */
    @ResponseBody
    @RequestMapping(value="/evaluate" , method = RequestMethod.POST)
    public void evaluate(){
        //�½�һ������汾
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EvaluateVersion ev = new EvaluateVersion();
        ev.setCreateDate(Calendar.getInstance());
        ev.setName("test��" + sf.format(ev.getCreateDate().getTime()));
        ev = evaluateService.saveEvaluateVersion(ev);
        
        //���ø�����汾�������н���ĵ�����
        List<AccessSystem> systems = systemService.findAllVaildAccessSystem();
        evaluateService.saveEvaluateSystem(ev.getId(), systems);
        
        //��ʼ����
        try {
            evaluateExcutor.execute(ev);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EvaluateControll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluateControll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * �������н���ĵ����ֵ�����
     */
    @ResponseBody
    @RequestMapping(value="/importData" , method = RequestMethod.POST)
    public void importData(){
        List<AccessSystem> systems = systemService.findAllVaildAccessSystem();
        for (AccessSystem accessSystem : systems) {
            try {
                importDataExcutor.excute(accessSystem.getCode());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EvaluateControll.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}