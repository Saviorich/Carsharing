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
        <fmt:message key="order.start_date" var="start_date"/>
        <fmt:message key="order.end_date" var="end_date"/>
    </fmt:bundle>

    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<c:url value="/js/order.js"/>"></script>
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
            ${price}: <span>${car.pricePerDay}</span> BYN
        </div>
    </div>
    <form name="Controller" method="get" onsubmit="return validateDate()">
        <input type="hidden" name="command" value="makeorder">
        <div class="form">
            <div class="content">
                ${start_date}<br/>
                <input id="start" name="start_date" type="date" value="" required/><br/>
                ${end_date}<br/>
                <input id="end" name="end_date" type="date" value="" required/><br/>
                <div class="total_price">
                    <input id="total_price" type="hidden" name="total_price" value="" readonly="readonly" required/>
                    <input type="hidden" name="data_id" value="${car.id}">
                    <p>Total price: <span>0</span> BYN</p>
                </div>
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
