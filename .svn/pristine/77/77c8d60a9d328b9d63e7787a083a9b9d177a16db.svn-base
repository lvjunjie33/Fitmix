package com.business.bbs.mix;

import com.business.bbs.base.support.AbstractDao;
import com.business.core.entity.mix.Mix;
import com.business.core.mongo.MongoType;
import com.mongodb.CommandResult;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/9/18.
 */
@Repository
public class MixDao extends AbstractDao<Mix> {

    public CommandResult getRankMap(String command) {
        return getMongoOps(MongoType.BUSINESS_SECONDARY).executeCommand(command);
    }

}
