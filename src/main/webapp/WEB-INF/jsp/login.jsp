<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/login.css" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="login.password" var="password"/>
        <fmt:message key="login.log_in" var="log_in"/>
        <fmt:message key="login.error_message" var="error_message"/>
        <fmt:message key="login.no_account" var="no_account"/>
        <fmt:message key="register.validation_message" var="validation_message"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
    </fmt:bundle>
</head>
<body>

<div class="bg-image"></div>
<div class="login">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="login">
        <label>
            Email
            <input type="text" name="email" required>
        </label>
        <label>
            ${password}
            <input type="password" name="password" required>
        </label>
        <input class="button" type="submit" value="${log_in}">
        <label>${no_account}</label>
        <a href="register">${sign_up}</a>
    </form>
    <c:set var="is_register_failed" scope="request" value="${requestScope.error}"/>
    <c:set var="is_invalid_data" scope="request" value="${requestScope.validation}"/>

    <c:choose>
        <c:when test="${is_register_failed}">
            ${error_message}
        </c:when>
        <c:when test="${is_invalid_data}">
            ${validation_message}
        </c:when>
    </c:choose>

</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
