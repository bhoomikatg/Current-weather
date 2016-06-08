package com.btg.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBException;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.btg.weather.exception.ErrorCode;
import com.btg.weather.exception.WeatherServiceException;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.google.gson.Gson;

@Service
public class WeatherService implements MessageSourceAware {
	
	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Gets weather for the given city. Uses Yahoo API.
	 * 
	 * @param city
	 * name of the city for which weather is to be retrieved.
	 * @return
	 * an object representing the weather details.
	 * @throws WeatherServiceException
	 *             if city is null or blank or any other error occurred.
	 */
	public Channel getWeather(String city) throws WeatherServiceException {
		YahooWeatherService service;
		Channel channel = null;
		if (StringUtils.isEmpty(city)) {			
			throw new WeatherServiceException(messageSource.getMessage(ErrorCode.CITY_INVALID, null, null));
		}
		try {
			// find woeid(where on earth identifier) for the given city
			Integer woeid = getWoeid(city);			
			if (woeid != null) {
				service = new YahooWeatherService();
				channel = service.getForecast(woeid.toString(),
						DegreeUnit.CELSIUS);				
			}
		} catch (JAXBException | IOException e) {
			throw new WeatherServiceException(messageSource.getMessage(ErrorCode.GENERIC_ERROR, null, null), e);
		}
		return channel;
	}

	private Integer getWoeid(String city) throws IOException {
		Integer woeid = null;
		String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
		String query = "select woeid from geo.places where text=\"" + city
				+ "\"";

		String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8")
				+ "&format=json";
		URL fullUrl = new URL(fullUrlStr);
		try (InputStream is = fullUrl.openStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr)) {
			String result = "";
			String read;
			while ((read = br.readLine()) != null) {
				result += read;
			}
			Gson gson = new Gson();
			ResultArray resultObj = gson.fromJson(result, ResultArray.class);
			if (resultObj != null && resultObj.query.results != null) {
				woeid = resultObj.query.results.place[0].woeid;
			}
		}
		return woeid;
	}
	/**
	 * Classes to store json result
	 * @author bgalrani
	 *
	 */
	private static class ResultArray {
		public QueryArray query;
	}

	private static class QueryArray {
		public ResultsArray results;
	}

	private static class ResultsArray {
		public Place[] place;
	}

	private static class Place {
		public int woeid;
	}
}