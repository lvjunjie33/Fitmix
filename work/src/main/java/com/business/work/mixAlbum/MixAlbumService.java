package com.business.work.mixAlbum;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAlbum;
import com.business.work.mix.MixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Sin on 2016/3/9.
 *
 * mix 专辑
 */
@Service
public class MixAlbumService {

    @Autowired
    private MixAlbumDao mixAlbumDao;
    @Autowired
    private MixDao mixDao;


    public void list(Page<MixAlbum> page) {
        mixAlbumDao.findPageMixAlbum(page);
    }

    public MixAlbum info(Integer albumId) {
        MixAlbum mixAlbum = mixAlbumDao.findMixAlbumById(albumId);
        List<Mix> mixList = mixDao.findMixByids(mixAlbum.getMixIds());
        mixAlbum.setMixList(mixList);
        return mixAlbum;
    }

    /**
     * 添加 mix 专辑
     *
     * @param file 专辑图片
     * @param title 标题
     * @param desc 描述
     * @param mixIds 歌曲编号
     */
    public void add(MultipartFile file, String title, String desc, List<Integer> mixIds) {
        String albumImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_ALBUM_IMAGE, file);
        MixAlbum album = new MixAlbum();
        album.setBackImage(albumImageUrl);
        album.setTitle(title);
        album.setDesc(desc);
        album.setSort(0);
        album.setMixIds(mixIds);
        album.setStatus(MixAlbum.STATUS_NOT_RELEASE);
        album.setAddTime(System.currentTimeMillis());
        mixAlbumDao.insertMixAlbum(album);
    }

    /**
     * 修改 mix 专辑
     *
     * @param albumId 编号
     * @param file 专辑图片
     * @param title 标题
     * @param desc 描述
     * @param mixIds 歌曲编号
     */
    public void modify(Integer albumId,
                       MultipartFile file, String title, Integer status, String desc, List<Integer> mixIds) {

        Update update = Update.update("title", title).set("desc", desc).set("mixIds", mixIds).set("status", status);
        if (file != null) {
            MixAlbum album = mixAlbumDao.findMixAlbumById(albumId);
            if (null != album.getBackImage()) {
                AliyunCenterClient.deleteFile(album.getBackImage());
            }
            String albumImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_ALBUM_IMAGE, file);
            update.set("backImage", albumImageUrl);
        }
        mixAlbumDao.updateMixAlbumById(albumId, update);
    }

    public MixAlbum findById(Integer id) {
        return mixAlbumDao.findMixAlbumById(id);
    }

    public void mixAlbumModifyCheckStatus(Integer id, Integer checkStatus, String checkMessage) {
        if(checkStatus == Constants.CHECK_STATUS_SUCCESS) {
            mixAlbumDao.updateMixAlbumById(id, Update.update("checkStatus", checkStatus));
        } else if(checkStatus == Constants.CHECK_STATUS_FAIL) {
            mixAlbumDao.updateMixAlbumById(id, Update.update("checkStatus", checkStatus).set("checkMessage", checkMessage));
        }
    }

}
