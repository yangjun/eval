/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.resultCollator;

import com.eu.evaluate.utils.StringUtils;
import com.eu.evaluation.model.PageData;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.eva.result.Result;
import com.eu.evaluation.model.eva.result.UnPassedEvaluatedData;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.eva.history.ResultDAO;
import com.eu.evaluation.server.dao.eva.history.UnPassedEvaluatedDataDAO;
import com.eu.evaluation.server.dao.sys.AccessSystemDAO;
import com.eu.evaluation.server.service.ResultCollator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 整理未通过评测的数据
 * 同一条数据，同一类型的评测，只保留一条记录。
 * 例如：站点的名称和编码字段都进行了非空评测，在Result表中存在2条记录。
 * 如果名称或编码字段的任意一个非空评测未通过，整理后，UnPassedEvaluatedData表中只存在一条记录，记载了站点ID，版本，评测类型（非空评测），未通过原因（如名称为空）
 * 如果名称或编码两个字段的非空评测未通过，整理后，UnPassedEvaluatedData表中也只存在一条记录，记载了站点ID，版本，评测类型（非空评测），未通过原因（如名称为空，编码为空）
 * @author dell
 */
@Component
public class UnPassedResultCollator implements ResultCollator {

    @Autowired
    private AccessSystemDAO accessSystemDAO;

    @Autowired
    private ResultDAO resultDAO;
    
    @Autowired
    private UnPassedEvaluatedDataDAO unPassedEvaluatedDataDAO;

    public void collate(EvaluateVersion ev) {
        int pageSize = 100;
        
        List<AccessSystem> systems = accessSystemDAO.findByEvaluateVersion(ev.getId());
        for (AccessSystem system : systems) {
            unPassedEvaluatedDataDAO.deleteByVersionAndPosition(ev, system);//按版本和区域删除
            
            //分页查询未通过的评测，查询结果已经按实体ID，实体类型、评测类型排序
            int pageNo = 1;
            PageData<Result> pageData = resultDAO.findUnPassed(ev, system, pageNo, pageSize);
            UnPassedEvaluatedData last = null;
            while (pageNo <= pageData.getTotalPageCount()) {//循环处理每一页的数据
                last = saveToUnPassedEvaluatedData(pageData.getRows(), last);//注意last的处理
                pageData = resultDAO.findUnPassed(ev, system, ++pageNo, pageSize);
            }
        }
    }

    /**
     * 多条result数据整理到一条UnPassedEvaluatedData数据中
     * @param results Result数据，已经按实体ID，实体类型、评测类型排序。
     * @param last 由于在翻页的while循环中调用该方法，所以传入上次调用该方法后得到的最后一个UnPassedEvaluatedData数据，这个数据将会与本次调用该方法时results的第一条数据进行分析
     * @return 
     */
    private UnPassedEvaluatedData saveToUnPassedEvaluatedData(List<Result> results, UnPassedEvaluatedData last) {
        List<UnPassedEvaluatedData> unSavaList = new ArrayList<UnPassedEvaluatedData>();
        if (last != null){
            unSavaList.add(last);
        }else{
            last = new UnPassedEvaluatedData();
        }
        
        /*循环分析results中的每条数据，如果前后两条数据的实体ID，实体类型、评测类型不等，则new一个UnPassedEvaluatedData，并赋值给变量last
          将前后两条result数据的describetion组合到last中
        */
        for (Result r : results) {
            if (!r.getInstanceId().equals(last.getInstanceId()) || r.getInstanceType() != last.getInstanceType() || r.getItemHistory().getEvaluateTypeEnum() != last.getEvaluateTypeEnum()) {
                last = new UnPassedEvaluatedData();
                last.setInstanceId(r.getInstanceId());
                last.setInstanceType(r.getInstanceType());
                last.setInstanceClass(r.getInstanceClass());
                last.setEvaluateTypeEnum(r.getItemHistory().getEvaluateTypeEnum());
                last.setPosition(r.getPosition());
                last.setEvaluateVersion(r.getEvaluateVersion());
                unSavaList.add(last);
            }
            if (StringUtils.isBlank(last.getDesciption())) {//多条reulst数据的原因组合到一起，用if判断的目的是setDesciption中不出现多余的“；”
                last.setDesciption(r.getDescribetion());
            } else {
                last.setDesciption(last.getDesciption() + " ; " + r.getDescribetion());
            }
        }
        unPassedEvaluatedDataDAO.save(unSavaList);
        return last;
    }
}
