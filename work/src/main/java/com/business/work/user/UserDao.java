package com.business.work.user;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.SmartDevice;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/7/31 0031.
 */
@Repository
public class UserDao extends BaseMongoDaoSupport {

    /**
     * 根据用户ID 获取用户信息
     * @param uid 用户ID
     * @return
     */
    public User findUserByUid(int uid, String...fields) {
        Criteria criteria = Criteria.where("id").is(uid);
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, User.class);
    }

    /**
     * 根据用户ID 获取用户列表
     * @param uids  用户ID集合
     * @param fields 列
     * @return
     */
    public List<User> findUserByUid(Collection<Integer> uids,String...fields){
        Criteria criteria = Criteria.where("id").in(uids);
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    public void findWithPage(Page<User> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        /// 名称
        if (!StringUtils.isEmpty(filter.get("name"))) {
            criteria.and("name").regex(String.format("%s.*", filter.get("name")));
        }

        /// 渠道
        if (!StringUtils.isEmpty(filter.get("registerChannel"))) {
            criteria.and("registerChannel").is(filter.get("registerChannel"));
        }

        // 渠道(android or ios)
        if(!StringUtils.isEmpty(filter.get("registerChannelT"))){
            if("appStore".equals(filter.get("registerChannelT"))){
                criteria.and("registerChannel").is("appStore");
            }else{
                criteria.and("registerChannel").ne("appStore");
            }
        }

        /// 版本
        if (!StringUtils.isEmpty(filter.get("version"))) {
            criteria.and("version").is(filter.get("version"));
        }

        if (filter.containsKey("currentVersion")) {
            criteria.and("currentVersion").is(filter.get("currentVersion"));
        }

        /// 性别
        if (filter.containsKey("gender")) {
            criteria.and("gender").is(filter.get("gender"));
        }

        /// 用户身高
        if (filter.containsKey("beginHeight") && filter.containsKey("endHeight")) {
            criteria.and("height").gte(filter.get("beginHeight")).lte(filter.get("endHeight"));
        }
        else if (filter.containsKey("beginHeight")) {
            criteria.and("height").gte(filter.get("beginHeight"));
        }
        else if (filter.containsKey("endHeight")) {
            criteria.and("height").lte(filter.get("endHeight"));
        }

        // 用户体重
        if (filter.containsKey("beginWeight") && filter.containsKey("endWeight")) {
            criteria.and("weight").gte(filter.get("beginWeight")).lte(filter.get("endWeight"));
        }
        else if (filter.containsKey("beginWeight")) {
            criteria.and("weight").gte(filter.get("beginWeight"));
        }
        else if (filter.containsKey("endWeight")) {
            criteria.and("weight").lte(filter.get("endWeight"));
        }

        // 年龄
        if (filter.containsKey("bAge")) {
            if (filter.containsKey("eAge")) {
                criteria.and("age").gte(filter.get("bAge")).lte(filter.get("eAge"));
            } else {
                criteria.and("age").gte(filter.get("bAge"));
            }
        } else if (filter.containsKey("eAge")) {
            criteria.and("age").lte(filter.get("eAge"));
        }

        /// 登录次数
        if (filter.containsKey("beginLoginCount") && filter.containsKey("endLoginCount")) {
            criteria.and("loginCount").gte(filter.get("beginLoginCount")).lte(filter.get("endLoginCount"));
        }
        else if(filter.containsKey("beginLoginCount")) {
            criteria.and("loginCount").gte(filter.get("beginLoginCount"));
        }
        else if(filter.containsKey("endLoginCount")) {
            criteria.and("loginCount").lte(filter.get("endLoginCount"));
        }

        // 注册时间
        if (filter.containsKey("beginTime")) {
            if (filter.containsKey("endTime")) {
                criteria.and("addTime").gte(((Date)filter.get("beginTime")).getTime()).lte(((Date)filter.get("endTime")).getTime());
            } else {
                criteria.and("addTime").gte(((Date)filter.get("beginTime")).getTime());
            }
        } else if (filter.containsKey("endTime")) {
            criteria.and("addTime").lte(((Date)filter.get("endTime")).getTime());
        }

        // isRun 根据卡路里 步数 距离
        if (filter.containsKey("isRun")) {
            // 没有
            if (Integer.parseInt(filter.get("isRun").toString()) == 0) {
                criteria.and("step").lte(0);
            } // 有
            else if(Integer.parseInt(filter.get("isRun").toString()) == 1) {
                criteria.and("step").gt(0);
            }
        }

        // isDownload 歌曲下载
        if (filter.containsKey("isDownload")) {
            // 没有
            if (Integer.parseInt(filter.get("isDownload").toString()) == 0) {
                criteria.and("downloadCount").lte(0);
            } // 有
            else if(Integer.parseInt(filter.get("isDownload").toString()) == 1) {
                criteria.and("downloadCount").gt(0);
            }
        }

        //用户手机号码
        if (filter.containsKey("mobile")) {
            criteria.and("mobile").regex(filter.get("mobile").toString());
        }

        if (filter.containsKey("email")) {
            criteria.and("email").regex(filter.get("email").toString());
        }

        /// 编号
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        } else {
            // 活跃用户 信息
//            if (null != uids) {
//                criteria.and("id").in(uids);
//            }
        }

        // 活跃用户 信息
        if (!StringUtils.isEmpty(filter.get("idfa"))) {
            List<String> idfas = CollectionUtil.splitList(filter.get("idfa").toString().trim(), ",");
            criteria.and("idfa").in(idfas);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "id"));
        findEntityPage(User.class, page, query, fields);
    }

    public void findEntityPage(Page<User> page, Query query, String ... fields) {
        findEntityPage(User.class, page, query, fields);
    }

    public void updateUserById(Integer id, Update update) {
        updateEntityFieldsById(User.class, id, update);
    }

    /**
     * 查询用户的动作信息
     *
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param channelId 渠道
     */
    public List<User> findByAddTimeAndChannel(Long bTime, Long eTime, String channelId, String...fields) {
        Criteria criteria = Criteria.where("addTime").lte(eTime).gte(bTime);
        if (!StringUtils.isEmpty(channelId)) {
            criteria.and("registerChannel").is(channelId);
        }
        Query query = Query.query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    /**
     * 查询设备信息
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void findSmartDevice(Page<SmartDevice> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }
        if (filter.containsKey("chipID")) {
            if(filter.get("chipID")!=null){
                criteria.and("deviceInfo.IRONCLOUD_CHIPID").regex(filter.get("chipID").toString());
            }

        }
        Query query = new Query(criteria);
        /// 名称
        findEntityPageSimple(SmartDevice.class, page, query, fields);
    }

    public void removeSmartDevice(Long id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), SmartDevice.class);
    }

    public void findWithPageRoc(Page<User> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();

        Criteria criteria = Criteria.where("version").in((Set)filter.get("vs")).and("addTime").gt(DateUtil.parse("2017-09-01", "yyyy-MM-dd").getTime());


        //注册时间
        if(filter.containsKey("beginTime")&&filter.containsKey("endTime")){

            Date begin = (Date) filter.get("beginTime");
            Date end = (Date) filter.get("endTime");

            criteria.and("addTime").gte(begin.getTime()).lte(end.getTime());
        }

        /// 名称
        if (!StringUtils.isEmpty(filter.get("name"))) {
            criteria.and("name").regex(String.format("%s.*", filter.get("name")));
        }

        /// 渠道
        if (!StringUtils.isEmpty(filter.get("registerChannel"))) {
            criteria.and("registerChannel").is(filter.get("registerChannel"));
        }

        // 渠道(android or ios)
        if(!StringUtils.isEmpty(filter.get("registerChannelT"))){
            if("appStore".equals(filter.get("registerChannelT"))){
                criteria.and("registerChannel").is("appStore");
            }else{
                criteria.and("registerChannel").ne("appStore");
            }
        }

        /// 性别
        if (filter.containsKey("gender")) {
            criteria.and("gender").is(filter.get("gender"));
        }

        /// 编号
        if(filter.containsKey("id") && filter.containsKey("id")){
            criteria.and("id").in(filter.get("id"));
        }

        /// 用户身高
        if (filter.containsKey("beginHeight") && filter.containsKey("endHeight")) {
            criteria.and("height").gte(filter.get("beginHeight")).lte(filter.get("endHeight"));
        }
        else if (filter.containsKey("beginHeight")) {
            criteria.and("height").gte(filter.get("beginHeight"));
        }
        else if (filter.containsKey("endHeight")) {
            criteria.and("height").lte(filter.get("endHeight"));
        }

        // 用户体重
        if (filter.containsKey("beginWeight") && filter.containsKey("endWeight")) {
            criteria.and("weight").gte(filter.get("beginWeight")).lte(filter.get("endWeight"));
        }
        else if (filter.containsKey("beginWeight")) {
            criteria.and("weight").gte(filter.get("beginWeight"));
        }
        else if (filter.containsKey("endWeight")) {
            criteria.and("weight").lte(filter.get("endWeight"));
        }

        // 年龄
        if (filter.containsKey("bAge")) {
            if (filter.containsKey("eAge")) {
                criteria.and("age").gte(filter.get("bAge")).lte(filter.get("eAge"));
            } else {
                criteria.and("age").gte(filter.get("bAge"));
            }
        } else if (filter.containsKey("eAge")) {
            criteria.and("age").lte(filter.get("eAge"));
        }

        /// 登录次数
        if (filter.containsKey("beginLoginCount") && filter.containsKey("endLoginCount")) {
            criteria.and("loginCount").gte(filter.get("beginLoginCount")).lte(filter.get("endLoginCount"));
        }
        else if(filter.containsKey("beginLoginCount")) {
            criteria.and("loginCount").gte(filter.get("beginLoginCount"));
        }
        else if(filter.containsKey("endLoginCount")) {
            criteria.and("loginCount").lte(filter.get("endLoginCount"));
        }


        //用户手机号码
        if (filter.containsKey("mobile")) {
            criteria.and("mobile").regex(filter.get("mobile").toString());
        }

        if (filter.containsKey("email")) {
            criteria.and("email").regex(filter.get("email").toString());
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "id"));
        findEntityPage(User.class, page, query, fields);
    }

    /**
     * 通过产品类型 查询设备信息
     */
    public List<UserDevice> findRocUserDeviceByProduct(String...fields) {
        Query query = Query.query(Criteria.where("product").is(UserDevice.PRODUCT_ROC));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserDevice.class);
    }

    /**
     * 查询ROC用户信息
     *
     * @param uid 用户
     */
    public UserDevice findRocUserDeviceByProductAndUid(Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("product").is(UserDevice.PRODUCT_ROC).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserDevice.class);
    }

}
