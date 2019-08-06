package com.business.core.constants;

/**
 * Created by Administrator on 2015/4/29.
 * 字典 不变的 key
 */
public class DicConstants {

    /// TODO 移出字典 改为 Element 模式
    /// Mix genreParent 分类 ，记录 genre 分类

    /**
     * 流行
     */
    public static final Integer[] MIX_GENRE_PARENT_POP_CHILDREN = {7, 11, 13, 17, 18, 19, 20, 21, 22, 39, 40, 41};
    /**
     * 摇滚
     */
    public static final Integer[] MIX_GENRE_PARENT_ROCK_CHILDREN = { 8, 9, 10, 14, 15, 23, 24, 25, 26, 27, 28, 42, 43};
    /**
     * 电子
     */
    public static final Integer[] MIX_GENRE_PARENT_ELECTRONIC_CHILDREN = { 1, 2, 3, 4, 5, 12, 16, 29, 30, 33, 34, 35, 36, 37, 38};
    /**
     * 嘻哈
     */
    public static final Integer[] MIX_GENRE_PARENT_HIP_HOP_CHILDREN = { 6, 31 };
    /**
     * 爵士
     */
    public static final Integer[] MIX_GENRE_PARENT_JAZZ_CHILDREN = {44, 45, 46};
    /**
     * 古典
     */
    public static final Integer[] MIX_GENRE_PARENT_CLASSICAL_CHILDREN = {47};
    /**
     * 世界音乐
     */
    public static final Integer[] MIX_GENRE_PARENT_WORLD_MUSIC_CHILDREN = {32};

    ///
    /// Dictionary, type

    /**
     * 性别
     */
    public static final Integer DIC_TYPE_GENDER = 1;
    /**
     * mix 歌曲场景
     */
    public static final Integer DIC_TYPE_MIX_SCENE = 2;
    /**
     * mix 歌曲曲风 （style）{@link com.business.core.entity.mix.Mix}
     */
    public static final Integer DIC_TYPE_MIX_PARENT_GENRE = 3;
    /**
     * 用户类型，国外 国内（ib/in，kg/cm） （style）{@link com.business.core.entity.user.User#type}
     */
    public static final Integer DIC_TYPE_USER_TYPE = 4;
    /**
     * mix 歌曲， genreParent 类型分类 {@link com.business.core.entity.mix.Mix}  {3 和 7 是节点关系}
     */
    public static final Integer DIC_TYPE_MIX_GENRE = 7;
    /**
     * channel app渠道号
     */
    public static final Integer DIC_TYPE_CHANNEL = 8;
    /**
     * 电台场景
     */
    public static final Integer DIC_TYPE_RADIO_SCENE = 9;
    /**
     * 第三方赛事渠道
     */
    public static final Integer DIC_TYPE_JOIN_ACTIVITY = 10;

    /**
     * 地区赛道banner
     */
    public static final Integer DIC_TYPE_BANNER_TARGET = 11;

    ///
    /// Dictionary, value

    /**
     * 男
     */
    public static final Integer DIC_VALUE_GENDER_MALE = 1;
    /**
     * 女
     */
    public static final Integer DIC_VALUE_GENDER_WOMEN = 2;

    /**
     * appStore 渠道
     */
    public static final String DIC_VALUE_CHANNEL_APP_STORE = "appStore";

}
