<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.defr.hotdealradar.vo.PagingVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");  // new PagingVO();

	int currentPage = 1;
	if (request.getParameter("pageIdx") != null) {
		currentPage = Integer.parseInt(request.getParameter("pageIdx"));
	}
	pagingVO.setCurrentPage(currentPage);

	//========= pagination ===========
	pagingVO.setEndPage( ((int) Math.ceil(pagingVO.getCurrentPage() / (double) pagingVO.getDisplayPage())) * pagingVO.getDisplayPage() );	//Math.ceil : 소수점 이하를 올림한다
	pagingVO.setBeginPage( pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1) );
	pagingVO.setTotalPage( (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()) );
	if (pagingVO.getEndPage() > pagingVO.getTotalPage()) {
		pagingVO.setEndPage(pagingVO.getTotalPage());
	}

%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main page</title>


    <link rel="stylesheet" type="text/css" href="assets/css/common.css">
    <link rel="stylesheet" type="text/css" href="assets/css/page.css">

    <script src="assets/js/common.js"></script>
    <script src="assets/js/content.js"></script>


</head>
<body>

    <!-- <c:forEach var="item" items="${list}">
        <p>${item.id}</p>
    </c:forEach> -->

    <div id="wrapper">
        <header id="header"></header>

        <div id="container" class="hide-scroll">
            <!-- 게시판 메뉴(검색창 등) -->
            <!-- 게시판 본체 -->

            <table id="deal-list">

                <colgroup>
                    <col></col>
                    <col></col>
                    <col></col>
                    <col></col>
                    <col></col>
                    <col></col>
                </colgroup>

                <thead>
                    <tr>
                        <th>번호</th>
                        <th>사이트</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>가격</th>
                        <th>날짜</th>
                    </tr>
                </thead>

                <tbody>
                    <!-- <tr>
                        <td>000</td>
                        <td>뽐뿌</td>
                        <td>콜라</td>
                        <td>홍길동</td>
                        <td>마넌</td>
                        <td>2023-06-01 23:59:59</td>
                    </tr> -->

                    <c:forEach var="item" items="${list}">
                        <tr>
                            <td>${item.number}</td>
                            <td>${item.site}</td>
                            <td class="tl">${item.title}</td>
                            <td>${item.seller}</td>
                            <td>${item.price_ori}</td>
                            <td>${item.regi_date}</td>
                        </tr>
                    </c:forEach>

                </tbody>






                
            </table>




        </div>

        <footer id="footer" class="align-cc">
            <div class="pagination">

                <c:set var="pageIndex" value="<%=pagingVO.getCurrentPage() %>" />
                <c:set var="beginPage" value="<%=pagingVO.getBeginPage() %>" />
                <c:set var="endPage" value="<%=pagingVO.getEndPage() %>" />
                <c:set var="totalPage" value="<%=pagingVO.getTotalPage() %>" />
                <c:set var="displayPage" value="<%=pagingVO.getDisplayPage() %>" />

                <c:if test="${ pageIndex > 1 }">
                    <a class="page-text" href="javascript:gotoPage(1)">
                        <span class="item">«</span>
                    </a>	
                    <a class="page-text" href="javascript:gotoPage(${ pageIndex - 1 })" >
                        <span>이전</span>
                    </a>
                </c:if>

                <c:forEach var="item" varStatus="status" begin="${ beginPage }" end="${ endPage }" step="1">
                    <c:if test="${ pageIndex == item }">
                        <a class="page-text current">
                            <span>${item}</span>
                        </a>
                    </c:if>
                    <c:if test="${ pageIndex != item }">
                        <a class="page-text" href="javascript:gotoPage(${item})">
                            <span>${item}</span>
                        </a>
                    </c:if>
                </c:forEach>

                <c:if test="${ pageIndex < totalPage }">
                    <a class="page-text" href="javascript:gotoPage(${ pageIndex + 1 })" >
                        <span>다음</span>
                    </a>
                    <!-- 끝으로 -->
                    <a class="page-text" href="javascript:gotoPage(${ totalPage })">
                        <span>»</span>
                    </a> 
                </c:if>
            </div>
        </footer>
    </div>







</body>
</html>