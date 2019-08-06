package script;

import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sin on 2015/9/9.
 * 将数据补全：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserChannelScript extends BaseMongoDaoSupport {

    /**
     * 渠道信息：0 改为 000  1 改为 1   其他 改为 = 000
     */
    @Test
    public void channel() {
        List<User> userAll = getRoutingMongoOps().findAll(User.class);
        for (User user : userAll) {
            String result = "0";
            if (null == user.getChannel()) {
                user.setChannel("0");
            }
            else {
                String channel = user.getChannel();
                for (int i = 0; i < 19; i++) {
                    if (channel.equals("appStore")) {
                        result = user.getChannel();
                        break;
                    }
                    if (channel.equals(String.valueOf(i)) || channel.equals(String.format("%03d", i))) {
                        result = String.valueOf(i);
                        break;
                    }
                }
            }
            getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(user.getId())), Update.update("channel", result), User.class);
        }
    }




    /**
     * 渠道信息：0 改为 000  1 改为 1   其他 改为 = 000
     */
    @Test
    public void registerChannel() {
        List<User> userAll = getRoutingMongoOps().findAll(User.class);
        for (User user : userAll) {
            String result = "0";
            if (null == user.getRegisterChannel()) {
                user.setRegisterChannel("0");
            }
            else {
                String channel = user.getRegisterChannel();
                for (int i = 0; i < 19; i++) {
                    if (channel.equals("appStore")) {
                        result = user.getRegisterChannel();
                        break;
                    }
                    if (channel.equals(String.valueOf(i)) || channel.equals(String.format("%03d", i))) {
                        result = String.valueOf(i);
                        break;
                    }
                }
            }
            getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(user.getId())), Update.update("registerChannel", result), User.class);
        }
    }

//

    /**
     * 渠道信息：0 改为 000  1 改为 1   其他 改为 = 000
     * sdk 为 iphone 转为 appStore
     *
     */
    @Test
    public void registerChannelIsSdkEqualsIphone() {
        List<User> userAll = getRoutingMongoOps().findAll(User.class);
        for (User user : userAll) {
            String result = "appStore";
            if (null != user.getSdk() && user.getSdk().indexOf("iPhone") != -1) {
                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(user.getId())), Update.update("registerChannel", result), User.class);
            }
        }
    }

    @Test
    public void test() {
        String cc = String.format("%03d", 1);
        System.out.println(cc);
    }
}
