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
    <link rel="stylesheet" href="plugins/fontawesome-6.2.0/css/solid.min.css">
    <%--    JS      --%>
    <script defer src="plugins/jquery-3.6.1/jquery-3.6.1.min.js"></script>
    <script defer src="plugins/jquery-3.6.1/jquery.dataTables.min.js"></script>
    <script defer src="plugins/dataTables-1.12.1/dataTables.bootstrap5.min.js"></script>
    <script defer src="plugins/bootstrap-5.2.0/bootstrap.min.js"></script>
    <script defer src="plugins/jstree-3.3.12/jstree.min.js"></script>
    <script defer src="js/organization.js"></script>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body class="d-flex flex-column min-vh-100 h-100">
<%--Header--%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%--Modal--%>
<div class="modal fade" id="groupModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="groupModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="organization-modal-content">
                    <span class="input-group-text" id="groupName">그룹명</span>
                    <input type="text" class="form-control" id="inputGroupName" aria-describedby="groupName">
                </div>
                <div class="organization-modal-content">
                    <span class="input-group-text" id="parentGroupName" >상위그룹명</span>
                    <input type="text" class="form-control" id="inputParentGroupName"
                           aria-describedby="parentGroupName">
                </div>
                <div class="organization-modal-content">
                    <span class="input-group-text" id="sortValue">정렬값</span>
                    <input type="text" class="form-control" id="inputSortValue" aria-describedby="sortValue">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" id="btnConfirmCreation" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>
<%--Contents--%>
<div class="tap-wrap">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link active" id="organization-tab" data-bs-toggle="tab"
               data-bs-target="#organization-tab-pane" type="button" aria-controls="organization-tab-pane"
               aria-selected="true">조직도 관리</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="policy-tab" data-bs-toggle="tab" data-bs-target="#policy-tab-pane" type="button"
               aria-controls="policy-tab-pane" aria-selected="false">정책 관리</a>
        </li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <div class="organization tab-pane fade show active" id="organization-tab-pane" role="tabpanel"
             aria-labelledby="organization-tab" tabindex="0">
            <div class="organization-tree-wrap">
                <div class="group-header">
                    <h4 class="content-title">조직도</h4>
                    <div class="organization-modify-wrap">
                        <button id="btnShowGroupCreationModal" type="button" class="btn btn-primary btn-sm">
                            <i class="fa-solid fa-user-plus"></i> 추가
                        </button>
                        <button id="btnShowGroupUpdateModal" type="button" class="btn btn-secondary btn-sm">
                            <i class="fa-solid fa-pencil"></i> 수정
                        </button>
                        <button id="btnDeleteGroup" type="button" class="btn btn-danger btn-sm">
                            <i class="fa-solid fa-user-xmark"></i> 삭제
                        </button>
                    </div>
                </div>
                <div class="organization-search-wrap">
                    <input type="text" id="organization-search-field" placeholder="그룹 검색"></input>
                    <button id="btnTreeSearch" type="button" class="btn btn-info btn-sm">
                        <i class="fa-solid fa-magnifying-glass"> </i> 검색
                    </button>
                </div>
                <div id="groupTree">
                </div>
            </div>
            <div class="organization-table-wrap">
                <div class="agent-header">
                    <h4 class="content-title">에이전트 목록</h4>
                    <div class="agent-control-wrap">
                        <button id="btnShowAgentCreationModal" type="button" class="btn btn-primary btn-sm">
                            <i class="fa-solid fa-user-plus"></i> 추가
                        </button>
                        <button id="btnShoAgentUpdateModal" type="button" class="btn btn-secondary btn-sm">
                            <i class="fa-solid fa-pencil"></i> 수정
                        </button>
                        <button id="btnDeleteAgent" type="button" class="btn btn-danger btn-sm">
                            <i class="fa-solid fa-user-xmark"></i> 삭제
                        </button>
                    </div>
                </div>
                <table id="organization-table" class="table table-striped">
                    <thead>
                    <tr>
                        <th>이름</th>
                        <th>그룹</th>
                        <th>IP</th>
                        <th>MAC</th>
                        <th>생성일</th>
                        <th>최근 변경일
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>

        </div>
        <div class="tab-pane container fade" id="policy-tab-pane" role="tabpanel" aria-labelledby="policy-tab"
             tabindex="0">menu1
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
