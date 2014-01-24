/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluate.web.controller;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.ResultService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
 *
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
     * 7.1.7	单项资源总体质量。 根据版本、地区局、资源类型，查询该资源下各维度的评测统计结果
     *
     * @param evaluateVersionID
     * @param position
     * @param instanceType
     * @return Map<评测维度名称 , 维度得分(满分100)>
     */
    @ResponseBody
    @RequestMapping(value = "/unilateral/{evaluateVersionID}/{position}/{instanceType}", method = RequestMethod.GET)
    public Map<String, String> unilateral(
            @PathVariable("evaluateVersionID") String evaluateVersionID,
            @PathVariable("position") String position,
            @PathVariable("instanceType") int instanceType) {

        //定义格式化模式，保留2位小数
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);

        evaluateVersionID = THE_LAST_EVALUATE_VERSION_ID.equals(evaluateVersionID) ? findTheLastEvaluateVersionID() : evaluateVersionID;

        Map<String, String> mav = new HashMap<String, String>();
        List<SimpleStatistics> list = resultService.findSimpleStatistics(evaluateVersionID, position, EntityEnum.getByInstanceType(instanceType));
        for (SimpleStatistics ss : list) {
            double persent = Double.valueOf(ss.getSuccessCount()) / Double.valueOf(ss.getTotal()) * 100;
            mav.put(ss.getEvaluateTypeEnum().getName(), formater.format(persent));
        }
        return mav;
    }

    /**
     * 7.1.6	地市维度资源评测。 根据版本、地区局、评测类型，查询该评测类型下各种资源的评测统计结果
     *
     * @param evaluateVersionID
     * @param position
     * @param evaluateType
     * @return Map<资源类型名称 , 得分(满分100)>
     */
    @ResponseBody
    @RequestMapping(value = "/keepEvaluateType/{evaluateVersionID}/{position}/{evaluateType}", method = RequestMethod.GET)
    public Map<String, String> keepEvaluateType(
            @PathVariable("evaluateVersionID") String evaluateVersionID,
            @PathVariable("position") String position,
            @PathVariable("evaluateType") int evaluateType) {

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);

        evaluateVersionID = THE_LAST_EVALUATE_VERSION_ID.equals(evaluateVersionID) ? findTheLastEvaluateVersionID() : evaluateVersionID;

        Map<String, String> mav = new HashMap<String, String>();
        List<SimpleStatistics> list = resultService.findSimpleStatistics(evaluateVersionID, position, EvaluateTypeEnum.getByType(evaluateType));
        for (SimpleStatistics ss : list) {
            double persent = Double.valueOf(ss.getSuccessCount()) / Double.valueOf(ss.getTotal()) * 100;
            mav.put(EntityEnum.getByInstanceType(ss.getInstanceType()).getName(), formater.format(persent));
        }
        return mav;
    }

    private String findTheLastEvaluateVersionID() {
        EvaluateVersion ev = evaluateService.findLastEvaluateVersion();
        if (ev == null) {
            throw new RuntimeException("评测系统从未进行过任何评测，请先评测后再查询！");
        } else {
            return ev.getId();
        }
    }
}
