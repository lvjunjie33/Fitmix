package com.business.app.mixAlbum;

import com.business.app.mix.MixDao;
import com.business.app.mix.MixService;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sin on 2016/3/8.
 */
@Service
public class MixAlbumService {

    @Autowired
    private MixAlbumDao mixAlbumDao;
    @Autowired
    private MixDao mixDao;
    @Autowired
    private MixService mixService;


    /**
     * 专辑列表
     */
    public List<MixAlbum> list() {
        List<MixAlbum> mixAlbumList = mixAlbumDao.findAllMixAlbum("id", "title", "desc");
        // 构建 专辑 文件地址
        buildMixAlbumFiles(mixAlbumList);
        return mixAlbumList;
    }

    /**
     * 专辑 信息
     *
     * @param id 专辑编号
     * @return 专辑信息
     */
    public MixAlbum album(String lan,Integer id) {
        MixAlbum mixAlbum = mixAlbumDao.findMixAlbumById(id);
        // 构建 专辑 文件地址
        buildMixAlbumFile(mixAlbum);
        if (null != mixAlbum.getMixIds()) {
            List<Mix> mixList = mixDao.findMixByIds(lan,mixAlbum.getMixIds());
            // 构建 歌曲 文件地址
            mixService.buildMixFileUrl(mixList);
            // 构建 歌曲作者 信息
            mixService.buildMixAuthor(mixList);
            mixAlbum.setMixList(mixList);
        }
        return mixAlbum;
    }

    ///
    /// 工具

    /**
     * 构建 banner file 路径
     *
     * @param mixBannerList banner 信息
     */
    public void buildMixAlbumFiles(List<MixAlbum> mixBannerList) {
        for (MixAlbum mixAlbum : mixBannerList) {
            buildMixAlbumFile(mixAlbum);
        }
    }

    /**
     * 构建 file 路径
     *
     * @param mixAlbum 专辑信息
     */
    public void buildMixAlbumFile(MixAlbum mixAlbum) {
        if (null != mixAlbum.getBackImage()) {
            mixAlbum.setBackImage(FileCenterClient.buildUrl(mixAlbum.getBackImage()));
        }
    }
}
