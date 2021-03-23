<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/header.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
        <fmt:message key="header.log_out" var="log_out"/>
        <fmt:message key="header.news" var="news"/>
        <fmt:message key="header.cars" var="cars"/>
        <fmt:message key="header.about" var="about"/>
        <fmt:message key="locale.change_lang" var="change_lang"/>
        <fmt:message key="locale.ru" var="ru"/>
        <fmt:message key="locale.be" var="be"/>
        <fmt:message key="locale.en" var="en"/>
        <fmt:message key="header.orders" var="orders"/>
    </fmt:bundle>

    <title>${news}</title>
</head>
<body>
<div class="header">
    <a href="Controller?command=gotonewspage" class="header__logo">CarSharing</a>
    <div class="header__links">
        <a href="Controller?command=gotonewspage">${news}</a>
        <a href="Controller?command=gotocarspage">${cars}</a>
        <c:if test="${sessionScope.user != null}">
            <a href="Controller?command=gotoorderspage">${orders}</a>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user eq null}">
                <a href="Controller?command=gotologinpage">${log_in}</a>
                <a href="Controller?command=gotoregisterpage">${sign_up}</a>
            </c:when>
            <c:otherwise>
                <a href="Controller?command=signout">${log_out}</a>
            </c:otherwise>
        </c:choose>
        <a href="#modal" id="globe">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 22 22">
                <g fill="#FFF" fill-rule="nonzero">
                    <path d="M20.166 5.382l-.324.103-1.725.155-.487.782-.354-.113-1.373-1.244-.2-.647-.266-.69-.863-.778-1.018-.2-.023.468.997.978.488.578-.549.288-.446-.132-.67-.28.023-.543-.878-.363-.292 1.275-.885.202.088.711 1.153.223.2-1.137.951.142.443.26h.71l.486.978 1.288 1.313-.094.51-1.04-.132-1.794.91-1.293 1.558-.168.69h-.464l-.864-.4-.84.4.21.89.365-.423.642-.02-.045.8.532.156.532.6.868-.245.99.157 1.152.31.575.069.974 1.111 1.882 1.112-1.217 2.336-1.284.6-.488 1.334-1.858 1.247-.198.719A10.863 10.863 0 0 0 22 11.435a10.87 10.87 0 0 0-1.834-6.053z"/>
                    <path d="M11.445 15.274l-.748-1.384.686-1.428-.686-.204-.77-.773-1.708-.382-.566-1.184v.703h-.25L5.932 8.63V6.995l-1.079-1.75-1.712.304H1.988l-.58-.38.74-.586-.739.17A10.172 10.172 0 0 0 0 9.919c0 5.66 4.595 10.249 10.264 10.249.437 0 .865-.039 1.289-.09l-.108-1.241s.472-1.844.472-1.907c-.001-.063-.472-1.655-.472-1.655zM4.391 3.252l2.041-.25.94-.452 1.06.267 1.69-.082.58-.8.845.123 2.051-.17.566-.546.797-.468 1.128.15.411-.055A12.79 12.79 0 0 0 11.61 0C8.043 0 4.854 1.427 2.75 3.667h.006l1.635-.415zm7.704-2.25l1.173-.566.754.382-1.09.729-1.042.091-.47-.267.675-.368zm-3.475.084l.518.19.678-.19.37.561-1.566.361-.753-.386s.736-.416.753-.536z"/>
                </g>
            </svg>
        </a>

        <div id="modal" class="modal">
            <div class="modal__content">
                <h1>${change_lang}</h1>

                <a href="#" class="modal__close">&times;</a>
                <div class="languages">
                    <a id="en" href="Controller?command=changelang&locale=en">${en}</a>
                    <a id="ru" href="Controller?command=changelang&locale=ru">${ru}</a>
                    <a id="be" href="Controller?command=changelang&locale=be">${be}</a>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>