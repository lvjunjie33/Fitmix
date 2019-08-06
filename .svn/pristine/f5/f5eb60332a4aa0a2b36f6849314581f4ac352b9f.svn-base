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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sin on 2015/7/16 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MixGenreParentScript extends BaseMongoDaoSupport {

/*
    流行	REGGAE、FUNKY、CLASSIC POP、OLDIES、COUNTRY、LATIN POP、R&B、FOLK、POP
    摇滚	ROCK、POP PUNK、PUNK、METAL、INDIE ROCK、BRITPOP、EMO、HARDCORE、GRUNGE
    电子	EDM、HOUSE、TECHOUSE、TRANCE、TRAP、DUBSTEP、DRUM&BASS、DEEP HOUSE
    嘻哈	HIP HOP、RAP

    HARD ROCK  POPROCK
*/

    @Test
    public void genre_转换父级接口 () {
        List<Mix> allMix = getRoutingMongoOps().findAll(Mix.class);

        Integer[] 流行 = new Integer[]{13, 11, 3, 7};
        Integer[] 摇滚 = new Integer[]{9, 14, 10, 15, 8};
        Integer[] 电子 = new Integer[]{2, 1, 16, 5, 3, 12, 4};
        Integer[] 嘻哈 = new Integer[]{6};

        List<Integer> newId = new ArrayList<>();
        for (Mix mix : allMix) {
            List<Integer> genreParent = new ArrayList<>();
            if (null != mix.getGenre()) {
                for (Integer genre : mix.getGenre()) {
                    if (Arrays.asList(流行).contains(genre)) {
                        genreParent.add(1);
                    } else if (Arrays.asList(摇滚).contains(genre)) {
                        genreParent.add(2);
                    } else if (Arrays.asList(电子).contains(genre)) {
                        genreParent.add(3);
                    } else if (Arrays.asList(嘻哈).contains(genre)) {
                        genreParent.add(4);
                    }
                }
                getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(mix.getId())), Update.update("genreParent", genreParent), Mix.class);
            }
            else {
                newId.add(mix.getId());

            }
        }

        for (Integer ids : newId) {
            System.out.println(ids);
        }
    }
}
