<%--
  Created by IntelliJ IDEA.
  User: JD-USER
  Date: 2022-09-06
  Time: 오후 4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>

    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>모달 페이지</title>
    <link rel="stylesheet" href="plugins/bootstrap-5.2.0/bootstrap.min.css">
    <%--jQuery library--%>
    <script defer src="plugins/jquery-3.6.1/jquery-3.6.1.min.js"></script>
    <%--Bootstrap.js--%>
    <script defer src="plugins/bootstrap-5.2.0/bootstrap.min.js"></script>
    <%--    modal.js--%>
    <script defer src="js/modal.js"></script>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body class="d-flex flex-column min-vh-100 h-100">
<style>
    .container {
        margin: 0 auto;
        height: 100%;
        display: flex;
        justify-content: center;
        align-content: center;
    }
</style>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%--Modal trigger--%>
<div class="container align-items-center justify-content-center">
    <div class="result">
        <input type="text" id="result" placeholder="입력 버튼을 누르세요" readonly>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">입력</button>
        <button type="button" id="transmit" class="btn btn-secondary">전송</button>
    </div>
</div>
<%--Modal--%>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">입력</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="text" id="value" placeholder="입력하세요">
            </div>
            <div class="modal-footer">
                <div type="button" id="confirm" class="btn btn-primary" data-bs-dismiss="modal">확인</div>
                <div type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</div>
            </div>
        </div>
    </div>
</div>

<%--<%@ include file="/WEB-INF/views/common/footer.jsp" %>--%>
</body>
</html>
