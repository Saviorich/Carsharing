<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/footer.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="footer.administrator" var="administrator"/>
    </fmt:bundle>
</head>
<body>
<div class="footer">
    <c:set var="user" scope="session" value="${sessionScope.user}"/>
    <c:if test="${not empty user and user.role eq 'ADMIN'}">
        <span>${administrator}</span>
    </c:if>
    <p>Epam &copy;, 2021</p>
</div>
</body>
</html>
