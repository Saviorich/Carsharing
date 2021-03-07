<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/news.css" type="text/css">

    <fmt:bundle basename="content" prefix="admin.">
        <fmt:message key="edit_news" var="edit_news"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

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
        <c:if test="${n.imagePath != null}">
            <div class="news_block__img">
                <img src="${n.imagePath}" alt="${n.header}"/>
            </div>
        </c:if>
        <div class="news_block__header__button">
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <button type="button" name="" value="${edit_news}">${edit_news}</button>
            </c:if>
        </div>


    </div>
</c:forEach>

<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
