<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/user_orders.css" type="text/css"/>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>

</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<c:set var="is_admin" value="${sessionScope.user.role eq 'ADMIN'}"/>
<div class="main_block">

<%--    <table>--%>
<%--        <tr>--%>
<%--            <c:if test="${is_admin}">--%>
<%--                <th>User</th>--%>
<%--            </c:if>--%>
<%--            <th>Car</th>--%>
<%--            <th>Start date</th>--%>
<%--            <th>End date</th>--%>
<%--            <th>Status</th>--%>
<%--            <th>Rejection comment</th>--%>
<%--            <th>Return comment</th>--%>
<%--        </tr>--%>
<%--        <c:forEach var="order" items="${requestScope.orders}">--%>
<%--            <tr>--%>
<%--                <c:if test="${is_admin}">--%>
<%--                    <td class="info">${order.userId}</td>--%>
<%--                </c:if>--%>
<%--                <td class="info">${order.carId}</td>--%>
<%--                <td class="info">${order.startDate}</td>--%>
<%--                <td class="info">${order.endDate}</td>--%>
<%--                <td class="info">${order.statusId}</td>--%>
<%--                <td class="info">${order.rejectionComment}</td>--%>
<%--                <td class="info">${order.returnComment}</td>--%>
<%--                <td class="button_panel">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${is_admin}">--%>
<%--                            <a href="#">Confirm</a>--%>
<%--                            <a href="#">Reject</a>--%>
<%--                            <a href="Controller?command=deleteorder" onclick="return confirm('')">Delete</a>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <a href="#">Edit</a>--%>
<%--                            <c:if test="${order.statusId == 3}">--%>
<%--                                <a href="#">Delete</a>--%>
<%--                            </c:if>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>

<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--    </table>--%>
</div>

<div class="main_block">
    <display:table name="requestScope.orders" uid="order" class="table" pagesize="8" requestURI=""
                   sort="list" defaultorder="ascending">
        <c:if test="${is_admin}">
            <display:column title="User" sortable="true" sortProperty="userId">
                ${order.userId}
            </display:column>
        </c:if>
        <display:column title="Car" sortable="true" sortProperty="carId">
            ${order.carId}
        </display:column>
        <display:column title="Start date" sortable="true" sortProperty="startDate">
            ${order.startDate}
        </display:column>
        <display:column title="End date" sortable="true" sortProperty="endDate">
            ${order.endDate}
        </display:column>
        <display:column title="Status" sortable="true" sortProperty="statusId">
            ${order.statusId}
        </display:column>
        <display:column title="Rejection comment">
            ${order.rejectionComment}
        </display:column>
        <display:column title="Return comment">
            ${order.returnComment}
        </display:column>
    </display:table>
</div>
</body>
</html>
