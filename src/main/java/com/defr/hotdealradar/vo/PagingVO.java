package com.defr.hotdealradar.vo;

import lombok.Data;

@Data
public class PagingVO {
    //paging
    private int totalCount; // 전체 게시글 수 (get)
    private int currentPage; // 현재 페이지 (get)
    private int totalPage; // 전체 페이지 수 (get)

    //pagination
    private int displayPage = 10; // 한 페이지에 몇 개의 페이지 (선택 set)
    private int displayRow = 10; // 한 페이지에 몇 개의 로우 (선택 set)
    private int beginPage; // 페이징 시작 페이지 수
    private int endPage; // 페이징 종료 페이지 수



    public int getStart(String pageIndex) {

        return (Integer.parseInt(pageIndex) * displayRow) - displayRow ;
    }

    public int getStart(int pageIndex) {

        return (pageIndex * displayRow) - displayRow ;
    }
}
