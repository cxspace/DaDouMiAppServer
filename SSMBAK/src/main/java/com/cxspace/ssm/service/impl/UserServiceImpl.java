package com.cxspace.ssm.service.impl;

import com.cxspace.ssm.dao.UserDao;
import com.cxspace.ssm.model.User;
import com.cxspace.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liujie on 2017/8/12.
 */


@Service("userServiceImpl")

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Integer register(User user) {
        return userDao.save(user);
    }

    public List<User> findUserByUserPhone(String phone) {
        return userDao.findUserByUserPhone(phone);
    }

    public List<User> login(String phone, String password) {
        return userDao.findUserByUserPhoneAndPassword(phone,password);
    }

    public Integer updateUserEmail(String email, String id) {
        return userDao.updateUserEmailByIdAndEmail(email,id);
    }

    public Integer updateUserPassword(String password, String id) {
        return userDao.updateUserPasswordByIdAndPassword(password,id);
    }

    public Integer updateUserImgsrc(String imgsrc, String id) {
        return userDao.updateUserImgsrcByUserIdAndImgsrc(imgsrc, id);
    }
}
