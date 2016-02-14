package com.ewing.core.jdbc;

import java.util.List;

import org.hibernate.Session;

import com.ewing.core.jdbc.util.PageBean;

public interface BaseDao {
    public void delete(Object entity);

    public void executeSql(String sql);

    public <T> List<T> find(String queryString, Class<T> entityClass);

    public <T> T findOne(Integer id, Class<T> entityClass);

    public <T> T findOne(String condition, Class<T> entityClass);
    
    public <T> List<T> findMuti(List<Integer> idList, Class<T> entityClass);

    public <T> PageBean<T> pageQuery(String condition, String orderBy, Integer pageSize,
            Integer page, Class<T> entityClass);

    public <T> PageBean<T> pageQuery(String sql, String condition, String orderBy,
            Integer pageSize, Integer page, Class<T> entityClass);

    public <T> PageBean<T> pageQuery(String condition, Integer pageSize, Integer page,
            Class<T> entityClass);

    public <T> T queryObject(String sql, Class<T> queryClass);

    public void save(Object entity);
    
    public void saveMuti(List<Object> entityList);

    public void update(Object entity);

    public Session getConnectionSession();

    public void releaseConnectionSession(Session session);

    public <T> List<T> noMappedObjectQuery(String sql);

}