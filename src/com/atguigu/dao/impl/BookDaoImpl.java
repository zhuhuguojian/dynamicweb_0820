package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 11:19
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT id,title,author,price,stock,sales,img_path imgPath FROM books";
        return getBeanList(sql);
    }

    @Override
    public void saveBook(Book book) {
        String sql = "INSERT INTO books(title,author,price,stock,sales,img_path) " +
                " VALUES(?,?,?,?,?,?)";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getStock(),book.getSales(),book.getImgPath());
    }

    @Override
    public void delBookById(String bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        update(sql,bookId);
    }

    @Override
    public Book getBookById(String bookId) {
        String sql = "SELECT id,title,author,price,stock,sales,img_path imgPath FROM books " +
                " WHERE id=?";
        return getBean(sql,bookId);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?,author=?,price=?,stock=?,sales=?,img_path=? WHERE id=?";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getStock(),book.getSales(),book.getImgPath(),book.getId());
    }

    /**
     * 分页实现查询Book信息
     * @param page
     * @return
     */
    @Override
    public Page<Book> getBooksByPage(Page<Book> page) {
//        Page<Book> page = new Page<Book>();
//        page.setPageNo(pageNo);
        //总记录数  dao
        String sql = "SELECT COUNT(1) FROM books";
        int totalRecord = Integer.parseInt(getSingleValue(sql)+"");     //错误，原因默认返回long
        //将总记录数，存放到page中。
        page.setTotalRecode(totalRecord);

        //当前页显示的数据集合    dao
        String sql2 = "SELECT id,title,author,price,stock,sales,img_path imgPath" +
                " FROM books WHERE 1=1 LIMIT ?,?";
        List<Book> list = getBeanList(sql2, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        page.setList(list);

        return page;
    }

    @Override
    public Page<Book> getBooksByPageAndPrice(Page<Book> page, double min, double max) {
        //总记录数  dao
        String sql = "SELECT COUNT(1) FROM books WHERE price BETWEEN ? AND ?";
        int totalRecord = Integer.parseInt(getSingleValue(sql,min,max)+"");     //错误，原因默认返回long
        //将总记录数，存放到page中。
        page.setTotalRecode(totalRecord);

        //当前页显示的数据集合    dao
        String sql2 = "SELECT id,title,author,price,stock,sales,img_path imgPath" +
                " FROM books WHERE 1=1 AND price BETWEEN ? AND ? LIMIT ?,?";
        List<Book> list = getBeanList(sql2,min,max,(page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        page.setList(list);

        return page;
    }

    @Override
    public void updateBook(int stock, int sales, int id) {
        String sql = "UPDATE books SET stock=?,sales=? WHERE id=?";
        update(sql,stock,sales,id);
    }

    @Override
    public void updateBook(Object[][] params) {
        String sql = "UPDATE books SET stock=?,sales=? WHERE id=?";
        updateBatch(sql,params);
    }


}
