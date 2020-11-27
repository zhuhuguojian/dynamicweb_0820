package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private Book book;//购物项Book信息
    private Integer count;//购物项数量
    private Double amount;//购物项金额
    private static final long serialVersionUID = -6743567631108323096L;
    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }

    public CartItem() {
    }

    public CartItem(Book book, Integer count, Double amount) {
        this.book = book;
        this.count = count;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        BigDecimal price = new BigDecimal(book.getPrice()+"");
        BigDecimal c = new BigDecimal(count);
        return price.multiply(c).doubleValue();
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
