package com.business.app.event;

import com.business.app.user.UserDao;
import com.business.core.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wjcaozhi on 16/1/28.
 */
@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    public User getUser(String uid){

        return userDao.findUserByUDID(uid,User.BASIC_INFO_FIELDS);
    }

}
