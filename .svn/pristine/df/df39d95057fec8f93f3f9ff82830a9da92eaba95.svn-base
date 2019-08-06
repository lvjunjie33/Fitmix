package com.business.work.apiDetails;

import com.business.core.entity.api.ApiDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Service
public class ApiDetailsService {

    @Autowired
    private ApiDetailsDao apiDetailsDao;

    /**
     * 添加ApiDetails
     * @param apiDetails 对象
     */
    public void apiDetailsAdd(ApiDetails apiDetails) {
        apiDetailsDao.apiDetailsAdd(apiDetails);
    }

    /**
     * 根据Api 编号获取Api详情
     * @param apiId Api编号
     * @return
     */
    public ApiDetails findApiDetailsByApiId(Integer apiId) {
       return apiDetailsDao.findApiDetailsByApiId(apiId);
    }

    /**
     * 删除所有API 详情
     */
    public void removeAll() {
        apiDetailsDao.removeAll();
    }
}
