package com.business.core.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * Created by edward on 2018/4/26.
 * lvjj add
 */
public class HFWeather {


    //用户认证key
    private static final String KEY = "f7e56f7c637a4a9ea86e8acd31f4212d";
    //用户ID
    private static final String USERNAME="HE1804241423401045";
    //城市查询
//    private static final String CITYURL="https://api.heweather.com/s6/search";
    //城市查询最新接口
    private static final String CITYURL="https://search.heweather.com/find";
    //空气质量数据集合
    private static final String AIRURL="https://api.heweather.com/s6/air";
    //常规天气数据集合
    private static final String WEATHERURL="https://api.heweather.com/s6/weather";
    //天气灾害预警
    private static final String ALARMURL="https://api.heweather.com/s6/alarm";
    /**
     * 获取和风天气
     * @param lonlat 城市名称
     * @return
     */
    public static JSONObject getWeather(String lonlat,String language) {
        String airurl = AIRURL+"?key="+KEY+"&location=" + getCity(lonlat)+"&lang="+language;
        String weatherurl = WEATHERURL+"?key="+KEY+"&location=" + lonlat+"&lang="+language;
        String alarmurl = ALARMURL+"?key="+KEY+"&location=" + lonlat+"&lang="+language;
        JSONObject airResult=JSON.parseObject(HttpUtil.get(airurl));
        JSONObject weatherResult=JSON.parseObject(HttpUtil.get(weatherurl));
        JSONObject alarmResult=JSON.parseObject(HttpUtil.get(alarmurl));
        JSONObject allresult=new JSONObject();
        allresult.put("airResult",airResult);
        allresult.put("weatherResult",weatherResult);
        allresult.put("alarmResult",alarmResult);
        return allresult;
    }

    public  static String getCity(String lonlat){
        String alarmurl = CITYURL+"?key="+KEY+"&location=" + lonlat+"&username"+USERNAME;
        JSONObject cityResult=JSON.parseObject(HttpUtil.get(alarmurl));
        JSONArray jsonArray = cityResult.getJSONArray("HeWeather6");
        JSONArray jSONArray = (JSONArray) jsonArray.getJSONObject(0).get("basic");
        String city="";
        if(jSONArray!=null) {
            if(jSONArray.getJSONObject(0)!=null) {
                if (jSONArray.getJSONObject(0).get("parent_city") != null) {
                    city = jSONArray.getJSONObject(0).get("parent_city").toString();
                }
            }
        }
        return city;
    }

    public static void main(String[] arg0) {
        JSONObject jsonObject =HFWeather.getWeather("114.15,22.15","en");
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
        //接口状态
        String stauts = jsonArray.getJSONObject(0).get("status").toString();
        //天气预报
        String daily_forecast = jsonArray.getJSONObject(0).get("daily_forecast").toString();
        //基础信息
        String basic = jsonArray.getJSONObject(0).get("basic").toString();
        //接口更新时间
        String update = jsonArray.getJSONObject(0).get("update").toString();
//        System.out.println("status:" + stauts + " now:" + now + " suggestion:" +suggestion);
        System.out.println(stauts);
        System.out.println(daily_forecast);
        System.out.println(basic);
        System.out.println(update);
        System.out.println(jsonArray.toString());
    }
}
