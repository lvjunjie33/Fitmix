package com.business.bbs.dynamic;

import com.business.bbs.base.QxMap;
import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.bbs.user.UserDao;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/10/8.
 */
@Service
public class DynamicService extends AbstractService<Dynamic> {

    @Autowired
    private DynamicDao dynamicDao;
    @Autowired
    private UserDao userDao;

    @Override
    protected AbstractDao<Dynamic> getAbstractDao() {
        return dynamicDao;
    }


    /**
     * 获取评论列表分页
     * @param did
     * @param index
     * @return
     */
    public Page<Comment> findCommentByPage(Long did, Integer index) {
        return dynamicDao.findCommentPageByDid(did, index);
    }


    /**
     * 构建所需对象
     * @param page
     */
    public void buildData(Page<Dynamic> page) {
        List<Dynamic> dynamicList = page.getResult();
        if(null != dynamicList && dynamicList.size() > 0) {
            for(Dynamic dynamic : dynamicList) {
                dynamic.setUser(userDao.findById(User.class, dynamic.getUid(), "id", "name", "avatar"));
                dynamic.getUser().setAvatar(buildFileUrl(dynamic.getUser().getAvatar()));
                dynamic.setCommentList(dynamicDao.findCommentPageByDid(dynamic.getId(), 0).getResult());
                dynamic.setFlowerList(dynamicDao.findFlowerPageByDid(dynamic.getId(), 0).getResult());
                dynamic.setFlowerCount(dynamicDao.getFlowerCountByDid(dynamic.getId()));
                dynamic.setCommentCount(dynamicDao.getCommentCountByDid(dynamic.getId()));
                if(null != dynamic.getImgs() && dynamic.getImgs().size() > 0) {
                    for(int i = 0; i < dynamic.getImgs().size(); i++) {
                        dynamic.getImgs().set(i, buildFileUrl(dynamic.getImgs().get(i)));
                    }
                }
            }
        }

    }

    /**
     * 处理 url地址
     * @param url 链接地址
     */
    public String buildFileUrl(String url) {
        if (url != null) {
            if (url.indexOf("http") == -1) {
                return FileCenterClient.buildUrl(url);
            }
        }
        return url;
    }


}
