<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <fmt:bundle basename="content" prefix="admin.">
        <fmt:message key="edit_news" var="edit_news"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

<c:forEach var="n" items="${requestScope.news}">
    <div class="news_block">
        <div class="news_block__header">

            <!--

            TODO: FIX USER IN SESSION AFTER REGISTRATION

            -->

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <button type="button" name="" value="${edit_news}"></button>
            </c:if>
                ${n.header}
        </div>
        <div class="news_block__content">
            <p>${n.content}</p>
        </div>
        <div class="news_block__img">
            <img src="${n.imagePath}" alt="${n.header}"/>
        </div>
    </div>
</c:forEach>

<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
