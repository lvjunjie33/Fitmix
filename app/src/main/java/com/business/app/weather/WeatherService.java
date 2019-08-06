package com.business.app.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.core.entity.PopUp;
import com.business.core.entity.cityNo.CityNoCoreDao;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.SmartWeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weird on 2016/5/19.
 */
@Service
public class WeatherService {

    @Autowired
    private CityNoCoreDao cityNoCoreDao;

     public PopUp getWeather(String currentCity){
         if(currentCity.contains("市")||currentCity.contains("区")||currentCity.contains("县")){
             currentCity = currentCity.substring(0,currentCity.length()-1);
         }
         int areaId = cityNoCoreDao.findCityByAreaCh(currentCity, "areaId").getAreaId();
         String interfaceURL = SmartWeatherUtil.getActionURL(areaId + "", "forecast_v");
         PopUp popUp = new PopUp();
         //获取天气
         JSONObject jsonObject = JSON.parseObject(HttpUtil.get(interfaceURL));
         //获取返回json的元素f（key:"f"）的value(即天气预报)
         JSONObject j_f = JSON.parseObject(jsonObject.get("f").toString());
         //获取返回json元素f.f1（"key:"f1""）的value(即为三天的天气预报)
         JSONArray j_f1 = JSON.parseArray(j_f.get("f1").toString());
         //获取返回json元素f.f1[0]的value(即当天天气预报)
         JSONObject result = JSON.parseObject(j_f1.get(0).toString());
         //获取返回json元素f.f1[0].fc(fd、fa、fb)的value(即白天天气现象、晚上天气现象、白天温度、晚上温度)
         popUp.setTemperature1(Integer.parseInt(result.get("fc").toString()));
         popUp.setTemperature2(Integer.parseInt(result.get("fd").toString()));
         popUp.setWeather1(result.get("fa").toString());
         popUp.setWeather2(result.get("fb").toString());
         popUp.setAreaCh(currentCity);
         return popUp;
     }
}
