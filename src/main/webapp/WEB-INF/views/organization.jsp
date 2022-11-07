<%--
  Created by IntelliJ IDEA.
  User: JD-USER
  Date: 2022-10-31
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>조직도 관리</title>
  <%--    CSS     --%>
  <link rel="stylesheet" href="plugins/bootstrap-5.2.0/bootstrap.min.css">
  <link rel="stylesheet" href="plugins/dataTables-1.12.1/dataTables.bootstrap5.min.css">
  <link rel="stylesheet" href="plugins/jstree-3.3.12/style.min.css">
  <link rel="stylesheet" href="css/index.css">
  <link rel="stylesheet" href="css/organization.css">
  <link rel="stylesheet" href="plugins/fontawesome-6.2.0/css/fontawesome.min.css">
  <link rel="stylesheet" href="plugins/fontawesome-6.2.0/css/brands.min.css">
  <%--    JS      --%>
  <script defer src="plugins/jquery-3.6.1/jquery-3.6.1.min.js"></script>
  <script defer src="plugins/jquery-3.6.1/jquery.dataTables.min.js"></script>
  <script defer src="plugins/dataTables-1.12.1/dataTables.bootstrap5.min.js"></script>
  <script defer src="plugins/bootstrap-5.2.0/bootstrap.min.js"></script>
  <script defer src="plugins/jstree-3.3.12/jstree.min.js"></script>
  <script defer src="js/organization.js"></script>
  <link rel="icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body class="d-flex flex-column min-vh-100 h-100" >
<%--Header--%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div style="height: 15%;">
</div>
<div class="tap-wrap" style="margin: 0 15%">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link active" data-bs-toggle="tab" href="#tab-organization">조직도 관리</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-bs-toggle="tab" href="#menu1">Menu 1</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-bs-toggle="tab" href="#menu2">Menu 2</a>
    </li>
  </ul>
  <!-- Tab panes -->
  <div class="tab-content">
    <div class="organization tab-pane container active" id="tab-organization">
      <div class="organization-tree-wrap">
        <p>조직도</p>
        <div class="organization-modify-wrap">
          <button id="btnTreeInsert" type="button" class="btn btn-primary btn-sm" >추가</button>
          <button id="btnTreeUpdate" type="button" class="btn btn-secondary btn-sm">수정</button>
          <button id="btnTreeDelete" type="button" class="btn btn-danger btn-sm">삭제</button>
        </div>
        <div class="organization-search-wrap">
          <input type="text" id="organization-search-field" placeholder="조직 검색"></input>
          <button id="btnTreeSearch" type="button" class="btn btn-info btn-sm">검색</button>
        </div>
        <div id="organization-tree">
        </div>
      </div>
      <div class="organization-table-wrap">
        <p>에이전트 목록</p>
        <table id="organization-table" class="table table-striped">
          <thead>
          <tr>
            <th>이름</th>
            <th>IP</th>
            <th>MAC</th>
            <th>생성일</th>
            <th>변경일</th>
          </tr>
          </thead>
        </table>
      </div>

    </div>
    <div class="tab-pane container fade" id="menu1">...</div>
    <div class="tab-pane container fade" id="menu2">...</div>
  </div>

</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
