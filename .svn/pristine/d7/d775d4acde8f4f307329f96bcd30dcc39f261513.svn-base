package com.business.app.mix;

import com.business.app.base.support.BaseServiceSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.DicConstants;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.Page;
import com.business.core.entity.SysParam;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAuthor;
import com.business.core.operations.mixAuthor.MixAuthorCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.DicUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by sin on 15/4/13.
 */
@Service
public class MixService extends BaseServiceSupport {

    @Autowired
    private MixDao mixDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private MixAuthorCoreDao mixAuthorCoreDao;

    private void adjustMix(List<Mix> mix) {
        for (Mix m : mix) {
            adjustMix(m);
        }
    }

    private Integer add(Integer a, Integer b) {
        if (a == null) {
            a = 0;
        }
        if (b == null) {
            b = 0;
        }
        return a + b;
    }

    private void adjustMix(Mix mix) {
        mix.setDownloadCount(add(mix.getDownloadCount(), mix.getdOffsetNum()));
        mix.setShareCount(add(mix.getShareCount(), mix.getsOffsetNum()));
        mix.setAuditionCount(add(mix.getAuditionCount(), mix.getaOffsetNum()));
        mix.setBaseAuditionCount(add(mix.getBaseAuditionCount(), mix.getbOffsetNum()));
        mix.setCollectNumber(add(mix.getCollectNumber(), mix.getcOffsetNum()));
        mix.setdOffsetNum(null);
        mix.setsOffsetNum(null);
        mix.setaOffsetNum(null);
        mix.setbOffsetNum(null);
        mix.setcOffsetNum(null);
    }

    /**
     * 歌曲分页
     * @param scene
     * @param index
     * @return
     */
    public List<Mix> runMixPage (Integer scene,
                                 Integer index,
                                 Integer version,
                                 String channel) {
        List<Mix> mixList = userCoreDao.findNewMixByType(scene, null, SysParam.INSTANCE.APP_MIX_TYPE_PAGE_LIMIT_SIZE, index);

        adjustMix(mixList);

        buildMixFileUrl(mixList, channel, version);
        buildMixAuthor(mixList);
        return mixList;
    }

    /**
     * 场景分页 （scene 改成跑速： 快速跑 中速跑 慢速跑 健走)
     * @param index 小标
     * @param scene 场景
     * @return map 场景分页
     */
    public List<Mix> pageScene (String lan,int index, Integer scene) {
        List<Mix> mixList = mixDao.findMixByScenePage(lan,scene, null, SysParam.INSTANCE.APP_MIX_SCENE_PAGE_LIMIT_SIZE, index, -1);

        adjustMix(mixList);

        buildMixAuthor(mixList);
        return mixList;
    }

    /**
     * 场景分页
     * @param index 小标
     * @param genreParent 曲风父级 {@link com.business.core.constants.DicConstants#DIC_TYPE_MIX_GENRE}
     * @return map 场景分页
     */
    public List<Mix> pageGenre (String lan,int index, Integer genreParent) {
        List<Mix> mixList =  mixDao.findMixByGenreParentPage(lan,genreParent, SysParam.INSTANCE.APP_MIX_SCENE_PAGE_LIMIT_SIZE, index);

        adjustMix(mixList);

        buildMixAuthor(mixList);
        return mixList;
    }

    /**
     * mix 歌曲搜索
     * @param dicBpm 大于 bpm
     * @param scene 场景
     * @param genre 曲风
     * @param dicTrackLength 歌曲长度
     * @param index 分页index
     * @param search 搜索内容， bpm,scene,genre,trackLength,name
     * @return Mix集合
     */
    public List<Mix> searchMix (String lan,
                                Integer dicBpm,
                                Integer scene,
                                Integer genre,
                                Integer dicTrackLength,
                                Integer index,
                                String search,
                                Integer version,
                               String channel) {
        List<Mix> mixList = mixDao.searchMix(lan,dicBpm, scene, genre, dicTrackLength, SysParam.INSTANCE.APP_MIX_SEARCH_PAGE_SIZE, index, search);

        adjustMix(mixList);

        if (!CollectionUtils.isEmpty(mixList)) {
            buildMixFileUrl(mixList, channel, version);
            buildMixAuthor(mixList);
        }
        return mixList;
    }

    ///
    /// 下载次数统计

    /**
     * mix 下载次数统计
     * @param uid 用户编号
     * @param mid 歌曲编号
     */
    public int incDownloadCount (String lan,Integer uid, Integer mid) {
        if (uid != null && userCoreDao.findUserById(uid, "id") == null) {
            return 1;
        }
        if (mixDao.findMixById(lan,mid, "id") == null) {
            return 2;
        }
        if (uid != null) {
            userCoreDao.updateUserById(uid, new Update().inc("downloadCount", 1));
        }
        mixDao.updateById(mid, new Update().inc("downloadCount", 1));
        return 0;
    }

