package com.business.work.file;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.file.File;
import com.business.core.entity.file.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by edward on 2016/8/17.
 */
@Service
public class FileService {

    @Autowired
    private FileDao fileDao;

    /**
     * 添加 file
     *
     * @param file 文件对象
     * @param uploadFile
     */
    public void add(File file, MultipartFile uploadFile) {
        if (uploadFile != null) {
            String fileLink =AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, uploadFile);
            file.setFileLink(fileLink);
        }
        file.setAddTime(System.currentTimeMillis());
        file.setStatus(Constants.STATE_INVALID);
        if("zh".equals(file.getDes_lan())){
            file.setDes_en("");
            file.setDes_lan_en("");
            file.setDes_lan(file.getDes_lan());
        }
        if("en".equals(file.getDes_lan())){
            file.setDes_en(file.getDes());
            file.setDes_lan_en(file.getDes_lan());
            file.setDes("");
            file.setDes_lan("");
        }
        fileDao.saveFile(file);
    }

    /**
     * 文件分页
     *
     * @param page 分页对象
     */
    public void page(Page<File> page) {
        fileDao.page(page);
    }

    /**
     * 文件分页API
     *
     * @param page 分页对象
     */
    public void pageApi(Page<File> page) {
        fileDao.pageApi(page);
    }


    /**
     * 文件分页API
     *
     * @param page 分页对象
     */
    public void getWatchVersion(Page<File> page,String lan) {
        fileDao.getWatchVersion(page, lan,"other", "fileLink", "des","isForce","title","des_en","des_lan","des_lan_en");
    }

    /**
     * 通过文件编号 查询文件
     *
     * @param id 文件编号
     */
    public File findFileById(Long id) {
        File file = fileDao.findFile(id);
        file.setFileLink(FileCenterClient.buildUrl(file.getFileLink()));
        return file;
    }

    /**
     * 通过文件编号 查询文件
     *
     * @param id 文件编号
     */
    public File findFileByIdAjax(Long id,String lan) {
        File file = fileDao.findFileAjax(id,lan);
        if(file==null && "zh".equals(lan)){
            file=new File();
            file.setDes_lan("zh");
            Update update = Update.update("des_lan", lan);
            fileDao.modify(file.getId(), update);
            file = fileDao.findFileAjax(id,lan);
        }
        if(file!=null){
            file.setFileLink(FileCenterClient.buildUrl(file.getFileLink()));
        }
        return file;
    }

    /**
     * 修改文件
     *
     * @param file 文件
     * @param uploadFile 上传的文件
     */
    public void modifyFile(File file, MultipartFile uploadFile) {
        Update update = Update.update("title", file.getTitle()).set("status", file.getStatus());
        if("zh".equals(file.getDes_lan())){
            update.set("des", file.getDes()).set("des_lan",file.getDes_lan());
        }
        if("en".equals(file.getDes_lan())){
            update.set("des_en", file.getDes()).set("des_lan_en",file.getDes_lan());
        }
        if (!CollectionUtils.isEmpty(file.getOther())) {
            update.set("other", file.getOther());
        }
        if (uploadFile != null) {
            String fileLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, uploadFile);
            update.set("fileLink", fileLink);
        }

        fileDao.modify(file.getId(), update);
    }


    /**
     * 删除文件
     *
     * @param file 文件
     */
    public void watchDelete(File file) {
        fileDao.deleteFile(file.getId());
    }

    /**
     * 设置语音包为最新版本
     */
    public void handsetSetNew(Long id) {
        File file = fileDao.findFile(id);
        Object serial = file.getOther().get("serial");
        if (serial != null) {
            fileDao.unsetIsNewBySerial(file.getOther().get("serial").toString());
            fileDao.modify(id, Update.update("other.isNew", Constants.IS_NEW_TRUE));
        }
    }

    /**
     * 获取语音清单
     */
    public List<Voice> voiceList() {
        return fileDao.voiceList();
    }

    /**
     * 添加语音清单
     *
     * @param voice 语音
     */
    public void voiceAdd(Voice voice) {
        voice.setStatus(Constants.SWITCH_OPEN);
        voice.setAddTime(System.currentTimeMillis());
        fileDao.voiceAdd(voice);
    }

    /**
     * 修改语音包清单
     *
     * @param voice 语音包清单
     */
    public void voiceModify(Voice voice) {
        Update update = Update.update("title", voice.getTitle()).set("tags", voice.getTags()).set("des", voice.getDes()).set("status", voice.getStatus());
        fileDao.updateVoice(voice.getId(), update);
    }

    /**
     * 查询语音清单
     *
     * @param id 编号
     */
    public Voice findVoice(Integer id) {
        return fileDao.findVoice(id);
    }

    /**
     * 文件添加封面图
     *
     * @param fileId 文件编号
     * @param coverImg 封面图文件
     */
    public void setCoverImg(Integer fileId, MultipartFile coverImg) {
        if (coverImg == null) {
            return;
        }
        String coverLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_COVER_FILE, coverImg);
        fileDao.updateVoice(fileId, Update.update("coverLink", coverLink));
    }

    /**
     * 文件添加icon图
     *
     * @param fileId 文件编号
     * @param iconImg icon图文件
     */
    public void setIconImg(Integer fileId, MultipartFile iconImg) {
        if (iconImg == null) {
            return;
        }
        String iconLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_ICON_FILE, iconImg);
        fileDao.updateVoice(fileId, Update.update("iconLink", iconLink));
    }

}
