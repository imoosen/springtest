package com.imoosen.dao.Impl;

import com.imoosen.basedao.IDBGeneric;
import com.imoosen.basedao.Impl.IDBGenericImpl;
import com.imoosen.dao.UserDao;
import com.imoosen.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Repository("userDao")
public class UserDaoImpl extends IDBGenericImpl implements UserDao {

    public int deleteByPrimaryKey(Integer id)throws Exception {
        return delete("com.imoosen.dao.UserDao.deleteByPrimaryKey",id);
    }

    public int insert(User record)throws Exception {
       Object temp =insert("com.imoosen.dao.UserDao.insert",record);
        return (Integer) temp;
    }

    public int insertSelective(User record)throws Exception {
        Object temp =insert("com.imoosen.dao.UserDao.insertSelective",record);
        return (Integer) temp;
    }

    public User selectByPrimaryKey(Integer id)throws Exception {
        return (User) find_one("com.imoosen.dao.UserDao.selectByPrimaryKey",id);
    }

    public List<User> selectAll(User record) throws Exception {
        return findAll("com.imoosen.dao.UserDao.selectAll",record);
    }

    public int updateByPrimaryKeySelective(User record)throws Exception {
        return update("com.imoosen.dao.UserDao.updateByPrimaryKeySelective",record);
    }

    public int updateByPrimaryKey(User record)throws Exception {
        return update("com.imoosen.dao.UserDao.updateByPrimaryKey",record);
    }
}
