<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/news.css" type="text/css">
    <link rel="stylesheet" href="css/cars.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content" prefix="admin.">
        <fmt:message key="add_news" var="add_news"/>
        <fmt:message key="edit" var="edit_news"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

<div class="admin_button_panel">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a id="admin_but_add" href="Controller?command=gotonewseditpage">${add_news}</a>
    </c:if>
</div>
<c:forEach var="n" items="${requestScope.news}">
    <div class="news_block">
        <div class="news_block__header">
                ${n.header}
            <div class="news_block__header__date">
                <fmt:formatDate value="${n.publicationDate}" type="date" pattern="dd.MM.yyyy"/>
            </div>

        </div>
        <div class="news_block__content">
            <p>${n.content}</p>
        </div>
        <c:if test="${not empty n.imagePath}">
            <div class="news_block__img">
                <img src="${n.imagePath}" alt="${n.header}"/>
            </div>
        </c:if>
        <div class="news_block__admin_panel">
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <a href="gotonewseditpage">${edit_news}</a>
                <a id="admin_but_delete" href="deletenews">Delete</a>
            </c:if>
        </div>


    </div>
</c:forEach>

<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
