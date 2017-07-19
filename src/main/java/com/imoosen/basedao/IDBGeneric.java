package com.imoosen.basedao;

import com.imoosen.baseentity.IEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public interface IDBGeneric {
    Object insert(String var1, IEntity var2) throws Exception;

    int update(String var1, IEntity var2) throws Exception;

    int update(String var1) throws Exception;

    int update(String var1, Map<String, ?> var2) throws Exception;

    int update(String var1, Object var2) throws Exception;

    int select(String var1, Map<String, ?> var2) throws Exception;

    int select2Int(String var1, IEntity var2) throws Exception;

    IEntity find_one(String var1, IEntity var2) throws Exception;

    IEntity find_one(String var1, Object var2) throws Exception;

    IEntity find_one(String var1, Map<String, ?> var2) throws Exception;

    List<?> findAll(String var1, IEntity var2) throws Exception;

    Map<String, Object> find(String var1, Map<String, ?> var2) throws Exception;

    List<?> findAll(String var1, Map<String, ?> var2) throws Exception;

    List<?> findAll(String var1, String var2) throws Exception;

    List<?> findAll(String var1) throws Exception;

    List<Map<?, ?>> findMapList(String var1, Map<String, String> var2) throws Exception;

    Object insert(String var1, Map<String, ?> var2) throws Exception;

    int delete(String var1, IEntity var2) throws Exception;

    int delete(String var1, Map<String, ?> var2) throws Exception;

    int delete(String var1, Object var2) throws Exception;
}