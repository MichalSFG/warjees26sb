<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${message}</h3>
<c:forEach items="${seats}" var="item">
    <h3>${item.seatNumber}</h3>
</c:forEach>

</body>
</html>
