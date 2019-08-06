package com.business.app.surprise;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.support.BaseServiceSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.CityNo;
import com.business.core.entity.PopUp;
import com.business.core.entity.cityNo.CityNoCoreDao;
import com.business.core.entity.surprise.Surprise;
import com.business.core.operations.sysManager.SysManagerService;
import com.business.core.utils.BaiduWeatherUtil;
import com.business.core.utils.HFWeather;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.SmartWeatherUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fenxio on 2016/5/19.
 * update lvjj 2018/4/26
 */
@Service
public class SurpriseService extends BaseServiceSupport {

    @Autowired
    private SurpriseDao surpriseDao;
    @Autowired
    private CityNoCoreDao cityNoCoreDao;
    @Autowired
    private SysManagerService sysManagerService;

    /**
     * 获取有效彩蛋信息
     * @return
     */
    public Surprise findValidSurprise(String cityName) {
        Criteria criteria = new Criteria();
        Long nowTime = System.currentTimeMillis();
        criteria.and("state").is(Surprise.STATE_2).and("startTime").lte(nowTime).and("endTime").gte(nowTime);
        Query query = new Query(criteria);
        Surprise surprise = surpriseDao.findOne(query, Surprise.class);
        if(null == surprise) {
            surprise = new Surprise();
            surprise.setType(Surprise.TYPE_DEFAULT);
        } else {

            if (getTerminalType() == Constants.TERMINAL_ANDROID
                    && getContext().getVersion() >= VersionConstants.Android.VERSION_39.getVersion()
                    || getTerminalType() == Constants.TERMINAL_IOS
                    && getContext().getVersion() >= VersionConstants.IOS.VERSION_9.getVersion()) {

                if(cityName.endsWith("市") && cityName.length() > 2) {
                    cityName = cityName.substring(0, cityName.length() - 1);
                }

                JSONObject jsonObject = null;

                //获取百度天气
                if(surprise.getType() == Surprise.TYPE_WEATHER) {
                    try {
                        jsonObject = JSON.parseObject(BaiduWeatherUtil.getWorldWeather(cityName));
//                        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
                        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                        String stauts = jsonArray.getJSONObject(0).get("status").toString();
                        if(stauts.equals("ok")) {
                            JSONObject resultJSON = new JSONObject();
//                            resultJSON.put("now", jsonArray.getJSONObject(0).getJSONObject("now"));
                            resultJSON.put("daily_forecast", jsonArray.getJSONObject(0).getJSONObject("daily_forecast"));
//                            resultJSON.put("suggestion" , jsonArray.getJSONObject(0).getJSONObject("suggestion"));
                            resultJSON.put("basic" , jsonArray.getJSONObject(0).getJSONObject("basic"));
                            surprise.setBaiduWeather(resultJSON);
                        } else {
                            surprise = new Surprise();
                            surprise.setType(Surprise.TYPE_DEFAULT);
                        }
                    }catch (Exception e) { //TODO 2016-11-24最近百度的天气接口经常异常(整点时刻)，临时这样处理
                        surprise = new Surprise();
                        surprise.setType(Surprise.TYPE_DEFAULT);

                        Map<String, Object> error = new HashMap<>();
                        error.put("url", "/surprise/get-surprise.json");
                        error.put("cityName", cityName);
                        error.put("error", ExceptionUtils.getStackTrace(e));
                        error.put("backInfo", JSON.toJSONString(jsonObject));
                        sysManagerService.saveCommonOversee("set-wx-step.in", JSON.toJSONString(error), "");
                    }
                }

            } else {
                if(surprise.getType() == Surprise.TYPE_WEATHER) {
                    if(cityName.endsWith("市") && cityName.length() > 2) {
                        cityName = cityName.substring(0, cityName.length() - 1);
                    }
                    CityNo cityNo = cityNoCoreDao.findCityByAreaCh(cityName, "areaId");
                    if(cityNo == null) {
                        surprise = new Surprise();
                        surprise.setType(Surprise.TYPE_DEFAULT);
                    } else {
                        int areaId = cityNo.getAreaId();
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

                        if(result.get("fc").toString().equals("") || result.get("fd").toString().equals("")) { //获取天气失败 返回默认彩蛋
                            surprise = new Surprise();
                            surprise.setType(Surprise.TYPE_DEFAULT);
                        } else {
                            //获取返回json元素f.f1[0].fc(fd、fa、fb)的value(即白天天气现象、晚上天气现象、白天温度、晚上温度)
                            popUp.setTemperature1(Integer.parseInt(result.get("fc").toString()));
                            popUp.setTemperature2(Integer.parseInt(result.get("fd").toString()));
                            popUp.setWeather1(result.get("fa").toString());
                            popUp.setWeather2(result.get("fb").toString());
                            popUp.setAreaCh(cityName);
                            surprise.setPopUp(popUp);
                        }
                    }
                }
            }
        }
        buildSurpriseImgUrl(surprise);
        return surprise;
    }

    /**
     * 构建彩蛋图片链接
     * @param surprise 彩蛋对象
     */
    public void buildSurpriseImgUrl(Surprise surprise) {
        if (surprise != null) {
            if (null != surprise.getImgUrl()) {
                surprise.setImgUrl(FileCenterClient.buildUrl(surprise.getImgUrl()));
            }
        }
    }




    /**
     * 获取天气信息
     * @return
     */
    public JSONObject findWeather(String lonlat,String language) {

        JSONObject allresult=HFWeather.getWeather(lonlat,language);
        JSONObject airResult= (JSONObject) allresult.get("airResult");
        JSONArray airArray = airResult.getJSONArray("HeWeather6");
        JSONObject airNowCity = (JSONObject) airArray.getJSONObject(0).get("air_now_city");
        String airStatus= airArray.getJSONObject(0).get("status").toString();


        JSONObject weatherResult= (JSONObject) allresult.get("weatherResult");
        JSONArray weatherArray = weatherResult.getJSONArray("HeWeather6");
        JSONArray dailyForecast = (JSONArray) weatherArray.getJSONObject(0).get("daily_forecast");
        JSONArray lifestyle = (JSONArray) weatherArray.getJSONObject(0).get("lifestyle");
        JSONObject basic = (JSONObject) weatherArray.getJSONObject(0).get("basic");
        JSONObject update = (JSONObject) weatherArray.getJSONObject(0).get("update");
        String weatherStatus= weatherArray.getJSONObject(0).get("status").toString();

        JSONObject alarmResult=(JSONObject)allresult.get("alarmResult");
        JSONArray alarmArray = alarmResult.getJSONArray("HeWeather6");
        JSONArray alarm = (JSONArray) alarmArray.getJSONObject(0).get("alarm");
        String alarmStatus= alarmArray.getJSONObject(0).get("status").toString();

        JSONObject resultData=new JSONObject();
        if("ok".equals(airStatus)){
            //AQI城市实况
            resultData.put("airNowCity",airNowCity);
        }
        if("ok".equals(weatherStatus)) {
            //接口更新时间
            resultData.put("update",update);
            //基础信息
            resultData.put("basic",basic);
            //天气预报
            resultData.put("dailyForecast", dailyForecast);
            //生活指数
            resultData.put("lifestyle", lifestyle);
        }
        if("ok".equals(alarmStatus)) {
            //灾害预警
            resultData.put("alarm", alarm);
        }
        return resultData;
    }

}
