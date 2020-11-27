package com.atguigu.dao;

import com.atguigu.bean.User;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/12
 * @Time 10:26
 */
public interface UserDao {

    /**
     * 通过用户名&密码查询用户信息【为登陆做准备】
     * @param user
     * @return
     */
    public User getUser(User user);

    /**
     * 检查用户名是否存在
     * sql: SELECT id,username,`password`,email FROM users WHERE username=?
     * @param username
     * @return
     */
    public User checkUserName(String username);


    /**
     * 新增user信息
     * @param user
     */
    public void saveUser(User user);


}
