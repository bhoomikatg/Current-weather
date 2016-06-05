package com.btg.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "CurrentWeather";		
	}
	
	@RequestMapping(value="/current-weather", method=RequestMethod.POST)
	@ExceptionHandler({WeatherServiceException.class})
	public String processForm(@Valid @ModelAttribute("wb") WeatherBean wb,BindingResult result) 
			throws WeatherServiceException
	{	
			Channel ch = null;
			ch = weatherService.getWeather(wb.getCity());			
			if (ch != null){
				wb.setWindSpeed(ch.getWind().getSpeed());
				wb.setTemprature(ch.getItem().getCondition().getTemp());
				wb.setWeather(ch.getItem().getCondition().getText());
				wb.setUpdatedTime(ch.getItem().getCondition().getDate());
			}
			return "CurrentWeather";		
	}
}