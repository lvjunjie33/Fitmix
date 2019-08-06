package com.business.core.operations.wx;

import com.business.core.entity.wx.WXGetUserInfoKey;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/23 0023.
 */
@Repository
public class WXGetUserInfoKeyCodeDao extends BaseMongoDaoSupport {

    /**
     * 获取微信用户信息 code
     * @return 返回微信 key
     */
    public WXGetUserInfoKey findWXGetUserInfoKey () {
        return getRoutingMongoOps().findAll(WXGetUserInfoKey.class).get(0);
    }
}