    /**
     * mix 试听次数统计
     * @param uid 用户编号
     * @param mid 歌曲编号
     */
    public int incAudition(String lan,Integer uid, Integer mid) {
        if (uid != null && userCoreDao.findUserById(uid, "id") == null) {
            return 1;
        }
        if (mixDao.findMixById(lan,mid, "id") == null) {
            return 2;
        }
        mixDao.updateById(mid, new Update().inc("auditionCount", 1));
        return 0;
    }



    /**
     * 查询多个 mix信息
     * @param ids mix 编号
     * @return 返回mix 信息
     */
    public List<Mix> findMixList(String lan,Collection<Integer> ids, String channel, Integer version) {
        List<Mix> mixList = mixDao.findMixByIds(lan,ids);

        adjustMix(mixList);

        buildMixFileUrl(mixList, channel, version);
        buildMixAuthor(mixList);
        return mixList;
    }


    public void buildMixFileUrl(Collection<Mix> mixList, String channel, Integer version) {
        for (Mix m : mixList) { // build 文件地址
            // TODO to [sin]  1.1.17 文件服务器切， Android sb 出问题 ，1.1.18 去掉
            if (StringUtils.isEmpty(channel) || StringUtils.isEmpty("appStore") || version != null && version >= 20) {
                m.setUrl(FileCenterClient.buildUrl(m.getUrl()));
                m.setAlbumUrl(FileCenterClient.buildUrl(m.getAlbumUrl()));
                m.setAlbumUrl_2(FileCenterClient.buildUrl(m.getAlbumUrl_2()));
            } else {
                m.setUrl(FileCenterClient.buildUrlDiscard(m.getUrl()));
                m.setAlbumUrl(FileCenterClient.buildUrlDiscard(m.getAlbumUrl()));
                m.setAlbumUrl_2(FileCenterClient.buildUrlDiscard(m.getAlbumUrl_2()));
            }
        }
    }

    public void buildMixFileUrl(Collection<Mix> mixList) {
        for (Mix mix : mixList) { // build 文件地址
            buildMixFileUrl(mix);
        }
    }

    public void buildMixFileUrl(Mix mix) {
        if (mix != null) {
            if (null != mix.getUrl()) {
                mix.setUrl(FileCenterClient.buildUrl(mix.getUrl()));
            }
            if (null != mix.getAlbumUrl()) {
                mix.setAlbumUrl(FileCenterClient.buildUrl(mix.getAlbumUrl()));
            }
            if (null != mix.getAlbumUrl_2()) {
                mix.setAlbumUrl_2(FileCenterClient.buildUrl(mix.getAlbumUrl_2()));
            }
        }
    }

    /**
     * 构建 mix 头像地址
     * @param mixList 歌曲集合
     */
    public void buildMixAuthor(List<Mix> mixList) {
        Set<Integer> midSet = CollectionUtil.buildSet(mixList, Integer.class, "maid");
        List<MixAuthor> mixAuthorList = mixAuthorCoreDao.findMixAuthor(midSet, "id", "name");
        Map<Integer, MixAuthor> mixAuthorMap = CollectionUtil.buildMap(mixAuthorList, Integer.class, MixAuthor.class, "id");
        for (Mix mix : mixList) {
            if (null != mix.getMaid()) {
                mix.setAuthor(mixAuthorMap.get(mix.getMaid()).getName());
            }
        }
    }

    /**
     * 2.0 用户首页图片
     * @param type 类型 1：运动歌单 2：电台
     * @return
     */
    public List<Dictionary> sceneList(String lan,Integer type) {
        List<Dictionary> descList = null;
        if(null != type && type == Mix.TYPE_RADIO) {
            descList = DicUtil.getDictionary(DicConstants.DIC_TYPE_RADIO_SCENE);
        } else {
            descList = DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_SCENE);
        }

