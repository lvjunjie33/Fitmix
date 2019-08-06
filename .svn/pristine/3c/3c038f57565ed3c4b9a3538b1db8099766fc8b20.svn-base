package script;

import com.business.core.entity.stat.UserGrowthLossStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sin on 2015/6/10 0010.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserGrowthLossJob添加strAddTimeScript extends BaseMongoDaoSupport {


    @Test
    public void 添加StrAddTime () {
        List<UserGrowthLossStat> userGrowthLossStatList = getRoutingMongoOps().findAll(UserGrowthLossStat.class);
        if (CollectionUtils.isEmpty(userGrowthLossStatList)) {
            return;
        }
        for (UserGrowthLossStat userGrowthLossStat : userGrowthLossStatList) {
            getRoutingMongoOps().updateFirst(Query.query(Criteria.where("addTime").is(userGrowthLossStat.getAddTime())),
                    Update.update("strAddTime", DateUtil.format(new Date(userGrowthLossStat.getAddTime()), "yyyy-MM-dd")), UserGrowthLossStat.class);
        }
    }
}
