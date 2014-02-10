/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl.importdata;

import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.IPositionEntity;
import com.eu.evaluation.model.entity.ListResponse;
import com.eu.evaluation.model.sys.AccessSystem;
import com.eu.evaluation.model.sys.ImportDataConfig;
import com.eu.evaluation.server.dao.DefaultDAO;
import com.eu.evaluation.server.service.ImportDataActuator;
import java.net.URI;
import java.text.MessageFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 默认的数据导入执行器 适用于不分页的情况下，直接整表导入。且资源系统提供的数据封装为List
 *
 * @author dell
 */
@Component
public class DefaultImportDataActuator implements ImportDataActuator {

    protected Log logger = LogFactory.getLog(getClass());

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DefaultDAO defaultDAO;

    public <T extends ListResponse<V>, V extends IPositionEntity> void importData(AccessSystem accessSystem, ImportDataConfig config) throws ClassNotFoundException {
        EntityEnum entityEnum = EntityEnum.getByInstanceType(config.getInstanceType());

        if (entityEnum != null) {
            String message = "{0}从 {1} 导入数据 {2}";
            logger.info(MessageFormat.format(message, new Object[]{"开始", accessSystem.getName(), entityEnum.getName()}));

            URI uri = getUrl(accessSystem, config);

            Class<T> listClass = (Class<T>) config.getListResponse();

            try {
                T list = (T) restTemplate.getForObject(uri, listClass);
                if (list.getRows() != null) {
                    int deleteCount = defaultDAO.deleteData(config.getOrgInstanceClass(), accessSystem.getCode());
                    String deleteMessage = "已清除 {0} 下的 {1} 数据 {2} 条";
                    logger.debug(MessageFormat.format(deleteMessage, new Object[]{accessSystem.getName(), entityEnum.getName(), deleteCount}));

                    for (V v : list.getRows()) {
                        v.setPosition(accessSystem.getCode());
                    }
                    defaultDAO.save(list.getRows());
                    String saveMessage = "新增 {0} 下的 {1} 共 {2} 条";
                    logger.debug(MessageFormat.format(saveMessage, new Object[]{accessSystem.getName(), entityEnum.getName(), list.getRows().size()}));
                }
                
                logger.info(MessageFormat.format(message, new Object[]{"完成", accessSystem.getName(), entityEnum.getName()}));
            } catch (RestClientException e) {
                message = "无法从 {0} 导入资源类型为 {1} 的数据，因为发生了RestClientException错误";
                logger.error(MessageFormat.format(message, new Object[]{accessSystem.getName(), entityEnum.getName()}) , e);
            }

        } else {
            String message = "无法从 {0} 导入资源类型为 {1} 的数据，因为类型 {1} 未定义";
            logger.info(MessageFormat.format(message, new Object[]{accessSystem.getName(), entityEnum.getName()}));
        }
    }
    
    private URI getUrl(AccessSystem accessSystem, ImportDataConfig config){
        String webroot = accessSystem.getPath().endsWith("/") ? accessSystem.getPath().substring(0 , accessSystem.getPath().length() - 1) : accessSystem.getPath();
        String path = config.getUrl().startsWith("/") ? config.getUrl().substring(1 , config.getUrl().length()) : config.getUrl();
        
        return URI.create(webroot + "/" + path);
    }

}
