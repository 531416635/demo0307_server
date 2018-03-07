package com.xiao.demo.model;

public class PageModel {

    private int pageSize;// 每页显示的记录条数
    private int currentPageNum;// 当前页数
    private int totalPageNum;// 总页数
    private int totalCount;// 总数
    private int startPage; //开始条数
    private int endPage; //结束条数


    // 所有参数都进行修改

    public PageModel(int currentPageNum, int totalCount, int pageSize) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPageNum = totalCount % pageSize == 0 ? totalCount
                / pageSize : totalCount / pageSize + 1;
        //如果当前页小于第一页，则停留在第一页
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);
        this.startPage = (currentPageNum-1) * pageSize;
        this.endPage = totalCount - this.startPage < pageSize ? (totalCount - this.startPage):currentPageNum * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }


}
