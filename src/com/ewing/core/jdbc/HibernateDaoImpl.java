package com.ewing.core.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ewing.core.jdbc.util.BeanListProcessHandler;
import com.ewing.core.jdbc.util.Count;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.utils.PageUtil;
import com.ewing.utils.SqlUtil;

@Repository("baseDao")
public class HibernateDaoImpl extends HibernateDaoSupport implements BaseDao {

    private void bulidEntityTime(Object entity, boolean isNew) {
        Class zzz = entity.getClass();
        Object oldentity = null;
        try {
            Field id = entity.getClass().getDeclaredField("id");
            id.setAccessible(true);
            oldentity = findOne(Integer.valueOf(id.get(entity).toString()), entity.getClass());
        } catch (Exception e1) {
            logger.warn("error find id in " + entity.getClass().toString());
        }
        try {
            Field createtime = entity.getClass().getDeclaredField("createTime");
            createtime.setAccessible(true);

            if (isNew) {
                if (createtime.get(entity) == null)
                    createtime
                            .set(entity, new java.sql.Timestamp((new java.util.Date()).getTime()));
            } else {
                if (oldentity != null) {
                    createtime = oldentity.getClass().getDeclaredField("createTime");
                    createtime.setAccessible(true);
                    createtime.set(entity, createtime.get(oldentity));
                }
            }
        } catch (Exception e) {
            logger.warn("error in bulid  createtime in " + entity.getClass().toString());
        }
        try {

            Field last_update = entity.getClass().getDeclaredField("lastUpdate");
            if (last_update != null) {
                last_update.setAccessible(true);
                last_update.set(entity, new java.sql.Timestamp((new java.util.Date()).getTime()));
            }
        } catch (Exception e) {
            logger.warn("error bulid  last_update in " + entity.getClass().toString());
        }
    }

    @Override
    public void delete(Object entity) {
        try {
            Method method = entity.getClass().getMethod("getId");
            Object primaryId = method.invoke(entity);
            if (primaryId == null)
                throw new DaoException("the primary key should not be null");
            Object object = findOne((Integer) primaryId, entity.getClass());
            getHibernateTemplate().delete(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void executeSql(String sql) {
        Session session = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            session = this.getSession();
            conn = session.connection();
            stmt = conn.createStatement();
            boolean ret = stmt.execute(sql);
            logger.info("executeSql sql:" + sql + "");
            logger.info("executeSql result:" + ret + "");
        } catch (Exception e) {
            logger.error("fail to execute sql:" + sql, e);
        } finally {
            this.releaseSession(session);
        }
    }

    @Override
    public <T> List<T> find(String condition, Class<T> entityClass) {
        try {
            getHibernateTemplate().setCacheQueries(true);
            List list = getHibernateTemplate().find(generateQuerySql(condition, entityClass));
            return list;
        } catch (DataAccessException e) {
            logger.error(e, e);
        }
        return null;
    }

    /**
     * @author Joeson
     */
    public <T> List<T> findMuti(List<Integer> idList, Class<T> entityClass) {
        if (CollectionUtils.isEmpty(idList) || null == entityClass) {
            return Collections.EMPTY_LIST;
        }

        List<T> list = find("id in (" + StringUtils.join(idList.toArray(), ",") + ")", entityClass);
        if (CollectionUtils.isNotEmpty(list))
            return list;
        return Collections.EMPTY_LIST;
    }

    @Override
    public <T> T findOne(Integer id, Class<T> entityClass) {
        List<T> list = find("id=" + id, entityClass);
        if (!list.isEmpty())
            return (T) list.get(0);
        return null;
    }

    @Override
    public <T> T findOne(String condition, Class<T> entityClass) {
        try {
            getHibernateTemplate().setCacheQueries(true);
            List<T> list = getHibernateTemplate().find(generateQuerySql(condition, entityClass));
            if (!list.isEmpty())
                return list.get(0);
        } catch (DataAccessException e) {
            logger.error(e, e);
        }
        return null;
    }

    /**
     * 根本查询条件创建SQL
     * 
     * @param condition
     * @param entityClass
     * @return
     */
    private <T> String generateQueryCountSql(String condition, Class<T> entityClass) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(entityClass.getName());
        if (!StringUtils.isEmpty(condition))
            sql.append(" where ").append(condition);
        return "select count(*) from ( " + sql.toString() + " )";
    }

    /**
     * 根本查询条件创建SQL
     * 
     * @param condition
     * @param entityClass
     * @return
     */
    private <T> String generateQuerySql(String condition, Class<T> entityClass) {
        return generateQuerySql(condition, "", entityClass);
    }

    /**
     * 根本查询条件创建SQL
     * 
     * @param condition
     * @param entityClass
     * @return
     */
    private <T> String generateQuerySql(String condition, String orderBy, Class<T> entityClass) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from ").append(entityClass.getName());
        orderBy = orderBy != null ? " " + orderBy : "";
        if (!StringUtils.isEmpty(condition)) {
            if (condition.trim().startsWith("select"))
                return condition + orderBy;
            if (condition.trim().startsWith("from"))
                return condition + orderBy;

            sql.append(" where 1=1 ");
            if (!(condition.trim().startsWith("and") || condition.trim().startsWith("AND"))) {
                sql.append(" and ");
            }
            sql.append(condition);
        }
        sql.append(orderBy);
        return sql.toString();
    }

