package com.imoosen.service;


import com.imoosen.model.User;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(User record)throws Exception;

    int insertSelective(User record)throws Exception;

    User selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(User record)throws Exception;

    List<User> selectAll(User record)throws Exception;

    int updateByPrimaryKey(User record)throws Exception;
}