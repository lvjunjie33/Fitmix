package com.business.work.api;

import com.business.core.entity.api.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Service
public class ApiService {

    @Autowired
    private ApiDao apiDao;


    /**
     * 添加API
     * @param api
     */
    public void apiAdd(Api api) {
        apiDao.insertApi(api);
    }

    /**
     * 根据URL 查找API
     * @param url
     * @return
     */
    public Api findApiByUrl(String url) {
        return apiDao.findApiByUrl(url);
    }

    /**
     * 获取所有API
     * @return
     */
    public List<Api> findAllApi() {
        return apiDao.findAllApi();
    }

    /**
     * 查找API列表
     * @param modules 模块
     * @return
     */
    public List<Api> findAllApiByModules(String modules) {
        if(null == modules || "".equals(modules.trim())){
            modules = "app";
        }
        return apiDao.findAllApiByModules(modules);
    }

    /**
     * 删除所有API
     */
    public void removeAll() {
        apiDao.removeAll();
    }
}
