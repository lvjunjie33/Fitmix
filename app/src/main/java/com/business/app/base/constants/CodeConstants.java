package com.business.app.base.constants;

/**
 * Created by sin on 2015/4/17.
 */
public class CodeConstants {

    ///
    /// 请求成功 code
    public static Integer SUCCESS = 0;
    /**
     * 认证失败
     */
    public static Integer INVALID_K = 1;
    /**
     * 解密失败
     */
    public static Integer DO_CODING_ERROR = 1;

    ///
    /// app 登录/注册/修改等  1000 ~ 2000

    /**
     * app注册/邮箱已存在
     */
    public static Integer REGISTER_APP_USER_EMAIL_REPEAT = 1000;
    /**
     * app注册/邮箱格式不正确
     */
    public static Integer REGISTER_APP_USER_EMAIL_NO_CORRECT = 1001;
    /**
     * app注册/密码长度不能小于 6 位
     */
    public static Integer REGISTER_APP_USER_PASSWORD_LENGTH = 1002;
    /**
     * 用户修改密码/非 app 注册用户不能修改密码
     */
    public static final Integer USER_NON_APP_REGISTER_NOT_MODIFY_PASSWORD = 1003;
    /**
     * 用户手机注册/该手机已被注册
     */
    public static final Integer USER_REGISTER_MOBILE_EXIST = 1004;
    /**
     *  用户手机注册/验证码错误
     */
    public static final Integer USER_REGISTER_MOBILE_CODE_ERROR = 1005;
    /**
     * 不要频繁点击注册
     */
    public static final Integer USER_REGISTER_FREQUENT_OPERATE = 1006;
    /**
     * 验证码错误
     */
    public static final Integer USER_VERIFY_CODE_ERROR = 1007;
    /**
     * 邮箱格式不对
     */
    public static final Integer USER_EMAIL_ERROR = 1009;
    /**
     * 密码长度异常
     */
    public static final Integer USER_PASSWORD_ERROR = 1010;
    /**
     * 手机号码注册，注册信息为空
     */
    public static final Integer USER_MOBILE_REGISTER_IS_EMPTY = 1012;
    /**
     * 邮箱注册，注册信息为空
     */
    public static final Integer USER_EMAIL_REGISTER_IS_EMPTY = 1013;
    /**
     * 手机号码修改密码 信息为空
     */
    public static final Integer USER_MOBILE_MODIFY_INFO_IS_EMPTY = 1014;
    /**
     * 邮箱修改密码 信息为空
     */
    public static final Integer USER_EMAIL_MODIFY_INFO_IS_EMPTY = 1015;

    ///
    /// app 登录 2000 ~ 3000

    /**
     * 用户登录/用户不存在
     */
    public static Integer  LOGIN_USER_NOT_EXIST = 2000;
    /**
     * 用户登录/用户密码错误（）
     */
    public static Integer LOGIN_USER_PASSWORD_ERROR = 2001;
    /**
     * 用户登录/账号不可用,请联系客服
     */
    public static Integer LOGIN_USER_STATE_NO_ACTIVATES = 2002;
    /**
     * 用户登录/用户没有登陆
     */
    public static Integer LOGIN_USER_STATE_NO_LOGIN = 2003;

    /**
     *  用户登录/用户登录失效
     */
    public static Integer LOGIN_USER_STATE_EXPIRATION = 2004;

    /**
     *  用户绑定/该微信已经是ifitmix用户
     */
    public static Integer WECHAT_IS_BINDED = 2005;
    /**
     * 万德游客登录异常访问
     */
    public static Integer LOGIN_USER_ERROR_WD_TOURIST = 2006;
    /**
     *  非法使用游客登录通道，正式用户不能再走游客登录通道
     */
    public static Integer LOGIN_USER_ERROR_WD_UN_TOURIST = 2007;
    /**
     * app版本过低，请需要到最新版本
     */
    public static Integer LOGIN_APP_TO_UPGRADE = 2008;

    ///
    /// User 用户操作，用户信息 3000 ~ 4000

