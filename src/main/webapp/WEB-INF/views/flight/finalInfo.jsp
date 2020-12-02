<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Flight number: FR ${flight.flightNumber}</h3>
<h3>Departure airport: ${flight.departureAirport} >>> Departure time: ${flight.scheduledTimeOfDept}</h3>
<h3>Arrival airport: ${flight.arrivalAirport} >>> Arrival time: ${flight.scheduledTimeOfArr}</h3>
<h1>Your seat: ${seat}</h1>
<h3>Miejsca, Które cię interesują:</h3>
<c:forEach items="${seats}" var="item">
    <h4>${item}</h4>
</c:forEach>
<p>Jeśli pasażer siedzący na jednym z miejsc ${seats} zaznaczył twoje miejsce (${seat}) jako
    interesujące go, będziesz mógł się z nim zamienić. Wynik będzie dostępny 20 min po starcie!</p>
<h3><a href="/flight/home">Przejdź do strony głównej</a> </h3>
</body>
</html>
