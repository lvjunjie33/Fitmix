package userRun;

import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.sort.SortFactory;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by Sin on 2016/1/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class CopyUserRunScript extends BaseMongoDaoSupport {

    /**
     * 哈哈
     */
    @Test
    public void copyRun() {
        int copyUid = 8;

        long beginTime = DateUtil.getDayBegin(DateUtil.parse("2016-03-20 07:33", "yyyy-MM-dd")).getTime();
        Query query = new Query(Criteria.where("id").in(97010));
//        for (UserRun userRun : getRoutingMongoOps().find(query, UserRun.class)) {
//            userRun.setId(null);
//            userRun.setUid(copyUid);
//            insertToMongo(userRun);
//        }
    }


    @Test
    public void 每月用户运动排行榜() {
        long beginTime = DateUtil.getDayBegin(DateUtil.parse("2015-12-01", "yyyy-MM-dd")).getTime();
        long endTime = DateUtil.getDayEnd(DateUtil.parse("2015-12-31", "yyyy-MM-dd")).getTime();

        List<UserRun> userRunList;
        Map<Integer, List<UserRun>> userRunMultimap;
        {
            Query query = new Query(Criteria.where("addTime").gte(beginTime).lte(endTime));
            userRunList = getRoutingMongoOps().find(query, UserRun.class);
            userRunMultimap = CollectionUtil.buildMultimap(userRunList, Integer.class, UserRun.class, "uid");
        }


        Map<Integer, User> userMap;
        {
            Query query = new Query(Criteria.where("id").in(CollectionUtil.buildSet(userRunList, Integer.class, "uid")));
            List<User> userList = getRoutingMongoOps().find(query, User.class);
            userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
        }

        List<UserRun> userRunCountList = new ArrayList<>(userRunMultimap.keySet().size());
        for (Map.Entry<Integer, List<UserRun>> entry : userRunMultimap.entrySet()) {
            long totalDistance = 0;
            for (UserRun userRun : entry.getValue()) {
                totalDistance += userRun.getDistance();
            }
            UserRun userRun = entry.getValue().get(0);
            userRun.setUser(userMap.get(userRun.getUid()));
            userRun.setDistance(totalDistance);
            userRunCountList.add(userRun);
        }

        Collections.sort(userRunCountList, SortFactory.USER_RUN_SORT);


        for (UserRun userRun : userRunCountList) {
            if (null == userRun.getUser() || null == userRun.getDistance()) {
                continue;
            }
            System.out.println("编号: " + userRun.getUid() + "名称: " + userRun.getUser().getName() + "公里: " + userRun.getDistance());
        }
    }
}
