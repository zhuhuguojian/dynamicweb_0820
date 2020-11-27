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
import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 11:25
 */
public class BookManagerServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 获取所有Book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数【可能省略】
        //2. 调用Service中的方法
        List<Book> list = bookService.getAllBooks();
        //3. 将数据存放到域中【可能省略】
        request.setAttribute("list",list);
        //4. 跳转路径
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }



//    /**
//     * 新增Book信息
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 获取请求参数
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        String price = request.getParameter("price");
//        String stock = request.getParameter("stock");
//        String sales = request.getParameter("sales");
//
//        // public Book(Integer id, String title, String author, Double price, Integer stock, Integer sales, String imgPath) {
//        //2. 调用service
//        bookService.saveBook(new Book(null,title,author,Double.valueOf(price),Integer.valueOf(stock),Integer.valueOf(sales),null));
//        //4. 路径跳转【业务】-重新查询Book信息
////        getAllBooks(request,response);
//        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=getAllBooks");
//    }
//    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 获取请求参数
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        String price = request.getParameter("price");
//        String stock = request.getParameter("stock");
//        String sales = request.getParameter("sales");
//        //bookId
//        String bookId = request.getParameter("bookId");
//        //2.调用service
//        bookService.updateBook(new Book(Integer.valueOf(bookId),title,author,Double.valueOf(price),Integer.valueOf(stock),Integer.valueOf(sales),null));
//        //4. 路径跳转
//        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=getAllBooks");
//    }

    protected void saveOrUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String sales = request.getParameter("sales");
        //bookId
        String bookId = request.getParameter("bookId");
        if(bookId == null || "".equals(bookId)){
            //添加book
            //2. 调用service
            bookService.saveBook(new Book(null,title,author,Double.valueOf(price),Integer.valueOf(stock),Integer.valueOf(sales),null));
        }else{
            //修改Book
            //2.调用service
            bookService.updateBook(new Book(Integer.valueOf(bookId),title,author,Double.valueOf(price),Integer.valueOf(stock),Integer.valueOf(sales),null));

        }
        //4. 路径跳转
        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=getAllBooksByPage");
    }



    /**
     * 删除Book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String bookId = request.getParameter("bookId");
        //2. 调用Service
        bookService.delBookById(bookId);
        //4. 路径跳转
        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=getAllBooksByPage");

    }

    /**
     * 通过id获取Book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取bookid参数
        String bookId = request.getParameter("bookId");
        //调用service
        Book book = bookService.getBookById(bookId);
        //将数据存放到域中
        request.setAttribute("book",book);
        //路径跳转
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }

    /**
     * 分页查询Book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取pageNo参数
        String pageNo = request.getParameter("pageNo");
        //调用service
        Page<Book> page = bookService.getBooksByPage(pageNo);

        //将page存放到域中
        request.setAttribute("page",page);
        //路径跳转
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }


}
