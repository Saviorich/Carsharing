<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content" prefix="car.">
        <fmt:message key="color" var="color"/>
        <fmt:message key="class" var="car_class"/>
        <fmt:message key="year" var="year"/>
        <fmt:message key="gearbox" var="gearbox"/>
        <fmt:message key="engine" var="engine"/>
        <fmt:message key="price" var="price"/>
        <fmt:message key="mileage" var="mileage"/>
    </fmt:bundle>

</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
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
                ${price}: ${car.pricePerDay} BR
            </div>
            <div class="car_block__button">
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                        <button id="admin_but1" type="button" name="" value="">Edit</button>
                        <button id="admin_but2" type="button" name="" value="">Delete</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" name="" value="">Order</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
