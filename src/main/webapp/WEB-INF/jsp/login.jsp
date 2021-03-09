<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/login.css" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content" prefix="login.">
        <fmt:message key="password" var="password"/>
        <fmt:message key="log_in" var="log_in"/>
        <fmt:message key="error_message" var="error_message"/>
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
    </form>
        <c:set var="is_login_failed" scope="session" value="${sessionScope.error}"/>
            <c:if test="${is_login_failed}">
                <div id="fail_message">
                        ${error_message}
                </div>
            </c:if>
        <c:set var="is_login_failed" scope="session" value=""/>

</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
