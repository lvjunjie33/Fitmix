package com.business.app.base.constants;

/**
 * Created by sin on 2015/5/4.
 */
public class Constants {

    ///
    /// 分页，参数

    public static final Integer PAGE_DEFAULT_INDEX = 0;

    /**
     * 注册方式 - 设备生成
     */
    public static final Integer USER_REG_TYPE_UDID = 0;


    ///
    /// mix 歌曲

    /**
     * mix歌曲 - 返回 app 的数据key，（类型）
     */
    public static final String KEY_MIX_SCENE = "scene";
    /**
     * mix歌曲 - 返回 app 的数据key，（类型）
     */
    public static final String KEY_MIX_GENRE = "genre";
    /**
     * mix歌曲 - 返回 app 的数据key，（数据集）
     */
    public static final String KEY_MIX_ARRAY = "array";

    public static final String SHOP_NEW_USER_ERROR = "新帐号拥有大额积分(金币)";
    public static final String SHOP_USER_RUN_LEVEL_ERROR = "帐号跑步等级不一致(AccountFlow =/= UserRunStatistics)";


    /**
     * 请求里k参数的对应的KEY
     */
    public static final String PARAM_DICK_VALUE_KEY = "_k";
    /**
     * 请求参数 lan 语言
     */
    public static final String PARAM_LANGUAGE = "_lan";
    /**
     * 请求参数 channel 渠道
     */
    public static final String PARAM_CHANNEL = "_ch";
    /**
     * 请求参数 version_i 版本
     */
    public static final String PARAM_VERSION = "_v";
    /**
     * 请求参数 sdk
     */
    public static final String PARAM_SDK = "_sdk";
    /**
     * 请求参数 network 网络情况
     */
    public static final String PARAM_NETWORK = "_nw";
    /**
     * 请求参数 uid 用户编号
     */
    public static final String PARAM_UID = "uid";
    /**
     * 请求app当前时间戳
     */
    public static final String PARAM_TIMESTAMP = "_tp";
    /**
     * 请求参数 udid 设备编号
     */
    public static final String PARAM_UDID = "udid";

    /**
     * 请求参数 mark 官网标记，不进行拦截请求
     */
    public static final String  GW_MARK= "gw_mark";

    public static Integer SPORTS_TYPE_RUN_OUTDOOR = 1;  //室外跑
    public static Integer SPORTS_TYPE_RUN_INDOOR = 2;  //室内跑
    public static Integer SPORTS_TYPE_WALK = 3;  //徒步
    public static Integer SPORTS_TYPE_CROSSCOUNTRY = 4;  //越野
    public static Integer SPORTS_TYPE_RIDE = 5;  //骑行
    public static Integer SPORTS_TYPE_SWIM_INDOOR = 6;  //室内游泳
    public static Integer SPORTS_TYPE_SWIM_OUTDOOR = 7; //室外游泳
    public static Integer SPORTS_TYPE_ROCK_CLIMB = 8; //攀岩
    public static Integer SPORTS_TYPE_MOUNTAINS_CLIMB = 9;  //爬山
    public static Integer SPORTS_TYPE_SKI = 10; //滑雪
    public static Integer SPORTS_TYPE_FISH = 11;  //钓鱼
    public static Integer SPORTS_TYPE_JUMP = 12;  //跳伞
    public static Integer SPORTS_TYPE_ROPE = 13;  //跳绳
    public static Integer SPORTS_TYPE_DIVE = 14;  //潜水
    public static Integer SPORTS_TYPE_ROW = 15;  //划船*/

}
