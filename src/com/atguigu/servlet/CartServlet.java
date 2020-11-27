package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        Book book = bookService.getBookById(bookId);
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addBookToCart(book);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void delCartItemById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null){
            cart.delCartItemById(bookId);
        }
      response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        if (cart != null){
            cart.clearCart();
        }
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    protected void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookID = request.getParameter("bookId");
        String newCount = request.getParameter("newCount");

        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart != null){
            cart.updateCartItemCount(bookID,newCount);
        }
//        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
        PrintWriter writer = response.getWriter();
        Gson gosn = new Gson();

       CartItem cartItem = cart.getMap().get(bookID);
       Double amount = cartItem.getAmount();

        Integer totalCount = cart.getTotalCount();
        Double totalAmount = cart.getTotalAmount();

        Map<String,Object> map = new HashMap<>();
        map.put("amount",amount);
        map.put("totalCount",totalCount);
        map.put("totalAmount",totalAmount);

        String jsonString = gosn.toJson(map);
        System.out.println(jsonString);

        writer.write(jsonString);

    }
}
