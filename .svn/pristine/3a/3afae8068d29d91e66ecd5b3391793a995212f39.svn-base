package com.business.work.userExperience;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserExperience;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by sin on 2015/9/7.
 */
@Repository
public class UserExperienceDao extends BaseMongoDaoSupport {


    public void findPage(Page<UserExperience> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        /// 编号
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }

        /// 渠道
        if (!StringUtils.isEmpty(filter.get("channel"))) {
            criteria.and("channel").is(filter.get("channel"));
        }

        /// 版本
        if (!StringUtils.isEmpty(filter.get("version"))) {
            criteria.and("version").is(filter.get("version"));
        }

        /// 登录次数(体验次数)
        if (filter.containsKey("beginExperienceCount") && filter.containsKey("endExperienceCount")) {
            criteria.and("count").gte(filter.get("beginExperienceCount")).lte(filter.get("endExperienceCount"));
        }
        else if(filter.containsKey("beginExperienceCount")) {
            criteria.and("count").gte(filter.get("beginExperienceCount"));
        }
        else if(filter.containsKey("endExperienceCount")) {
            criteria.and("count").lte(filter.get("endExperienceCount"));
        }

        /// 试用时间
        if (!StringUtils.isEmpty(filter.get("beginRegisterTime")) && !StringUtils.isEmpty(filter.get("endRegisterTime"))) {
            criteria.and("addTime").gte(page.parseBeginTime("beginRegisterTime")).lte(page.parseEndTime("endRegisterTime"));
        }
        else if (!StringUtils.isEmpty(filter.get("beginRegisterTime"))) {
            criteria.and("addTime").gte(page.parseBeginTime("beginRegisterTime"));
        } else if (!StringUtils.isEmpty(filter.get("endRegisterTime"))) {
            criteria.and("addTime").lte(page.parseEndTime("endRegisterTime"));
        }

        Query query = new Query(criteria);
        findEntityPage(UserExperience.class, page, query, fields);
    }
}
