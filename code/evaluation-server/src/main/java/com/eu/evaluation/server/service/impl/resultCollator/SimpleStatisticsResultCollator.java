/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.resultCollator;

import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.SimpleStatistics;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.eva.history.ResultDAO;
import com.eu.evaluation.server.dao.eva.history.SimpleStatisticsDAO;
import com.eu.evaluation.server.dao.eva.history.UnPassedEvaluatedDataDAO;
import com.eu.evaluation.server.dao.sys.AccessSystemDAO;
import com.eu.evaluation.server.service.ResultCollator;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * 单维度、分资源类型、分地区局统计评测信息，计算资源总数、评测通过数，评测未通过数
 *
 * @author dell
 */
@Component
public class SimpleStatisticsResultCollator implements ResultCollator {

    @Autowired
    private DefaultDAO defaultDAO;

    @Autowired
    private SimpleStatisticsDAO simpleStatisticsDAO;

    @Autowired
    private AccessSystemDAO accessSystemDAO;
    
    @Autowired
    private UnPassedEvaluatedDataDAO unPassedEvaluatedDataDAO;
    
    @Autowired
    private ResultDAO resultDAO;

    public void collate(EvaluateVersion ev) {
        
        List<AccessSystem> systems = accessSystemDAO.findByEvaluateVersion(ev.getId());
        for (AccessSystem system : systems) {
            //按版本、系统删除SimpleStatistics
            simpleStatisticsDAO.delete(ev, system);
            
            //根据Result的结果，构建SimpleStatistics。主要是确定评测的资源类型、每种资源的评测类型
            List<SimpleStatistics> statisticses = resultDAO.groupToSimpleStatistics(ev, system);
            
            //统计每种资源、每种评测类型未通过的数量
            List<SimpleStatistics> list = unPassedEvaluatedDataDAO.countToSimpleStatistics(ev, system);
            
            //补充SimpleStatistics的其他字段，主要是总量、未通过量、已通过量
            for (SimpleStatistics simpleStatistics : statisticses) {
                //补充填写资源总量
                String jpql = "select count(*) from {0} t where t.evaluateVersion.id = :evid and t.position = :position";
                jpql = MessageFormat.format(jpql, new Object[]{simpleStatistics.getInstanceClass()});
                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("evid", ev.getId());
                params.addValue("position", system.getCode());
                Long total = defaultDAO.executeCount(jpql, params);
                simpleStatistics.setTotal(total);
                
                //补充填写评测未通过量
                for(SimpleStatistics ss : list){
                    if (ss.getInstanceType() == simpleStatistics.getInstanceType() && ss.getEvaluateTypeEnum() == simpleStatistics.getEvaluateTypeEnum()){
                        simpleStatistics.setFailCount(ss.getFailCount());
                        break;
                    }
                }   
                
                //补充填写评测已通过量
                simpleStatistics.setSuccessCount(simpleStatistics.getTotal() - simpleStatistics.getFailCount());
                
                //补充其他信息
                simpleStatistics.setEvaluateVersion(ev);
                simpleStatistics.setPosition(system.getCode());
            }
            
            this.simpleStatisticsDAO.save(statisticses);
            
            
            
        }

    }

}
