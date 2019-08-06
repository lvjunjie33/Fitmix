package com.business.app.userSportsWatch;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.user.watch.UserSportsWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/4/5.
 */
@Service
public class UserSportsWatchService {

    @Autowired
    private UserSportsWatchDao userSportsWatchDao;

    public UserSportsWatch add(Map<String, MultipartFile> fileMap, Integer type, Integer uid, Long sportBTime, Long sportETime, Long sportSumTime, Map<String, Object> sportDetails) {
        String sportFileZipUrl = null;
        if (fileMap.containsKey("sportFileZip")) {
            MultipartFile sportFileZip = fileMap.get("sportFileZip");
            if (sportFileZip != null) {
                sportFileZipUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RUN_FILE_ZIP, sportFileZip);
            }
        }

        UserSportsWatch userSportsWatch = new UserSportsWatch();
        userSportsWatch.setUid(uid);
        userSportsWatch.setType(type);
        userSportsWatch.setSportFile(sportFileZipUrl);
        userSportsWatch.setSportBTime(sportBTime);
        userSportsWatch.setSportETime(sportETime);
        userSportsWatch.setSportDetails(sportDetails);
        userSportsWatch.setSportSumTime(sportSumTime);

        userSportsWatch.setAddTime(System.currentTimeMillis());
        userSportsWatch.setStatus(Constants.STATUS_NORMAL);

        UserSportsWatch watch = userSportsWatchDao.findBySportBTime(sportBTime, uid);
        if (null == watch) {
            userSportsWatchDao.saveSportWatch(userSportsWatch);
            watch = userSportsWatch;
        }
        watch.setSportFile(FileCenterClient.buildUrl(watch.getSportFile()));
        return watch;
    }

    /**
     * 查询历史数据
     *
     * @param sportBTime 运动开始时间
     * @param uid 用户编号
     */
    public List<UserSportsWatch> history(Long sportBTime, Integer uid) {
        List<UserSportsWatch> userSportsWatches = userSportsWatchDao.findUserSportsWatch(sportBTime, uid);
        for (UserSportsWatch u : userSportsWatches) {
            u.setSportFile(FileCenterClient.buildUrl(u.getSportFile()));
        }
        return userSportsWatches;
    }
}
