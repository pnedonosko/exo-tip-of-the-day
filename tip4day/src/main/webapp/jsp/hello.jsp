<%@ page import="java.util.ResourceBundle" %>

<%
    String contextPath = request.getContextPath();
%>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/some.css"/>

<script src="<%=contextPath%>/js/tip.js"></script>
<script src="<%=contextPath%>/js/jquery.min.js"></script>




<div class="container">
    <h2>Tip for day</h2>
    <div id="tip">
        Some tip
    </div>
    <div id="demo" class="collapse">
        <div>
            <p>Input your tip</p>
            <textarea id="tipFromArea" maxlength="350"  name="text"></textarea>
        </div>
    </div>
    <div id="btnblock">
        <a class="btn btn-info" id="nextTip" onclick="getRandomTip();">Next tip</a>
        <script>getRandomTip();</script>
        <a href="#demo" id="addNewTipButton" class="btn btn-info" onclick="postTip();">Add new tip</a>
    </div>
</div>
