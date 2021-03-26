<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sharing" uri="carsharing" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>
    <link rel="stylesheet" href="css/news.css" type="text/css"/>

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
        <fmt:message key="header.cars" var="cars"/>
        <fmt:message key="delete_dialog.message" var="message"/>

        <fmt:message key="car.class_hatchback" var="hatchback_class"/>
        <fmt:message key="car.class_muscle" var="muscle_class"/>
        <fmt:message key="car.class_pickup" var="pickup_class"/>
        <fmt:message key="car.class_sedan" var="sedan_class"/>
        <fmt:message key="car.class_sport" var="sport_class"/>
        <fmt:message key="car.class_suv" var="suv_class"/>
        <fmt:message key="car.class_wagon" var="wagon_class"/>
    </fmt:bundle>
    <title>${cars}</title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<div class="admin_button_panel">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a id="admin_but_add" href="Controller?command=gotocareditpage">${add_car}</a>
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
                    ${car_class}: <sharing:ConstantFormatTag constant="${car.carClass}" enumeration="CarClass"/><br/>
            </div>
            <div class="car_block__price">
                ${price}: ${car.pricePerDay} BYN
            </div>
            <div class="car_block__button">
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                        <a href="Controller?command=gotocareditpage&data_id=${car.id}">${edit}</a>
                        <a href="Controller?command=deletecar&data_id=${car.id}" onclick="return confirm('${message}')">${delete}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="Controller?command=gotoorderpage&data_id=${car.id}">${order}</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
