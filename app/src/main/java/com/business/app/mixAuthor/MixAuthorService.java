package com.business.app.mixAuthor;

import com.business.core.entity.mix.MixAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Service
public class MixAuthorService {

    @Autowired
    private MixAuthorDao mixAuthorDao;


    /**
     * mix 歌曲作者
     * @param ids 作者编号
     * @return 歌曲作者
     */
    public List<MixAuthor> info(Collection<Integer> ids) {
        return mixAuthorDao.findMixAuthorByIds(ids);
    }
}
