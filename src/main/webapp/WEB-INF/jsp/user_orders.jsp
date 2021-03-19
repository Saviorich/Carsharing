<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<c:forEach var="order" items="${requestScope.orders}">
    <c:out value="${order}"/>
</c:forEach>
</body>
</html>
