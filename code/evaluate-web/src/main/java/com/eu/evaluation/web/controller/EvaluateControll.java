/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.web.controller;


import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.ApplicationContext;
import com.eu.evaluation.server.eva.EvaluateExcutor;
import com.eu.evaluation.server.eva.ImportDataExcutor;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.SystemService;
import com.eu.evaluation.web.controller.pojo.EvaluateVersionVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ��������Controller
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

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * ��ѯ����汾
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/evaluateVersion", method = RequestMethod.GET)
    public List<EvaluateVersionVO> findEvaluateVersion(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "startDate", required = false) Calendar startDate,
            @RequestParam(value = "endDate", required = false) Calendar endDate) {
        EvaluateVersion ev = new EvaluateVersion();
        ev.setName(name);
        List<EvaluateVersion> versions = evaluateService.findEvaluateVersion(ev, startDate, endDate);
        
        List<EvaluateVersionVO> result = new ArrayList<EvaluateVersionVO>();
        for (EvaluateVersion evaluateVersion : versions) {
            EvaluateVersionVO vo = EvaluateVersionVO.cloneWith(evaluateVersion);
            result.add(vo);
        }
        return result;
    }

    /**
     * ��ѯ��ǰ�Ƿ���������
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/evaludateStatus", method = RequestMethod.GET)
    public boolean getEvaludateStatus() {
        return applicationContext.isEvaluating();
    }

    /**
     * ��ѯ��ǰ���ݵ����Ƿ����
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/importDataStatus", method = RequestMethod.GET)
    public boolean getImportDataStatus() {
        return applicationContext.isImporting();
    }

    /**
     * �½�һ������汾������ʼ�������н���ĵ�����
     */
    @ResponseBody
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public void evaluate() {
        //�½�һ������汾
        SimpleDateFormat sf = new SimpleDateFormat("yyyy �� MM �� dd �� , HH ʱ mm �� ss ��");
        EvaluateVersion ev = new EvaluateVersion();
        ev.setCreateDate(Calendar.getInstance());
        ev.setName(sf.format(ev.getCreateDate().getTime()));
        ev = evaluateService.saveEvaluateVersion(ev);

        //���ø�����汾�������н���ĵ�����
        List<AccessSystem> systems = systemService.findAllVaildAccessSystem();
        evaluateService.saveEvaluateSystem(ev.getId(), systems);

        //��ʼ����
        evaluateExcutor.executeInThread(ev);
    }

    /**
     * �������н���ĵ����ֵ�����
     */
    @ResponseBody
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    public void importData() {
        importDataExcutor.excuteInThread();
    }
}
