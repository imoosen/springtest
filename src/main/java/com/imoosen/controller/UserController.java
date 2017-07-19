package com.imoosen.controller;

import com.imoosen.model.User;
import com.imoosen.service.UserService;
import com.imoosen.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Controller
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "getNameById",method = RequestMethod.GET)
    public String getUserName(HttpServletRequest req, HttpServletResponse resp){

        log.info("sfsdfsdfdsfsdf");

        String id = req.getParameter("id");
        User u = new User();
        String json="";

        try {
            u= userService.selectByPrimaryKey(Integer.parseInt(id));
            json=JSONUtils.writeJson(u);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.GET)
    public String deleteById(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        int result;
        String json="";
        try {
            result= userService.deleteByPrimaryKey(Integer.parseInt(id));
            json=JSONUtils.writeJson(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;

    }


    @ResponseBody
    @RequestMapping(value = "updateByUser",method = RequestMethod.POST)
    public String updatebyUser(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        User u = new User();
        if(StringUtils.isNotBlank(id)){
            u.setId(Integer.parseInt(id));
        }
        if(StringUtils.isNotBlank(name)){
            u.setName(name);
        }
        if(StringUtils.isNotBlank(age)){
            u.setAge(age);
        }
        String json="";
        try {
            int result= userService.updateByPrimaryKeySelective(u);
            json=JSONUtils.writeJson(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "selectUserList",method = RequestMethod.POST)
    public String selectAll(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        User u = new User();
        if(StringUtils.isNotBlank(id)){
            u.setId(Integer.parseInt(id));
        }
        if(StringUtils.isNotBlank(name)){
            u.setName(name);
        }
        if(StringUtils.isNotBlank(age)){
            u.setAge(age);
        }
        String json="";
        try {
            List<User> userList= userService.selectAll(u);
            json=JSONUtils.writeJson(userList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
