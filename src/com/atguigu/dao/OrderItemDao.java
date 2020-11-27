package com.atguigu.dao;

import com.atguigu.bean.OrderItem;

public interface OrderItemDao {
    public void saveOrderItem(OrderItem oi);
    public void saveOrderItem(Object[][] params);
}
