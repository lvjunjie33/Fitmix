package com.business.work.mix;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixDetail;
import com.business.core.entity.mix.RunMix;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.mix.MixCoreDao;
import com.business.core.operations.mixAuthor.MixAuthorCoreDao;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
* Created by sin on 15/4/13.
*/
@Service
public class MixService {

    @Autowired
    private MixDao mixDao;
    @Autowired
    private MixCoreDao mixCoreDao;
    @Autowired
    private MixAuthorCoreDao mixAuthorCoreDao;
    @Autowired
    private DefaultDao defaultDao;

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
    }

    /**
     * Mix 分页
     * @param page 分页
     */
    public void list(Page<Mix> page) {
        mixDao.findMixPage(page);
    }

    /**
     * Mix 添加
     * @param mix 对象
    *   0、成功 1、标识重复
     */
    public Object[] mixAdd(Mix mix) throws IOException {
        if (!StringUtils.isEmpty(mix.getCustomIdentification()) && mixCoreDao.findMixByCustomIdentification(mix.getCustomIdentification(), "id") != null) {
            return new Object[]{1};
        }
        // image 压缩并缩小分辨率
//        if (null != image) {
//            byte[] imageByte = ImageUtil.compress(ImageUtil.resize(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION[0], FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION[1]));
//            String imageUrl = FileCenterClient.upload(imageByte, FileConstants.FILE_TYPE_AUDIO_MIX_IMAGE, image.getOriginalFilename()); // 上传 album
//            String imageUrl_2 = FileCenterClient.upload(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_IMAGE, image.getOriginalFilename()); // 上传 album
//            mix.setAlbumUrl(imageUrl);
//            mix.setAlbumUrl_2(imageUrl_2);
//        }

        /// 改为 js 调用 modify 借口
//        String fileUrl = AliyunCenterClient.multipartFile(FileConstants.FILE_TYPE_AUDIO_MIX.toString(), file, name, size, partSize, part);
//        byte[] imageByte_2 = ImageUtil.compress(ImageUtil.resize(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION2[0], FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION2[1]));
//        String fileUrl = FileCenterClient.upload(file, FileConstants.FILE_TYPE_AUDIO_MIX); // 上传 mix

//        mix.setName(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")));
        mix.setName("未设置文件");
        mix.setAddTime(System.currentTimeMillis());
        mix.setState(Mix.STATE_1);
        mix.setUrl("");
        mix.setCollectNumber(0);
        mix.setDownloadCount(0);
        mix.setShareCount(0);
        mix.setSort(0);
        if (mix.getBpm().indexOf("-") != -1) {
            mix.setBpmType(Mix.BMP_TYPE_VARIABLE);
            String[] bpm = mix.getBpm().split("-");
            mix.setBpmVariableBegin(Integer.valueOf(bpm[0]));
            mix.setBpmVariableEnd(Integer.valueOf(bpm[1]));
        }
        else {
            mix.setBpmType(Mix.BMP_TYPE_FIXED);
            mix.setBpmVariableBegin(Integer.valueOf(mix.getBpm()));
            mix.setBpmVariableEnd(Integer.valueOf(mix.getBpm()));
        }
        mixDao.insertMix(mix);
        //2018-08-30新增歌曲明细
        MixDetail mixDetail=new MixDetail();
        mixDetail.setMixid(mix.getId());
        mixDetail.setIntroduce(mix.getIntroduce());
        mixDetail.setName(mix.getName());
        mixDetail.setName_lan(mix.getMixDetails().get(0).getIntroduce_lan());
        mixDetail.setIntroduce_lan(mix.getMixDetails().get(0).getIntroduce_lan());
        mixDetail.setAddTime(System.currentTimeMillis());
        mixDao.insertMixDetail(mixDetail);
        return new Object[]{0, mix};
    }

    /**
     * bpmStr 构建 bpm[]
     * @param bpmStr 字符 格式: 22~33 or 22
     * @return bpm数组
     */
    public Integer[] buildMixBpm (String bpmStr) {
        String[] strings = bpmStr.split("~");
        if (strings.length > 1) {
            int begin = Integer.valueOf(strings[0]);
            int end = Integer.valueOf(strings[1]);
            Integer[] bpm = new Integer[end - begin + 1];
            int bpmBool = 0;
            for (int i = begin;i <= end; i++) {
                bpm[bpmBool] = i;
                bpmBool++;
            }
            return bpm;
        }
        return new Integer[]{Integer.valueOf(strings[0])};
    }

    /**
     * 查询 mix 歌曲
     * @param id 编号
     * @return mix 歌曲信息
     */
    public Mix mixInfo(Integer id) {
        Mix mix = mixDao.findMixById(id);
        MixDetail mixDetail= mixDao.findMixDetail("zh",mix.getId());
        mix.setName("");
        mix.setIntroduce("");
        if(mixDetail!=null){
            mix.setName(mixDetail.getName());
            mix.setIntroduce(mixDetail.getIntroduce());
        }
        if (null != mix.getMaid() ) {
            mix.setMixAuthor(mixAuthorCoreDao.findMixAuthor(mix.getMaid()));
        }
        adjustMix(mix);
        return mix;
    }

    /**
     * 查询 mix 歌曲
     * @param id 编号
     * @return mix 歌曲信息
     */
    public Mix mixInfoLan(Integer id,String lan) {
        Mix mix = mixDao.findMixById(id);
        MixDetail mixDetail= mixDao.findMixDetail(lan,mix.getId());
        mix.setName("");
        mix.setIntroduce("");
        if(mixDetail!=null) {
            mix.setName(mixDetail.getName());
            mix.setIntroduce(mixDetail.getIntroduce());
        }
        if (null != mix.getMaid() ) {
            mix.setMixAuthor(mixAuthorCoreDao.findMixAuthor(mix.getMaid()));
        }
        adjustMix(mix);
        return mix;
    }

    /**
     * Mix 更新
     * @param mix 对象
     * @return 1、Identification 重复
     */
    public int mixModify(Mix mix) {
        Mix baseMix = mixCoreDao.findMixByCustomIdentification(mix.getCustomIdentification(), "id");
        if (!StringUtils.isEmpty(mix.getCustomIdentification()) && baseMix != null && !mix.getId().equals(baseMix.getId())) {
            return 1;
        }
        Update update = new Update();
        update.set("name", mix.getName()).set("bpm", mix.getBpm())
                .set("introduce", mix.getIntroduce().trim()).set("scene", mix.getScene()).set("genre", mix.getGenre()).set("trackLength", mix.getTrackLength())
                .set("maid", mix.getMaid()).set("mixMusics", mix.getMixMusics()).set("customIdentification", mix.getCustomIdentification());

        /// 处理 bpm 类型
        if (mix.getBpm().indexOf("-") != -1) {
            String[] bpm = mix.getBpm().split("-");
            update.set("bpmType", Mix.BMP_TYPE_VARIABLE)
                    .set("bpmVariableBegin", Integer.valueOf(bpm[0]))
                    .set("bpmVariableEnd", Integer.valueOf(bpm[1]));
        }
        else {
            update.set("bpmType", Mix.BMP_TYPE_FIXED)
                    .set("bpmVariableBegin", Integer.valueOf(mix.getBpm()))
                    .set("bpmVariableEnd", Integer.valueOf(mix.getBpm()));
        }
        mixDao.updateMixById(mix.getId(), update);
        //2018-08-30 更新mix
        MixDetail mixDetails= mixDao.findMixDetail(mix.getMixDetails().get(0).getIntroduce_lan(),mix.getId());
        if(mixDetails==null){
            MixDetail mixDetail=new MixDetail();
            mixDetail.setMixid(mix.getId());
            mixDetail.setIntroduce(mix.getIntroduce());
            mixDetail.setName(mix.getName());
            mixDetail.setName_lan(mix.getMixDetails().get(0).getIntroduce_lan());
            mixDetail.setIntroduce_lan(mix.getMixDetails().get(0).getIntroduce_lan());
            mixDetail.setAddTime(System.currentTimeMillis());
            mixDao.insertMixDetail(mixDetail);
        }else{
            Update updateMixDetail = new Update();
            updateMixDetail.set("name",mix.getName())
                    .set("introduce",mix.getIntroduce())
                    .set("name_lan",mixDetails.getName_lan())
                    .set("introduce_lan",mixDetails.getIntroduce_lan());
            mixDao.updateMixDetail(mixDetails.getId(),updateMixDetail);
        }
        return 0;
    }

    /**
     * 修改歌曲上下架状态
     * @param id 歌曲编号
     * @param state 歌曲状态
     * @return 0、成功  1、没有歌曲文件
     */
    public int mixModifyState (Integer id, Integer state) {
        Mix mix = mixCoreDao.findMixById(id, "id", "url");
        if (StringUtils.isEmpty(mix.getUrl())) {
            return 1;
        }
        mixDao.updateMixById(id, Update.update("state", state).set("shelvesTime", System.currentTimeMillis()));
        return 0;
    }

    /**
     * 修改歌曲排序顺序
     * @param id 歌曲编号
     * @param sort 排序
     */
    public void mixSort (Integer id, Integer sort) {
        mixDao.updateMixById(id, Update.update("sort", sort));
    }

    /**
     * 修改 mix 歌曲图片信息
     * @param mid 歌曲编号
     * @param albumUrl mix 歌曲小图标
     * @param albumUrl_2 mix 歌曲大图标
     */
    public void mixImageModify (Integer mid, String albumUrl, String albumUrl_2) {
        Mix mix = mixDao.findMixById(mid, "id", "url", "albumUrl", "albumUrl_2");
        if (null != mix.getAlbumUrl()) {
            FileCenterClient.removeFile(mix.getAlbumUrl());
        }
        if (null != mix.getAlbumUrl_2()) {
            FileCenterClient.removeFile(mix.getAlbumUrl_2());
        }
        mixDao.updateMixById(mid, Update.update("albumUrl", albumUrl).set("albumUrl_2", albumUrl_2));
    }

    /**
     * 修改 mix 歌曲文件信息
     * @param mid 歌曲编号
     * @param url 文件地址
     */
    public void mixFileModify (Integer mid, String url) {
        Mix mix = mixDao.findMixById(mid, "id", "url", "albumUrl", "albumUrl_2");
        if (null != mix.getUrl()) {
            FileCenterClient.removeFile(mix.getUrl());
        }
        mixDao.updateMixById(mid, Update.update("url", url));
    }

    /**
     * mix 歌曲信息删除
     * @param mid 文件编号
     */
    public void mixRemove (Integer mid) {
        Mix mix = mixDao.findMixById(mid, "id", "url", "albumUrl", "albumUrl_2");
        if (mix != null) {
            if (null != mix.getAlbumUrl()) {
                FileCenterClient.removeFile(mix.getAlbumUrl());
            }
            if (null != mix.getAlbumUrl_2()) {
                FileCenterClient.removeFile(mix.getAlbumUrl_2());
            }
            if (null != mix.getUrl()) {
                FileCenterClient.removeFile(mix.getUrl());
            }
        }
        mixDao.removeMixByIds(mid);
    }

    /**
     * 清楚 mix 歌曲排序
     */
    public void clearSort () {
        mixDao.updateAll(new Update().unset("sort"));
    }

    public List<Mix> findByMixAlbumsId(Integer maid) {
        return mixDao.findByMixAlbumsId(maid);
    }

    public void mixModifyCheckStatus(Integer id, Integer checkStatus) {
        mixDao.updateMixById(id, Update.update("checkStatus", checkStatus));
    }

    /**
     * Mix 推荐管理
     */
    public List<Mix> recommendManager() {
        return mixDao.findByRecommend(System.currentTimeMillis());
    }

    /**
     * 添加推荐
     *
     * @param mid mix编号
     * @param bTime 开始时间
     * @param eTime 结束时间
     */
    public void addRecommend(Integer mid, String bTime, String eTime) {
        Long beginTime = DateUtil.parse(bTime, "yyyy-MM-dd HH:mm:ss").getTime();
        Long endTime = DateUtil.parse(eTime, "yyyy-MM-dd HH:mm:ss").getTime();

        mixDao.updateMixById(mid, Update.update("recommendBeginDate", beginTime).set("recommendEndDate", endTime));
    }

    /**
     * 设置偏移量
     *
     * @param id 编号
     * @param type 类型
     * @param offsetNum 偏移值
     */
    public void setOffset(Integer id, Integer type, Integer offsetNum) {
        String key = null;
        switch (type){
            case 1:
                key = "dOffsetNum";
                break;
            case 2:
                key = "sOffsetNum";
                break;
            case 3:
                key = "aOffsetNum";
                break;
            case 4:
                key = "bOffsetNum";
                break;
            case 5:
                key = "cOffsetNum";
                break;
        }
        if (key != null) {
            mixDao.updateMixById(id, Update.update(key, offsetNum));
        }
    }

    /**
     * 运动音乐管理
     *
     * @param page 分页对象
     */
    public void runMixPage(Page<RunMix> page) {
        mixDao.runMixPage(page);
    }

    /**
     * 新增运动音乐
     *
     * @param runMix 运动音乐
     */
    public void runMixAdd(RunMix runMix) {
        mixDao.runMixAdd(runMix);
    }

    public RunMix runMixFind(Long id) {
        return mixDao.runMixFindById(id);
    }

    /**
     * 修改
     *
     * @param id 编号
     */
    public void runMixModify(Long id, Update update) {
        mixDao.runMixModify(id, update);
    }

    public void remove() {
        mixDao.remove();
    }

    /**
     *
     * @param bpm
     * @param duration
     * @param emotion
     * @return
     */
    public List<RunMix> getSongList(Integer bpm, Long duration, Integer emotion) {
        Integer tagBpm = RunMix.BPM_MAP.get(bpm);
        Integer bBpm = tagBpm - 2;
        Integer eBpm = tagBpm + 2;

        Integer bBpm2 = tagBpm / 2;
        Integer eBpm2 = eBpm / 2;

        Long currentD = 0L;
        List<RunMix> link = new LinkedList<>();

        RunMix first;
        {//第一首
            Criteria criteria = new Criteria();
            Criteria c1 = Criteria.where("energyLevel").in(1, 10).and("bpm").gte(bBpm).lte(eBpm).and("emotion").is(emotion);
            Criteria c2 = Criteria.where("energyLevel").in(1, 10).and("bpm").gte(bBpm).lte(eBpm);
            Criteria c3 = Criteria.where("energyLevel").in(1, 10).and("emotion").is(emotion);

            Criteria c4 = Criteria.where("energyLevel").in(1, 10).and("bpm").gte(bBpm2).lte(eBpm2).and("emotion").is(emotion);
            Criteria c5 = Criteria.where("energyLevel").in(1, 10).and("bpm").gte(bBpm2).lte(eBpm2);

            criteria.orOperator(c1, c2, c3, c4, c5);

            Query query = Query.query(criteria);
            query.with(new Sort(Sort.Direction.ASC, "randomVal"));
            first = defaultDao.findOne(query, RunMix.class);

            if (first == null) {
                return Collections.emptyList();
            }
            currentD += first.getDuration();
            link.add(first);
        }

        RunMix second;
        {// 第二首
            Criteria criteria = new Criteria();

            Criteria c1 = Criteria.where("bpm").gte(bBpm).lte(eBpm).and("energyLevel").in(2).and("emotion").is(emotion);
            Criteria c2 = Criteria.where("bpm").gte(bBpm).lte(eBpm).and("emotion").is(emotion);
            Criteria c3 = Criteria.where("bpm").gte(bBpm).lte(eBpm).and("energyLevel").in(2);

            Criteria c4 = Criteria.where("bpm").gte(bBpm2).lte(eBpm2).and("energyLevel").in(2).and("emotion").is(emotion);
            Criteria c5 = Criteria.where("bpm").gte(bBpm2).lte(eBpm2).and("emotion").is(emotion);
            Criteria c6 = Criteria.where("bpm").gte(bBpm2).lte(eBpm2).and("energyLevel").in(2);

            criteria.orOperator(c1, c2, c3, c4, c5, c6);

            Query query = Query.query(criteria);
            query.with(new Sort(Sort.Direction.ASC, "randomVal"));
            second = defaultDao.findOne(query, RunMix.class);

            if (second == null) {
                return Collections.emptyList();
            }
            currentD += second.getDuration();
            link.add(second);
        }

        RunMix lastOne;
        {//最后一首
            Criteria criteria = new Criteria();
            Criteria c1 = Criteria.where("energyLevel").in(11, 1).and("duration").lte(duration - currentD).and("bpm").gte(bBpm).lte(eBpm).and("emotion").is(emotion);
            Criteria c2 = Criteria.where("energyLevel").in(11, 1).and("duration").lte(duration - currentD).and("bpm").gte(bBpm).lte(eBpm);
            Criteria c3 = Criteria.where("energyLevel").in(11, 1).and("duration").lte(duration - currentD).and("emotion").is(emotion);

            Criteria c4 = Criteria.where("energyLevel").in(11, 1).and("duration").lte(duration - currentD).and("bpm").gte(bBpm2).lte(eBpm2).and("emotion").is(emotion);
            Criteria c5 = Criteria.where("energyLevel").in(11, 1).and("duration").lte(duration - currentD).and("bpm").gte(bBpm2).lte(eBpm2);

            criteria.orOperator(c1, c2, c3, c4, c5);
            Query query = Query.query(criteria);

            query.with(new Sort(Sort.Direction.ASC, "randomVal"));
            lastOne = defaultDao.findOne(query, RunMix.class);
            if (lastOne == null) {
                return Collections.emptyList();
            }
            currentD += lastOne.getDuration();
        }

        //满足时长则结束 !!!
        if ((second.getDuration() + first.getDuration() + lastOne.getDuration()) > duration) {
            link.add(lastOne);
            return link;
        }

        RunMix preMix = null;
        for (Integer i = 1; i < 100; i++) {
            Criteria criteria = new Criteria();
            if (preMix == null) {
                Criteria c1 = Criteria.where("bpm").gte(bBpm).lte(eBpm).and("emotion").is(emotion);
                Criteria c2 = Criteria.where("bpm").gte(bBpm).lte(eBpm);
                Criteria c3 = Criteria.where("emotion").is(emotion);

                Criteria c4 = Criteria.where("bpm").gte(bBpm2).lte(eBpm2).and("emotion").is(emotion);
                Criteria c5 = Criteria.where("bpm").gte(bBpm2).lte(eBpm2);

                criteria.orOperator(c1, c2, c3, c4, c5);
            } else {
                Criteria c1 = Criteria.where("energyLevel").ne(preMix.getEnergyLevel()).and("songStyle").ne(preMix.getSongStyle()).and("bpm").gte(bBpm).lte(eBpm).and("emotion").is(emotion);
                Criteria c2 = Criteria.where("energyLevel").ne(preMix.getEnergyLevel()).and("songStyle").ne(preMix.getSongStyle()).and("bpm").gte(bBpm).lte(eBpm);
                Criteria c3 = Criteria.where("energyLevel").ne(preMix.getEnergyLevel()).and("songStyle").ne(preMix.getSongStyle()).and("emotion").is(emotion);

                Criteria c4 = Criteria.where("energyLevel").ne(preMix.getEnergyLevel()).and("songStyle").ne(preMix.getSongStyle()).and("bpm").gte(bBpm2).lte(eBpm2).and("emotion").is(emotion);
                Criteria c5 = Criteria.where("energyLevel").ne(preMix.getEnergyLevel()).and("songStyle").ne(preMix.getSongStyle()).and("bpm").gte(bBpm2).lte(eBpm2);

                criteria.orOperator(c1, c2, c3);
            }
            Query query = Query.query(criteria);
            query.with(new Sort(Sort.Direction.ASC, "randomVal"));
            preMix = defaultDao.findOne(query, RunMix.class);
            if (preMix == null) {
                return Collections.emptyList();
            }
            link.add(preMix);
            if ((currentD + preMix.getDuration()) >= duration) {
                break;
            }
            currentD += preMix.getDuration();
        }
        link.add(lastOne);
        return link;
    }

    /**
     * 修改随机值
     *
     * @param list 下发的歌单
     */
    public void updateRandomVal(List<RunMix> list) {
        Set<Long> set = new HashSet<>();
        for (RunMix runMix : list) {
            set.add(runMix.getId());
        }
        mixDao.updateRandomVal(set);
    }
}
