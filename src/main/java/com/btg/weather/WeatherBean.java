package com.btg.weather;

import java.util.Date;

public class WeatherBean {
	String city;
	Date updatedTime;
	String weather;
	Integer temprature;
	Float windSpeed;	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}	
	
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Integer getTemprature() {
		return temprature;
	}

	public void setTemprature(Integer temprature) {
		this.temprature = temprature;
	}

	public Float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Float windSpeed) {
		this.windSpeed = windSpeed;
	}
}
