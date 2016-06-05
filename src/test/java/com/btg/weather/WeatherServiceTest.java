package com.btg.weather;
import org.junit.Assert;
import org.junit.Test;

import com.btg.weather.WeatherService;
import com.btg.weather.exception.ErrorCode;
import com.btg.weather.exception.WeatherServiceException;
import com.github.fedy2.weather.data.Channel;


public class WeatherServiceTest {
	WeatherService weatherService = new WeatherService();

	@Test
	public void testGetWeatherWithCityAsNull() {
		try {
			weatherService.getWeather(null);
			Assert.fail("It should have been fail as city id null");
		} catch (WeatherServiceException e) {
			Assert.assertTrue(ErrorCode.CITY_INVALID == e.getErrorCode());
		}
	}
	
	@Test
	public void testGetWeatherWithCityAsBlank() {
		try {
			weatherService.getWeather("");
			Assert.fail("It should have been fail as city id blank");
		} catch (WeatherServiceException e) {
			Assert.assertTrue(ErrorCode.CITY_INVALID == e.getErrorCode());
		}
	}
	
	@Test
	public void testGetWeatherWithValidCity() {
		try {
			Channel ch = weatherService.getWeather("Sydney");
			Assert.assertNotNull(ch);
		} catch (WeatherServiceException e) {
			Assert.fail("It should not have been fail as city is valid");
		}
	}
}
