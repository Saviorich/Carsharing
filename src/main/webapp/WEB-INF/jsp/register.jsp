<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:bundle basename="content">
        <fmt:message key="register.password" var="password"/>
        <fmt:message key="register.sign_up" var="register"/>
        <fmt:message key="register.error_message" var="error_message"/>
        <fmt:message key="register.have_account" var="have_account"/>
        <fmt:message key="register.first_name" var="first_name"/>
        <fmt:message key="register.second_name" var="second_name"/>
        <fmt:message key="register.middle_name" var="middle_name"/>
        <fmt:message key="register.phone_number" var="phone_number"/>
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
    </fmt:bundle>
    <title>${sign_up}</title>
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
        <label>
            ${first_name}
            <input name="first_name" required>
        </label>
        <label>
            ${second_name}
            <input name="second_name" required>
        </label>
        <label>
            ${middle_name}
            <input name="middle_name">
        </label>
        <label>
            ${phone_number}
            <input type="tel" name="phone_number" size="13" pattern="[+]([0-9]{12})" required>
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
