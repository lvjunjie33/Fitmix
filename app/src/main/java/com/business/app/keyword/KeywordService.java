package com.business.app.keyword;

import com.business.core.entity.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/5/17.
 */
@Service
public class KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    /**
     * 获取所有上架的 关键字热词
     */
    public List<Keyword> findAllkeywordList() {
        return keywordDao.findAllkeywordList();
    }
}
