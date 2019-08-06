package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import com.google.common.collect.ImmutableMap;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2018/1/29.
 */
@Document(collection = "RunMix")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunMix extends IncIdEntity<Long>{

    public static final ImmutableMap<String, Integer> COLOR_TAG_MAP = ImmutableMap.<String, Integer>builder()
            .put("#EF4646", 1)
            .put("#FF0066", 1)
            .put("#FF0000", 1)
            .put("#FCAC15", 1)
            .put("#FFDD21", 1)

            .put("#7DC251", 2)
            .put("#FF6023", 2)
            .put("#FFF212", 2)
            .put("#DAE034", 2)
            .put("#FFA7A7", 2)

            .put("#FBF1EA", 3)
            .put("#0FEAFE", 3)
            .put("#5FCCFF", 3)
            .put("#FEDE71", 3)
            .put("#FFF1BA", 3)
            .put("#FFA7CB", 3)
            .put("#A1A7D0", 3)

            .put("#47627F", 4)
            .put("#2F635D", 4)
            .put("#5C5140", 4)
            .put("#2871A6", 4)
            .put("#656C7E", 4)
            .put("#95284D", 4)
            .put("#7E2D2E", 4)
            .put("#392441", 4)
            .put("#896A32", 4)
            .build();

    public static final ImmutableMap<Integer, Integer> BPM_MAP = ImmutableMap.<Integer, Integer>builder()
            .put(70, 70)
            .put(71, 70)
            .put(72, 70)
            .put(73, 75)
            .put(74, 75)
            .put(75, 75)
            .put(76, 75)
            .put(77, 75)
            .put(78, 80)
            .put(79, 80)
            .put(80, 80)
            .put(81, 80)
            .put(82, 80)
            .put(83, 85)
            .put(84, 85)
            .put(85, 85)
            .put(86, 85)
            .put(87, 85)
            .put(88, 90)
            .put(89, 90)
            .put(90, 90)
            .put(91, 90)
            .put(92, 90)
            .put(93, 95)
            .put(94, 95)
            .put(95, 95)
            .put(96, 95)
            .put(97, 95)
            .put(98, 100)
            .put(99, 100)
            .put(100, 100)
            .put(101, 100)
            .put(102, 100)
            .put(103, 105)
            .put(104, 105)
            .put(105, 105)
            .put(106, 105)
            .put(107, 105)
            .put(108, 110)
            .put(109, 110)
            .put(110, 110)
            .put(111, 110)
            .put(112, 110)
            .put(113, 115)
            .put(114, 115)
            .put(115, 115)
            .put(116, 115)
            .put(117, 115)
            .put(118, 120)
            .put(119, 120)
            .put(120, 120)
            .put(121, 120)
            .put(122, 120)
            .put(123, 125)
            .put(124, 125)
            .put(125, 125)
            .put(126, 125)
            .put(127, 125)
            .put(128, 130)
            .put(129, 130)
            .put(130, 130)
            .put(131, 130)
            .put(132, 130)
            .put(133, 135)
            .put(134, 135)
            .put(135, 135)
            .put(136, 135)
            .put(137, 135)
            .put(138, 140)
            .put(139, 140)
            .put(140, 140)
            .put(141, 140)
            .put(142, 140)
            .put(143, 145)
            .put(144, 145)
            .put(145, 145)
            .put(146, 145)
            .put(147, 145)
            .put(148, 150)
            .put(149, 150)
            .put(150, 150)
            .put(151, 150)
            .put(152, 150)
            .put(153, 155)
            .put(154, 155)
            .put(155, 155)
            .put(156, 155)
            .put(157, 155)
            .put(158, 160)
            .put(159, 160)
            .put(160, 160)
            .put(161, 160)
            .put(162, 160)
            .put(163, 165)
            .put(164, 165)
            .put(165, 165)
            .put(166, 165)
            .put(167, 165)
            .put(168, 170)
            .put(169, 170)
            .put(170, 170)
            .put(171, 170)
            .put(172, 170)
            .put(173, 175)
            .put(174, 175)
            .put(175, 175)
            .put(176, 175)
            .put(177, 175)
            .put(178, 180)
            .put(179, 180)
            .put(180, 180)
            .put(181, 180)
            .put(182, 180)
            .put(183, 185)
            .put(184, 185)
            .put(185, 185)
            .put(186, 185)
            .put(187, 185)
            .put(188, 190)
            .put(189, 190)
            .put(190, 190)
            .put(191, 190)
            .put(192, 190)
            .put(193, 195)
            .put(194, 195)
            .put(195, 195)
            .put(196, 195)
            .put(197, 195)
            .put(198, 200)
            .put(199, 200)
            .put(200, 200)
            .put(201, 200)
            .put(202, 200)
            .put(203, 205)
            .put(204, 205)
            .put(205, 205)
            .put(206, 205)
            .put(207, 205)
            .put(208, 210)
            .put(209, 210)
            .put(210, 210)
            .put(211, 210)
            .put(212, 210)
            .put(213, 215)
            .put(214, 215)
            .put(215, 215)
            .put(216, 215)
            .put(217, 215)
            .put(218, 220)
            .put(219, 220)
            .put(220, 220)
            .put(221, 220)
            .put(222, 220)
            .put(223, 225)
            .put(224, 225)
            .put(225, 225)
            .put(226, 225)
            .put(227, 225)
            .put(228, 230)
            .put(229, 230)
            .put(230, 230)
            .build();

    //流行
    public static final Integer SONG_STYLE_1 = 1;
    //流行摇滚
    public static final Integer SONG_STYLE_2 = 2;
    //电子
    public static final Integer SONG_STYLE_3 = 3;
    //摇滚
    public static final Integer SONG_STYLE_4 = 4;
    //爵士
    public static final Integer SONG_STYLE_5 = 5;
    //古典
    public static final Integer SONG_STYLE_6 = 6;
    //纯音乐
    public static final Integer SONG_STYLE_7 = 7;

    //能量级别 1
    public static final Integer ENERGY_LEVEL_1 = 1;
    //能量级别 2
    public static final Integer ENERGY_LEVEL_2 = 2;
    //能量级别 3
    public static final Integer ENERGY_LEVEL_3 = 3;
    //能量级别 4
    public static final Integer ENERGY_LEVEL_4 = 4;
    //能量级别 10
    public static final Integer ENERGY_LEVEL_10 = 10;
    //能量级别 11
    public static final Integer ENERGY_LEVEL_11 = 11;


    //================================基本信息=========================================
    /**
     * 类型 1、普通音乐
     */
    private Integer type;
    /**
     * 标题、主题
     */
    private String title;
    /**
     * 描述
     */
    private String des;
    /**
     * 地址
     */
    private String link;
    /**
     * 封面图
     */
    private String imgLink;
    /**
     * bpm
     */
    private Integer bpm;
    /**
     * 双倍BPM
     * 1、可以 {@link com.business.core.constants.Constants#STATUS_YES}
     * 0、不可以
     */
    private Integer bpmIsDouble;
    /**
     * Mix 时长
     */
    private Long duration;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态 0、删除,1、正常，可用
     * {@link com.business.core.constants.Constants#STATUS_YES}
     */
    private Integer status;
    /**
     * 是否打开 (0、关闭，1打开)
     * {@link com.business.core.constants.Constants#SWITCH_OPEN}
     */
    private Integer watch;

    //=============================特色属性===================================

    /**
     * 感情色彩
     * 1、兴奋, 2、愉快, 3、平和, 4、悲伤
     */
    private Integer emotion;
    /**
     * 色彩标签
     */
    private String colorTag;
    /**
     * 能量级别(1、2、3、4、10、11)
     */
    private Integer energyLevel;
    /**
     * 曲风(1、流行，2、流行摇滚，3、电子，4、摇滚，5、爵士，6、古典，7、纯音乐)
     */
    private Integer songStyle;
    /**
     * 随机值(随机值越低，越容易出现在用户歌单中)
     */
    @Indexed
    private Long randomVal;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEmotion() {
        return emotion;
    }

    public void setEmotion(Integer emotion) {
        this.emotion = emotion;
    }

    public String getColorTag() {
        return colorTag;
    }

    public void setColorTag(String colorTag) {
        this.colorTag = colorTag;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Integer getSongStyle() {
        return songStyle;
    }

    public void setSongStyle(Integer songStyle) {
        this.songStyle = songStyle;
    }

    public Long getRandomVal() {
        return randomVal;
    }

    public void setRandomVal(Long randomVal) {
        this.randomVal = randomVal;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getWatch() {
        return watch;
    }

    public void setWatch(Integer watch) {
        this.watch = watch;
    }

    public Integer getBpmIsDouble() {
        return bpmIsDouble;
    }

    public void setBpmIsDouble(Integer bpmIsDouble) {
        this.bpmIsDouble = bpmIsDouble;
    }
}
