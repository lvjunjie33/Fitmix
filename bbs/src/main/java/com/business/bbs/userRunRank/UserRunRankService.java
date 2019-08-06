package com.business.bbs.userRunRank;

import com.business.bbs.base.QxMap;
import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.bbs.user.UserDao;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRunRank;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/18.
 */
@Service
public class UserRunRankService extends AbstractService<UserRunRank> {

    @Autowired
    private UserRunRankDao userRunRankDao;
    @Autowired
    private UserDao userDao;

    @Override
    protected AbstractDao<UserRunRank> getAbstractDao() {
        return userRunRankDao;
    }

    /**
     * 获取榜单列表
     * @return
     */
    public List<UserRunRank> getRankList() {
        List<UserRunRank> resultList = new ArrayList<>();

        // 1. 获取每日冠军
        UserRunRank dayRank = userRunRankDao.findOneByMap(new QxMap<String, Object>("type", UserRunRank.TYPE_DAY).add("typeValue", DateUtil.format(new Date(), "yyyy-MM-dd")), new QxMap<>("distance", Sort.Direction.DESC));
        if(null != dayRank) {
            dayRank.setUser(userDao.findById(User.class, dayRank.getUid(), "name", "avatar"));
        }

        // 2. 周冠军
        String weekStart = DateUtil.format(DateUtil.getWeekBegin(new Date()), "yyyy-MM-dd");
        String weekEnd = DateUtil.format(DateUtil.getWeekEnd(new Date()), "yyyy-MM-dd");
        String typeValue = weekStart + "~" + weekEnd;
        UserRunRank weekRank = userRunRankDao.findOneByMap(new QxMap<String, Object>("type", UserRunRank.TYPE_WEEK).add("typeValue", typeValue), new QxMap<>("distance", Sort.Direction.DESC));
        if(null != weekRank) {
            weekRank.setUser(userDao.findById(User.class, weekRank.getUid(), "name", "avatar"));
        }

        // 3. 月冠军
        UserRunRank monthRank = userRunRankDao.findOneByMap(new QxMap<String, Object>("type", UserRunRank.TYPE_MONTH).add("typeValue", DateUtil.format(new Date(), "yyyy-MM")), new QxMap<>("distance", Sort.Direction.DESC));
        if(null != monthRank) {
            monthRank.setUser(userDao.findById(User.class, monthRank.getUid(), "name", "avatar"));
        }

        resultList.add(dayRank);
        resultList.add(weekRank);
        resultList.add(monthRank);

        return resultList;
    }
}
