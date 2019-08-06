package com.business.core.operations.logs;

import com.business.core.entity.logs.VerifyCodeLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by edward on 2017/7/18.
 */
@Repository
public class VerifyCodeCoreDao extends BaseMongoDaoSupport{


    /**
     * 保存验证码
     *
     * @param log 验证码记录
     */
    public void saveVerifyCode(VerifyCodeLog log) {
        insertToMongo(log);
    }


    /**
     * 通过ip地址查询
     *
     * @param remoteAddr 手机号码
     */
    public VerifyCodeLog findVerifyCodeByaddr(String remoteAddr, String...fields) {
        Query query = Query.query(Criteria.where("remoteAddr").is(remoteAddr).and("type").is(VerifyCodeLog.CODE_TYPE_MOBILE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, VerifyCodeLog.class);
    }
    /**
     * 通过手机号码查询验证嘛
     *
     * @param mobile 手机号码
     */
    public VerifyCodeLog findVerifyCodeByMobile(String mobile, String...fields) {
        Query query = Query.query(Criteria.where("keywords").is(mobile).and("type").is(VerifyCodeLog.CODE_TYPE_MOBILE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, VerifyCodeLog.class);
    }

    /**
     * 通过邮件查询验证码
     *
     * @param mail 邮件
     */
    public VerifyCodeLog findVerifyCodeByMail(String mail, String...fields) {
        Query query = Query.query(Criteria.where("keywords").is(mail).and("type").is(VerifyCodeLog.CODE_TYPE_MAIL));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, VerifyCodeLog.class);
    }
}
