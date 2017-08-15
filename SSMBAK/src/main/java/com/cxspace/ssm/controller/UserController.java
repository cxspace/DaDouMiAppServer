package com.cxspace.ssm.controller;

import com.cxspace.ssm.model.User;
import com.cxspace.ssm.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liujie on 2017/8/11.
 */

@RestController


public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册逻辑
     *
     * @param user
     * @param ucBuilder
     * @return
     */

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user, UriComponentsBuilder ucBuilder){

        System.out.println("注册用户信息手机号:"+user.getPhone() +"\n 密码："+ user.getPassword());

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        List<User> createUserOne = userService.findUserByUserPhone(user.getPhone());
        if (createUserOne.size() == 0)
        {

            user.setImgsrc("icon.png");
            userService.register(user);

        }else {
            User userError = new User();

            userError.setPhone("error");

            return new ResponseEntity<User>(userError,headers, HttpStatus.OK);
        }

        List<User> createUser = userService.findUserByUserPhone(user.getPhone());

        return new ResponseEntity<User>(createUser.get(0),headers, HttpStatus.OK);
    }

    /**
     * 用户登录逻辑
     * @param user
     * @param builder
     * @return
     */

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user, UriComponentsBuilder builder)
    {

        User returnUser = new User();

        List<User> userList = userService.login(user.getPhone(),user.getPassword());
        //查询有相应用户
        if (userList.size()>0)
        {
            returnUser = userList.get(0);
        }else {
            //登录失败
            returnUser.setPhone("error");
        }

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");


        return new ResponseEntity<User>(returnUser,headers,HttpStatus.OK);
    }

    /**
     * 判断用户是否重复
     *
     * @param user
     * @param ucBuilder
     * @return
     */

    @RequestMapping(value = "/is_duplicate",method = RequestMethod.POST)
    public ResponseEntity<User> isDuplicate(@RequestBody User user, UriComponentsBuilder ucBuilder)
    {

        List<User> existUser = userService.findUserByUserPhone(user.getPhone());


        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        User returnUser = new User();

        if (existUser.size()>0){
            returnUser.setPhone("error");
        }

        return new ResponseEntity<User>(returnUser,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/update_email",method = RequestMethod.POST)
    public ResponseEntity<User> updateEmail(@RequestBody User user , UriComponentsBuilder builder)
    {

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        User returnUser = new User();

        userService.updateUserEmail(user.getEmail(),user.getId());

        returnUser.setEmail("ok");

        return new ResponseEntity<User>(returnUser,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/update_password",method = RequestMethod.POST)
    public ResponseEntity<User> updatePassword(@RequestBody User user , UriComponentsBuilder builder)
    {
        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        User returnUser = new User();

        userService.updateUserPassword(user.getPassword(),user.getId());

        returnUser.setPassword("ok");

        return new ResponseEntity<User>(returnUser,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/update_imgsrc",method = RequestMethod.POST)
    public ResponseEntity<User> updateImgsrc(@RequestBody User user , UriComponentsBuilder builder)
    {
        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        User returnUser = new User();

        userService.updateUserImgsrc(user.getImgsrc(),user.getId());

        returnUser.setPassword("ok");

        return new ResponseEntity<User>(returnUser,headers,HttpStatus.OK);

    }


}
