package com.business.bbs.mix;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.bbs.base.QxMap;
import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.entity.mix.Mix;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * Created by fenxio on 2016/9/18.
 */
@Service
public class MixService extends AbstractService<Mix> {

    @Autowired
    private MixDao mixDao;

    @Override
    protected AbstractDao<Mix> getAbstractDao() {
        return mixDao;
    }

    /**
     * 获取 歌曲榜单
     * @return
     */
    public Mix getRank() {
        String result = "";
        String command = "{ \n" +
                "    \"mapReduce\": \"Mix\",\n" +
                "    \"map\": \"function () { var audition = this.auditionCount == null ? 0 : this.auditionCount;var value = this.baseAuditionCount + audition;value += '_' + this._id;var key = 'audition';emit(key, value);}\",\n" +
                "    \"reduce\": \"function (key, values) {var reducedValue = '' + values;return reducedValue;}\",\n" +
                "    \"out\": { \"inline\" : 1},\n" +
                "    \"query\": {\"state\":2},\n" +
                "    \"sort\": {\"_id\": 1},\n" +
                "    \"inputDB\": \"secondary\"\n" +
                " }";
        CommandResult out = mixDao.getRankMap(command);
        BasicDBList list = (BasicDBList) out.get("results");
        for (int i = 0; i < list.size(); i++) {
            BasicDBObject b = (BasicDBObject) list.get(i);
            result = b.getString("value");
        }
        String[] countAndId = result.split(",");
        Integer mixId = 0;
        Integer auditionCount = 0;
        for(String each : countAndId) {
            String[] value = each.split("_");
            if(StringUtils.isNumeric(value[0])) {
                if(Integer.parseInt(value[0]) > auditionCount) {
                    auditionCount = Integer.parseInt(value[0]);
                    mixId = Integer.parseInt(value[1]);
                }
            }
        }
        Mix mix = mixDao.findById(Mix.class, mixId);
//        if(mix.getName().length() > 11) {
//            mix.setName(mix.getName().substring(0, 10) + "...");
//        }
        return mix;
    }

}
