package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 11:21
 */
public interface BookService {


    /**
     * 获取所有book信息
     * @return
     */
    public List<Book> getAllBooks();


    /**
     * 新增Book信息
     */
    public void saveBook(Book book);

    /**
     * 删除Book信息
     * @param bookId
     */
    public void delBookById(String bookId);

    /**
     * 通过bookId获取Book信息
     * @param bookId
     * @return
     */
    public Book getBookById(String bookId);


    /**
     * 修改Book信息
     * @param book
     */
    public void updateBook(Book book);


    /**
     * 分页查询Book信息
     * @param pageNo
     * @return
     */
    public Page<Book> getBooksByPage(String pageNo);


    /**
     * 带价格区间的分页查询
     * @param pageNo
     * @return
     */
    public Page<Book> getBooksByPageAndPrice(String pageNo,String min,String max);

}
