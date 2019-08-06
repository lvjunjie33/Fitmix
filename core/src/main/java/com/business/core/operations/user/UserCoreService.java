package com.business.core.operations.user;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.user.User;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.DicUtil;
import com.business.core.utils.HttpUtil;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Service
public class UserCoreService {

    /**
     * 处理用户头像 url地址
     * @param user 用户对象
     */
    public void buildUserFileUrl(User user) {
        if (user != null && null != user.getAvatar()) {
            if (user.getAvatar().indexOf("http") == -1) {
                user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            }
        }
    }

    /**
     * 处理用户头像 url地址
     * @param userCollection 用户集合
     */
    public void buildUserFileUrl(Collection<User> userCollection) {
        for (User user : userCollection) {
            buildUserFileUrl(user);
        }
    }

    /**
     *  注册渠道
     * @param channel
     * @return
     */
    public String buildChannel(String channel) {
        String result = "";
        if (StringUtils.isEmpty(channel)) {
            return result;
        }
        Pattern pattern = Pattern.compile("\\d+");
        if (pattern.matcher(channel).matches() && channel.length() < 4) { // 处理不规范 channel， 超出 Integer 取值范围
            Dictionary registerChannelDic = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL, Integer.valueOf(channel)); //注册渠道
            if (registerChannelDic != null) {
                result = registerChannelDic.getName();
            }
        }
        return result;
    }

    public String buildTerminal(Integer registerType) {
        String result = "";
        if (null != registerType) {
            switch (registerType) {
                case 1:
                    result = "app";
                    break;
                case 2:
                    result = "QQ";
                    break;
                case 3:
                    result = "微信";
                    break;
                case 4:
                    result = "微博";
                    break;
            }
        }
        return result;
    }

}
