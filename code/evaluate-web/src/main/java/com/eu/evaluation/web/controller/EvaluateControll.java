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
 * 数据评测Controller
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
     * 查询评测版本
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
     * 查询当前是否在评测中
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/evaludateStatus", method = RequestMethod.GET)
    public boolean getEvaludateStatus() {
        return applicationContext.isEvaluating();
    }

    /**
     * 查询当前数据导入是否完成
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/importDataStatus", method = RequestMethod.GET)
    public boolean getImportDataStatus() {
        return applicationContext.isImporting();
    }

    /**
     * 新建一个评测版本，并开始评测所有接入的地区局
     */
    @ResponseBody
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public void evaluate() {
        //新建一个评测版本
        SimpleDateFormat sf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 , HH 时 mm 分 ss 秒");
        EvaluateVersion ev = new EvaluateVersion();
        ev.setCreateDate(Calendar.getInstance());
        ev.setName(sf.format(ev.getCreateDate().getTime()));
        ev = evaluateService.saveEvaluateVersion(ev);

        //设置该评测版本评测所有接入的地区局
        List<AccessSystem> systems = systemService.findAllVaildAccessSystem();
        evaluateService.saveEvaluateSystem(ev.getId(), systems);

        //开始评测
        evaluateExcutor.executeInThread(ev);
    }

    /**
     * 导入所有接入的地区局的数据
     */
    @ResponseBody
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    public void importData() {
        importDataExcutor.excuteInThread();
    }
}