        if (getTerminalType() == Constants.TERMINAL_ANDROID
                && getContext().getVersion() > VersionConstants.Android.VERSION_30.getVersion()
                || getTerminalType() == Constants.TERMINAL_IOS
                && getContext().getVersion() > VersionConstants.IOS.VERSION_4.getVersion()) {
            // step 新版 不做任何操作
        }
        else {
            List<Dictionary> newDecList = new ArrayList<>(descList.size());
            for (Dictionary dictionary : descList) {
                // clone object
                Dictionary newDictionary = ObjectUtils.clone(dictionary);
                // setting mix
                Mix mix = mixDao.findMixBySceneFirst(lan,newDictionary.getValue(), type, "albumUrl_2");
                mix.setAlbumUrl_2(FileCenterClient.buildUrl(mix.getAlbumUrl_2()));
                if (null != newDictionary.getOther()) {
                    Map<String, Object> dictionaryOther = ObjectUtils.clone(newDictionary.getOther());
                    dictionaryOther.put("image", mix.getAlbumUrl_2());
                    newDictionary.setOther(dictionaryOther);
                }
                newDecList.add(newDictionary);
            }
            // change descList
            descList = newDecList;
        }
        return descList;
    }

    /**
     * 场景 数据分页
     * @param type 分类
     * @param scene 场景
     * @param index 第几页
     * @return 场景数据
     */
    public Page<Mix> scenePage(String lan,Integer type, Integer scene, int index, Collection<Integer> ids, Integer sortType) {
        Page<Mix> page = new Page<>();
        // TODO index 0 ++
        index++;
        page.setPageNo(index);
        if (getTerminalType() == Constants.TERMINAL_ANDROID
                && getContext().getVersion() >= VersionConstants.Android.VERSION_37.getVersion()
                || getTerminalType() == Constants.TERMINAL_IOS
                && getContext().getVersion() >= VersionConstants.IOS.VERSION_8.getVersion()) {
            // 最新最热排序
            if(null == sortType) {
                sortType = 0;
            }
            mixDao.findMixByScenePageAndSort(lan,type, scene, page, ids, sortType);

        } else {
            mixDao.findMixByScenePage(lan,type, scene, page);
        }

        for (Mix mix : page.getResult()) {
            if (null != mix.getUrl()) {
                mix.setUrl(FileCenterClient.buildUrl(mix.getUrl()));
            }
            if (null != mix.getAlbumUrl()) {
                mix.setAlbumUrl(FileCenterClient.buildUrl(mix.getAlbumUrl()));
            }
            if (null != mix.getAlbumUrl_2()) {
                mix.setAlbumUrl_2(FileCenterClient.buildUrl(mix.getAlbumUrl_2()));
            }
        }
        buildMixAuthor(page.getResult());

        adjustMix(page.getResult());

        return page;
    }


    /**
     * 分享 Count +1
     *
     * @param mid 歌曲编号
     */
    public void shareCountInc(int mid) {
        mixDao.updateById(mid, new Update().inc("shareCount", 1));
    }

    /**
     * 歌曲分享详细
     *
     * @param mid 歌曲编号
    * @return 歌曲信息
     */
    public Mix shareInfo(String lan,int mid) {
        Mix mix = mixDao.findMixById(lan,mid);
        adjustMix(mix);
        buildMixFileUrl(mix);
//        mix.setUrl(URLEncoder.encode(mix.getUrl()));
        return mix;
    }

    /**
     * 获取今日新歌，不足十首以下载量前10的补至10首
     * @return  歌曲信息List
     */
    public List<Mix> mixRankingShelvesTime(String lan) {
        long shelvesTime = DateUtil.getDayBegin(new Date()).getTime();
        List<Mix> mix = mixDao.findMixByShelvesTime(lan,shelvesTime,"id","name", "downloadCount","shelvesTime","url","albumUrl","albumUrl_2");

        adjustMix(mix);

        if(mix.size() < 10){
            int size = 10-mix.size();
            List<Mix> mix_d = mixDao.findMixRankingByDownloadCount(lan,size,"id","name", "downloadCount","shelvesTime","url","albumUrl","albumUrl_2");
            mix.addAll(mix_d);
        }
        buildMixFileUrl(mix);
        return mix;
    }

    /**
     * 获取场景前四个数据
     * @param scene 场景
     * @param type 类型
     * @param limit 个数
     * @return
     */
    public List<Mix> findMixListBySceneFour(String lan,Integer scene, Integer type, Integer limit) {
        List<Mix> list = Collections.EMPTY_LIST;
        if (getTerminalType() == Constants.TERMINAL_ANDROID
                && getContext().getVersion() >= VersionConstants.Android.VERSION_37.getVersion()
                || getTerminalType() == Constants.TERMINAL_IOS
                && getContext().getVersion() >= VersionConstants.IOS.VERSION_8.getVersion()) {
            list = mixDao.findMixListBySceneAndType(lan,scene, type, limit);
            buildMixFileUrl(list);
        }

        adjustMix(list);
        return list;
    }

    /**
     * 获取当日推荐歌曲
     */
    public Mix recommendMix(String lan) {
        //获取推荐歌曲
        List<Mix> mixs = mixDao.findMixByRecommendDate(lan,System.currentTimeMillis());
        Mix mix = null;
        Random r = new Random();
        if (!CollectionUtils.isEmpty(mixs)){
            mix = mixs.get(r.nextInt(mixs.size()));
            buildMixFileUrl(mix);
            return mix;
        }

        //获取今日新歌随机一首进行推荐
        mixs = mixDao.findMixByShelvesTime(lan,System.currentTimeMillis());
        if (!CollectionUtils.isEmpty(mixs)){
            mix = mixs.get(r.nextInt(mixs.size()));
            buildMixFileUrl(mix);
            return mix;
        }

        //获取下载量前50名随机一首推荐
        mixs = mixDao.findMixRankingByDownloadCount(lan,50);
        mix = mixs.get(r.nextInt(mixs.size()));
        buildMixFileUrl(mix);
        adjustMix(mix);
        return mix;
    }
}
