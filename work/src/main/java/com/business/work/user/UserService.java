package com.business.work.user;

import com.business.core.entity.Page;
import com.business.core.entity.logs.UserLoginLog;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.SmartDevice;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.operations.logs.UserLoginLogCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.sort.SortFactory;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by sin on 2015/7/31 0031.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserLoginLogCoreDao userLoginLogCoreDao;

    @Autowired
    private UserCoreService userCoreService;

    /**
     * 用户分页
     */
    public void userPage(Page<User> page) {
        userDao.findWithPage(page);
    }

    /**
     * 用户详细信息
     * @param uid 用户编号
     * @return 用户信息
     */
    public User userInfo(Integer uid) {
        User user = userCoreDao.findUserById(uid);
        userCoreService.buildUserFileUrl(user);
        user.setPassword(null);
        return user;
    }

    public void list(Page<User> page) {
        LinkedHashMap filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        if(filter.containsKey("activatesUgc")) {
            criteria.and("activatesUgc").is(filter.get("activatesUgc"));
        }
        if(filter.containsKey("checkStatus") && !filter.get("checkStatus").equals("-1")) {
            criteria.and("userIdentityInfo.checkStatus").is(Integer.parseInt((String)filter.get("checkStatus")));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        userDao.findEntityPage(page, query);
    }

    /**
     * 修改用户审核状态
     * @param id
     * @param checkStatus
     * @param checkMessage
     */
    public void userModifyCheckStatus(Integer id, Integer checkStatus, String checkMessage) {
        Update update = new Update();
        update.set("userIdentityInfo.checkStatus", checkStatus);
        update.set("userIdentityInfo.checkMessage", checkMessage);
        userDao.updateUserById(id, update);
    }

    public List<User> findUserByIdIn(Set<Integer> uids) {
        return userDao.findUserByUid(uids);
    }

    /**
     * 设置用户话题Vip
     *
     * @param uid 用户编号
     * @param themeVip 0.普通用户、1.vip用户
     */
    public void setThemeVip(Integer uid, Integer themeVip) {
        userCoreDao.updateUserById(uid, Update.update("themeVip", themeVip));
    }

    /**
     * 查询设备信息
     *
     * @param page 分页对象
     */
    public void findSmartDevice(Page<SmartDevice> page) {
        userDao.findSmartDevice(page);
    }

    public void removeSmartDevice(Long id) {
        userDao.removeSmartDevice(id);
    }

    public void findPageRoc(Page<User> page) {
//        List<UserDevice> userDevices = userDao.findRocUserDeviceByProduct("uid");
//        Set<Integer> uids = new HashSet<>();
//        for (UserDevice userDevice : userDevices) {
//            uids.add(userDevice.getUid());
//        }

        Set<String> vs = new HashSet<>();
        vs.add("1.0.0");
        vs.add("1.0.1");
        vs.add("1.0.2");
        vs.add("1.0.3");
        vs.add("1.0.4");
        vs.add("1.0.5");
        vs.add("1.0.6");
        vs.add("1.0.7");
        vs.add("3");

        page.getFilter().put("vs", vs);

        userDao.findWithPageRoc(page);
        page.getFilter().remove("vs");
    }

    /**
     * 用户详细信息
     * @param uid 用户编号
     * @return 用户信息
     */
    public User userInfoRoc(Integer uid) {
        User user = userCoreDao.findUserById(uid);
        if (user == null) {
            return null;
        }
        userCoreService.buildUserFileUrl(user);
        user.setPassword(null);
        return user;
    }
}
