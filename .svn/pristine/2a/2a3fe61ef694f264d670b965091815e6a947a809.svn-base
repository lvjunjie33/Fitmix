package com.business.core.operations.mixAuthor;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.mix.MixAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/8/5 0005.
 */
@Service
public class MixAuthorCoreService {

    @Autowired
    private MixAuthorCoreDao mixAuthorCoreDao;

    /**
     * 构建 MuAuthor 文件路径
     * @param mixAuthor 对象
     */
    public void buildMixAuthorFileUrl(MixAuthor mixAuthor) {
        if (mixAuthor != null) {
            if (null != mixAuthor.getAvatar()) {
                mixAuthor.setAvatar(FileCenterClient.buildUrl(mixAuthor.getAvatar()));
            }
            if (!CollectionUtils.isEmpty(mixAuthor.getPhotos())) {
                List<String> buildPhotos = new ArrayList<>();
                for (String url : mixAuthor.getPhotos()) {
                    buildPhotos.add(FileCenterClient.buildUrl(url));
                }
                mixAuthor.setPhotos(buildPhotos);
            }
        }
    }

    /**
     * 构建 MuAuthor 文件路径
     * @param mixAuthorList 集合
     */
    public void buildMixAuthorFileUrl(Collection<MixAuthor> mixAuthorList) {
        if (!CollectionUtils.isEmpty(mixAuthorList)) {
            for (MixAuthor mixAuthor : mixAuthorList) {
                buildMixAuthorFileUrl(mixAuthor);
            }
        }
    }

    /**
     * 查询所有 MixAuthor
     * @return MixAuthor集合
     */
    public List<MixAuthor> findAllMixAuthor() {
        List<MixAuthor> mixAuthorList = mixAuthorCoreDao.findAllMixAuthor();
        buildMixAuthorFileUrl(mixAuthorList);
        return mixAuthorList;
    }

    /**
     * mixAuthor 信息
     * @param id 用户编号
     * @return mixAuthor 信息
     */
    public MixAuthor findMixAuthor(Integer id) {
        MixAuthor mixAuthor = mixAuthorCoreDao.findMixAuthor(id);
        buildMixAuthorFileUrl(mixAuthor);
        return mixAuthor;
    }

    /**
     * 删除 相册信息
     * @param id
     * @param url
     */
    public void removePhotos(Integer id, String url) {
        MixAuthor mixAuthor = mixAuthorCoreDao.findMixAuthor(id, "id", "photos");
        if (!CollectionUtils.isEmpty(mixAuthor.getPhotos())) {
            mixAuthor.getPhotos().remove(url);
            mixAuthorCoreDao.updateMixAuthor(id, Update.update("photos", mixAuthor.getPhotos()));
        }
    }

    /**
     * 上传头像
     * @param id 编号
     * @param avatarUrl 头像地址
     */
    public void uploadAvatar(Integer id, String avatarUrl) {
        mixAuthorCoreDao.updateMixAuthor(id, Update.update("avatar", avatarUrl));
    }

    /**
     * 上传照片
     * @param id 编号
     * @param photos 照片
     */
    public void uploadPhotos(Integer id, Collection<String> photos) {
        MixAuthor mixAuthor = mixAuthorCoreDao.findMixAuthor(id, "photos");
        if (null != mixAuthor.getPhotos()) {
            photos.addAll(mixAuthor.getPhotos());
        }
        mixAuthorCoreDao.updateMixAuthor(id, Update.update("photos", photos));
    }
}
