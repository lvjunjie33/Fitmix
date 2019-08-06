package com.business.core.entity.cityNo;

import com.business.core.entity.CityNo;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Sin on 2016/2/26.
 *
 * 城市 编号
 */
@Repository
public class CityNoCoreDao extends BaseMongoDaoSupport {

    /**
     * 根据 areaEn 查询 城市编号
     *
     * @param areaEn 地区 en
     * @param fields 列
     * @return 城市信息
     */
    public CityNo findCityByAreaEn(String areaEn, String...fields) {
        Query query = new Query(Criteria.where("areaEn").is(areaEn));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, CityNo.class);
    }

    /**
     * 根据 areaCh 查询 城市编号
     *
     * @param areaCh 地区 ch
     * @param fields 列
     * @return 城市信息
     */
    public CityNo findCityByAreaCh(String areaCh, String...fields) {
        Query query = new Query(Criteria.where("areaCh").is(areaCh));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, CityNo.class);
    }
}
