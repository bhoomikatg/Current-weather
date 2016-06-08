package com.btg.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.btg.weather.exception.WeatherServiceException;
import com.github.fedy2.weather.data.Channel;

@Controller
public class WeatherController {
	@Autowired
	WeatherService weatherService;
	
	@ModelAttribute("cityList")
	public List<String> getCity()
	{
		List<String> cityList = new ArrayList<>();
		cityList.add("Melbourne");
		cityList.add("Sydney");
		cityList.add("Wollongong");
		return cityList;
	}
	
	@RequestMapping(value="/current-weather", method=RequestMethod.GET)
	public String dispForm(Map<String, WeatherBean> model)
	{
		WeatherBean wb = new WeatherBean();
		model.put("wb",wb);
		return "CurrentWeatherAjax";		
	}
	
	@RequestMapping(value="/current-weather", method=RequestMethod.POST)
	@ResponseBody
	public WeatherBean processForm(@Valid @ModelAttribute("wb") WeatherBean wb,BindingResult result) 
			throws WeatherServiceException, ParseException
	{	
		Channel ch = null;
			ch = weatherService.getWeather(wb.getCity());	
			System.out.println(ch);
			if (ch != null){
				wb.setWindSpeed(ch.getWind().getSpeed());
				wb.setTemprature(ch.getItem().getCondition().getTemp());
				wb.setWeather(ch.getItem().getCondition().getText());
				
				wb.setUpdatedTime(ch.getItem().getCondition().getDate());
				System.out.println(wb.getUpdatedTime());
			}
			return wb;	
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleExcpetion(Exception e) {		
		if (e instanceof WeatherServiceException) {
			return ((WeatherServiceException) e).getErrorCode();
		} else {
			return e.getMessage();
		}
	}
}