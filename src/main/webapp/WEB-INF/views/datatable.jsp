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
    <title>Title</title>
    <%--    CSS     --%>
    <link rel="stylesheet" href="plugin/bootstrap-5.2.0/bootstrap.min.css">
    <link rel="stylesheet" href="plugin/DataTables-1.12.1/dataTables.bootstrap5.min.css">
    <%--    JS      --%>
    <script defer src="plugin/jquery-3.6.1/jquery-3.6.1.min.js"></script>
    <script defer src="plugin/jquery-3.6.1/jquery.dataTables.min.js"></script>
    <script defer src="plugin/DataTables-1.12.1/dataTables.bootstrap5.min.js"></script>
    <script defer src="js/datatable.js"></script>


</head>
<body class="d-flex flex-column min-vh-100 h-100">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="container d-flex align-items-center justify-content-center flex-column h-100 w-50">
    <table id="datatable" class="table table-striped" >
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
        <button type="button" id="insertRow" class="btn btn-primary ">등록</button>
    </div>

</div>
<%--<%@ include file="/WEB-INF/views/common/footer.jsp" %>--%>
</body>
</html>
