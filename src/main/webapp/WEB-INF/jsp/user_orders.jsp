<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sharing" uri="carsharing" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/user_orders.css" type="text/css"/>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>

    <fmt:bundle basename="content">
        <fmt:message key="order.start_date" var="start_date"/>
        <fmt:message key="order.end_date" var="end_date"/>
        <fmt:message key="header.cars" var="cars"/>
        <fmt:message key="orders.new_status" var="new_status"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<c:set var="is_admin" value="${sessionScope.user.role eq 'ADMIN'}"/>

<div class="main_block">
    <display:table name="requestScope.orders" uid="order" class="table" pagesize="8" requestURI=""
                   sort="list" defaultorder="ascending">
        <c:if test="${is_admin}">
            <display:column title="User" sortable="true" sortProperty="user.email">
                ${order.user.email}
            </display:column>
        </c:if>
        <display:column title="${cars}" sortable="true" sortProperty="car.brand">
            ${order.car.brand} ${order.car.model}
        </display:column>
        <display:column title="${start_date}" sortable="true" sortProperty="startDate">
            ${order.startDate}
        </display:column>
        <display:column title="${end_date}" sortable="true" sortProperty="endDate">
            ${order.endDate}
        </display:column>
        <display:column title="Status" sortable="true" sortProperty="status">
            <sharing:ConstantFormatTag constant="${order.status}" enumeration="OrderStatus"/>
        </display:column>
        <display:column title="Rejection comment">
            ${order.rejectionComment}
        </display:column>
        <display:column title="Return comment">
            ${order.returnComment}
        </display:column>
        <display:column title="Actions">
            <div class="actions">
                <a id="approve" href="#">Approve</a><br/>
                <a id="reject" href="#">Reject</a><br/>
                <a href="#">Change status</a><br/>
            </div>
        </display:column>
    </display:table>
</div>
</body>
</html>
