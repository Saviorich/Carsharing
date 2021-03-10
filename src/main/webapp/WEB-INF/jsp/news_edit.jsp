<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>EDITOR</h1>

<div class="news_block">
    <c:set var="n" value="${requestScope.data_id}"/>
    <div class="news_block__header">
        ${n.header}
        <div class="news_block__header__date">
            <fmt:formatDate value="${n.publicationDate}" type="date" pattern="dd.MM.yyyy"/>
        </div>

    </div>
    <div class="news_block__content">
        <p>${n.content}</p>
    </div>
</div>
</body>
</html>
