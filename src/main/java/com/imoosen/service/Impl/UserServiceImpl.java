package com.imoosen.service.Impl;

import com.imoosen.dao.UserDao;
import com.imoosen.model.User;
import com.imoosen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    public int deleteByPrimaryKey(Integer id)throws Exception {
        return userDao.deleteByPrimaryKey(id);
    }

    public int insert(User record)throws Exception {
        return userDao.insert(record);
    }

    public int insertSelective(User record)throws Exception {
        return userDao.insertSelective(record);
    }

    public User selectByPrimaryKey(Integer id)throws Exception {
        return userDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record)throws Exception {
        return userDao.updateByPrimaryKeySelective(record);
    }

    public List<User> selectAll(User record) throws Exception {
        return userDao.selectAll(record);
    }

    public int updateByPrimaryKey(User record)throws Exception {
        return userDao.updateByPrimaryKey(record);
    }
}
