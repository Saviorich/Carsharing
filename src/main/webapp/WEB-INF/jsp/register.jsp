<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:bundle basename="content">
        <fmt:message key="register.password" var="password"/>
        <fmt:message key="register.sign_up" var="register"/>
        <fmt:message key="register.error_message" var="error_message"/>
        <fmt:message key="register.have_account" var="have_account"/>
        <fmt:message key="header.log_in" var="log_in"/>
    </fmt:bundle>
</head>
<body>

<div class="bg-image"></div>
<div class="register">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="register">
        <label>
            Email
            <input type="text" name="email" required>
        </label>
        <label>
            ${password}
            <input type="password" name="password" required>
        </label>
        <input class="button" type="submit" value="${register}">
        <label>${have_account}</label>
        <a href="login">${log_in}</a>
    </form>
    <c:set var="is_register_failed" scope="session" value="${sessionScope.error}"/>
    <c:if test="${is_register_failed}">
        <div id="fail_message">
                ${error_message}
        </div>
    </c:if>
    <c:set var="is_login_failed" scope="session" value=""/>

</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
