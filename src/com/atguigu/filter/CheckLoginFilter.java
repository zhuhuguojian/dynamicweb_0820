package com.atguigu.filter;

import com.atguigu.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter extends HttpFilter{


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null){
                request.setAttribute("msg","需要先登录在进行购物");
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            }else {
                chain.doFilter(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
