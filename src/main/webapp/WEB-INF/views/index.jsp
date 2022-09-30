<%--
  Created by IntelliJ IDEA.
  User: JD-USER
  Date: 2022-09-05
  Time: 오후 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>메인 페이지</title>
    <link rel="stylesheet" href="plugins/bootstrap-5.2.0/bootstrap.min.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <script defer src="plugins/bootstrap-5.2.0/bootstrap.min.js"></script>
    <script defer src="plugins/chart.js-3.9.1/chart.min.js"></script>
    <script defer src="plugins/chartjs-plugin-datalabels-2.1.0/chartjs-plugin-datalabels.min.js"></script>
    <script defer src="js/chart.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="container d-flex justify-content-center align-items-center" style="height: 85%">
    <div style="width: 400px; border: 1px solid #b6effb">
        <h5 style="text-align: center">지역별 사원 수</h5>
        <canvas id="regionChart"></canvas>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
