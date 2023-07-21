<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main page</title>


    <link rel="stylesheet" type="text/css" href="assets/css/common.css">
    <link rel="stylesheet" type="text/css" href="assets/css/page.css">
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
                <a class="page-text">
                    <span>이전</span>
                </a>
                <a class="page-text current">
                    <span>1</span>
                </a>
                <a class="page-text">
                    <span>2</span>
                </a>
                <a class="page-text">
                    <span>3</span>
                </a>
                <a class="page-text">
                    <span>4</span>
                </a>
                <a class="page-text">
                    <span>5</span>
                </a>
                <a class="page-text">
                    <span>다음</span>
                </a>
            </div>
        </footer>
    </div>







</body>
</html>