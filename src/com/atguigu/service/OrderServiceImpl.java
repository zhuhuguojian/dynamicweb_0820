package com.atguigu.service;

import com.atguigu.bean.*;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String creatOrder(Cart cart, User user) {
        String orderId = System.currentTimeMillis() + "" + user.getId();
        Order order = new Order(orderId, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
        orderDao.saveOrder(order);


        List<CartItem> cartItems = cart.getCartItems();
        //定义用于批处理新增订单的二维数组
        Object[][] oiParams = new Object[cartItems.size()][];
        //定义批处理更改图书信息的二维数组
        Object[][] bookParams = new Object[cartItems.size()][];
        for (int i = 0; i <cartItems.size() ; i++) {
            CartItem cartItem = cartItems.get(i);
            Book book = cartItem.getBook();
//            OrderItem oi = new OrderItem(null,cartItem.getCount(),cartItem.getAmount(),book.getTitle(),book.getAuthor(),book.getPrice(),book.getImgPath(),orderId);
//            orderItemDao.saveOrderItem(oi);
            oiParams[i] = new Object[]{cartItem.getCount(),cartItem.getAmount(),book.getTitle(),book.getAuthor(),book.getPrice(),book.getImgPath(),orderId};
            int newStock = book.getStock() - cartItem.getCount();
            int newSales = book.getSales() + cartItem.getCount();
//            bookDao.updateBook(newStock,newSales,book.getId());
            bookParams[i] = new Object[]{newStock,newSales,book.getId()};
        }
        orderItemDao.saveOrderItem(oiParams);
        bookDao.updateBook(bookParams);
        cart.clearCart();
        return orderId;
    }
}