    /**
     * 用户收藏/mix不存在
     */
    public static final Integer USER_MIX_COLLECTION_MIX_EXIST = 3001;
    /**
     * 用户收藏/用户不存在
     */
    public static final Integer USER_MIX_COLLECTION_USER_EXIST = 3002;
    /**
     * 用户资料修改/用户年龄不能小于 0 岁
     */
    public static final Integer USER_INFO_MODIFY_AGE_ERROR = 3003;
    /**
     * 用户资料修改/用户性别不正确
     */
    public static final Integer USER_INFO_MODIFY_GENDER_ERROR = 3004;
    /**
     * 用户资料修改/用户类型不正确
     */
    public static final Integer USER_INFO_MODIFY_TYPE_ERROR = 3005;
    /**
     * 用户自定义歌单/歌单不存在2001
     */
    public static final Integer USER_CUSTOM_MUSIC_GROUP_NOT_EXIST = 3006;
    /**
     * 用户绑定/已是 fitmix 用户不能绑定
     */
    public static final Integer USER_BINDING_IS_FITMIX_USER = 3007;
    /**
     * 用户解绑/不能解绑，当前登录用户
     */
    public static final Integer USER_UNBINDING_LOGIN_NOT_OPERATION = 3008;
    /**
     *  用户资料修改/用户上传头像失败
     */
    public static final Integer USER_INFO_MODIFY_FILE_UPLOAD_FAILED = 3009;

    /**
     *  用户训练计划/计划已失效
     */
    public static final Integer RUN_PLAN_STATE_EXPIRATION = 3010;

    /**
     *  用户训练计划/已存在执行中的计划
     */
    public static final Integer RUN_PLAN_ALREADY_EXISTS_IN_THE_EXECUTION = 3011;

    /**
     *  用户训练计划/用户没有训练计划
     */
    public static final Integer RUN_PLAN_USER_DO_NOT_OWN_RUN_PLAN = 3012;

    /**
     *  用户训练计划/用户没已完成的训练计划
     */
    public static final Integer RUN_PLAN_USER_DO_NOT_OWN_PAST_RUN_PLAN = 3013;

    /**
     *  用户训练计划/计划开始时间无效
     */
    public static final Integer RUN_PLAN_PLAN_TIME_OF_NO_AVAIL = 3014;


    /**
     *  计划延期过多
     */
    public static final Integer RUN_PLAN_DELAY_TOO_MORE = 3015;

    /**
     *  用户训练计划/计划暂未开始
     */
    public static final Integer RUN_PLAN_NO_PLAN_TO_START = 3016;
    /**
     * 用户训练计划/计划结束
     */
    public static final Integer RUN_PLAN_END_PLAN = 3017;


    /**
     * 用户设备信息上传失败,KEY与CHIPID不一致
     */
    public static final Integer UPLOAD_SMART_DEVICE_KEY_ERROR = 3018;
    /**
     * 用户设备信息上传失败
     */
    public static final Integer UPLOAD_SMART_DEVICE_ERROR = 3019;
    /**
     * 设备已经存在
     */
    public static final Integer UPLOAD_SMART_DEVICE_REPEAT = 3020;
    /**
     * 用户已经绑定了
     */
    public static final Integer UPLOAD_SMART_DEVICE_EXIST = 3021;
    /**
     * 未绑定设备
     */
    public static final Integer UPLOAD_SMART_DEVICE_UNBINDING = 3022;
    /**
     * 未知设备
     */
    public static final Integer UPLOAD_SMART_DEVICE_UNKNOWN_DEVICE = 3023;

    ///
    ///  UserRun 用户跑步 4001 ~ 4100

    /**
     * 用户跑步/地图信息出错
     */
    public static final Integer USER_RUN_ADD_DETAIL_ERROR = 4001;
    /**
     * 用户运动/活动用户版本不存在（或不是活动版本）
     */
    public static final Integer ACTIVITY_USER_RUN_VERSION_NOT_EXIST = 4002;
    /**
     * 用户跑步/运动记步文件没有上传
     */
    public static final Integer USER_RUN_STEP_DETAIL_NOT_UPLOAD = 4003;
    /**
     * 用户跳跃个数详细/跳频详细文件没有上传
     */
    public static final Integer USER_SKIP_ROPE_DETAIL_NOT_UPLOAD = 4004;

