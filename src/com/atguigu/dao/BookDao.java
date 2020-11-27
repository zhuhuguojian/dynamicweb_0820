package com.atguigu.dao;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 11:16
 */
public interface BookDao {

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
     *      返回值：Page<Book>,page中应该包含5个属性值
     *      参数：pageNo->page
     *          //总记录数  dao  select count(1) from books
     *          //当前页显示的数据集合    dao  SELECT * FROM books LIMIT x,PAGE_SIZE
     *              -- x:开始下标	PAGE_SIZE:每页显示数量    pageNo
     *              -- 总结：x = (pageNo-1)*PAGE_SIZE
     * @return
     */
    public Page<Book> getBooksByPage(/*int pageNo*/Page<Book> page);


    /**
     * 带价格区间的分页查询
     */
    public Page<Book> getBooksByPageAndPrice(Page<Book> page,double min,double max);

    //更改book的库存和销量
    public void updateBook(int stock,int sales,int id);
    public void updateBook(Object[][] params);
}
