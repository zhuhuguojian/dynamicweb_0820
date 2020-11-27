package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/13
 * @Time 11:40
 */
public class BaseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            String method = request.getParameter("method");
            //通过方法名，获取方法对象【当前类】
            Method methodObj = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            methodObj.invoke(this,request,response);

        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }

        /*
         * 反射：Class
         *       User
         *       Student : name,age,gender,  study()
         *       Class : filed , method
         * 获取Class方式
         *   类名.class
         *   对象名.getClass()
         *
         * */
//        System.out.println("method = " + method);
//        if("login".equals(method)){
//            //登录
//            login(request,response);
//        }else if("regist".equals(method)){
//            //注册
//            regist(request,response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
