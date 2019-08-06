package com.business.ugc.user;

import com.business.core.entity.user.User;
import com.business.ugc.base.support.AbstractDao;
import com.business.ugc.base.support.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/8/23.
 */
@Service
public class UserService extends AbstractService<User>{

    @Autowired
    private UserDao userDao;

    @Override
    protected AbstractDao<User> getAbstractDao() {
        return userDao;
    }

    /**
     * 根据 邮箱查找用户
     * @param email 邮箱
     * @return
     */
    public User selectByEmail(String email){
        return userDao.selectByEmail(email);
    }

    /**
     * 根据手机号 查找用户
     * @param mobile 手机号
     * @return
     */
    public User selectByMobile(String mobile) {
        return userDao.selectByMobile(mobile);
    }


}
