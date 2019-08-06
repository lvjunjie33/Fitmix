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
 * Created by sin on 2015/7/22 0022.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MixBpmScript extends BaseMongoDaoSupport {

    @Test
    public void bpm转成数组 () {
        List<Mix> mixList = getRoutingMongoOps().findAll(Mix.class);
        for (Mix mix : mixList) {
            getRoutingMongoOps().updateFirst(
                    Query.query(Criteria.where("id").is(mix.getId())),
                    Update.update("bpmType", Mix.BMP_TYPE_FIXED).set("bpmVariableBegin", Integer.valueOf(mix.getBpm())).set("bpmVariableEnd", Integer.valueOf(mix.getBpm())), Mix.class);
        }
    }
}
