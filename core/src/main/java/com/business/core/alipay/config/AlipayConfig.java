package com.business.core.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.alipay.sign.RSA;
import com.business.core.alipay.util.AlipayCore;
import com.business.core.alipay.util.AlipaySubmit;
import com.business.core.sort.SortFactory;
import com.mongodb.util.Hash;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088121268480264";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJU6Tunharw5XwESfkrTPic1gEwTBo7J2z5rEXkR3cJhsIDGrBeaWw+TYo1MSS5jB9IUltyOKHwYsfA9FHJpnRvlTgZUAk+78vJVehttMPCZtZH8U0hjs+hbsAHLqBJWn7Owu5mWuTsZQngZW7Ob/dtT9U01r5jn3wabiXgI88P7AgMBAAECgYBCJsLsstyZ6+TNmOEbUmFvCCyDjLuPeLQUC2qHfQANNzkDHQ8Ut3w+f1tkv7iBM60316C4zNvthxT6Jll2DpNyKPiQZl2Fy3A+tDvNdRz6fsmk3lcfUNZTvMq2529bQHFTKvuHhr/h95NGswcSSkiuneZt1PUOiTkj7v53p9fbwQJBAMakvnEK6YyxWtC9E3qIbK2MspnykAabGn1gpT1x0oMJCMYXsklCKxhfuRqK2s+zni86nVCpLk4yFaIssBZ9wOMCQQDAUN5vwwU/GXY0q4iXhd7uxEfAT8BvhESEnqKSmQ8C0IwAikq//P7OwNqAyqrA1/6ysg3MZaidfC8vIN9vxNQJAkAWyknrMERRVvvDXR4B/eryEmVfUjwB9gOZBOCQZyGu1PeDuq5Cx2uoVTsod2vZTpNEeeYYngBpCkf/Hj9ppS3fAkEAiJ9qxn8HVSJjhVtu+CYUkA9E4exGS3LtskF4QSkXLuq06xVTsTysUHlVQTW5RLKtsIkpoFGADhFQ++wgj6iG8QJAWWea6/VZAheiW9JSvTeokQYfr8wcY6S7uM/G0g+jgTZRunytFrFpAJbgJ92ar0Gked9x1gPJJARyXn99NILZ6w==";


	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVOk7p4Wq8OV8BEn5K0z4nNYBMEwaOyds+axF5Ed3CYbCAxqwXmlsPk2KNTEkuYwfSFJbcjih8GLHwPRRyaZ0b5U4GVAJPu/LyVXobbTDwmbWR/FNIY7PoW7ABy6gSVp+zsLuZlrk7GUJ4GVuzm/3bU/VNNa+Y598Gm4l4CPPD+wIDAQAB";


	//必填，不能修改 服务器异步同步处理地址域名
	public static final String server_url = "http://appt.igeekery.com";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = server_url + "/ali-pay/notify-url.htm";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = server_url + "/ali-pay/return-url.htm";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "/data/logs";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


	public static void main(String[] args) throws Exception {

/*

		buyer_id - - - 2088012891601633
		trade_no - - - 2016042121001004630200910957
		use_coupon - - - N
		notify_time - - - 2016-04-21 00:28:08
		subject - - - test
		sign_type - - - RSA
		is_total_fee_adjust - - - N
		notify_type - - - trade_status_sync
		out_trade_no - - - 1461169680232TPjWGJydNp
		gmt_payment - - - 2016-04-21 00:28:08
		trade_status - - - TRADE_SUCCESS
		sign - - - o+MFcA+FGgZMmT3WbuzaOtvUKKrW2F+PGlVXpo8BzrsOMJo04+4JMBSH4X5yg11+aMJosgvinrIp6G3oR8ikR5Z7IQA2NB5MJCsMN2gNKHq5DRed9gMa8Sihzkp0hVYbr17LlBe1iVnsbAV7psabD55L4sQs4HzThb2992kUs6o=
		buyer_email - - - cherishsince@aliyun.com
		gmt_create - - - 2016-04-21 00:28:08
		price - - - 0.01
		total_fee - - - 0.01
		quantity - - - 1
		seller_id - - - 2088121268480264
		notify_id - - - 6ae41a13ae9bda31854ad0728011cbekv2
		seller_email - - - sunj@igeekery.com
		payment_type - - - 1
*/

		String objJson = "{\"buyer_email\":\"cherishsince@aliyun.com\",\"buyer_id\":\"2088012891601633\",\"gmt_create\":\"2016-04-21 00:28:08\",\"gmt_payment\":\"2016-04-21 00:28:08\",\"is_total_fee_adjust\":\"N\",\"notify_id\":\"6ae41a13ae9bda31854ad0728011cbekv2\",\"notify_time\":\"2016-04-21 00:28:08\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1461169680232TPjWGJydNp\",\"payment_type\":\"1\",\"price\":\"0.01\",\"quantity\":\"1\",\"seller_email\":\"sunj@igeekery.com\",\"seller_id\":\"2088121268480264\",\"sign\":\"o+MFcA+FGgZMmT3WbuzaOtvUKKrW2F+PGlVXpo8BzrsOMJo04+4JMBSH4X5yg11+aMJosgvinrIp6G3oR8ikR5Z7IQA2NB5MJCsMN2gNKHq5DRed9gMa8Sihzkp0hVYbr17LlBe1iVnsbAV7psabD55L4sQs4HzThb2992kUs6o=\",\"sign_type\":\"RSA\",\"subject\":\"test\",\"total_fee\":\"0.01\",\"trade_no\":\"2016042121001004630200910957\",\"trade_status\":\"TRADE_SUCCESS\",\"use_coupon\":\"N\"}";
		JSONObject objMap = JSON.parseObject(objJson);
		String sign = String.valueOf(objMap.get("sign"));
		String signTypeobj = String.valueOf(objMap.get("sign_type"));

		Map<String, String> strMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : objMap.entrySet()) {
			strMap.put(entry.getKey(), String.valueOf(entry.getValue()));
		}

		Map<String, String> filterMap = AlipayCore.paraFilter(strMap);

		List<String> sortKeyList = new ArrayList<>(filterMap.keySet());

		Collections.sort(sortKeyList, SortFactory.STR_SORT);

		LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();

		for (String sortKey : sortKeyList) {
			sortMap.put(sortKey, strMap.get(sortKey));
		}

		System.out.println(RSA.verify(AlipayCore.createLinkString(sortMap), sign, alipay_public_key, input_charset));
		System.out.println(AlipaySubmit.buildRequestMysign(sortMap));
		System.out.println(RSA.sign(AlipayCore.createLinkString(sortMap), private_key, input_charset));

//		Map<String, String> sPara = AlipaySubmit.buildRequestPara(strMap);
//
//		String a = "";
	}
}

