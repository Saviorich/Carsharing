<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sharing" uri="carsharing" %>
<html>
<head>
    <title></title>
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
        <fmt:message key="admin.submit" var="submit"/>
        <fmt:message key="admin.delete" var="delete"/>

        <fmt:message key="car.class_hatchback" var="hatchback_class"/>
        <fmt:message key="car.class_muscle" var="muscle_class"/>
        <fmt:message key="car.class_pickup" var="pickup_class"/>
        <fmt:message key="car.class_sedan" var="sedan_class"/>
        <fmt:message key="car.class_sport" var="sport_class"/>
        <fmt:message key="car.class_suv" var="suv_class"/>
        <fmt:message key="car.class_wagon" var="wagon_class"/>

        <fmt:message key="car.engine_diesel" var="diesel_engine"/>
        <fmt:message key="car.engine_gas" var="gas_engine"/>
        <fmt:message key="car.engine_petrol" var="petrol_engine"/>

        <fmt:message key="car.gearbox_manual" var="manual_gearbox"/>
        <fmt:message key="car.gearbox_automatic" var="automatic_gearbox"/>

        <fmt:message key="car.color_black" var="black_color"/>
        <fmt:message key="car.color_blue" var="blue_color"/>
        <fmt:message key="car.color_grey" var="grey_color"/>
        <fmt:message key="car.color_red" var="red_color"/>
        <fmt:message key="car.color_yellow" var="yellow_color"/>
        <fmt:message key="car.color_white" var="white_color"/>
        <fmt:message key="editor.error" var="error_message"/>
    </fmt:bundle>
</head>
<body>
<c:set var="car" value="${requestScope.car}"/>
<c:set var="comments" value="${requestScope.data}"/>

<jsp:include page="../jsp/header.jsp"/>
<div class="order_main_block">
    <div class="car_block" style="width: 440px;">
        <div class="car_block__brand">
            ${car.brand} ${car.model}
        </div>
        <div class="car_block__img">
            <img src="${car.imagePath}"/>
        </div>
        <div class="car_block__characteristics">
            ${color}: <sharing:ConstantFormatTag constant="${car.color}" enumeration="CarColor"/><br/>
            ${mileage}: ${car.mileage} KM<br/>
            ${gearbox}: <sharing:ConstantFormatTag constant="${car.gearbox}" enumeration="GearboxType"/><br/>
            ${year}: ${car.manufacturedYear}<br/>
            ${engine}: <sharing:ConstantFormatTag constant="${car.engineType}" enumeration="EngineType"/><br/>
            ${car_class}: <sharing:ConstantFormatTag constant="${car.carClass}" enumeration="CarClass"/><br/>
        </div>
        <div class="car_block__price">
            ${price}: <span>${car.pricePerDay}</span> BYN
        </div>
    </div>

    <c:if test="${requestScope.able_to_comment}">
        <form name="main_form" action="Controller?command=leavecomment" method="post">
            <textarea name="return_comment" placeholder="..." form="main_form"></textarea>
        </form>
    </c:if>

    <div class="comments">
        <c:forEach var="c" items="${comments}">
            <div class="comment">
                <div class="comment__header">
                    <c:out value="${c.user.email}"/>
                </div>
                <div class="comment__content">
                    <c:out value="${c.content}"/>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
