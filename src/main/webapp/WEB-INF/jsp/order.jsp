<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>
    <link rel="stylesheet" href="css/order.css" type="text/css"/>
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
        <fmt:message key="admin.submit" var="submit"/>
    </fmt:bundle>
    <title></title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
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
    <form name="Controller" method="get">
        <div class="form">
            <div class="content">
                Passport number<br/>
                <input name="passport_number" value="" pattern="([A-ZА-Я]{2})([0-9]{7})" placeholder="AA1234567" required><br/>
                Passport indetification number<br/>
                <input name="identification_number" value="" placeholder="1234567A123PB1"
                       pattern="([0-9]{7})[A-Z]([0-9]{3})[A-Z][A-Z][0-9]" required><br/>
                Passport issue date<br/>
                <input name="issue_date" type="date" value="" required><br/>
            </div>
        </div>

        <div class="form">
            <div class="content">
                Start Date<br/>
                <input name="start_date" type="date" value="" required><br/>
                End Date<br/>
                <input name="end_date" type="date" value="" required><br/>
            </div>
        </div>
        <div class="but">
            <button type="submit" value="">${submit}</button>
        </div>
    </form>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
