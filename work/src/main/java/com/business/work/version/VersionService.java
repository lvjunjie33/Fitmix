package com.business.work.version;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.version.Version;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/5/25.
 */
@Service
public class VersionService {

    @Autowired
    private VersionDao versionDao;

    /**
     * 版本信息列表
     * @param page 分页
     */
    public void list(Page<Version> page) {
        versionDao.findVersionPage(page);
        buildFileUrls(page.getResult());
    }

    /**
     * 添加版本信息
     * @param version 版本对象
     * @return
     */
    public Object[] versionAdd(Version version) {
        version.setState(Version.STATE_1);
        version.setAddTime(System.currentTimeMillis());
        versionDao.insertVersion(version);
        return new Object[]{0, version};
    }

    /**
     * 根据编号查找版本信息
     * @param id 编号
     * @return
     */
    public Version findVersionById(Long id) {
        Version version = versionDao.findVersionById(id);
        buildFileUrl(version);
        return version;
    }

    /**
     * 更新版本基本信息
     * @param version 版本对象
     */
    public void versionModify(Version version) {
        Update update = new Update();
        update.set("versionCode", version.getVersionCode()).set("type", version.getType());
        versionDao.updateVersionById(version.getId(), update);
    }

    /**
     * 更新app 链接
     * @param id 编号
     * @param fileUrl 链接
     */
    public void versionModifyUrl(Long id, String fileUrl) {
        Update update = new Update();
        update.set("url", fileUrl);
        versionDao.updateVersionById(id, update);
    }

    /**
     * 更新上架状态
     * @param id 编号
     * @param state 上架状态
     */
    public void versionModifyState(Long id, Integer state, Integer type) {
        if(state == Version.STATE_2) {
            // 1. 重置所有上架状态
            Query query = new Query(Criteria.where("type").is(type));
            Update updateReset = Update.update("state", Version.STATE_1);
            versionDao.updateMulti(query, updateReset, Version.class);
            // 2. 修改上架状态
            Update update = new Update();
            update.set("state", state);
            versionDao.updateVersionById(id, update);
        }
    }


    /**
     * 构建多个 APP文件路径
     * @param versionList 版本信息集合
     */
    public void buildFileUrls(List<Version> versionList) {
        if (!CollectionUtils.isEmpty(versionList)) {
            for (Version version : versionList) {
                buildFileUrl(version);
            }
        }
    }

    /**
     * 构建一个 APP文件路径
     * @param version 版本信息
     */
    public void buildFileUrl(Version version) {
        if (version != null) {
            if (null != version.getUrl()) {
                version.setUrl(FileCenterClient.buildUrl(version.getUrl()));
            }
        }
    }

}
