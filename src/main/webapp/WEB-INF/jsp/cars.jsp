<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>

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
        <fmt:message key="admin.delete" var="delete"/>
        <fmt:message key="admin.edit" var="edit"/>
        <fmt:message key="admin.add_car" var="add_car"/>
    </fmt:bundle>

</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<div class="admin_button_panel">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a id="admin_but_add">${add_car}</a>
    </c:if>
</div>
<div class="main_block">
    <c:forEach var="car" items="${requestScope.cars}">
        <div class="car_block">
            <div class="car_block__brand">
                    ${car.brand} ${car.model}
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
            <div class="car_block__button">
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                        <button id="admin_but_edit" type="button" name="" value="">${edit}</button>
                        <button id="admin_but_delete" type="button" name="" value="">${delete}</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" name="" value="">${order}</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
