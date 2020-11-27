package com.atguigu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/16
 * @Time 9:07
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -8039769267268119404L;

    // 5/48
    private Integer pageNo;                         //当前页   用户行为
    private Integer totalPageNo;                    //总页数   计算 109/10=10.0
    private Integer totalRecode;                    //总记录数  dao
    public static final Integer PAGE_SIZE = 10;    //可以是变量

    private List<T> list;                           //当前页显示的数据集合    dao

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPageNo=" + totalPageNo +
                ", totalRecode=" + totalRecode +
                ", list=" + list +
                '}';
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer totalPageNo, Integer totalRecode, List<T> list) {
        this.pageNo = pageNo;
        this.totalPageNo = totalPageNo;
        this.totalRecode = totalRecode;
        this.list = list;
    }

    /**
     * 获取pageNo
     * @return
     */
    public Integer getPageNo() {
        if(pageNo < 1){
            return 1;
        }
        if(pageNo > getTotalPageNo()){
            return getTotalPageNo();
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 计算总页数
     * @return
     */
    public Integer getTotalPageNo() {
        int totalPageNo = 0;
        totalPageNo = totalRecode % PAGE_SIZE==0?totalRecode / PAGE_SIZE:
                totalRecode / PAGE_SIZE+1;
        return totalPageNo;
    }

    public void setTotalPageNo(Integer totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public Integer getTotalRecode() {
        return totalRecode;
    }

    public void setTotalRecode(Integer totalRecode) {
        this.totalRecode = totalRecode;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
