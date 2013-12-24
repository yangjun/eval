package com.eu.evaluation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PageData<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1000964592333263107L;
    private static final int DEFAULT_PAGE_SIZE = 20;//默认的每页记录数
    private long totalCount;//记录总数
    private List<T> rows;//当前页的数据
    private int pageSize = DEFAULT_PAGE_SIZE;//每页记录数
    private long start;//当前页第一条记录在数据库中的序号，从0开始

    public PageData() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }

    public PageData(long start, long total, int pageSize, List<T> rows) {
        this.start = start;
        this.totalCount = total;
        this.pageSize = pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize;
        this.rows = new ArrayList<T>();
        this.rows.addAll(rows);
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 取总页数.
     *
     * @return
     */
    public long getTotalPageCount() {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    /**
     * 取当前页页码，页码从1开始
     *
     * @return
     */
    public long getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /**
     * 该页是否有下一页.
     */
    public boolean isHasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    /**
     * 该页是否有上一页.
     */
    public boolean isHasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     *
     * @see #getStartOfPage(int,int)
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     *
     * @param pageNo 从1开始的页号
     * @param pageSize 每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}
