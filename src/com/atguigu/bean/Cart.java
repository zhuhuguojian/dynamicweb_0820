package com.atguigu.bean;

import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {


    //购物项集合
    private Map<String,CartItem> map = new LinkedHashMap<>();
    //总数量
    private Integer totalCount;
    //总金额
    private Double totalAmount;

    private static final long serialVersionUID = -6743567631108323096L;
    /**
     * 获取所有购物项集合
     * @return
     */
    public List<CartItem> getCartItems(){
        Collection<CartItem> values = map.values();
        return new ArrayList<>(values);
    }
    public void delCartItemById(String bookId){
        map.remove(bookId);
    }
    public void clearCart(){
        map.clear();
    }
    public void updateCartItemCount(String bookId,String newCount){
        CartItem cartItem = map.get(bookId);
        try {
            Integer nCount = Integer.valueOf(newCount);
            cartItem.setCount(nCount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    /**
     * 1. 添加Book到购物车
     * 2. 显示购物车中数据
     * 3. 删除指定购物项
     * 4. 清空购物车
     * 5. 更改购物项数量
     * @return
     */

    public void addBookToCart(Book book){
        CartItem cartItem = map.get(book.getId() + "");
        if (cartItem == null){
            cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCount(1);
            map.put(book.getId()+"",cartItem);
        }else {
            int newCount = cartItem.getCount() + 1;
            cartItem.setCount(newCount);
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    /**
     * 计算购物车总数量
     * @return
     */
    public Integer getTotalCount() {
        int totalCount=0;
        for (CartItem cartItem : getCartItems()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 计算购物车总金额
     * @return
     */
    public Double getTotalAmount() {
        double totalAmount = 0;
        for (CartItem cartItem : getCartItems()) {
            totalAmount += cartItem.getAmount();
        }
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cart() {
    }

    public Cart(Map<String, CartItem> map, Integer totalCount, Double totalAmount) {
        this.map = map;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
    }
}
