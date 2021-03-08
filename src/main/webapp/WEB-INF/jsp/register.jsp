<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>" type="text/css"/>

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:bundle basename="content" prefix="register.">
        <fmt:message key="password" var="password"/>
        <fmt:message key="sign_up" var="register"/>
        <fmt:message key="error_message" var="error_message"/>
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
