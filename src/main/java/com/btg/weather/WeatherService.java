package com.btg.weather;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.btg.weather.exception.ErrorCode;
import com.btg.weather.exception.WeatherServiceException;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;

@Service
public class WeatherService {
	
	/**
	 * Gets weather for the given city. Uses Yahoo API.
	 * @param city
	 * @return
	 * @throws WeatherServiceException 
	 * if city is null or blank.
	 */
	public Channel getWeather(String city) throws WeatherServiceException {
		YahooWeatherService service;
		Channel channel = null;
		if(StringUtils.isEmpty(city)) {
			throw new WeatherServiceException(ErrorCode.CITY_NOT_VALID);
		}
		try {
			service = new YahooWeatherService();
			//TODO remove hard coding
			channel = service.getForecast("1105779", DegreeUnit.CELSIUS);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}

}