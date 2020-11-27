package com.atguigu.dao.impl;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

public class OrderImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders(id,order_time,total_count,total_amount,state,user_id) VALUES(?,?,?,?,?,?)";
        update(sql,order.getId(),order.getOrderdate(),order.getTotalCount(),order.getTotalAmount(),order.getState(),order.getUserId());
    }
}
