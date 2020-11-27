package com.atguigu.service;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/12
 * @Time 11:59
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public User checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }



}