    ///
    /// UserRun Watch 用户手表运动 4101 ~ 4200
    /**
     * 上传的手表运动数据为空
     */
    public static final Integer USER_WATCH_RUN_ADD_DETAIL_ERROR = 4101;

    ///
    /// 分享 4501 ~ 5000

    /**
     * 运动分享图片不能为空
     */
    public static final Integer USER_RUN_SHARE_IMAGE_NOT_NULL = 4501;
    /**
     * 运动分享 new/分享的数据不存在
     */
    public static final Integer USER_RUN_SHARE_NEW_RUN_DATA_NULL = 4502;
    /**
     * 跳绳分享 分享的数据不存在
     */
    public static final Integer USER_SKIP_ROPE_SHARE_DATA_NULL = 4503;

    ///
    /// 俱乐部 5001 ~ 5500

    /**
     * 俱乐部/名称重复
     */
    public static final Integer CLUB_NAME_REPEAT = 5001;
    /**
     * 俱乐部/俱乐部不存在
     */
    public static final Integer CLUB_NOT_EXIST = 5002;
    /**
     * 俱乐部/已是俱乐部成员
     */
    public static final Integer CLUB_IS_CLUB_MEMBER = 5003;
    /**
     * 俱乐部/之前加入过俱乐部，恢复数据
     */
    public static final Integer CLUB_RECOVERY_MEMBER = 5004;
    /**
     * 俱乐部/成员已被移除
     */
    public static final Integer CLUB_MEMBER_IS_REMOVE = 5005;
    /**
     * 权限不足/您的权限不足
     */
    public static final Integer CLUB_INSUFFICIENT_AUTHORITY = 5006;
    /**
     * 俱乐部/您是俱乐部创建者不能退出
     */
    public static final Integer CLUB_CREATE_USER_NOT_QUIT = 5007;
    /**
     * 俱乐部/加入失败、俱乐部不存在
     */
    public static final Integer CLUB_JOIN_ERROR_NOT_EXIST = 5008;
    /**
     * 俱乐部/加入失败、俱乐部已删除
     */
    public static final Integer CLUB_JOIN_ERROR_THE_REMOVE = 5009;
    /**
     * 俱乐部/你已不是俱乐部成员
     */
    public static final Integer CLUB_ARE_NOT_MEMBER = 5010;
    /**
     * 俱乐部/俱乐部成员达到上限
     */
    public static final Integer CLUB_JOIN_MEMBER_LIMIT = 5011;

    ///
    /// 活动 6001 ~ 7000

    /**
     * 活动报名成功
     */
    public static final Integer ACTIVITY_SIGN_UP_SUCCESS = 6001;
    /**
     * 报名失败(该手机号码已经报名了)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_REPEAT_MOBILE = 6002;
    /**
     * 报名失败(身份证已经参与报名了)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_REPEAT_ID_CARD = 6003;
    /**
     * 报名失败(参赛成员性别不符合赛事要求)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_REPEAT_SEX = 6004;
    /**
     * 报名失败(总参数组已满)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_REPEAT_FULL = 6005;
    /**
     * 报名失败(重复填写了身份证信息)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_DOUBLE_ID_CARD = 6006;
    /**
     * 报名失败(重复填写了手机号码)
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR_DOUBLE_MOBILES = 6007;
    /**
     * 报名失败，赛事信息不全
     */
    public static final Integer ACTIVITY_SIGN_UP_ERROR = 6008;

    ///
    /// 活动 7001 ~ 8000

