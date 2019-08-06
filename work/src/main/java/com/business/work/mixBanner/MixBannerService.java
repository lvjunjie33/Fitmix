package com.business.work.mixBanner;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.MixBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Sin on 2016/3/7.
 */
@Service
public class MixBannerService {

    @Autowired
    private MixBannerDao mixBannerDao;
    /**
     * banenr 分页
     *
     * @param page
     */
    public void list(Page<MixBanner> page) {
        mixBannerDao.findPageMixBanner(page);
    }

    /**
     * 添加 banner
     *
     * @param file backImage 图片
     * @param title 标题
     * @param sort 排序
     * @param type 类型 （1、mix 专辑 2、url 网页 3、单曲 4、电台）
     * @param typeValue 类型值
     * @param desc 描述
     */
    public void add(MultipartFile file, String title, Integer sort,
                   Integer type, String typeValue, String desc, Integer channel, String iosSchemesUrl, String androidSchemesUrl) {

        String backImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_BANNER_IMAGE, file);

        MixBanner banner = new MixBanner();
        banner.setBackImage(backImageUrl);
        banner.setTitle(title);
        banner.setSort(sort);
        banner.setType(type);
        banner.setTypeValue(typeValue);
        banner.setDesc(desc);
        banner.setStatus(MixBanner.STATUS_NOT_RELEASE);
        banner.setAddTime(System.currentTimeMillis());
        banner.setChannel(channel);
        banner.setIosSchemesUrl(iosSchemesUrl);
        banner.setAndroidSchemesUrl(androidSchemesUrl);

        mixBannerDao.insertMixBanner(banner);
    }

    /**
     * 查询一个 mix Banner
     *
     * @param bannerId 编号
     * @return banner 信息
     */
    public MixBanner findMixBanner(Integer bannerId) {
        return mixBannerDao.findMixBannerById(bannerId);
    }

    /**
     * 修改 banner
     *
     * @param bannerId 编号
     * @param file backImage 图片
     * @param title 标题
     * @param sort 排序
     * @param type 类型 （1、mix 专辑 2、url 网页 3、单曲 4、电台）
     * @param status 状态
     * @param typeValue 类型值
     * @param desc 描述
     */
    public void modify(Integer bannerId, MultipartFile file, String title, Integer sort,
                    Integer type, Integer status, String typeValue, String desc, Integer channel, String iosSchemesUrl, String androidSchemesUrl) {
        MixBanner banner =  mixBannerDao.findMixBannerById(bannerId);

        Update update = Update.update("title", title).
                set("sort", sort).set("status", status).set("desc", desc).set("type", type)
                .set("typeValue", typeValue).set("channel", channel).set("iosSchemesUrl", iosSchemesUrl).set("androidSchemesUrl", androidSchemesUrl);

        // 更新文件
        if (file != null) {
            if (null != banner.getBackImage()) {
                AliyunCenterClient.deleteFile(banner.getBackImage());
            }
            String backImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_BANNER_IMAGE, file);
            update.set("backImage", backImageUrl);
        }

        mixBannerDao.updateMixBannerById(bannerId, update);
    }

    /**
     * 修改 状态：0、发布 1、未发布
     *
     * @param bannerId 编号
     * @param status 状态 0、发布 1、未发布
     */
    public void modifyStatus(Integer bannerId, Integer status) {
        mixBannerDao.updateMixBannerById(bannerId, Update.update("status", status));
    }
}
