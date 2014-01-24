/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.server.service.impl;

import com.eu.evaluate.utils.BeanUtils;
import com.eu.evaluation.anno.Dictinary;
import com.eu.evaluation.anno.ForeignKey;
import com.eu.evaluation.model.EntityEnum;
import com.eu.evaluation.model.EvaluatedData;
import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.model.dictionary.ObjectRelation;
import com.eu.evaluation.server.dao.dictionary.FieldDictionaryDAO;
import com.eu.evaluation.server.dao.dictionary.ObjectDictionaryDAO;
import com.eu.evaluation.server.dao.dictionary.ObjectRelationDAO;
import com.eu.evaluation.server.service.DictionaryService;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class DictionaryServiceImpl implements DictionaryService {

    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ObjectDictionaryDAO objectDictionaryDAO;

    @Autowired
    private FieldDictionaryDAO fieldDictionaryDAO;

    @Autowired
    private ObjectRelationDAO objectRelationDAO;

    public ObjectDictionary findObjectDictionary(String id) {
        return objectDictionaryDAO.get(id);
    }

    public FieldDictionary findFielddDictionary(String id) {
        return fieldDictionaryDAO.get(id);
    }

    public void initDirectionary() {
        //清理数据字典
        objectRelationDAO.removeAll();
        objectDictionaryDAO.invalidateAll();
        fieldDictionaryDAO.invalidateAll();

        int i = 0;
        for (EntityEnum ee : EntityEnum.values()) {
            if (ee.getEntityClass() == null){
                continue;
            }
            logger.debug("开始数据库字典处理，对象：" + ee.getName());
            Entity entity = ee.getEntityClass().getAnnotation(Entity.class);
            if (entity == null) {
                logger.warn("EntityEnum枚举中定义的实体类型 " + ee.getName() + " ，实体类 " + ee.getEntityClass().getName() + " 中未定义@Entity注解，无法初始化到数据库字典");
                continue;
            }
            Table table = ee.getEntityClass().getAnnotation(Table.class);
            String tableName = ee.getEntityClass().getSimpleName();
            if (table != null) {
                tableName = table.name();
            }
            ObjectDictionary od = objectDictionaryDAO.findByInstanceType(ee.getInstanceType());
            if (od == null) {
                od = new ObjectDictionary();
            }
            od.setDisplayname(ee.getName());
            od.setInstanceClass(ee.getEntityClass().getName());
            od.setInstanceType(ee.getInstanceType());
            od.setSerial(i++);
            od.setValid(true);
            od.setTableName(tableName);
            objectDictionaryDAO.save(od);
            initField(ee, od);
            logger.debug("完成数据库字典处理，对象：" + ee.getName());
        }
    }

    private void initField(EntityEnum ee, ObjectDictionary od) {
        Field[] fields = BeanUtils.getDeclaredFields(ee.getEntityClass() , EvaluatedData.class);
        List<FieldDictionary> fdList = new ArrayList<FieldDictionary>();
        List<ObjectRelation> foreignList = new ArrayList<ObjectRelation>();
        for (Field f : fields) {
            try {
                Method method = BeanUtils.getReadMethod(ee.getEntityClass(), f);
                Transient t = method.getAnnotation(Transient.class);
                if (t != null) {//非持久性字段不处理
                    logger.warn("字段跳过数据库字典处理，原因：\n对象 " + ee.getName() + " 的属性 " + f.getName() + " 定义了 Transient 注解");
                    continue;
                }
                Dictinary dictinary = method.getAnnotation(Dictinary.class);
                if (dictinary == null) {//未定义数据字典不处理
                    logger.warn("字段跳过数据库字典处理，原因：\n对象 " + ee.getName() + " 的属性 " + f.getName() + " 未定义 Dictinary 注解");
                    continue;
                }
                
                FieldDictionary fd = fieldDictionaryDAO.findByObjectAndProperty(od.getId(), f.getName());
                fd = fd == null ? new FieldDictionary() : fd;
                
                fd.setObjectDictionary(od);//所属对象
                fd.setPropertyName(f.getName());//属性名
                fd.setValid(true);//是否有效
                fd.setDisplayname(dictinary.displayname());//显示名
                fd.setVisible(dictinary.visible());//是否可见
                fd.setSimpleProperty(BeanUtils.isSimpleTypeField(f));//是否简单类型
                
                Column column = method.getAnnotation(Column.class);
                fd.setFieldName(column != null ? column.name() : fd.getPropertyName());//数据库字段名
                
                //处理外键关系
                if (dictinary.foreignKey() != null && dictinary.foreignKey().foreignClass() != Object.class) {
                    ForeignKey foreignKey = dictinary.foreignKey();
                    ObjectRelation or = new ObjectRelation();
                    or.setSelfClass(od.getInstanceClass());
                    or.setPropertyName(fd.getPropertyName());
                    or.setRelationClass(foreignKey.foreignClass().getName());
                    or.setSimpleProperty(fd.isSimpleProperty());
                    foreignList.add(or);
                }
                fdList.add(fd);
            } catch (NoSuchMethodException ex) {
                logger.warn("处理数据库字典失败，原因：\n对象 " + ee.getName() + " 的属性 " + f.getName() + " 无read方法 " + ex.getMessage());
                
            }

        }
        fieldDictionaryDAO.save(fdList);
        objectRelationDAO.save(foreignList);
    }

    public ObjectDictionary findObjectDictionaryByName(String displayname) {
        return objectDictionaryDAO.findByDisplayName(displayname);
    }

    public FieldDictionary findFielddDictionaryByName(String objectDictnaryName, String propertyName) {
        return fieldDictionaryDAO.findByObjectNameAndProperty(objectDictnaryName, propertyName);
    }

    public List<FieldDictionary> findFieldDictionaryByInstanceType(int instanceType) {
        return fieldDictionaryDAO.findByInstanceType(instanceType);
    }
}
