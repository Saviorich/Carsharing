<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                    ${car.color}<br/>
                    ${car.mileage}<br/>
                    ${car.gearbox}<br/>
                    ${car.manufacturedYear}<br/>
                    ${car.engineType}<br/>
                    ${car.carClass}<br/>
            </div>
            <div class="car_block__price">
                ${car.pricePerDay}
            </div>
            <div class="car_block__button">
                <button type="button" name="" value="">Order</button>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
