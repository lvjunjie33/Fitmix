package com.business.app.restHeartRate;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.user.info.RestHeartRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edward on 2016/9/5.
 */
@Service
public class RestHeartRateService {

    @Autowired
    private RestHeartRateDao restHeartRateDao;

    /**
     * 保存静息心率
     *
     * @param uid 用户编号
     * @param heartRateVal 心率值
     * @param detectTime 检测时间
     */
    public Long save(Integer uid , Integer heartRateVal, Long detectTime) {
        RestHeartRate restHeartRate = new RestHeartRate();
        restHeartRate.setUid(uid);
        restHeartRate.setDetectTime(detectTime);
        restHeartRate.setHeartRateVal(heartRateVal);
        restHeartRate.setLastUpdateTime(System.currentTimeMillis());
        restHeartRate.setIsRemove(Constants.STATE_EFFECTIVE);
        restHeartRateDao.save(restHeartRate);
        return restHeartRate.getDetectTime();
    }

    /**
     * 删除静息心率
     *
     * @param id 删除静息心率
     * @param uid 用户编号
     */
    public void remove(Long id, Integer uid) {
        restHeartRateDao.modify(id, uid, Update.update("isRemove", Constants.STATE_INVALID));
    }

    /**
     * 获取静息心率全列表
     *
     * @param index 当前页
     * @param uid 用户编号
     * @param isAll 是否是全列表
     */
    public List<RestHeartRate> page(Integer index, Integer uid, Integer isAll) {
        if (RestHeartRate.IS_ALL_YES.equals(isAll)) {//全列表
            return restHeartRateDao.list(uid, RestHeartRate.fields);
        } else {//分页查询
            Page<RestHeartRate> page = new Page<>();
            page.setPageNo(index);
            page.getFilter().put("uid", uid);
            restHeartRateDao.page(page, RestHeartRate.fields);
            return page.getResult();
        }
    }
}
