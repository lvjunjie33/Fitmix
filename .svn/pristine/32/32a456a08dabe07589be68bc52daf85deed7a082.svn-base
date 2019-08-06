package com.business.core.alipay;

/**
 * Created by wjcaozhi1314 on 2016/3/15.
 */
public class AlipayConfig {


    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088121268480264";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public static String seller_id = partner;
    // 商户的私钥
    public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJU6Tunharw5XwESfkrTPic1gEwTBo7J2z5rEXkR3cJhsIDGrBeaWw+TYo1MSS5jB9IUltyOKHwYsfA9FHJpnRvlTgZUAk+78vJVehttMPCZtZH8U0hjs+hbsAHLqBJWn7Owu5mWuTsZQngZW7Ob/dtT9U01r5jn3wabiXgI88P7AgMBAAECgYBCJsLsstyZ6+TNmOEbUmFvCCyDjLuPeLQUC2qHfQANNzkDHQ8Ut3w+f1tkv7iBM60316C4zNvthxT6Jll2DpNyKPiQZl2Fy3A+tDvNdRz6fsmk3lcfUNZTvMq2529bQHFTKvuHhr/h95NGswcSSkiuneZt1PUOiTkj7v53p9fbwQJBAMakvnEK6YyxWtC9E3qIbK2MspnykAabGn1gpT1x0oMJCMYXsklCKxhfuRqK2s+zni86nVCpLk4yFaIssBZ9wOMCQQDAUN5vwwU/GXY0q4iXhd7uxEfAT8BvhESEnqKSmQ8C0IwAikq//P7OwNqAyqrA1/6ysg3MZaidfC8vIN9vxNQJAkAWyknrMERRVvvDXR4B/eryEmVfUjwB9gOZBOCQZyGu1PeDuq5Cx2uoVTsod2vZTpNEeeYYngBpCkf/Hj9ppS3fAkEAiJ9qxn8HVSJjhVtu+CYUkA9E4exGS3LtskF4QSkXLuq06xVTsTysUHlVQTW5RLKtsIkpoFGADhFQ++wgj6iG8QJAWWea6/VZAheiW9JSvTeokQYfr8wcY6S7uM/G0g+jgTZRunytFrFpAJbgJ92ar0Gked9x1gPJJARyXn99NILZ6w==";
    // 支付宝的公钥，无需修改该值
    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVOk7p4Wq8OV8BEn5K0z4nNYBMEwaOyds+axF5Ed3CYbCAxqwXmlsPk2KNTEkuYwfSFJbcjih8GLHwPRRyaZ0b5U4GVAJPu/LyVXobbTDwmbWR/FNIY7PoW7ABy6gSVp+zsLuZlrk7GUJ4GVuzm/3bU/VNNa+Y598Gm4l4CPPD+wIDAQAB";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    //public static String log_path = "D:\\";//demo自带
    //public static String log_path = "/Users/wjcaozhi/Desktop/zhifubaoLogo/";//caozhi mac 本地地址
    public static String log_path = "/data/logs/";//linux 服务器地址

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "RSA";


    //支付类型
    public static String PAYMENT_TYPE = "1";
    //必填，不能修改 服务器异步同步处理地址域名
    public static final String THEDOMAINNAME_URL = "http://appt.igeekery.com";
    //服务器异步通知页面路径
    public static final String NOTIFY_URL = THEDOMAINNAME_URL + "/activity/notify_url.htm";
    //需http://格式的完整路径，不能加?id=123这类自定义参数

    //页面跳转同步通知页面路径
    public static final String RETURN_URL = THEDOMAINNAME_URL + "/activity/return_url.htm";
    //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

}
