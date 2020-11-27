package com.atguigu.filter;

import com.atguigu.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            chain.doFilter(request,response);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
                response.sendRedirect(request.getContextPath()+"/pages/error/trans_error.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } finally {
            JDBCUtils.releaseConnection();
        }
    }
}
