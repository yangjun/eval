/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.eva.history.EvaluateVersion;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.dao.dictionary.FieldDictionaryDAO;
import com.eu.evaluation.server.dao.dictionary.ObjectDictionaryDAO;
import com.eu.evaluation.server.service.DataService;
import java.text.MessageFormat;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author dell
 */
@Component
public class DataServiceImpl implements DataService {
    
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ObjectDictionaryDAO objectDictionaryDAO;

    @Autowired
    private FieldDictionaryDAO fieldDictionaryDAO;

    @Autowired
    private DefaultDAO defaultDAO;

    public void copyData(EvaluateVersion ev , AccessSystem system) {
        List<ObjectDictionary> ods = objectDictionaryDAO.findAndOrder();

        String sqlTemp = "insert into {0} ( {1} ) select {2} , ''{3}'' from {4} o "
                + "where o.position = :position "
                + "and not exists (select 1 from {5} t where t.id = o.id and t.evaluateVersion_id = :evid and t.position = :position)";
        for (ObjectDictionary od : ods) {
            List<String> fds = fieldDictionaryDAO.findByObject(od.getId());
            String orgFieldStr = StringUtils.arrayToCommaDelimitedString(fds.toArray(new String[]{}));
            
            fds.add("evaluateVersion_id");
            String feildStr = StringUtils.arrayToCommaDelimitedString(fds.toArray(new String[]{}));
            
            String tableName = od.getTableName();
            String orgTableName = tableName + "_org";
            
            String sql = MessageFormat.format(sqlTemp, new Object[]{tableName, feildStr, orgFieldStr, ev.getId() , orgTableName, tableName});
            logger.debug("复制被评数据sql：" + sql);
            
            MapSqlParameterSource params = new MapSqlParameterSource("evid", ev.getId());
            params.addValue("position", system.getCode());
            defaultDAO.executeNativeInsert(sql, params);
        }
    }

}
