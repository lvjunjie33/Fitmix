package script;

import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.CollectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Cheri on 2015/8/19.
 * 转换 用户总共跑步 距离
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserDistanceScript extends BaseMongoDaoSupport {

    /**
     * 转换 distance
     * 转换 step
     * 转换 calorie
     */
    @Test
    public void execute() {
        for (User user : getRoutingMongoOps().findAll(User.class)) {
            // 更新用户 总共跑步多少米
            getRoutingMongoOps().updateMulti(Query.query(new Criteria().and("id").is(user.getId())), Update.update("distance", 0).set("step", 0).set("calorie", 0).set("runTime", 0), User.class);
        }
    }
}
