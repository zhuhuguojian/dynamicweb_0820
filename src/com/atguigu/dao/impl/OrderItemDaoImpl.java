package com.atguigu.dao.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem oi) {
        String sql = "INSERT INTO order_items(`count`,amount,title,author,price,img_path,order_id) VALUES(?,?,?,?,?,?,?)";
        update(sql,oi.getCount(),oi.getAmount(),oi.getTitle(),oi.getAuthor(),oi.getPrice(),oi.getImgPath(),oi.getOrderId());
    }

    @Override
    public void saveOrderItem(Object[][] params) {
        String sql = "INSERT INTO order_items(`count`,amount,title,author,price,img_path,order_id) VALUES(?,?,?,?,?,?,?)";
        updateBatch(sql,params);
    }
}
