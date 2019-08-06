package script;

import com.business.core.entity.stat.UserRegisterChannelStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.scheduler.jobs.stat.UserExperienceAndUserJob;
import com.business.scheduler.jobs.stat.UserGrowthLossJob;
import com.business.scheduler.jobs.stat.UserRegisterChannelJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class JobTest extends BaseMongoDaoSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void userExperienceAndUserJobTest() {
        logger.info("job 开始 ...");
        Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, -0);
        Long now = System.currentTimeMillis();
        UserExperienceAndUserJob userExperienceAndUserJob = BeanManager.getBean(UserExperienceAndUserJob.class);
        userExperienceAndUserJob.home(DateUtil.getDayBegin(date).getTime(), DateUtil.getDayEnd(date).getTime());
        logger.info("job 结束 ...消耗 {} 毫秒", System.currentTimeMillis() - now);
    }



    @Test
    public void userGrowthLossJobTest() {
//        ///跑前两个月数据
//        for (int i = -70; i < 0; i++) {
//            Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, i);
//            date = DateUtil.getDayBegin(date);
//
//            UserGrowthLossJob userGrowthLossJob = BeanManager.getBean(UserGrowthLossJob.class);
//            for (int j = 1; j < 25; j ++) {
//                date = DateUtil.setDate(date, Calendar.HOUR_OF_DAY, j);
//                System.out.println(DateUtil.format(date, "yyyy-MM-dd HH:mm"));
//                userGrowthLossJob.home(date);
//            }
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        }


        /// 跑当天数据
        Date date = new Date();
        UserGrowthLossJob userGrowthLossJob = BeanManager.getBean(UserGrowthLossJob.class);
        for (int j = 1; j <= 10; j ++) {
            date = DateUtil.setDate(date, Calendar.HOUR_OF_DAY, j);
            System.out.println(DateUtil.format(date, "yyyy-MM-dd HH:mm"));
            userGrowthLossJob.home(date);
        }
    }



    @Test
    public void UserRegisterChannelJobTest() {
//        for (int i = -30; i <= 0; i++) {
//            Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, i);
////            Date date = new Date();
//            for (int j = 1; j <= 24; j ++) {
//                UserRegisterChannelJob userRegisterChannelJob = BeanManager.getBean(UserRegisterChannelJob.class);
//                date = DateUtil.setDate(date, Calendar.HOUR_OF_DAY, j);
//                Long beginTime = DateUtil.getHourBegin(date).getTime();
//                Long endTime = DateUtil.getHourEnd(date).getTime();
//                System.out.println(DateUtil.format(date, "yyyy-MM-dd HH:mm"));
//
//                userRegisterChannelJob.registerChannel(beginTime, endTime);
//            }
//        }

        for (int i = 0; i <= 0; i++) {
            Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, i);
            for (int j = 0; j <= 10; j ++) {
                UserRegisterChannelJob userRegisterChannelJob = BeanManager.getBean(UserRegisterChannelJob.class);
                date = DateUtil.setDate(date, Calendar.HOUR_OF_DAY, j);
                Long beginTime = DateUtil.getHourBegin(date).getTime();
                Long endTime = DateUtil.getHourEnd(date).getTime();
                System.out.println(DateUtil.format(date, "yyyy-MM-dd HH:mm"));

                userRegisterChannelJob.registerChannel(beginTime, endTime);
            }
        }
    }

    @Test
    public void test() {
        Long beginTime = DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, 0)).getTime();
        Long endTime = DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, 0)).getTime();
        System.out.println(DateUtil.format(new Date(beginTime), "yyyy-MM-dd HH:mm"));
        System.out.println(DateUtil.format(new Date(endTime), "yyyy-MM-dd HH:mm"));
        Query query = Query.query(Criteria.where("addTime").gte(beginTime).lte(endTime));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        List<UserRegisterChannelStat> userRegisterChannelStatList = getRoutingMongoOps().find(query, UserRegisterChannelStat.class);
        Set<Integer> userRegisterChannelStatSet = CollectionUtil.buildSet(userRegisterChannelStatList, Integer.class, "uid");
        Integer count = 0;
        for (UserRegisterChannelStat userRegisterChannelStat : userRegisterChannelStatList) {
            count += userRegisterChannelStat.getUserRegisterTypeStatId().size();
        }
        System.out.println(count);
    }
}
