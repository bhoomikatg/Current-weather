<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Weather</title>
</head>
<body>
 <form:form method="post" action="current-weather" commandName="wb">
 <table>
 <tr>
 <td>City</td>
 <td>
 <form:select path="city" onchange="this.form.submit()">
 <form:option value="" label="Select" />
 <form:options items="${cityList}"/>
 </form:select>
 </td>
 </tr>
 </table>
 </form:form>
 <br>
 <c:set var="city" scope="session" value="${wb.city}"/>
<c:if test="${not empty city}">
 <table border="1">
<tr>
 <td>City</td>
 <td> ${wb.city}</td>
 </tr>
 <tr>
 <td>Updated time</td>
 <td>${wb.updatedTime}</td>
 </tr>
 <tr>
 <tr>
 <td>Weather</td>
 <td>${wb.weather}</td>
 </tr>
  <tr>
 <td>Temperature</td>
 <td>${wb.temprature}&deg;C</td>
 </tr>
 <tr>
 <td>Wind</td>
 <td>${wb.windSpeed} km/h</td>
 </tr>

 </table>
</c:if>
</body>
</html>