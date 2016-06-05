import org.junit.Assert;
import org.junit.Test;

import com.btg.weather.WeatherService;
import com.btg.weather.exception.ErrorCode;
import com.btg.weather.exception.WeatherServiceException;


public class WeatherServiceTest {
	WeatherService weatherService = new WeatherService();

	@Test
	public void testGetWeatherWithCityAsNull() {
		try {
			weatherService.getWeather(null);
		} catch (WeatherServiceException e) {
			Assert.assertTrue(ErrorCode.CITY_NOT_VALID == e.getErrorCode());
		}
	}
	
	@Test
	public void testGetWeatherWithCityAsBlank() {
		try {
			weatherService.getWeather("");
		} catch (WeatherServiceException e) {
			Assert.assertTrue(ErrorCode.CITY_NOT_VALID == e.getErrorCode());
		}
	}
}
