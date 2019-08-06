package com.business.ugc.content.mix;

import com.business.core.entity.mix.Mix;
import com.business.ugc.base.support.AbstractDao;
import com.business.ugc.base.support.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/8/30.
 */
@Service
public class MixService extends AbstractService<Mix> {

    @Autowired
    private MixDao mixDao;

    @Override
    protected AbstractDao<Mix> getAbstractDao() {
        return mixDao;
    }
}
