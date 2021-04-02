<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>

    <fmt:bundle basename="content">
        <fmt:message key="register.first_name" var="first_name"/>
        <fmt:message key="register.second_name" var="second_name"/>
        <fmt:message key="payment.holder" var="holder"/>
        <fmt:message key="payment.card_number" var="card_number"/>
        <fmt:message key="payment.expiry_date" var="expiry"/>
        <fmt:message key="payment.pay" var="pay"/>
        <fmt:message key="orders.total_price" var="total_price"/>
    </fmt:bundle>
    <title></title>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="css/payment.css" type="text/css">
    <script type="text/javascript" rel="script" src="js/payment.js"></script>
</head>
<body>

<div class="payment">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="makepayment">
        <input type="hidden" name="data_id" value="${requestScope.data.id}">
        <label>
            ${holder}
            <input name="holder" type="text" placeholder="${second_name} ${first_name}" required>
        </label>
        <label>
            ${card_number}
            <input name="card_number" type="number" placeholder="1111222233334444" pattern="[0-9]{16}" required>
        </label>
        <label>
            ${expiry}
            <input name="expiry_date" type="date" pattern="yyyy-MM-dd" required>
        </label>
        <label>
            CVV
            <input name="cvv" placeholder="123" pattern="[0-9]{3}" required>
        </label>
        <c:out value="${total_price}: ${requestScope.total_price}"/>
        <input class="button" type="submit" value="${pay}">
    </form>
    <c:set var="is_error" scope="request" value="${requestScope.error}"/>
    <c:set var="is_invalid_data" scope="request" value="${requestScope.validation}"/>

    <c:choose>
        <c:when test="${is_error}">
            ${error_message}
        </c:when>
        <c:when test="${is_invalid_data}">
            ${validation_message}
        </c:when>
    </c:choose>


</div>

</body>
</html>
