/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.dao;

import com.eu.evaluation.model.BaseEntity;
import com.eu.evaluation.model.PageData;
import com.eu.server.utils.CommonUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author dell
 * @param <T>
 */
public class AbstractDAO<T extends BaseEntity> {
    
    protected Log logger = LogFactory.getLog(getClass());
    
    private Class<T> entityClass;
    
    @PersistenceContext(unitName = "Unit_server")
    private EntityManager entityManager;
    
    private String JPQL_SELECT_ALL;
    private String JPQL_SELECT_COUNT;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] tArray = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T>) tArray[0];
            initJPQL();
        }
    }
    
    private void initJPQL() {
        JPQL_SELECT_ALL = "from " + getEntityClass().getName();
        JPQL_SELECT_COUNT = "select count(1) from " + getEntityClass().getName();
    }
    
    private Query initQueryParams(Query query, MapSqlParameterSource params) {
        if (params != null) {
            for (String paramsName : params.getValues().keySet()) {
                query.setParameter(paramsName, params.getValue(paramsName));
            }
        }
        return query;
    }
    
    /**
     * 创建一个query
     *
     * @param jpql
     * @param params
     * @return
     */
    protected Query createQuery(String jpql, MapSqlParameterSource params) {
        Query query = entityManager.createQuery(jpql);
        return initQueryParams(query, params);
    }

    protected Query createNativeQuery(String jpql, MapSqlParameterSource params) {
        Query query = entityManager.createNativeQuery(jpql);
        return initQueryParams(query, params);
    }

    protected Query createNativeQuery(String jpql, Class<?> classType, MapSqlParameterSource params) {
        Query query = entityManager.createNativeQuery(jpql, classType);
        return initQueryParams(query, params);
    }

    /**
     * 去掉jpql中的order By子句，用于分页查询
     *
     * @param jpql
     * @return
     */
    private static String removeOrders(String jpql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(jpql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 去除jpql的select 子句，未考虑union的情况,用于分页查询.
     *
     * @param jpql
     * @return
     */
    private static String removeSelect(String jpql) {
        int beginPos = jpql.toLowerCase().indexOf("from");
        return jpql.substring(beginPos);
    }

    public T get(String id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> query(String jpql) {
        return createQuery(jpql, null).getResultList();
    }

    /**
     * 根据jpql查询数据
     *
     * @param jpql 以参数名的方式声明的jpql
     * @param params 查询参数
     * @return
     */
    public List<T> query(String jpql, MapSqlParameterSource params) {
        return createQuery(jpql, params).getResultList();
    }

    public void update(String jpql, MapSqlParameterSource params) {
        createQuery(jpql, params).executeUpdate();
    }
    
    public void updateNative(String jpql, MapSqlParameterSource params) {
        logger.debug("执行原生sql：" + jpql);
        createNativeQuery(jpql, params).executeUpdate();
    }

    public List<T> queryByNativeSQL(String sql, MapSqlParameterSource params) {
        return createNativeQuery(sql, params).getResultList();
    }

    public List<T> queryByNativeSQL(String sql, Class<?> classType, MapSqlParameterSource params) {
        return createNativeQuery(sql, classType, params).getResultList();
    }

    /**
     * 分页查询
     *
     * @param jpql
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageData<T> query(String jpql, MapSqlParameterSource params, int pageNo, int pageSize) {
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(jpql));
        Query countQuery = createQuery(countQueryString, params);
        Long totalCount = (Long) countQuery.getSingleResult();
        if (totalCount < 1) {
            return new PageData<T>();
        }

        // 实际查询返回分页对象
        int startIndex = PageData.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(jpql, params);
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        List<T> rows = query.getResultList();

        return new PageData<T>(startIndex, totalCount, pageSize, rows);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return query(JPQL_SELECT_ALL);
    }

    public boolean isUnique(T entity, String... propertys) {
        if (propertys == null || propertys.length == 0) {
            return true;
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);
            Predicate predicate = null;
            for (String property : propertys) {
                if (predicate == null) {
                    predicate = cb.equal(root.get(property), PropertyUtils.getProperty(entity, property));
                } else {
                    predicate = cb.and(predicate, cb.equal(root.get(property), PropertyUtils.getProperty(entity, property)));
                }
            }

            if (!CommonUtils.isBlank(entity.getId())) {
                predicate = cb.and(predicate, cb.notEqual(root.get("id"), entity.getId()));
            }
            criteriaQuery.where(predicate);

            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            List<T> result = typedQuery.getResultList();
            return result.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            ReflectionUtils.handleReflectionException(e);
        }
        return false;
    }

    public int countAll() {
        Query query = entityManager.createQuery(JPQL_SELECT_COUNT);
        return (Integer) query.getSingleResult();
    }

    /**
     * 保存单个实体对象。
     * @param entity
     * @return 
     */
    public T save(T entity) {
        if (CommonUtils.isBlank(entity.getId())) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }
    
    public List<T> save(List<T> entitys){
        long start = System.currentTimeMillis();
        int i = 100;
        
        for(T en : entitys){
            en = save(en);
            if (i % 100 == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        long end = System.currentTimeMillis();
        long millisecond = end - start;
        logger.debug("批量保存" + entitys.size() + "条数据耗时：" + millisecond/1000/3600 + "小时 " + (millisecond/1000/60) + "分" + (millisecond / 1000 % 60) + "秒" + millisecond + "毫秒");
        return entitys;
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public void remove(String id) {
        remove(get(id));
    }
    
    public void remove(List<T> entitys){
        long start = System.currentTimeMillis();
        for(T en : entitys){
            remove(en);
        }
        long end = System.currentTimeMillis();
        logger.debug("List批量删除耗时：（" + (end - start)/1000/3600 + "小时 " + ((end - start)/1000/60) + "分" + ((end - start) / 1000 % 60) + "秒");
    }
    
    public int remove(String jpql, MapSqlParameterSource params){
        long start = System.currentTimeMillis();
        Query query = createQuery(jpql, params);
        int result = query.executeUpdate();
        long end = System.currentTimeMillis();
        logger.debug("JPQL批量删除耗时：（" + (end - start)/1000/3600 + "小时 " + ((end - start)/1000/60) + "分" + ((end - start) / 1000 % 60) + "秒");
        return result;
    }

//    public String toUTF8(String str) {
//        if (GlobalContext.getInstance().isConvertStringCode()) {
//            String rerult = "";
//            try {
//                rerult = new String(str.getBytes("ISO-8859-1"), "UTF-8");
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            return rerult;
//        } else {
//            return str;
//        }
//    }

    public String getCurrentTime() {
        String result = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        result = df.format(new Date()).toString();
        return result;
    }
}
