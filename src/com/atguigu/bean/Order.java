package com.atguigu.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String id;//订单号
    private Date orderdate;//订单下单日期
    private int totalCount;//商品总数量
    private double totalAmount;//商品总金额
    private int state;//订单状态 0未发货 1已发货 2交易完成
    private int userId;
    private static final long serialVersionUID = -8039769267268119404L;
    public Order() {
    }

    public Order(String id, Date orderdate, int totalCount, double totalAmount, int state, int userId) {
        this.id = id;
        this.orderdate = orderdate;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.state = state;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderdate=" + orderdate +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                ", state=" + state +
                ", userId=" + userId +
                '}';
    }
}
