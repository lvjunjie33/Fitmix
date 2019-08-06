package script;

import com.business.core.entity.mix.Mix;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by sin on 2015/7/10 0010.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MixScript extends BaseMongoDaoSupport {


    @Test
    public void 歌曲名_首字母大写 () {
        List<Mix> allMix = getRoutingMongoOps().findAll(Mix.class);
        for (Mix mix : allMix) {

            // 处理歌曲名字， 首字母大写，其他字母小写
            String[] names = mix.getName().split("\\s+");
            String buildNames = 格式化_首字母大写_其他字母小写(names);

            // 处理作者名称 ， DJ 大写，没DJ前缀的 + 上 DJ， 首字母大写，其他字母小写
            String authorName;
            if ((mix.getAuthor().toLowerCase()).indexOf("dj") == -1) {
                authorName = "DJ " + 格式化_首字母大写_其他字母小写(mix.getAuthor().split("\\s+"));
            }
            else {
                authorName = "DJ " + 格式化_首字母大写_其他字母小写((mix.getAuthor().toLowerCase()).replace("dj", " ").split("\\s+"));
            }
            updateEntityFieldsById(Mix.class, mix.getId(), Update.update("name", buildNames.trim()).set("author", authorName.trim()));
        }
    }

    public String 格式化_首字母大写_其他字母小写 (String[] strArr) {
        String buildNames = "";
        for (int i = 0; i < strArr.length; i ++) {
            String st = strArr[i];
            char[] chars = st.toCharArray();
            if (chars.length == 0) {
                continue;
            }
            chars[0] = (String.valueOf(chars[0]).toUpperCase()).toCharArray()[0];
            for (int j = 1; j < chars.length; j++) {
                chars[j] = (String.valueOf(chars[j]).toLowerCase()).toCharArray()[0];
            }
            buildNames += String.valueOf(chars) + " ";
        }
        return buildNames;
    }

    @Test
    public void allMixDownloadCount() {
        List<Mix> allMix = getRoutingMongoOps().findAll(Mix.class);
        Integer totalCount = CollectionUtil.buildPropertyCount(allMix, Integer.class, "downloadCount");
        System.out.println(totalCount);
    }


    @Test
    public void 上价歌曲() {

        Date date = DateUtil.parse("2015-12-01", "yyyy-MM-dd");
        Query query = new Query(Criteria.where("shelvesTime").gte(date.getTime()));
        includeFields(query, "id", "name", "url", "albumUrl", "albumUrl_2");
        List<Mix> mixList = getRoutingMongoOps().find(query, Mix.class);


        for (Mix mix : mixList) {
            System.out.println("编号：" + mix.getId() + "\r\n歌曲名：" + mix.getName() + "\r\n文件地址：" + mix.getUrl() + "\r\n歌曲图片大图：" + mix.getAlbumUrl() + "\r\n歌曲图片小图：" + mix.getAlbumUrl_2());
            System.out.println("\r\n");
        }
    }
}
