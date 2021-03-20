<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/user_orders.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<div class="main_block">
    <c:forEach var="order" items="${requestScope.orders}">
        <div class="order_block">
            <c:out value="${order}"/>
        </div>
    </c:forEach>
</div>
</body>
</html>
