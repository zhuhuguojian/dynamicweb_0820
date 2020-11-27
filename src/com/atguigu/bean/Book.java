package com.atguigu.bean;

import java.io.Serializable;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/14
 * @Time 10:45
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -8484491866384942709L;
    /**
     *
     *  分页实现查询Book信息
     *      * 为什么需要分页
     *          1. 提高用户体验度
     *          2. 降低服务器压力
     *      * 分页中page业务bean【共有5个属性】   5/19
     *          pageNo          用户行为
     *          totalPageNo     计算  总页数= 总记录数 / 每页显示数量
     *          totalRecord
     *          PAGE_SIZE
     *
     *          List<T>
     *
     *
     */

    private Integer id;
    private String title;
    private String author;
    private Double price;
    private Integer stock;
    private Integer sales;
    private String imgPath="static/img/default.jpg";


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sales=" + sales +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Book() {
    }

    public Book(Integer id, String title, String author, Double price, Integer stock, Integer sales, String imgPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
        this.imgPath = "static/img/default.jpg";
    }
}
