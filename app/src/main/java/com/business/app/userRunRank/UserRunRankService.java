package com.business.app.userRunRank;

import com.business.core.entity.user.UserRunRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/9/18.
 */
@Service
public class UserRunRankService {

    @Autowired
    private UserRunRankDao userRunRankDao;

    /**
     * 根据 用户编号 和 榜单类型 获取数据
     * @param uid
     * @param type
     * @param typeValue
     * @return
     */
    public UserRunRank findByUidAndTypeAndTypeValue(Integer uid, Integer type, String typeValue) {
        Query query = new Query(Criteria.where("uid").is(uid).and("type").is(type).and("typeValue").is(typeValue));
        return userRunRankDao.findOne(query, UserRunRank.class);
    }



}
