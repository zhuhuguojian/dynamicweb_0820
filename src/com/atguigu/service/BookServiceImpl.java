package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;

import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 11:22
 */
public class BookServiceImpl implements  BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void delBookById(String bookId) {
        bookDao.delBookById(bookId);
    }

    @Override
    public Book getBookById(String bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getBooksByPage(String pageNo) {
        Page<Book> page = new Page<>();
        int pNo=1;
        try {
            pNo = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        page.setPageNo(pNo);

//        Page<Book> pageRs = bookDao.getBooksByPage(page);
        page = bookDao.getBooksByPage(page);

        return page;
    }

    @Override
    public Page<Book> getBooksByPageAndPrice(String pageNo, String min, String max) {
        Page<Book> page = new Page<>();
        //处理当前页码
        int pNo=1;
        try {
            pNo = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        page.setPageNo(pNo);

        //处理价格区间问题
        double minRs = 0,maxRs = Double.MAX_VALUE;
        try {
            minRs = Double.parseDouble(min);
            maxRs = Double.parseDouble(max);
            //支持用户反向输入
            if(minRs > maxRs){
                double temp = minRs;
                minRs = maxRs;
                maxRs = temp;
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }

        page = bookDao.getBooksByPageAndPrice(page,minRs,maxRs);

        return page;
    }


}
