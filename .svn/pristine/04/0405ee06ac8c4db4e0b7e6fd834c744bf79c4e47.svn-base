package com.business.live.base.wxSHA1;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.business.core.utils.HttpUtil;

public class SHA1 {

	public static final String WXURL = "https://api.weixin.qq.com/cgi-bin/token";// 微信普通鉴权请求路劲

	public static final String WXPAGE = "grant_type=client_credential&appid=wx7adcfc1457f072b4&secret=3022239db350cccc777bebc315a2dc51";// 微信普通鉴权请求参数

	private static long time;

	public static long getTime() {
		return time;
	}

	public static void setTime(long time) {
		SHA1.time = time;
	}

	public static String getHtml(String urlStr) {
		String htmlStr = "";
		try {
			HttpGet httpGet = new HttpGet(urlStr);
			CloseableHttpResponse response = HttpUtil.getHttpClient().execute(httpGet);
			htmlStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlStr;
	}
}
