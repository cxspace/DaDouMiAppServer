package com.cxspace.ssm.service;

import com.cxspace.ssm.model.User;

import java.util.List;

/**
 * Created by liujie on 2017/8/12.
 */
public interface UserService {

    public Integer register(User user);

    public List<User> findUserByUserPhone(String phone);

    public List<User> login(String phone , String password);

    public Integer updateUserEmail(String email ,String id);

    public Integer updateUserPassword(String password , String id);

    public Integer updateUserImgsrc(String imgsrc , String id);


}
