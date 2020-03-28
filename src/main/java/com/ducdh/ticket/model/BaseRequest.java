package com.ducdh.ticket.model;

public class BaseRequest {

    private Integer pageNum;

    private Integer pageSize;

    private String sortBy;

    private String sort;

    public BaseRequest(Integer pageNum, Integer pageSize, String sortBy, String sort) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sort = sort;
    }

    public static BaseRequest defaultInstance() {
        return new BaseRequest(0, 100, "", "ASC");
    }
}
