package com.business.work.userExperience;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sin on 2015/9/7.
 */
@Service
public class UserExperienceService {

    @Autowired
    private UserExperienceDao userExperienceDao;

    /**
     * 分页
     */
    public void page(Page<UserExperience> page) {
        userExperienceDao.findPage(page);
    }
}
