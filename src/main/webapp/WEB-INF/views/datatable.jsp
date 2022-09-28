<%--
  Created by IntelliJ IDEA.
  User: JD-USER
  Date: 2022-09-13
  Time: 오후 3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title>데이터테이블 페이지</title>
    <%--    CSS     --%>
    <link rel="stylesheet" href="plugins/bootstrap-5.2.0/bootstrap.min.css">
    <link rel="stylesheet" href="plugins/dataTables-1.12.1/dataTables.bootstrap5.min.css">
    <%--    JS      --%>
    <script defer src="plugins/jquery-3.6.1/jquery-3.6.1.min.js"></script>
    <script defer src="plugins/jquery-3.6.1/jquery.dataTables.min.js"></script>
    <script defer src="plugins/dataTables-1.12.1/dataTables.bootstrap5.min.js"></script>
    <script defer src="plugins/bootstrap-5.2.0/bootstrap.min.js"></script>
    <script defer src="js/datatable.js"></script>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body class="d-flex flex-column min-vh-100 h-100">
<%--Modal--%>
<div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="ModalLabel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalLabel">사용자 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <p id="id" class="d-none"></p>
                <div class="d-flex justify-content-evenly m-0 fw-bold">
                    <p class="m-0">아이디</p>
                    <p class="m-0">이름</p>
                    <p class="m-0">비밀번호</p>
                </div>
                <input type="text" id="memberId" placeholder="입력하세요">
                <input type="text" id="name" placeholder="입력하세요">
                <input type="text" id="password" placeholder="입력하세요">
                <div class="d-flex justify-content-evenly m-0 fw-bold">
                    <p class="m-0">부서</p>
                    <p class="m-0">직위</p>
                    <p class="m-0">거주지</p>
                </div>
                <input type="text" id="department" placeholder="입력하세요">
                <input type="text" id="position" placeholder="입력하세요">
                <input type="text" id="region" placeholder="입력하세요">

            </div>
            <div class="modal-footer">
                <div type="button" id="btnCreate" class="btn btn-primary" data-bs-dismiss="modal">등록</div>
                <div type="button" id="btnUpdate" class="btn btn-primary" data-bs-dismiss="modal">수정</div>
                <div type="button" id="btnDelete" class="btn btn-danger" data-bs-dismiss="modal">삭제</div>
                <div type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</div>
            </div>
        </div>
    </div>
</div>
<%--Header--%>
<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<tiles:insertAttribute name="header"></tiles:insertAttribute>
<%--Contents--%>
<div class="container d-flex align-items-center justify-content-center flex-column h-100 w-100">
    <div class="searchForm form-group d-flex">
        <div>
            <select name="search_type" class="form-control" id="search_type">
                <option value="all">전체</option>
                <option value="memberId">아이디</option>
                <option value="name">이름</option>
                <option value="department">부서</option>
                <option value="position">직위</option>
                <option value="region">거주지</option>
            </select>
        </div>
        <input type="text" class="form-control" id="search_value" style="width: 200px;">

        <button id="btnSearch" class="btn btn-info">검색</button>
    </div>
    <table id="datatable" class="table table-striped">
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>부서</th>
            <th>직위</th>
            <th>거주지</th>
        </tr>
        </thead>
    </table>
    <div id="inner-container" class="d-flex justify-content-end">
        <button type="button" id="btnModal" class="btn btn-primary ">등록
        </button>
    </div>
</div>
<%--<%@ include file="/WEB-INF/views/common/footer.jsp" %>--%>
</body>
</html>
