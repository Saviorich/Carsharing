<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/news_edit.css" type="text/css"/>
    <link rel="stylesheet" href="css/news.css" type="text/css"/>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/news_edit.js"></script>
    <script type="text/javascript" src="./ckeditor/ckeditor.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="editor.content" var="content"/>
        <fmt:message key="editor.header" var="editor_header"/>
        <fmt:message key="admin.preview" var="preview"/>
        <fmt:message key="admin.submit" var="submit"/>
    </fmt:bundle>

</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<c:set var="n" value="${requestScope.data_id}"/>
<form action="Upload" method="post" enctype='multipart/form-data'>
    <div class="container">
        <div class="editor_block">
            <div class="editor_block__header">
                <h5>${editor_header}</h5>
                <input name="header_editor" id="header_editor" type="text" value="${n.header}">
            </div>
            <div class="editor_block__content">
                <h5>${content}</h5>
                <textarea name="content_editor" id="content_editor">
                    ${n.content}
                </textarea>
            </div>
            <div class="editor_block__image">
                <input id="image_input" name="image_editor" type='file' onchange="readURL(this);"/>
                <img id="blah" src="${n.imagePath}" alt="your image"/>
            </div>
            <div class="navigation">
                <a id="view" href="#preview">${preview}</a>
                <input type="submit" value="${submit}">
                <c:choose>
                    <c:when test="${n ne null}">
                        <input type="hidden" name="command" value="editnews">
                        <input type="hidden" name="data_id" value="${n.id}">
                        <input type="hidden" name="image_path" value="${n.imagePath}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="addnews">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div id="preview" class="preview">
        <div class="news_block">
            <div class="news_block__header">
                <a href="#" class="preview__close">&times;</a>
                <span>${n.header}</span>
                <div class="news_block__header__date">
                    <fmt:formatDate value="${n.publicationDate}" type="date" pattern="dd.MM.yyyy"/>
                </div>

            </div>
            <div class="news_block__content">
                <span>${n.content}</span>
            </div>
            <div class="news_block__img">
                <img src="${n.imagePath}" alt="${n.header}"/>
            </div>
        </div>
    </div>
</form>

<script src="js/ckeditor_config.js"></script>

</body>
</html>
