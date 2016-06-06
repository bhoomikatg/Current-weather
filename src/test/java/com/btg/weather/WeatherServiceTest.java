package com.btg.weather;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import com.btg.weather.exception.ErrorCode;
import com.btg.weather.exception.WeatherServiceException;
import com.github.fedy2.weather.data.Channel;

@RunWith (MockitoJUnitRunner.class)
public class WeatherServiceTest {
	@Mock MessageSource messageSource;
	@InjectMocks WeatherService weatherService = new WeatherService();
	@Before
	public void setUp() {
		Mockito.when(messageSource.getMessage(ErrorCode.CITY_INVALID, null, null)).thenReturn(ErrorCode.CITY_INVALID);
	}
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
