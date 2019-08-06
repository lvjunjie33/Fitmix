package com.business.app.userSportsWatch;

import com.alibaba.fastjson.JSON;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.user.watch.UserSportsWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/4/5.
 */
@Controller
@RequestMapping("user-sport-watch")
public class UserSportsWatchController {

    @Autowired
    private UserSportsWatchService userSportsWatchService;

    /**
     * 保存 运动手表的运动记录
     * @param type 运动类型
     * @param uid 用户编号
     * @param sportBTime 运动开始时间
     * @param sportETime 运动结束时间
     * @param sportSumTime 运动总时间
     */
    @RequestMapping("add")
    public void add(Model model, HttpServletRequest request, Integer type, Integer uid, Long sportBTime, Long sportETime, Long sportSumTime, String sportDetails) {
        Map<String, MultipartFile> fileMap = FileCenterClient.buildMultipartFile(request);

        Map<String, Object> details = null;
        if (!StringUtils.isEmpty(sportDetails)) {
            details = JSON.parseObject(sportDetails, Map.class);
        }

        UserSportsWatch userSportsWatch = userSportsWatchService.add(fileMap, type, uid, sportBTime, sportETime, sportSumTime, details);
        model.addAttribute("sportsWatch", userSportsWatch);
    }

    /**
     * 查询运动手表运动记录
     *
     * @param sportBTime 开始时间
     * @param uid 用户编号
     */
    @RequestMapping("history")
    public void history(Model model, Long sportBTime, Integer uid) {
        List<UserSportsWatch> userSportsWatches = userSportsWatchService.history(sportBTime, uid);
        model.addAttribute("sportWatch", userSportsWatches);
    }
}
