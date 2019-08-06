package com.business.app.file;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.file.File;
import com.business.core.entity.file.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * Created by edward on 2016/8/17.
 */
@Service
public class FileService {

    @Autowired
    private FileDao fileDao;

    public List<File> voicePackets(Integer voiceType) {
        List<File> files = fileDao.findByFileType(File.FILE_TYPE_VOICE_PACKETS, voiceType);
        if (CollectionUtils.isEmpty(files)) {
            return Collections.EMPTY_LIST;
        }
        return files;
    }

    /**
     * 查询所有语音包清单
     */
    public List<Voice> findVoices() {
        return fileDao.findVoices();
    }

    public File findFile(String voiceId, String targetApp) {
        return fileDao.findFileByVoiceId(voiceId, targetApp);
    }

    /**
     * 添加 file
     *
     * @param file 文件对象
     * @param uploadFile
     */
    public void add(File file, MultipartFile uploadFile) {
        if (uploadFile != null) {
            String fileLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, uploadFile);
            file.setFileLink(fileLink);
        }
        file.setAddTime(System.currentTimeMillis());
        file.setStatus(Constants.STATE_INVALID);
        fileDao.saveFile(file);
    }
}