    @Override
    public Session getConnectionSession() {
        return this.getSession();
    }
 
    @Override
    public <T> List<T> noMappedObjectQuery(String sql, Class<T> beanClass) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Session session = null;
        try {
            session = this.getSession();
            connection = session.connection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            BeanListProcessHandler beanListProcessHandler = new BeanListProcessHandler();
            return beanListProcessHandler.toBeanList(rs, beanClass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.releaseSession(session);
        }

    }
    
    @Override
    public <T> PageBean<T> noMappedObjectPageQuery(String sql, Class<T> beanClass, Integer page,
            Integer pageSize) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Session session = null;
        try {
            final Integer limit = PageUtil.getLimit(page, pageSize);
            final Integer start = PageUtil.getOffset(page, pageSize);
            String pageSql = sql + " limit " + start + "," + limit;
            session = this.getSession();
            connection = session.connection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(pageSql);
            BeanListProcessHandler beanListProcessHandler = new BeanListProcessHandler();
            List<T> list = beanListProcessHandler.toBeanList(rs, beanClass);
            Integer count = noMappedObjectCountQuery(sql);
            return new PageBean(page, count, pageSize, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.releaseSession(session);
        }

    }

    public Integer noMappedObjectCountQuery(String sql) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Session session = null;
        try {
            String coutSql = SqlUtil.generateCountSql(sql);
            session = this.getSession();
            connection = session.connection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(coutSql);
            BeanListProcessHandler beanListProcessHandler = new BeanListProcessHandler();
            List<Count> count = beanListProcessHandler.toBeanList(rs, Count.class);
            if (count == null || count.get(0) == null || count.get(0).getCount() == null)
                return 0;
            return count.get(0).getCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.releaseSession(session);
        }

    }

    @Override
    public <T> PageBean<T> pageQuery(final String condition, Integer pageSize, Integer page,
            Class<T> entityClass) {
        return pageQuery(condition, "", pageSize, page, entityClass);
    }

    @Override
    public <T> PageBean<T> pageQuery(final String condition, String orderBy, Integer pageSize,
            Integer page, Class<T> entityClass) {

        final Integer limit = PageUtil.getLimit(page, pageSize);
        final Integer start = PageUtil.getOffset(page, pageSize);

        final String _orderBy = orderBy;
        final Class<T> entity = entityClass;

        Object ps = super.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session s) throws HibernateException, SQLException {
                Query rowCountQuery = s.createQuery(generateQuerySql(condition, _orderBy, entity));
                int totalCount = rowCountQuery.list().size();
                Query pageQuery = s.createQuery(generateQuerySql(condition, _orderBy, entity))
                        .setMaxResults(limit).setFirstResult(start);
                PageBean ps = new PageBean(start, totalCount, limit, pageQuery.list());
                return ps;
            }
        });
        return (PageBean) ps;
    }

    @Override
    public <T> PageBean<T> pageQuery(String sql, String condition, String orderBy,
            Integer pageSize, Integer page, Class<T> entityClass) {
        if (!StringUtils.isEmpty(condition))
            sql += condition;
        if (!StringUtils.isEmpty(orderBy))
            sql += orderBy;
        return pageQuery(sql, pageSize, page, entityClass);
    }

    @Override
    public <T> T queryObject(String sql, Class<T> queryClass) {
        List<T> list = getHibernateTemplate().find(sql);
        if (!list.isEmpty()) {
            if (list.size() > 1)
                throw new RuntimeException("result more than one , is not legal.");
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public void releaseConnectionSession(Session session) {
        this.releaseSession(session);
    }

    @Override
    public void save(Object entity) {
        bulidEntityTime(entity, true);
        getHibernateTemplate().persist(entity);
    }
    
    @Override
    public <T> void saveMuti(List<T> entityList) {
        if(CollectionUtils.isEmpty(entityList)){
            return;
        }
        for(Object entity : entityList){
            bulidEntityTime(entity, true);
        }
        
        getHibernateTemplate().saveOrUpdateAll(entityList);
    }

    @Override
    public void update(Object entity) {
        bulidEntityTime(entity, false);
        getHibernateTemplate().update(getHibernateTemplate().merge(entity));
    }
    
    @Override
    public <T> void updateMuti(List<T> entityList) {
        if(CollectionUtils.isEmpty(entityList)){
            return;
        }
        for(Object entity : entityList){
            bulidEntityTime(entity, true);
        }
        
        getHibernateTemplate().saveOrUpdateAll(entityList);
    }

}
