<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>
    <link rel="stylesheet" href="css/order.css" type="text/css"/>
    <fmt:bundle basename="content">
        <fmt:message key="car.order" var="order"/>
        <fmt:message key="car.color" var="color"/>
        <fmt:message key="car.class" var="car_class"/>
        <fmt:message key="car.year" var="year"/>
        <fmt:message key="car.gearbox" var="gearbox"/>
        <fmt:message key="car.engine" var="engine"/>
        <fmt:message key="car.price" var="price"/>
        <fmt:message key="car.mileage" var="mileage"/>
        <fmt:message key="admin.submit" var="submit"/>
    </fmt:bundle>
    <title></title>
</head>
<body>
<c:set var="car" value="${requestScope.car}"/>
<div class="order_main_block">
    <div class="car_block" style="width: 440px;">
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
    </div>
    <div class="form">
        <form name="Controller" method="get">
            Start Date<br/>
            <input name="start_date" type="date" value=""><br/>
            End Date<br/>
            <input name="end_date" type="date" value=""><br/>

            <button type="submit" value="">${submit}</button>
        </form>
    </div>
</div>
</body>
</html>
