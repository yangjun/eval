/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.web.controller;

import com.eu.evaluate.utils.StringUtils;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.ResultService;
import com.eu.evaluation.web.controller.pojo.StatisticsVO;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ������Controller
 * @author dell
 */
@Controller
@RequestMapping("/rest/result")
public class ResultController {

    private final static String THE_LAST_EVALUATE_VERSION_ID = "TheLast";

    @Autowired
    private ResultService resultService;

    @Autowired
    private EvaluateService evaluateService;

    /**
     * 7.1.7	������Դ���������� ���ݰ汾�������֡���Դ���ͣ���ѯ����Դ�¸�ά�ȵ�����ͳ�ƽ��
     *
     * @param evaluateVersionID
     * @param position
     * @param instanceType
     * @return List<StatisticsVO>
     */
    @ResponseBody
    @RequestMapping(value = "/unilateral/{position}/{evaluateVersionID}/{instanceType}", method = RequestMethod.GET)
    public List<StatisticsVO> unilateral(
            @PathVariable("evaluateVersionID") String evaluateVersionID,
            @PathVariable("position") String position,
            @PathVariable("instanceType") int instanceType) {

        evaluateVersionID = THE_LAST_EVALUATE_VERSION_ID.equals(evaluateVersionID) ? findTheLastEvaluateVersionID() : evaluateVersionID;

        List<SimpleStatistics> list = resultService.findSimpleStatistics(evaluateVersionID, position, EntityEnum.getByInstanceType(instanceType));
        List<StatisticsVO> result = new ArrayList<StatisticsVO>();
        for (SimpleStatistics ss : list) {
            double persent = Double.valueOf(ss.getSuccessCount()) / Double.valueOf(ss.getTotal()) * 100;
            StatisticsVO vo = new StatisticsVO(ss.getEvaluateTypeEnum().getName(), Double.valueOf(StringUtils.formatDouble_2_floor(persent)));
            result.add(vo);
        }
        return result;
    }

    /**
     * 7.1.6	����ά����Դ���⡣ ���ݰ汾�������֡��������ͣ���ѯ�����������¸�����Դ������ͳ�ƽ��
     *
     * @param evaluateVersionID
     * @param position
     * @param evaluateType
     * @return List<StatisticsVO>
     */
    @ResponseBody
    @RequestMapping(value = "/keepEvaluateType/{position}/{evaluateVersionID}/{evaluateType}", method = RequestMethod.GET)
    public List<StatisticsVO> keepEvaluateType(
            @PathVariable("evaluateVersionID") String evaluateVersionID,
            @PathVariable("position") String position,
            @PathVariable("evaluateType") int evaluateType) {
        
        evaluateVersionID = THE_LAST_EVALUATE_VERSION_ID.equals(evaluateVersionID) ? findTheLastEvaluateVersionID() : evaluateVersionID;

        List<SimpleStatistics> list = resultService.findSimpleStatistics(evaluateVersionID, position, EvaluateTypeEnum.getByType(evaluateType));
        List<StatisticsVO> result = new ArrayList<StatisticsVO>();
        for (SimpleStatistics ss : list) {
            double persent = Double.valueOf(ss.getSuccessCount()) / Double.valueOf(ss.getTotal()) * 100;
            StatisticsVO vo = new StatisticsVO(ss.getEvaluateTypeEnum().getName(), Double.valueOf(StringUtils.formatDouble_2_floor(persent)));
            result.add(vo);
        }
        return result;
    }

    private String findTheLastEvaluateVersionID() {
        EvaluateVersion ev = evaluateService.findLastEvaluateVersion();
        if (ev == null) {
            throw new RuntimeException("����ϵͳ��δ���й��κ����⣬����������ٲ�ѯ��");
        } else {
            return ev.getId();
        }
    }
}
