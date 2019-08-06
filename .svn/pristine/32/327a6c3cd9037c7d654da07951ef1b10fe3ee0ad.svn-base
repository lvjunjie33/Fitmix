package com.business.work.app;

import com.business.core.entity.SysParam;
import com.business.work.sys.SysParamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sin on 2015/8/28.
 */
@Service
public class AppService {

    @Autowired
    private SysParamDao sysParamDao;

    /**
     * 设置 app 首页背景图
     * @param fileUrl 图片文件地址
     */
    public void setHomeBackgroundImage(String fileUrl) {
        sysParamDao.updateSysParam(Update.update("APP_HOME_BACKGROUND_IMAGE", fileUrl));
        SysParam.init(); // 刷新缓存 TODO redis 缓存是需要去掉
    }
}
