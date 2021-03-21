<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/news_edit.css" type="text/css"/>
    <link rel="stylesheet" href="css/news.css" type="text/css"/>
    <link rel="stylesheet" href="css/cars.css" type="text/css">
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/news_edit.js"></script>
    <script type="text/javascript" src="./ckeditor/ckeditor.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="car.order" var="order"/>
        <fmt:message key="car.color" var="color"/>
        <fmt:message key="car.class" var="car_class"/>
        <fmt:message key="car.year" var="year"/>
        <fmt:message key="car.gearbox" var="gearbox"/>
        <fmt:message key="car.engine" var="engine"/>
        <fmt:message key="car.price" var="price"/>
        <fmt:message key="car.mileage" var="mileage"/>
        <fmt:message key="car.plate" var="plate"/>
        <fmt:message key="editor.content" var="content"/>
        <fmt:message key="editor.header" var="editor_header"/>
        <fmt:message key="admin.submit" var="submit"/>
        <fmt:message key="admin.preview" var="preview"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<c:set var="car" value="${requestScope.data}"/>
<form action="Upload" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="editor_block">
            <div class="editor_block__header">
                <h5>${editor_header}</h5>
                <label for="brand_editor">Brand</label>
                <input id="brand_editor" name="brand_editor" type="text" value="${car.brand}"/>
                <label for="model_editor" draggable="true">Model</label>
                <input id="model_editor" name="model_editor" type="text" value="${car.model}"/>
            </div>
            <div class="editor_block__content">
                <h5>${content}</h5>
                    ${color}: <input class="content_editor" name="color_editor" value="${car.color}"/><br/>
                    ${mileage}: <input class="content_editor" name="mileage_editor" type="number" value="${car.mileage}"/>KM<br/>
                    ${gearbox}: <input class="content_editor" name="gearbox_editor" value="${car.gearbox.toString().toLowerCase()}"/><br/>
                    ${year}: <input class="content_editor" name="year_editor" type="number" value="${car.manufacturedYear}"/><br/>
                    ${engine}: <input class="content_editor" name="engine_editor" value="${car.engineType.toString().toLowerCase()}"/><br/>
                    ${car_class}: <input class="content_editor" name="class_editor" value="${car.carClass.toString().toLowerCase()}"/><br/>
                    VIN: <input class="content_editor" name="vin_editor" value="${car.vin}"><br/>
                    ${plate}: <input class="content_editor" name="plate_editor" value="${car.plate}"><br/>
            </div>
            <div class="editor_block__car_price">
                ${price}: <input class="content_editor" name="price_editor" type="number" min="0" step="0.1" value="${car.pricePerDay}">
            </div>
            <div class="editor_block__image">
                <input id="image_input" name="image_editor" type='file' onchange="readURL(this);"/>
                <img id="uploaded image" src="${car.imagePath}" alt="your image"/>
            </div>
            <div class="navigation">
                <a id="view" href="#preview">${preview}</a>
                <input type="submit" value="${submit}">
                <c:choose>
                    <c:when test="${car ne null}">
                        <input type="hidden" name="command" value="editcar">
                        <input type="hidden" name="data_id" value="${car.id}">
                        <input type="hidden" name="image_path" value="${car.imagePath}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="addcar">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <%--    TODO: change to car block--%>
    <div class="preview" id="preview">
        <div class="car_block">
            <div class="car_block__brand">
                <span>${car.brand} ${car.model}</span>
            </div>
            <div class="car_block__img">
                <img src="${car.imagePath}"/>
            </div>
            <div class="car_block__characteristics">
                ${color}: ${car.color}<br/>
                ${mileage}: ${car.mileage} KM<br/>
                ${gearbox}: ${car.gearbox.toString().toLowerCase()}<br/>
                ${year}: ${car.manufacturedYear}<br/>
                ${engine}: ${car.engineType.toString().toLowerCase()}<br/>
                ${car_class}: ${car.carClass.toString().toLowerCase()}<br/>
            </div>
            <div class="car_block__price">
                ${price}: ${car.pricePerDay} BYN
            </div>
        </div>
    </div>
</form>
</body>
</html>
