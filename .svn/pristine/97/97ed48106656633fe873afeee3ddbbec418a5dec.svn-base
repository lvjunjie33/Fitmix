package com.business.core.operations.schedulerValue;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.entity.SchedulerValue;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sin on 2015/10/14.
 *
 * <p>
 *     定时器数据值，定时更新数据
 * </p>
 */
@Service
public class SchedulerValueCoreService {

    @Autowired
    private SchedulerValueCoreDao schedulerValueCoreDao;

    /**
     * 获取新的 token
     * @return 新的token
     */
    public String getNewAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid=%s&secret=%s", Constants.WX_APPID, Constants.WX_SECRET);
        String result = HttpUtil.get(url);
        return JSON.parseObject(result).get("access_token").toString();
    }

    /**
     * 获取现在使用的token
     * <p>
     *     等于 null 时，从微信服务器获取新的 token 保存并返回
     * </p>
     * @return
     */
    public String getCurrentAccessToken() {
        SchedulerValue schedulerValue = schedulerValueCoreDao.findSchedulerValue("wxMqAccessToken");
        /*if (null == schedulerValue.getWxMqAccessToken()) {
            schedulerValueCoreDao.schedulerValueUpsert(Update.update("wxMqAccessToken", getNewAccessToken()));
        }*/
        return schedulerValue.getWxMqAccessToken();
    }

    /**
     * 获取 新的 jsapi_ticket
     * @param token
     * @return
     */
    public String getNewJsapiTicket(String token) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", token);
        String result = HttpUtil.get(url);
        return JSON.parseObject(result).get("ticket").toString();
    }

}
