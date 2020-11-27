package com.atguigu.util;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.bean.User;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.UserDaoImpl;

import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/12
 * @Time 10:38
 */
public class Test {

    public static void main(String[] args) {

//        UserDao userDao = new UserDaoImpl();
//
//        User user = userDao.getUser(new User(null, "kaifa", "kaifa", null));
//
//        System.out.println("user = " + user);

        BookDao bookDao = new BookDaoImpl();
//
//        List<Book> allBooks = bookDao.getAllBooks();
//
//        for (Book allBook : allBooks) {
//            System.out.println("allBook = " + allBook);
//        }

        Page<Book> page = new Page<Book>();
        page.setPageNo(4);

        Page<Book> pagers = bookDao.getBooksByPage(page);
        System.out.println(pagers.getPageNo()+"/"+pagers.getTotalPageNo());
        for (Book book : pagers.getList()) {
            System.out.println("book = " + book);

        }


    }
    
}