    /**
     * 创建环信群组失败
     */
    public static final Integer IM_INFO_GROUP_CREATE_ERROR = 7001;
    /**
     * 修改环信群组信息失败
     */
    public static final Integer IM_INFO_GROUP_MODIFY_INFO_ERROR = 7002;
    /**
     * 申请加入群聊，密码错误
     */
    public static final Integer IM_INFO_GROUP_ADD_SINGLE_USER_PASSWORD_ERROR = 7003;
    /**
     * 申请加入群聊，用户信息异常
     */
    public static final Integer IM_INFO_GROUP_ADD_SINGLE_USER_INFO_ERROR = 7004;
    /**
     * 群组不存在
     */
    public static final Integer IM_INFO_GROUP_ADD_SINGLE_UN_GROUP = 7005;
    /**
     * 群组编号为空
     */
    public static final Integer IM_INFO_GROUP_ID_IS_NULL_ERROR = 7006;
    /**
     * 用户帐号为空
     */
    public static final Integer IM_INFO_USER_NAME_IS_NULL_ERROR = 7007;

    ///
    /// 金币任务商城 8001~ 9000

    // 余额不足
    public static final Integer BALANCE_NOT_ENOUGH = 8001;

    // 库存不足
    public static final Integer STOCKS_NOT_ENOUGH = 8002;

    // 无效产品
    public static final Integer INVALID_GOOD = 8003;

    // 请求流米订单接口出错
    public static final Integer REQUEST_BAD_LIUMI_PLACE_ORDER = 8004;
    //获取流米token 失败
    public static final Integer REQUEST_BAD_LIUMI_GET_TOKEN = 8005;

    // 无效手机号
    public static final Integer MOBILE_INVALID = 8006;

    // 无效任务
    public static final Integer INVALID_TASK = 9001;
    // 任务已完成
    public static final Integer HED_FINISH = 9002;
    // 无效任务类型
    public static final Integer INVALID_TASK_TYPE = 9003;
    // 未达到完成任务条件
    public static final Integer NOT_MEET_CONDITIONS = 9004;

    ///
    /// 社交-话题 10001 ~11000
    /**
     * 话题不存在
     */
    public static final Integer THEME_IS_NULL_ERROR = 10001;
    /**
     * 上传的图片不存在
     */
    public static final Integer IMG_UP_LOAD_IS_NULL = 10002;
    /**
     * 问题异常的回答
     */
    public static final Integer THEME_ERROR_ANSWER = 10003;
    /**
     * 非法调用接口
     */
    public static final Integer THEME_OPERATION_ERROR = 10004;
    /**
     * 报错了
     */
    public static final Integer THEME_CONTENT_ERROR = 10005;
    /**
     * 空的内容
     */
    public static final Integer THEME_CONTENT_NULL = 10006;
    /**
     * 消息发送失败
     */
    public static final Integer USER_PRIVATE_MSG_ERROR = 10007;
    /**
     * 消息目标用户将消息发送方拉黑，不能发送消息
     */
    public static final Integer USER_PRIVATE_MSG_TARGET_REJECT_TRUE = 10008;
    /**
     * 消息发送过于频繁
     */
    public static final Integer USER_PRIVATE_MSG_FREQUENT = 10009;
    /**
     * 消息发送方将对方拉黑，不能发送消息
     */
    public static final Integer USER_PRIVATE_MSG_FROM_REJECT_TRUE = 10010;


    ///
    /// 微信  75000 ~ 80000

    /**
     * 微信/用户未关注公众号
     */
    public static final Integer WECHAT_NOT_FOLLOW = 78000;
    /**
     * 微信/获取 token 失败
     */
    public static final Integer WECHAT_TOKEN_GET_ERROR = 78001;
    /**
     * 微信设置步数，没有用户信息、未知的微信账号(未进行微信登录)、微信步数设置失败(微信帐号信息过期，需要重新登录)、微信步数设置失败(微信帐号信息过期，需要重新登录)
     */
    public static final Integer WX_SET_STEP_ERROR_USER_INFO = 78002;
    /**
     * 微信设置步数，异常的步数设置，步数为0、没有步数、步数超过8万
     */
    public static final Integer WX_SET_STEP_ERROR_STEP = 78003;
    /**
     * 微信步数设置频繁，请在xxx秒之后再试
     */
    public static final Integer WX_SET_STEP_FREQUENT_ACCESS = 78004;
    /**
     * 微信步数接口非法访问
     */
    public static final Integer WX_SET_STEP_FAIL_USER = 78005;
}
