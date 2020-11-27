package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/12
 * @Time 10:31
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User getUser(User user) {
        String sql = "SELECT id,username,`password`,email FROM users " +
                " WHERE username=? AND `password`=? ";
        return getBean(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User checkUserName(String username) {
        String sql = "SELECT id,username,`password`,email FROM users " +
                "   WHERE username=?";
        return getBean(sql,username);
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users(username,`password`,email) VALUES(?,?,?)";
        update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }


}
