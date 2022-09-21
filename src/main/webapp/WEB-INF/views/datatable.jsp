<%--
  Created by IntelliJ IDEA.
  User: JD-USER
  Date: 2022-09-13
  Time: 오후 3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="container d-flex align-items-center justify-content-center flex-column h-100 w-100">
    <table id="datatable" class="table table-striped" style="width: 1200px">
        <thead>
        <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>비밀번호</th>
            <th>부서</th>
            <th>직위</th>
            <th>거주지</th>
        </tr>
        </thead>
    </table>
    <div id="inner-container" class="d-flex justify-content-end">
        <button type="button" id="createRow" class="btn btn-primary " data-bs-toggle="modal" data-bs-target="#memberCreateModal">등록</button>
    </div>
</div>


<div class="modal fade" id="memberCreateModal" tabindex="-1" aria-labelledby="ModalCreateLabel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalCreateLabel">사용자 등록</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <div class="d-flex justify-content-evenly m-0 fw-bold">
                    <p class="m-0">아이디</p>
                    <p class="m-0">이름</p>
                    <p class="m-0">비밀번호</p>
                </div>
                <input type="text" id="c_memberId" placeholder="입력하세요">
                <input type="text" id="c_name" placeholder="입력하세요">
                <input type="text" id="c_password" placeholder="입력하세요">
                <div class="d-flex justify-content-evenly m-0 fw-bold">
                    <p class="m-0">부서</p>
                    <p class="m-0">직위</p>
                    <p class="m-0">거주지</p>
                </div>
                <input type="text" id="c_department" placeholder="입력하세요">
                <input type="text" id="c_position" placeholder="입력하세요">
                <input type="text" id="c_region" placeholder="입력하세요">
            </div>
            <div class="modal-footer">
                <div type="button" id="c_confirm" class="btn btn-primary" data-bs-dismiss="modal">확인</div>
                <div type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="memberUpdateModal" tabindex="-1" aria-labelledby="ModalLabel">
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
                <div type="button" id="update" class="btn btn-primary" data-bs-dismiss="modal">확인</div>
                <div type="button" id="delete" class="btn btn-danger" data-bs-dismiss="modal">삭제</div>
                <div type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</div>
            </div>
        </div>
    </div>
</div>
<%--<%@ include file="/WEB-INF/views/common/footer.jsp" %>--%>
</body>
</html>
