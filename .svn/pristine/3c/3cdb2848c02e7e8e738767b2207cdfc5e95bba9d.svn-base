package com.business.core.operations.activity;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.activity.Activity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Sin on 2016/2/17.
 *
 * 活动 核心 Server
 */
@Service
public class ActivityCoreService {

    /**
     * 构建多个 活动文件路径
     * @param activityList 活动集合
     */
    public void buildFileUrls(List<Activity> activityList) {
        if (!CollectionUtils.isEmpty(activityList)) {
            for (Activity activity : activityList) {
                buildFileUrl(activity);
            }
        }
    }

    /**
     * 构建一个 活动文件路径
     * @param activity 活动集合
     */
    public void buildFileUrl(Activity activity) {
        if (activity != null) {
            if (null != activity.getThemeImage()) {
                activity.setThemeImage(FileCenterClient.buildUrl(activity.getThemeImage()));
            }
        }
    }
}
