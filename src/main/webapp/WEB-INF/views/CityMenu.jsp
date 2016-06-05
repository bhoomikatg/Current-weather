<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Weather</title>
</head>
<body>
 <form:form method="post" action="processCityWeather" commandName="wb">
 <table>
 <tr>
 <td>City</td>
 <td>
 <form:select path="city">
 <form:option value="" label="Select" />
 <form:options items="${cityList}"/>
 </form:select>
 </td>
 </tr>
 <tr>
 <td></td><td><input type="submit"></td>
 </tr>
 </table>
 </form:form>
 <table>
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
 <td>${wb.temprature})C</td>
 </tr>
 <tr>
 <td>Wind</td>
 <td>${wb.windSpeed} km/h</td>
 </tr>

 </table>
</body>
</html>