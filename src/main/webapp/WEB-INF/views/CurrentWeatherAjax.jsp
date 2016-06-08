<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.hidden {
	visibility: hidden;
}
</style>
<title>Current weather</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">
	function fetchWeather() {

		// get the form value
		var selectedCity = $('#selectedCity').val();

		if (selectedCity == '') {
			$('#wait').html("Please select a city")
			wipeTable();

		} else {
			$('#wait').html("Please wait while we retrieve current weather...")
			$.ajax({

				type : "POST",
				url : "/current-weather",
				data : "city=" + selectedCity,
				success : function(response) {
					// we have the response					
					$('#wait').html("<br>")
					$('#cityRow').html(selectedCity)
					$('#updatedTimeRow').html(response.updatedTime)
					$('#weatherRow').html(response.weather)
					$('#tempRow').html(response.temprature + "&deg;" + "C")
					$('#windRow').html(response.windSpeed + "km/h")
					$('#weather').removeClass("hidden")
				},
				error : function(){
					$('#wait').html("An error occurred, please try again later.")
					wipeTable();
				}
			});
		}
	}
	function wipeTable(){
			$('#cityRow').html("")
			$('#updatedTimeRow').html("")
			$('#weatherRow').html("")
			$('#tempRow').html("" + "&deg;" + "C")
			$('#windRow').html("" + "km/h")
	}
</script>
</head>
<body>
	<form:form commandName="wb">
		<table>
			<tr>
				<td>City</td>
				<td><form:select path="city" id="selectedCity"
						onchange="fetchWeather()">
						<form:option value="" label="Select" />
						<form:options items="${cityList}" />
					</form:select></td>
			</tr>
		</table>
		<div id="wait" style="color: green;"></div>
	</form:form>
	<br>
	<table border="1" class="hidden" id="weather">
		<tr>
			<td>City</td>
			<td id="cityRow"></td>
		</tr>
		<tr>
			<td>Updated time</td>
			<td id="updatedTimeRow"></td>
		</tr>
		<tr>
		<tr>
			<td>Weather</td>
			<td id="weatherRow"></td>
		</tr>
		<tr>
			<td>Temperature</td>
			<td id="tempRow"></td>
		</tr>
		<tr>
			<td>Wind</td>
			<td id="windRow"></td>
		</tr>

	</table>
</body>
</html>
