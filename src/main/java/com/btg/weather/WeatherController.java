package com.btg.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value="/weather")
	public String dispForm(Map<String, WeatherBean> model)
	{
		WeatherBean wb = new WeatherBean();
		model.put("wb",wb);
		return "CityMenu";		
	}
	
	@RequestMapping("/processCityWeather")
	public String processForm(@Valid @ModelAttribute("wb") WeatherBean wb,BindingResult result)
	{	
			Channel ch = null;
			try {
				ch = weatherService.getWeather(wb.getCity());
			} catch (WeatherServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Channel :"+ ch);
			if (ch != null){
				wb.setWindSpeed(ch.getWind().getSpeed());
				wb.setTemprature(ch.getItem().getCondition().getTemp());
				wb.setWeather(ch.getItem().getCondition().getText());
				wb.setUpdatedTime(ch.getItem().getCondition().getDate());
			}
			return "DisplayWeather";		
	}
}