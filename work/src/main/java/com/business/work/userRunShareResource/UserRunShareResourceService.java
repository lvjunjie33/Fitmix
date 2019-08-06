package com.business.work.userRunShareResource;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.user.UserRunShareResource;
import com.business.core.operations.userRunShareResource.UserRunShareResourceCoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by sin on 2016/1/14.
 */
@Service
public class UserRunShareResourceService {

    @Autowired
    private UserRunShareResourceDao userRunShareResourceDao;
    @Autowired
    private UserRunShareResourceCoreDao userRunShareResourceCoreDao;


    /**
     * 查询 Page 资源分享
     * @param page 分页信息
     */
    public void page(Page<UserRunShareResource> page) {
        userRunShareResourceDao.page(page);
    }

    /**
     * 添加 分享资源信息
     *
     * @param image 图片
     * @param content 内容
     * @param type 类型：{@link UserRunShareResource#type}
     * @return 0、成功 1、内容不能为空 2、图片不能为空
     */
    public int add(MultipartFile image, String content, int type) {
        UserRunShareResource userRunShareResource = new UserRunShareResource();
        userRunShareResource.setType(type);
        if (UserRunShareResource.TYPE_CONTENT == type) {
            // 内容不能为空
            if (StringUtils.isEmpty(content) || content.length() < 1) {
                return 1;
            }
            userRunShareResource.setContent(content);
        }
        else {
            // 图片不能为空
            if (null == image) {
                return 2;
            }
            String imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_RUN_SHARE_RESOURCE_IMAGE, image);
            userRunShareResource.setImageUrl(imageUrl);
        }
        userRunShareResource.setStatus(UserRunShareResource.STATUS_INVALID);
        userRunShareResource.setAddTime(System.currentTimeMillis());

        // 插入数据库
        userRunShareResourceCoreDao.insertUserRunShareResource(userRunShareResource);
        return 0;
    }

    /**
     * 修改 资源图片
     * @param id 编号
     * @param image 图片
     * @return 0、成功 1、资源不存在 2、修改的资源不能为空
     */
    public int modifyImage(Integer id, MultipartFile image) {
        if (null == userRunShareResourceCoreDao.findUserRunShareResourceByIdAndStatus(id, null, "id")) {
            return 1;
        }
        if (null == image) {
            return 2;
        }
        String imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_RUN_SHARE_RESOURCE_IMAGE, image);
        userRunShareResourceCoreDao.updateUserRunShareResourceById(id, Update.update("imageUrl", imageUrl));
        return 0;
    }


    /**
     * 修改 资源内容
     * @param id 编号
     * @param content 资源内容信息
     * @return 0、成功 1、资源不存在 2、资源内容不能为空
     */
    public int modifyContent(Integer id, String content) {
        if (null == userRunShareResourceCoreDao.findUserRunShareResourceByIdAndStatus(id, null, "id")) {
            return 1;
        }
        if (StringUtils.isEmpty(content) || content.length() < 1) {
            return 2;
        }
        userRunShareResourceCoreDao.updateUserRunShareResourceById(id, Update.update("content", content));
        return 0;
    }

    /**
     * 修改 资源状态
     * @param id 编号
     * @param status 类型
     * @return 0、成功 1、资源不存在
     */
    public int modifyStatus(Integer id, int status) {
        if (null == userRunShareResourceCoreDao.findUserRunShareResourceByIdAndStatus(id, null, "id")) {
            return 1;
        }
        userRunShareResourceCoreDao.updateUserRunShareResourceById(id, Update.update("status", status));
        return 0;
    }
}
