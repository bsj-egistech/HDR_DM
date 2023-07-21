package com.defr.hotdealradar.vo;

import lombok.Data;

@Data
public class PageVo {
    private int totalCount;
    private int currentPage;
    private int totalPage;
    private int displayPage;     //표시 페이지(최대)
    private int displayRow;     //표시 행
    private int beginPage;
    private int endPage;

    public PageVo() {
        this.displayPage = 10;
        this.displayRow = 20;
    }
}
