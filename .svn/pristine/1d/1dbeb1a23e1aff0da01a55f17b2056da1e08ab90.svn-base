package script;

import com.business.core.entity.mix.Mix;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sin on 2015/7/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MixSceneScript extends BaseMongoDaoSupport {

    @Test
    public void mix歌曲跑步_转快速跑等 () {
        List<Mix> allMix = getRoutingMongoOps().findAll(Mix.class);
//        for (Mix mix : allMix) {
//            if (mix.getBpm() >= 180) {
//                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(mix.getId())), Update.update("scene", new Integer[]{1}), Mix.class);
//            }
//            else if (mix.getBpm() >= 140 && mix.getBpm() < 180) {
//                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(mix.getId())), Update.update("scene", new Integer[]{2}), Mix.class);
//            }
//            else if (mix.getBpm() >= 120 && mix.getBpm() < 140) {
//                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(mix.getId())), Update.update("scene", new Integer[]{3}), Mix.class);
//            }
//            else if (mix.getBpm() < 120) {
//                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(mix.getId())), Update.update("scene", new Integer[]{4}), Mix.class);
//            }
//        }
    }
}
