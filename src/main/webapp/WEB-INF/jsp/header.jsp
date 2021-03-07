<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/header.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content" var="loc"/>

    <fmt:bundle basename="content" prefix="header.">
        <fmt:message key="log_in" var="log_in"/>
        <fmt:message key="sign_up" var="sign_up"/>
        <fmt:message key="log_out" var="log_out"/>
        <fmt:message key="news" var="news"/>
        <fmt:message key="cars" var="cars"/>
        <fmt:message key="about" var="about"/>
    </fmt:bundle>
</head>
<body>
<div class="header">
    <a href="Controller?command=gotonewspage" class="header__logo">CarSharing</a>
        <div class="header__links">

            <c:choose>
                <c:when test="${sessionScope.user eq null}">
                    <a href="Controller?command=gotologinpage">${log_in}</a>
                    <a href="Controller?command=gotoregisterpage">${sign_up}</a>
                </c:when>
                <c:otherwise>
                    <a href="Controller?command=gotonewspage">${news}</a>
                    <a href="Controller?command=gotocarspage">${cars}</a>
                    <a href="Controller?command=gotoaboutpage">${about}</a>
                    <a href="Controller?command=signout">${log_out}</a>
                </c:otherwise>
            </c:choose>
        </div>

</div>
</body>
</html>