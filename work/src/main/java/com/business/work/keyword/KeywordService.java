package com.business.work.keyword;

import com.business.core.entity.Page;
import com.business.core.entity.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fenxio on 2016/5/17.
 */
@Service
public class KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    /**
     * 关键字分页查询
     * @param page 分页信息
     */
    public void list(Page<Keyword> page) {
        keywordDao.findKeywordPage(page);
    }

    /**
     * 添加关键字
     * @param keyword 关键字对象
     * @return
     */
    public Object[] keywordAdd(Keyword keyword) {
        keyword.setAddTime(System.currentTimeMillis());
        keyword.setState(Keyword.STATE_1);
        keywordDao.insertKeyword(keyword);
        return new Object[]{0, keyword};
    }

    /**
     * 根据编号 查找关键字
     * @param id 编号
     * @return
     */
    public Keyword findKeywordById(Integer id) {
        return keywordDao.findKeywordById(id);
    }

    /**
     * 修改 关键字基本信息
     * @param keyword 关键字对象
     */
    public void modifyKeyword(Keyword keyword) {
        Update update = new Update();
        update.set("name", keyword.getName()).set("type", keyword.getType());
        keywordDao.updateKeywordById(keyword.getId(), update);
    }

    /**
     * 修改上架状态
     * @param id 编号
     * @param state 上架状态
     */
    public void keywordModifyState(Long id, Integer state) {
        Update update = new Update();
        update.set("state", state);
        keywordDao.updateKeywordById(id, update);
    }

    /**
     * 修改 关键字排序
     * @param id 编号
     * @param sort 排序
     */
    public void keywordModifySort(Long id, Integer sort) {
        Update update = new Update();
        update.set("sort", sort);
        keywordDao.updateKeywordById(id, update);
    }

    /**
     * 删除关键字
     * @param id 编号
     */
    public void removeKeywordById(Long id) {
        keywordDao.removeKeywordById(id);
    }
}
