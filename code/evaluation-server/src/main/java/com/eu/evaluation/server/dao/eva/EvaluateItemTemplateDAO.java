/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.dao.eva;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.EvaluateItemTemplate;
import com.eu.evaluation.model.eva.EvaluateTypeEnum;
import com.eu.evaluation.server.dao.AbstractDAO;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dell
 */
@Repository
public class EvaluateItemTemplateDAO extends AbstractDAO<EvaluateItemTemplate> {

    public EvaluateItemTemplate findTheMatching(EvaluateTypeEnum type, ObjectDictionary od, FieldDictionary fd) {
        String jpql = "select t from EvaluateItemTemplate t where t.evaluateTypeEnum = :evaluateTypeEnum ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("evaluateTypeEnum", type);
        List<EvaluateItemTemplate> result = this.query(jpql, params);
        if (result.isEmpty()) {
            throw new RuntimeException("未对评测类型：" + type.getName() + " 定义评测模板！");
        } else if (result.size() == 1) {
            return result.get(0);
        } else {
            jpql += "and t.objectDictionary.id = :odID ";
            params.addValue("odID", od.getId());
            result = this.query(jpql, params);
            if (result.size() == 1) {
                return result.get(0);
            } else {
                jpql += "and t.fieldDictionary.id = :fdID ";
                params.addValue("fdID", fd.getId());
                result = this.query(jpql, params);
                if (result.size() == 1) {
                    return result.get(0);
                } else if (result.isEmpty()) {
                    throw new RuntimeException("未对评测类型：" + type.getName() + " ，数据字典对象：" + od.getDisplayname() + "，数据字典属性：" + fd.getDisplayname() + "定义评测模板！");
                } else{
                    throw new RuntimeException("未对评测类型：" + type.getName() + " ，数据字典对象：" + od.getDisplayname() + "，数据字典属性：" + fd.getDisplayname() + "定义的评测模板有多个，应该只有唯一的一个！");
                }
            }
        }

    }
}
