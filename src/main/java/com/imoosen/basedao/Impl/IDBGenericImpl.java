package com.imoosen.basedao.Impl;

import com.google.code.routing4db.proxy.RountingProxyFactory;
import com.google.code.routing4db.strategy.RoutingStrategy;
import com.imoosen.basedao.IDBGeneric;
import com.imoosen.baseentity.IEntity;
import com.imoosen.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class IDBGenericImpl<T> extends SimpleJdbcDaoSupport implements IDBGeneric, FactoryBean<T>, InitializingBean{


    private final Logger log = Logger.getLogger(IDBGenericImpl.class);
    @Autowired
    private SqlSession sqlSessionTemplate;

    @Autowired
    private RoutingStrategy routingStrategy;

    private Class targetInterface = IDBGeneric.class;

    private Object targetObject = this;

    public T getObject() throws Exception {
        return (T)RountingProxyFactory.proxy(this.targetObject, this.targetInterface, this.routingStrategy);
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setTargetInterface(Class<T> targetInterface) {
        this.targetInterface = targetInterface;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public void setRoutingStrategy(RoutingStrategy routingStrategy) {
        this.routingStrategy = routingStrategy;
    }

    @Resource(
            name = "routing4DBDataSource"
    )
    public void setDataSource1(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public void setSqlSessionTemplate(SqlSession sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Object insert(String sqlname, IEntity entity) throws Exception {
        if(!sqlname.startsWith("t_mail_")) {
            EntityUtils.reflect(entity);
        }

        return Integer.valueOf(this.sqlSessionTemplate.insert(sqlname, entity));
    }

    public int update(String sqlname, Object arg) throws Exception {
        return this.sqlSessionTemplate.update(sqlname, arg);
    }

    public int update(String sqlname, IEntity entity) throws Exception {
        if(!sqlname.startsWith("t_mail_")) {
            EntityUtils.reflect(entity);
        }

        return this.sqlSessionTemplate.update(sqlname, entity);
    }

    public int update(String sqlname) throws Exception {
        return this.sqlSessionTemplate.update(sqlname);
    }

    public int delete(String sqlname, IEntity entity) throws Exception {
        return this.sqlSessionTemplate.delete(sqlname, entity);
    }

    public int select(String sqlname, Map<String, ?> paramMap) throws Exception {
        return ((Integer)this.sqlSessionTemplate.selectOne(sqlname, paramMap)).intValue();
    }

    public int select2Int(String sqlname, IEntity entity) throws Exception {
        return ((Integer)this.sqlSessionTemplate.selectOne(sqlname, entity)).intValue();
    }

    public IEntity find_one(String sqlname, IEntity entity) throws Exception {
        return (IEntity)this.sqlSessionTemplate.selectOne(sqlname, entity);
    }

    public IEntity find_one(String sqlname, Object args) throws Exception {
        return (IEntity)this.sqlSessionTemplate.selectOne(sqlname, args);
    }

    public IEntity find_one(String sqlname, Map<String, ?> paramMap) throws Exception {
        return (IEntity)this.sqlSessionTemplate.selectOne(sqlname, paramMap);
    }

    public List<?> findAll(String sqlname, IEntity entity) throws Exception {
        return this.sqlSessionTemplate.selectList(sqlname, entity);
    }

    public Object insert(String sqlname, Map<String, ?> paramMap) throws Exception {
        return Integer.valueOf(this.sqlSessionTemplate.insert(sqlname, paramMap));
    }

    public int update(String sqlname, Map<String, ?> paramMap) throws Exception {
        return this.sqlSessionTemplate.update(sqlname, paramMap);
    }

    public int delete(String sqlname, Map<String, ?> paramMap) throws Exception {
        return this.sqlSessionTemplate.delete(sqlname, paramMap);
    }

    public Map<String, Object> find(String sqlname, Map<String, ?> paramMap) throws Exception {
        return (Map)this.sqlSessionTemplate.selectOne(sqlname, paramMap);
    }

    public List<?> findAll(String sqlname, Map<String, ?> paramMap) throws Exception {
        return this.sqlSessionTemplate.selectList(sqlname, paramMap);
    }

    public int insertList(String sqlname, List<?> list) throws Exception {
        return this.sqlSessionTemplate.insert(sqlname, list);
    }

    public List<?> findAll(String sqlname, String param) throws Exception {
        return this.sqlSessionTemplate.selectList(sqlname, param);
    }

    public List<?> findAll(String sqlname) throws Exception {
        return this.sqlSessionTemplate.selectList(sqlname);
    }

    public List<Map<?, ?>> findMapList(String sqlname, Map<String, String> paramMap) throws Exception {
        return this.sqlSessionTemplate.selectList(sqlname, paramMap);
    }

    public int delete(String sqlname, Object id) throws Exception {
        return this.sqlSessionTemplate.delete(sqlname, id);
    }

}
