package com.business.work.mixAuthor;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAuthor;
import com.business.core.operations.mix.MixCoreDao;
import com.business.core.operations.mixAuthor.MixAuthorCoreDao;
import com.business.core.operations.mixAuthor.MixAuthorCoreService;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by sin on 2015/8/4 0004.
 */
@Service
public class MixAuthorService {

    @Autowired
    private MixCoreDao mixCoreDao;
    @Autowired
    private MixAuthorDao mixAuthorDao;
    @Autowired
    private MixAuthorCoreDao mixAuthorCoreDao;

    @Autowired
    private MixAuthorCoreService mixAuthorCoreService;

    /**
     * MixAuthor 分页
     * @param page 分页工具
     */
    public void pageMixAuthor(Page<MixAuthor> page) {
        mixAuthorDao.findMixAuthorPage(page);
    }

    /**
     * 添加 MixAuthor 信息
     * @param name 名称
     * @param introduce 简介
     * @param introduceDetail 详细简介
     * @return 0、成功 1、名称重复
     */
    public int addMixAuthor(String name, String introduce, String introduceDetail) {
        if ( null != mixAuthorCoreDao.findMixAuthor(name, "id", "name")) {
            return 1;
        }
        MixAuthor mixAuthor = buildMixAuthor(name, introduce, introduceDetail);
        mixAuthorCoreDao.insertMixAuthor(mixAuthor);
        return 0;
    }

    /**
     * 构建 mixAuthor 对象
     * @param name 名称
     * @param introduce 简介
     * @param introduceDetail 详细简介
     * @return mixAuthor对象
     */
    public MixAuthor buildMixAuthor(String name, String introduce, String introduceDetail) {
        MixAuthor mixAuthor = new MixAuthor();
        mixAuthor.setName(name);
        mixAuthor.setIntroduce(introduce);
        mixAuthor.setIntroduceDetail(introduceDetail);
        mixAuthor.setUserCount(0);
        mixAuthor.setAddTime(System.currentTimeMillis());
        return mixAuthor;
    }

    /**
     * 修改用户基本资料
     * @param mixAuthor 歌曲作者信息
     * @return 0、成功 1、名称重复
     */
    public int modifyMixAuthor(MixAuthor mixAuthor) {
        MixAuthor baseMixAuthor =  mixAuthorCoreDao.findMixAuthor(mixAuthor.getId(), "id", "name");
        if (baseMixAuthor != null && !baseMixAuthor.getId().equals(mixAuthor.getId())) {
            return 1;
        }
        mixAuthorCoreDao.updateMixAuthor(mixAuthor.getId(), Update.update("name", mixAuthor.getName()).set("phone", mixAuthor.getPhone()).set("international", mixAuthor.getInternational())
                .set("email", mixAuthor.getEmail()).set("webUrl", mixAuthor.getWebUrl()).set("weiXin", mixAuthor.getWeiXin()).set("weiBo", mixAuthor.getWeiBo())
                .set("introduce", mixAuthor.getIntroduce()).set("introduceDetail", mixAuthor.getIntroduceDetail()).set("dynamic", mixAuthor.getDynamic()).set("memo", mixAuthor.getMemo()));
        return 0;
    }

    /**
     * 删除作者
     * @param id 用户编号
     */
    public void removeMixAuthor(@RequestParam("id") Integer id) {
        MixAuthor mixAuthor = mixAuthorCoreDao.findMixAuthor(id, "id", "avatar", "photos");
        if (mixAuthor != null) {
            /// 处理 mix 绑定的 author 编号
            List<Mix> mixList = mixCoreDao.findMixWithMaids(mixAuthor.getId(), "id");
            Set<Integer> mIds = CollectionUtil.buildSet(mixList, Integer.class, "id");
            mixCoreDao.updateMixWithIds(mIds, Update.update("","").unset("maid"));

            /// 删除文件服务器文件
            if (null != mixAuthor.getAvatar()) { // 删除 头像
                FileCenterClient.removeFile(mixAuthor.getAvatar());
            }
            if (!CollectionUtils.isEmpty(mixAuthor.getPhotos())) { // 删除相册
                for (String url : mixAuthor.getPhotos()) {
                    FileCenterClient.removeFile(url);
                }
            }

            /// 删除用户
            mixAuthorDao.removeMixAuthor(id);
        }
    }
}
