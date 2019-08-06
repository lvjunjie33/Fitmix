package com.business.ugc.content.albums;

import com.business.core.entity.mix.MixAlbum;
import com.business.ugc.base.support.AbstractDao;
import com.business.ugc.base.support.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/8/29.
 */
@Service
public class MixAlbumsService extends AbstractService<MixAlbum> {

    @Autowired
    private MixAlbumsDao mixAlbumsDao;

    @Override
    protected AbstractDao<MixAlbum> getAbstractDao() {
        return mixAlbumsDao;
    }
}
