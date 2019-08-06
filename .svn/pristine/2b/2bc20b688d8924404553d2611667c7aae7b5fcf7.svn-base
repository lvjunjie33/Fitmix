package com.business.app.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weird on 2016/5/19.
 */
@Controller
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("weather")
    public void getWeather(Model model, String currentCity){
        model.addAttribute("weather",weatherService.getWeather(currentCity));
    }
}
