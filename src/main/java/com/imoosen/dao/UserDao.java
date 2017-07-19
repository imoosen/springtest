package com.imoosen.dao;


import com.imoosen.basedao.IDBGeneric;
import com.imoosen.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao{
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(User record)throws Exception;

    int insertSelective(User record)throws Exception;

    User selectByPrimaryKey(Integer id)throws Exception;

    List<User> selectAll(User record)throws Exception;

    int updateByPrimaryKeySelective(User record)throws Exception;

    int updateByPrimaryKey(User record)throws Exception;
}