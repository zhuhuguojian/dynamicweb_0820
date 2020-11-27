package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/16
 * @Time 14:07
 */
public class BookClientServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 分页查询Book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取pageNo
        String pageNo = request.getParameter("pageNo");
        //调用service
        Page<Book> page = bookService.getBooksByPage(pageNo);
        //将数据存放到域中
        request.setAttribute("page",page);
        //路径跳转
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);

    }

    /**
     * 实现带价格区间的分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBooksByPageAndPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数
        String pageNo = request.getParameter("pageNo");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        //2. 调用service
        Page<Book> page = bookService.getBooksByPageAndPrice(pageNo, min, max);
        //3. 将数据存放到域中
        request.setAttribute("page",page);
        //4. 路径跳转
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);

    }



}
