<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/news.css" type="text/css">
    <link rel="stylesheet" href="css/cars.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="admin.add_news" var="add_news"/>
        <fmt:message key="admin.edit" var="edit_news"/>
        <fmt:message key="admin.delete" var="delete"/>
        <fmt:message key="delete_dialog.message" var="message"/>
        <fmt:message key="delete_dialog.yes" var="yes"/>
        <fmt:message key="delete_dialog.no" var="no"/>
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
                <a href="Controller?command=gotonewseditpage&data_id=${n.id}">${edit_news}</a>
                <a href="#delete_dialog">${delete}</a>
            </c:if>
        </div>

        <div id="delete_dialog" class="delete_dialog">
            <div class="delete_dialog__content">
                <div class="delete_dialog__message">
                    <h1>${message}</h1>
                </div>
                <div class="delete_dialog__options">
                    <a id="delete_dialog__yes" href="Controller?command=deletenews&data_id=${n.id}">${yes}</a>
                    <a href="#">${no}</a>
                </div>
            </div>
        </div>

    </div>
</c:forEach>

<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
