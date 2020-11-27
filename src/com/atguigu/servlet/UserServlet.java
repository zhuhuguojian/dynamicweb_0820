package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.UserServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/13
 * @Time 11:14
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    //注意：method参数值与Servlet中的方法名，必须一致。

    /**
     * 处理登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        HttpSession session = request.getSession();
//        ServletContext servletContext = getServletContext();
        //1. 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2. 调用userdao接口
        User user = userService.getUser(new User(null, username, password, null));
        //3. 判断登陆成功或失败【登陆成功：重定向login_success.jsp；登陆失败：转发login.jsp】
        //4. 跳转路径
        if(user == null){
            //将数据存放到域中【提示登录失败】
            request.setAttribute("msg","用户名或密码输入有误，请求重新输入！");
            //登陆失败：转发login.jsp
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            HttpSession session = request.getSession();
            //将用户信息保存到域中
            session.setAttribute("user",user);
            //登陆成功：重定向login_success.jsp
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

    /**
     * 处理注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username"); //获取用户名信息
        String pwd = request.getParameter("pwd");           //获取密码信息
        String email = request.getParameter("email");       //获取邮箱信息
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        Object codesession = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (codesession!=null&&codesession.toString().equals(code)) {
            session.removeAttribute("KAPTCHA_SESSION_KEY");
            //调用userdao中checkUserName(),判断数据库是否存在该用户
            User user = userService.checkUserName(username);
            if (user == null) {
                //不存在：调用saveuser(),重定向regist_success.jsp
                userService.saveUser(new User(null, username, pwd, email));
                response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
            } else {
                //注册失败【用户名已存在】
                request.setAttribute("msg", "用户名已存在！");
                //存在：转发regist.jsp
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        }else {
                request.setAttribute("msgcode","验证码输入有误，请重新输入");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }

    /**
     * 注销
     *  将user信息，从session中移除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //将user信息，从session中移除
        session.removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }



}
