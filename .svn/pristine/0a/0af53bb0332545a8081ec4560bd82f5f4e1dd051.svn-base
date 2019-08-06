package com.business.live.socket;

/**
 * Created by Administrator on 2015/5/13.
 * socketInfo
 */
public class SocketMessage {

    public static final int MSG_TYPE_CONTROL = 0;
    public static final int MSG_TYPE_FEATURE = 1;
    public static final int MSG_TYPE_TEXT = 2;
    public static final int MSG_TYPE_VOICE = 3;


    /**
     * 特殊字符/断开服务 ()
     */
    public final static String MSG_C = "_c_";
    /**
     * 特殊字符/网络波动，重连 （reconnection）
     */
    public final static String MSG_RC = "_rc_";
    /**
     * 特殊字符/断开服务 （直播被服务器拒接,您的帐号存在多点登录直播，建议您修改密码）
     */
    public final static String MSG_CN = "_cn_";
    /**
     * 特殊字符/激励音乐
     */
    public final static String MSG_J = "_j_";
    /**
     * 特殊字符/鞭打声
     */
    public final static String MSG_B = "_b_";
    /**
     * 特殊字符/鼓掌声
     */
    public final static String GZ = "_gz_";
    /**
     * 特殊字符/电击声
     */
    public final static String DIANJI = "_dianji_";
    /**
     * 特殊字符/狗叫声
     */
    public final static String GJ = "_gj_";
    /**
     * 特殊字符/鲜花声
     */
    public final static String LH = "_lh_";
    /**
     * 特殊字符/直播语音
     */
    public final static String MSG_V = "_v_";
    /**
     * 消息发送者/系统发送 （system）
     */
    public static final String SNA_S = "_s_";
    /**
     * 消息发送者/分析用户-直播用户 （share user）
     */
    public static final String SNA_SU = "_su_";
    /**
     * 消息发送者/观看者 （watch user）
     */
    public static final String SNA_WU = "_wu_";

    ///
    /// 用户信息  （用户信息字段重复使用， 根据 sna（sender name）发送者类型，来区分）

    /**
     * uid 编号
     */
    private Integer ui;
    /**
     * name 名称
     */
    private String na;
    /**
     * name avatar头像
     */
    private String ua;
    /**
     * mo model 跑步模式：{@link com.business.core.entity.user.UserRun#type}
     */
    private Integer mo;
    /**
     * WebSocketServer 使用
     * y , n
     */
    private String sm;

    ///
    /// 发送这信息

    /**
     * 发送者名称 sender name
     */
    private String sna;
    /**
     * msg 消息 (特殊字符 : _c_(断开服务器) _j_(激励音乐) _b_(鞭打声) _v_(live voice 直播语音,语音地址 fu)) _st_ (直播暂停 stop（信息）)
     */
    private String msg;
    private int msgtype;
    /**
     * fileUrl 文件地址（语音，视频，等等）
     */
    private String fu;

    ///
    /// 歌曲信息

    /**
     * mix 编号
     */
    private Integer mi;
    /**
     * mix 名称
     */
    private String mn;
    /**
     * mix 作者，歌手 (Mix author)
     */
    private String ma;
    /**
     * 歌曲地址
     */
    private String mu;
    /**
     * 歌曲图片地址
     */
    private String mau;

    ///
    /// 实时地图信息

    /**
     * 经度
     */
    private Double lng;
    /*
     * 纬度
     */
    private Double lat;
    /**
     * 方向
     */
    private String angle;
    /**
     * 区分轨迹 0、正常轨迹  1、暂停轨迹
     */
    private String lty;

    ///
    /// 实时运动信息

    /**
     * 距离 (distance)
     */
    private Integer dst;
    /**
     * 运动时常
     */
    private Integer rt;
    /**
     * bpm
     */
    private Integer bpm;
    /**
     * 配速
     */
    private Integer ps;

    ///
    /// 其他

    /**
     * 在线人数
     */
    private Integer onl;
    /***
     * 微信用户名
     */
    private String nickname;
    /***
     * 微信用户头像
     */
    private String headimgurl;
    /***
     * 自定义openid
     * 用于页面接收到的消息是否为自己的消息
     */
    private String userOpenId;
    /***
     * 微信服务器语音id
     */
    private String serverId;

    public Integer getUi() {
        return ui;
    }

    public void setUi(Integer ui) {
        this.ui = ui;
    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public Integer getMo() {
        return mo;
    }

    public void setMo(Integer mo) {
        this.mo = mo;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getSna() {
        return sna;
    }

    public void setSna(String sna) {
        this.sna = sna;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgtype;
    }

    public void setMsgType(int msgtype) {
        this.msgtype = msgtype;
    }

    public String getFu() {
        return fu;
    }

    public void setFu(String fu) {
        this.fu = fu;
    }

    public Integer getMi() {
        return mi;
    }

    public void setMi(Integer mi) {
        this.mi = mi;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMu() {
        return mu;
    }

    public void setMu(String mu) {
        this.mu = mu;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getLty() {
        return lty;
    }

    public void setLty(String lty) {
        this.lty = lty;
    }

    public Integer getDst() {
        return dst;
    }

    public void setDst(Integer dst) {
        this.dst = dst;
    }

    public Integer getRt() {
        return rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public Integer getOnl() {
        return onl;
    }

    public void setOnl(Integer onl) {
        this.onl = onl;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
