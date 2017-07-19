package com.imoosen.service.Impl;

import com.imoosen.model.User;
import com.imoosen.service.UserService;
import com.imoosen.util.junit.SpringTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by [mengsen] on 2017/7/18 0018.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: [mengsen] on 2017/7/18 0018.
 */
public class UserServiceImplTest extends SpringTest{

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void selectByPrimaryKey() throws Exception {

        User  u = new User();

       u =userService.selectByPrimaryKey(2);

        System.out.println(u.getName()+u.getAge());
    }

    @Test
    public void selectAll()throws Exception{
        User u =new User();
        u.setAge("21");
        List<User> users = userService.selectAll(u);
        for (User user : users) {
            System.out.println(user.getName()+"<<>>"+user.getAge());
        }
    }
}