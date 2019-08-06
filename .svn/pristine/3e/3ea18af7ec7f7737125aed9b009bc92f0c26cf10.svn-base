package script;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.client.WXCenterClient;
import com.business.core.entity.wx.WXUser;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.operations.wxUser.WXUserCoreDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sin on 2015/11/9.
 * <p>
 *     将公众号关注用户，同步到数据库
 * </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class WXUserScript extends BaseMongoDaoSupport {

    private static final String token = "Q4UqUIcwYMFQV2fG_dYLp94qQHcza3O1bKA9OBpqwS1qEH2HmynbMtAV1EIDFp8xSf1s1nj8_Yh6-f-xP8IAGT3_fjcK6OEa3s4tXjpyb1sVCYfAEAKRN";

    @Test
    public void execute() {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&next_openid=";
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(result);
        List<String> openids = (List<String>) ((JSONObject) jsonObject.get("data")).get("openid");
        for (String str : openids) {
            WXUser wxUser = WXCenterClient.getWXUserInfo(str);
            WXUserCoreDao wxUserCoreDao = BeanManager.getBean(WXUserCoreDao.class);
            if (wxUserCoreDao.findWXUserByOpenid(str, "openid") != null) {
                continue;
            }
            wxUserCoreDao.insertWXUser(wxUser);
        }

    }
}
