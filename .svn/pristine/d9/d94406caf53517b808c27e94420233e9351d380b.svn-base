package script;

import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAuthor;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.CollectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sin on 2015/8/10 0010.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MixAuthorScript extends BaseMongoDaoSupport {

    @Test
    public void mixAuthor() {
        List<Mix> mixList = getRoutingMongoOps().findAll(Mix.class);
        Map<String, List<Mix>> mixMultimap = CollectionUtil.buildMultimap(mixList, String.class, Mix.class, "author");

        /// 将 Mix 中的 Author 属性名称， 转换到  MixAuthor 中
        for (Map.Entry<String, List<Mix>> entry : mixMultimap.entrySet()) {
            String key = entry.getKey();

            MixAuthor mixAuthor = new MixAuthor();
            mixAuthor.setName(key);
            mixAuthor.setAddTime(System.currentTimeMillis());
            insertToMongo(mixAuthor);

            /// 再将 MixAuthor 作者编号给 Mix 中的 AuthorId maid
            Set<Integer> mixIdSet = CollectionUtil.buildSet(entry.getValue(), Integer.class, "id");
            getRoutingMongoOps().updateMulti(Query.query(Criteria.where("id").in(mixIdSet)), Update.update("maid", mixAuthor.getId()).unset("author"), Mix.class);
        }
    }
}
