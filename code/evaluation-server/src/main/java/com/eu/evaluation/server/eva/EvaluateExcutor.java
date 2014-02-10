/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.eva;

import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.eva.ResultCollatorConfig;
import com.eu.evaluation.model.eva.history.EvaluateItemHistory;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.Result;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.ApplicationContext;
import com.eu.evaluation.server.service.DataService;
import com.eu.evaluation.server.service.EvaluateService;
import com.eu.evaluation.server.service.Evaluating;
import com.eu.evaluation.server.service.ResultCollator;
import com.eu.evaluation.server.service.SystemService;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 评测项目执行器，负责逐个执行评测项目
 *
 * @author dell
 */
@Component
public class EvaluateExcutor{

    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private DataService dataService;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private ApplicationContext applicationContext;

    
    /**
     * 执行评测
     * @param ev
     * @param systems
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception 
     */
    public void execute(EvaluateVersion ev) throws InstantiationException, IllegalAccessException, Exception{
        //验证当前状态，如果当前正在导入或评测中，则不处理
        if (applicationContext.isEvaluating() || applicationContext.isImporting()) {
            return;
        } else {
            applicationContext.setEvaluating(true);
        }
        
        logger.info("评测开始，本次评测版本：" + ev.getName());
        
        //复制评测项目到历史信息
        logger.info("开始评测项目初始化");
        initEvaluateItem(ev);
        logger.info("已完成评测项目初始化");
        
        logger.info("开始分地区进行数据评测");
        List<AccessSystem> systems = systemService.findByEvaluateVersion(ev.getId());
        for(AccessSystem system : systems){
            execute(ev , system);
        }
        logger.info("完成所有地区的数据评测");
        
        logger.info("开始评测结果整理");
        List<ResultCollatorConfig> resultCollatorConfigs = evaluateService.findAllResultCollatorConfig();
        for(ResultCollatorConfig config : resultCollatorConfigs){
            ResultCollator rc = ResultCollatorFactory.getResultCollator(config.getCollatorClass());
            rc.collate(ev);
        }
        logger.info("已完成整理评测结果，所有评测结束");
        
        applicationContext.setEvaluating(false);//评测完成，改变状态
    }
    
    public void executeInThread(final EvaluateVersion ev){
        Runnable runnable = new Runnable() {

            public void run() {
                try {
                    execute(ev);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(EvaluateExcutor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(EvaluateExcutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread thread = new Thread(runnable);
        thread.start();
    }
    
    /**
     * 评测执行主方法，调度评测项目执行过程（可考虑多线程）
     *
     * @param ev
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception
     */
    private void execute(EvaluateVersion ev , AccessSystem system) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        //复制被评测数据到历史表
        initData(ev , system);
        logger.info("已完成被评数据初始化，即将开始评测项目初始化");

        //初始化评测结果
        initResult(ev.getId() , system);
        logger.info("已完成评测结果初始化，即将开始执行评测逻辑");

        //执行评测逻辑
        execute(ev.getId() , system);
        logger.info("已完成全部评测逻辑，即将开始整理评测结果");
    }

    /**
     * 根据评测版本，将被评测数据复制到历史信息
     *
     * @param ev
     */
    private void initData(EvaluateVersion ev , AccessSystem system) {
        dataService.copyData(ev , system);
    }

    /**
     * 根据评测版本，将评测项目复制到评测项目历史信息
     *
     * @param ev
     */
    private void initEvaluateItem(EvaluateVersion ev) throws InstantiationException, IllegalAccessException {
        evaluateService.copyEvaluateItemToHistory(ev);
    }

    /**
     * 初始化评测结果 针对每种评测方式，查找需要评测的数据，将这些数据形成空白的评测结果
     *
     * @param evaluateVersionID
     */
    private void initResult(String evaluateVersionID , AccessSystem system) {
        logger.debug("开始初始化评测数据");
        EvaluateVersion ev = evaluateService.findEvaluateVersionByID(evaluateVersionID);
        //根据评测项目查找被评数据，生成空白评测结果
        List<EvaluateItemHistory> itemHistorys = evaluateService.findEvaluateItemHistoryByVersion(ev);
        for (EvaluateItemHistory item : itemHistorys) {//此处可考虑多线程调用
            //根据评测项目中记录的数据字典查找需要被评测的数据
            List evaluatedEntity = evaluateService.findEvaluatedEntity(item , system);
            for (Object obj : evaluatedEntity) {
                EvaluatedData entity = (EvaluatedData) obj;
                Result result = new Result();
                result.setEvaluateVersion(item.getEvaluateVersion());
                result.setInstanceId(entity.getPk().getId());
                result.setInstanceType(entity.getEntityType().getInstanceType());
                result.setInstanceClass(entity.getClass().getName());
                result.setItemHistory(item);
                result.setStatus(Result.STATUS_UNEVALUATE);//状态置为“未评测”
                result.setPosition(entity.getPosition());
                evaluateService.saveResult(result);
            }
        }
    }

    /**
     * 执行评测逻辑
     *
     * @param evaluateVersionID
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception
     */
    private void execute(String evaluateVersionID , AccessSystem system) throws InstantiationException, IllegalAccessException, Exception {
        EvaluateVersion ev = evaluateService.findEvaluateVersionByID(evaluateVersionID);
        
        //查找未执行的评测
        List<Result> unFinishedItem = evaluateService.findUnFinishedEvaluate(ev , system);

        //执行评测逻辑
        for (Result result : unFinishedItem) {//此处可考虑多线程调用
            execute(result , system);
        }

    }

    /**
     * 执行具体的评测
     *
     * @param result
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception
     */
    private void execute(Result result , AccessSystem system) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        //实例化评测逻辑实现类
        Evaluating evaluating = EvaluatingFactory.getEvaluating(result.getItemHistory().getEvaluateClass());

        //对每个数据执行评测逻辑
        boolean passed = evaluating.evaluate(result.getItemHistory().getId(), system , result.getInstanceClass() , result.getInstanceType() , result.getInstanceId());
        if (!passed) {
            String message = evaluating.notPassedReason(result.getItemHistory().getId(), result.getInstanceClass() , result.getInstanceType() , result.getInstanceId());
            result.setDescribetion(message);
        }
        result.setStatus(passed == true ? Result.STATUS_PASSED : Result.STATUS_FAILURE);
        result.setCompletedDate(Calendar.getInstance());
        evaluateService.saveResult(result);
    }

    

}
