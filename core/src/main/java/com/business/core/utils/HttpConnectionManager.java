package com.business.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import org.apache.commons.lang.StringUtils;


/**
 * http连接、抓取管理类
 * @author sin
 * @createtime Oct 18, 2012 1:55:18 PM
 * 
 * @note 基本测试版
 */
public class HttpConnectionManager {

    private static Logger logger = LoggerFactory.getLogger(HttpConnectionManager.class);

    private static final int FACTOR = 10;

    /** 
     * 连接池里的最大连接数
     */  
    public static final int MAX_TOTAL_CONNECTIONS = 100;
//    public static final int MAX_TOTAL_CONNECTIONS = 1000;

    /** 
     * 每个路由的默认最大连接数
     */  
    public static final int MAX_ROUTE_CONNECTIONS = 50 / FACTOR;
//    public static final int MAX_ROUTE_CONNECTIONS = 500;

    /** 
     * 连接超时时间
     */  
    public static final int CONNECT_TIMEOUT = 50000 / FACTOR;
//    public static final int CONNECT_TIMEOUT = 500000;

    /**
     * 套接字超时时间
     */
    public static final int SOCKET_TIMEOUT = 50000 / FACTOR;
//    public static final int SOCKET_TIMEOUT = 500000;

    /**
     * 连接池中 连接请求执行被阻塞的超时时间
     */
    public static final long CONN_MANAGER_TIMEOUT = 60000 / FACTOR;
//    public static final long CONN_MANAGER_TIMEOUT = 600000;

    /**
     * http连接相关参数
     */
    private static HttpParams parentParams;
    
    /**
     * http线程池管理器
     */
    private static PoolingClientConnectionManager cm;

    private static final int EXECUTION_COUNT_MAX = 1;
    
    /**
     * http客户端
     */
    private static DefaultHttpClient httpClient;
    
    /**
     * 默认目标主机
     */
    private static final HttpHost DEFAULT_TARGETHOST = new HttpHost("http://www.qq.com", 80);
    
    /**
     * 初始化http连接池，设置参数、http头等等信息
     */
    static {
    	SchemeRegistry schemeRegistry = new SchemeRegistry();
    	schemeRegistry.register(
    	         new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    	schemeRegistry.register(
    	         new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

    	cm = new PoolingClientConnectionManager(schemeRegistry);

    	cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
    	
    	cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

    	cm.setMaxPerRoute(new HttpRoute(DEFAULT_TARGETHOST), 20);		//设置对目标主机的最大连接数
    	
    	parentParams = new BasicHttpParams(); 
    	parentParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

    	parentParams.setParameter(ClientPNames.DEFAULT_HOST, DEFAULT_TARGETHOST);	//设置默认targetHost
    	
    	parentParams.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
    	
    	parentParams.setParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
    	parentParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
    	parentParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
    	
    	parentParams.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
    	parentParams.setParameter(ClientPNames.HANDLE_REDIRECTS, true);
    	
    	//设置头信息,模拟浏览器
    	Collection<Header> collection = new ArrayList<Header>();
    	collection.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0"));
    	collection.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
    	collection.add(new BasicHeader("Accept-Language", "zh-cn,zh,en-US,en;q=0.5"));
    	collection.add(new BasicHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7"));
    	collection.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
    	
    	parentParams.setParameter(ClientPNames.DEFAULT_HEADERS, collection);
    	//请求重试处理
    	HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
    		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= EXECUTION_COUNT_MAX) {
    				// 如果超过最大重试次数，那么就不要继续了
    				return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// 不要重试SSL握手异常
					return false;
				}
				HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试
					return true;
				}
				return false;
    		}
    	};
    	
    	httpClient = new DefaultHttpClient(cm, parentParams);

//        addJavCookie("__qca", "P0-1558079705-1391845437675");
//        addJavCookie("cf_clearance", "6a5dd3f2b46b9b34e0e0856d4c313372e9259552-1391859846-3600");
//        addJavCookie("__cfduid", "d96b6de462efe07a7b0709556d25660301391862873785");
//        addJavCookie("__utma", "45030847.2112792778.1391845504.1391845504.1391845504.1");
//        addJavCookie("__utmb", "45030847.10.10.1391845504");
//        addJavCookie("__utmc", "45030847");

    	
    	httpClient.setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    private static void addJavCookie(String key, String value) {
        BasicClientCookie cookie = new BasicClientCookie(key, value);
        cookie.setDomain(".javlibrary.com");
        httpClient.getCookieStore().addCookie(cookie);
    }

    private static List<HttpHost> proxy = new ArrayList<HttpHost>();

    static {

        proxy.add(new HttpHost("211.138.121.36", 85));
        proxy.add(new HttpHost("183.207.224.50", 81));
        proxy.add(new HttpHost("202.171.253.84", 86));
        proxy.add(new HttpHost("203.100.80.81", 8080));
        proxy.add(new HttpHost("222.246.232.55", 80));
        proxy.add(new HttpHost("110.4.24.178", 80));
        proxy.add(new HttpHost("202.106.169.228", 8080));
        proxy.add(new HttpHost("113.107.57.76", 80));
        proxy.add(new HttpHost("59.151.103.15", 80));
        proxy.add(new HttpHost("183.207.224.50", 81));
        proxy.add(new HttpHost("211.138.121.36", 85));
        proxy.add(new HttpHost("110.4.12.176", 80));
//        proxy.add(new HttpHost("218.201.38.49", 80));
//        proxy.add(new HttpHost("111.11.153.23", 80));
//        proxy.add(new HttpHost("183.203.22.94", 80));
//        proxy.add(new HttpHost("183.203.22.69", 80));
//        proxy.add(new HttpHost("183.207.224.42", 80));
//        proxy.add(new HttpHost("113.57.252.104", 80));
//        proxy.add(new HttpHost("211.138.121.37", 82));
//        proxy.add(new HttpHost("111.1.36.6", 80));
//        proxy.add(new HttpHost("211.138.121.36", 83));


        //
//        proxy.add(new HttpHost("124.133.232.26", 3128));
//        proxy.add(new HttpHost("218.93.112.72", 3128));
        proxy.add(new HttpHost("113.78.190.30", 1111));
        proxy.add(new HttpHost("14.18.16.69", 80));
        proxy.add(new HttpHost("122.228.177.155", 8080));
        proxy.add(new HttpHost("122.96.59.99", 81));
        proxy.add(new HttpHost("122.136.46.151", 3128));
        proxy.add(new HttpHost("111.161.126.84", 80));
        proxy.add(new HttpHost("58.221.160.70", 8080));
        proxy.add(new HttpHost("121.9.231.82", 9999));
        proxy.add(new HttpHost("222.132.29.10", 8080));
        proxy.add(new HttpHost("210.101.131.231", 8080));
        proxy.add(new HttpHost("113.57.252.107", 80));
        proxy.add(new HttpHost("59.49.216.141", 8080));
        proxy.add(new HttpHost("116.112.66.102", 808));
        proxy.add(new HttpHost("121.11.167.246", 9999));
        proxy.add(new HttpHost("1.85.4.142", 8080));
        proxy.add(new HttpHost("113.57.252.105", 80));
        proxy.add(new HttpHost("114.80.120.53", 8080));
        proxy.add(new HttpHost("121.12.167.197", 3128));
        proxy.add(new HttpHost("111.1.36.137", 80));
        proxy.add(new HttpHost("110.84.129.27", 8888));
        proxy.add(new HttpHost("221.130.23.8", 80));
        proxy.add(new HttpHost("59.120.87.11", 8080));
        proxy.add(new HttpHost("113.57.252.103", 80));
//        proxy.add(new HttpHost("112.175.251.57", 8080));
//        proxy.add(new HttpHost("61.134.38.42", 7280));
//        proxy.add(new HttpHost("58.22.28.134", 81));
//        proxy.add(new HttpHost("58.22.28.134", 82));
//        proxy.add(new HttpHost("221.130.29.184", 8888));
//        proxy.add(new HttpHost("111.1.36.165", 80));
//        proxy.add(new HttpHost("183.63.130.218", 9999));
//        proxy.add(new HttpHost("61.143.124.155", 80));
//        proxy.add(new HttpHost("211.143.226.179", 80));
//        proxy.add(new HttpHost("221.130.23.6", 80));
//        proxy.add(new HttpHost("180.173.85.204", 8080));
//        proxy.add(new HttpHost("61.156.217.135", 9000));
//        proxy.add(new HttpHost("221.2.228.202", 9000));
//        proxy.add(new HttpHost("61.143.61.92", 52013));
//        proxy.add(new HttpHost("117.59.224.62", 80));
//        proxy.add(new HttpHost("117.41.182.188", 8080));
//        proxy.add(new HttpHost("112.90.146.76", 3128));
//        proxy.add(new HttpHost("112.175.251.56", 8080));
//        proxy.add(new HttpHost("60.161.198.240", 87));
//        proxy.add(new HttpHost("111.161.126.85", 80));
//        proxy.add(new HttpHost("221.130.23.29", 80));
//        proxy.add(new HttpHost("14.18.17.166", 80));
//        proxy.add(new HttpHost("61.153.98.6", 8080));
//        proxy.add(new HttpHost("58.20.223.230", 3128));
//        proxy.add(new HttpHost("113.57.252.106", 80));
//        proxy.add(new HttpHost("58.20.127.178", 3128));
//        proxy.add(new HttpHost("184.107.159.158", 3128));
//        proxy.add(new HttpHost("14.18.16.68", 80));
//        proxy.add(new HttpHost("61.53.64.37", 8080));
//        proxy.add(new HttpHost("111.161.126.93", 8080));
//        proxy.add(new HttpHost("111.1.36.140", 80));
//        proxy.add(new HttpHost("61.160.108.115", 8080));
//        proxy.add(new HttpHost("202.98.1.218", 8080));
//        proxy.add(new HttpHost("111.161.126.88", 8080));
//        proxy.add(new HttpHost("61.55.141.11", 81));
//        proxy.add(new HttpHost("119.233.255.24", 843));
//        proxy.add(new HttpHost("59.172.208.186", 8080));
//        proxy.add(new HttpHost("221.181.192.91", 80));
//        proxy.add(new HttpHost("111.206.27.2", 9000));
//        proxy.add(new HttpHost("211.144.81.105", 80));
//        proxy.add(new HttpHost("59.46.72.246", 80));
//        proxy.add(new HttpHost("117.36.50.52", 3128));
//        proxy.add(new HttpHost("122.96.59.100", 82));
//        proxy.add(new HttpHost("61.54.221.200", 3128));
//        proxy.add(new HttpHost("122.226.122.201", 8080));
//        proxy.add(new HttpHost("219.135.189.90", 8080));
//        proxy.add(new HttpHost("114.80.136.223", 7780));
//        proxy.add(new HttpHost("221.176.14.72", 80));
//        proxy.add(new HttpHost("221.193.237.73", 8080));
//        proxy.add(new HttpHost("14.18.16.66", 80));
//        proxy.add(new HttpHost("114.80.136.112", 7780));
//        proxy.add(new HttpHost("58.22.28.138", 843));
//        proxy.add(new HttpHost("61.163.236.172", 9999));
//        proxy.add(new HttpHost("182.151.212.242", 8080));
//        proxy.add(new HttpHost("221.181.192.60", 80));
//        proxy.add(new HttpHost("221.130.23.7", 80));
//        proxy.add(new HttpHost("124.225.52.14", 8080));
//        proxy.add(new HttpHost("122.96.59.100", 843));
//        proxy.add(new HttpHost("111.1.36.26", 80));
//        proxy.add(new HttpHost("218.104.148.59", 3128));
//        proxy.add(new HttpHost("61.135.151.122", 80));
//        proxy.add(new HttpHost("218.29.90.30", 9999));
//        proxy.add(new HttpHost("221.130.23.156", 80));
//        proxy.add(new HttpHost("61.55.141.10", 81));
//        proxy.add(new HttpHost("218.4.236.117", 80));
//        proxy.add(new HttpHost("111.161.126.91", 8080));
//        proxy.add(new HttpHost("14.18.16.71", 80));
//        proxy.add(new HttpHost("114.80.136.220", 7780));
//        proxy.add(new HttpHost("111.161.126.90", 8080));
//        proxy.add(new HttpHost("61.163.101.42", 3128));
//        proxy.add(new HttpHost("183.207.228.7", 9999));
//        proxy.add(new HttpHost("210.14.79.178", 3128));
//        proxy.add(new HttpHost("119.97.146.16", 8080));
//        proxy.add(new HttpHost("120.202.249.230", 80));
//        proxy.add(new HttpHost("221.130.23.27", 80));
//        proxy.add(new HttpHost("113.57.252.104", 80));
//        proxy.add(new HttpHost("60.22.80.5", 3128));
//        proxy.add(new HttpHost("219.132.140.218", 9999));
//        proxy.add(new HttpHost("60.223.241.92", 9999));
//        proxy.add(new HttpHost("118.244.239.2", 3128));
//        proxy.add(new HttpHost("221.130.17.39", 80));
//        proxy.add(new HttpHost("183.60.44.136", 88));
//        proxy.add(new HttpHost("119.97.146.16", 80));
//        proxy.add(new HttpHost("61.144.48.169", 3128));
//        proxy.add(new HttpHost("210.22.115.162", 3128));
//        proxy.add(new HttpHost("111.161.126.87", 8080));
//        proxy.add(new HttpHost("111.1.36.163", 80));
//        proxy.add(new HttpHost("112.218.68.155", 8080));
//        proxy.add(new HttpHost("221.130.23.144", 80));
//        proxy.add(new HttpHost("121.33.222.228", 9999));
//        proxy.add(new HttpHost("106.120.108.163", 3128));
//        proxy.add(new HttpHost("221.210.40.150", 8080));
//        proxy.add(new HttpHost("61.163.236.158", 9999));
//        proxy.add(new HttpHost("119.145.141.98", 9999));
//        proxy.add(new HttpHost("183.207.228.40", 81));
//        proxy.add(new HttpHost("61.139.5.229", 9000));
//        proxy.add(new HttpHost("58.20.127.26", 3128));
//        proxy.add(new HttpHost("114.80.91.166", 7780));
//        proxy.add(new HttpHost("61.181.131.102", 9999));
//        proxy.add(new HttpHost("61.143.248.100", 1120));
//        proxy.add(new HttpHost("122.136.46.151", 80));
//        proxy.add(new HttpHost("121.15.230.126", 8080));
//        proxy.add(new HttpHost("120.198.230.8", 81));
//        proxy.add(new HttpHost("58.20.127.106", 3128));
//        proxy.add(new HttpHost("61.167.49.188", 8080));
//        proxy.add(new HttpHost("14.18.16.67", 80));
//        proxy.add(new HttpHost("59.46.72.247", 8080));
//        proxy.add(new HttpHost("117.36.231.239", 9999));
//        proxy.add(new HttpHost("122.96.59.99", 843));
//        proxy.add(new HttpHost("58.22.28.135", 83));
//        proxy.add(new HttpHost("221.130.23.89", 8080));
//        proxy.add(new HttpHost("58.22.28.135", 82));
//        proxy.add(new HttpHost("123.13.226.56", 9999));
//        proxy.add(new HttpHost("222.87.129.30", 80));
//        proxy.add(new HttpHost("220.162.237.125", 81));
//        proxy.add(new HttpHost("59.52.95.118", 80));
//        proxy.add(new HttpHost("111.11.27.194", 83));
//        proxy.add(new HttpHost("61.148.10.18", 8080));
//        proxy.add(new HttpHost("221.10.40.234", 81));
//        proxy.add(new HttpHost("60.161.14.77", 8001));
//        proxy.add(new HttpHost("61.181.131.34", 9999));
//        proxy.add(new HttpHost("58.22.28.133", 843));
//        proxy.add(new HttpHost("114.80.136.222", 7780));
//        proxy.add(new HttpHost("222.134.147.74", 8080));
//        proxy.add(new HttpHost("220.248.180.149", 3128));
//        proxy.add(new HttpHost("59.172.208.190", 8080));
//        proxy.add(new HttpHost("221.130.17.27", 80));
//        proxy.add(new HttpHost("119.186.160.86", 8000));
//        proxy.add(new HttpHost("210.101.131.232", 8080));
//        proxy.add(new HttpHost("111.1.55.18", 8080));
//        proxy.add(new HttpHost("61.163.163.145", 9999));
//        proxy.add(new HttpHost("60.190.138.151", 80));
//        proxy.add(new HttpHost("115.238.142.178", 80));
//        proxy.add(new HttpHost("61.178.208.12", 63000));
//        proxy.add(new HttpHost("61.158.219.226", 8118));
//        proxy.add(new HttpHost("111.47.92.226", 8080));
//        proxy.add(new HttpHost("140.207.194.70", 80));
//        proxy.add(new HttpHost("111.1.36.166", 80));

    }

    /**
     * 抓取页面代码
     * @param url 目标页面的url
     * @return 页面代码
     */
    public String getHtml(String url) {
    	HttpHost proxyHost = getProxy();

    	String html = getHtml(url, proxyHost);

    	int count = 0;
    	while(StringUtils.isEmpty(html)){
	    	proxyHost = getProxy();
	    	html = getHtml(url, proxyHost);
	    	count++;
	    	if(count > 10){
                logger.warn("抓取失败");
	    		break;
	    	}
    	}

        logger.info("html.length :{}",html.length());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return html;
    }

    /**
     * 抓取url所指的页面代码
     * @param url 目标页面的url
     * @return 页面代码
     */
    public String getHtml(String url, HttpHost proxyHost) {
    	String html = null;
    	HttpGet httpGet = new HttpGet(url);
        // TODO 去掉代理！
        if(proxyHost != null) {
            httpGet.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);//设置代理
        }

    	HttpResponse httpResponse;
    	HttpEntity httpEntity;
		try {
//            logger.info("getHtml url: {}", url);
			httpResponse = httpClient.execute(httpGet);

			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
//            logger.info("statusCode:" + statusCode);
			if(200 != statusCode) {
				return null;
			}

	    	httpEntity = httpResponse.getEntity();
	    	if(httpEntity != null){
	    		html = readHtmlContentFromEntity(httpEntity);
	    	}
		}
//        catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        catch (Exception e) {
            logger.error("fetch url({}) error({})", url, ExceptionUtils.getStackTrace(e));
        }

        finally {
            httpGet.releaseConnection();
        }

    	return html;
    }

    private HttpHost getProxy() {
        return proxy.get(new Random().nextInt(proxy.size()));
//        return null;
    }

    /**
     * 从response返回的实体中读取页面代码
     * @param httpEntity Http实体
     * @return 页面代码
     * @throws ParseException
     * @throws java.io.IOException
     */
    private String readHtmlContentFromEntity(HttpEntity  httpEntity) throws ParseException, IOException {
    	String html = "";
    	Header header = httpEntity.getContentEncoding();
    	if(httpEntity.getContentLength() < 2147483647L){			//EntityUtils无法处理ContentLength超过2147483647L的Entity
    		if(header != null && "gzip".equals(header.getValue())){
    			html = EntityUtils.toString(new GzipDecompressingEntity(httpEntity), "UTF-8");
    		} else {
    			html = EntityUtils.toString(httpEntity,"gb2312");
    		}
    	} else {
    		InputStream in = httpEntity.getContent();
    		if(header != null && "gzip".equals(header.getValue())){
    			html = unZip(in, ContentType.getOrDefault(httpEntity).getCharset().toString());
    		} else {
    			html = readInStreamToString(in, ContentType.getOrDefault(httpEntity).getCharset().toString());
    		}
    		if(in != null){
    			in.close();
    		}
    	}
    	return html;
    }

    /**
     * 测试代理是否可用（其实和getHtml(String url, HttpHost proxyHost)的代码差不多，为了从功能上区别，暂时这样）
     * @param proxyHost 封装了代理的ip地址和端口
     * @param url 用来测试的页面
     * @return true 可用 false 不可用
     */
    public boolean isProxyUsable(HttpHost proxyHost, String url) {
    	HttpGet httpGet = new HttpGet(url);
    	httpGet.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
    	try {
            HttpContext context = new BasicHttpContext();
			HttpResponse httpResponse = httpClient.execute(httpGet, context);

			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
//            logger.info("isProxyUsable statusCode: " + statusCode);
			if(200 != statusCode) {
				return false;
			}
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpEntity != null) {
				String html = readHtmlContentFromEntity(httpEntity);
//                logger.info("isProxyUsable html.length(): " + html.length());
				if(StringUtils.isEmpty(html)){
					return false;
				}
			} else {
				return false;
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

    	return true;
    }

    /**
	 * 解压服务器返回的gzip流
	 * @param in 抓取返回的InputStream流
	 * @param charSet 页面内容编码
	 * @return 页面内容的String格式
	 * @throws java.io.IOException
	 */
	private String unZip(InputStream in, String charSet) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPInputStream gis = null;
		try {
			gis = new GZIPInputStream(in);
			byte[] _byte = new byte[1024];
			int len = 0;
			while ((len = gis.read(_byte)) != -1) {
				baos.write(_byte, 0, len);
			}
			String unzipString = new String(baos.toByteArray(), charSet);
			return unzipString;
		} finally {
			if (gis != null) {
				gis.close();
			}
			if(baos != null){
				baos.close();
			}
		}
	}

	/**
	 * 读取InputStream流
	 * @param in InputStream流
	 * @return 从流中读取的String
	 * @throws java.io.IOException
	 */
    private String readInStreamToString(InputStream in, String charSet) throws IOException {
    	StringBuilder str = new StringBuilder();
		String line;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, charSet));
		while((line = bufferedReader.readLine()) != null){
			str.append(line);
			str.append("\n");
		}
		if(bufferedReader != null) {
			bufferedReader.close();
		}
		return str.toString();
    }
    
    /**
     * for test
     * @author lidongyang
     * @createtime Oct 18, 2012 2:35:09 PM
     */
    public class Test implements Runnable {
    	String url;
    	int threadNum;
    	
    	public Test() {
    		
    	}
    	
    	public Test(String url, int threadNum) {
    		this.url = url;
    		this.threadNum = threadNum;
    	}
    	
		@Override
		public void run() {
			getHtml(url);
		}
    }
    
    
    /**
     * for test
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException{
//		HttpConnectionManager httpConnectionManager = new HttpConnectionManager();
//		Date start = new Date();
//		httpConnectionManager.getHtml("http://tool.oschina.net/codeformat/json");
//		Date end = new Date();
//		System.out.println((end.getTime() - start.getTime())/1000.0 + " 秒");

//        proxy.add(new HttpHost("211.138.121.36", 85));
//        proxy.add(new HttpHost("183.207.224.50", 81));
//        proxy.add(new HttpHost("202.171.253.84", 86));
//        proxy.add(new HttpHost("203.100.80.81", 8080));
//        proxy.add(new HttpHost("222.246.232.55", 80));
//        System.out.println(new HttpConnectionManager().isProxyUsable(new HttpHost("211.138.121.36", 85), "http://tool.oschina.net/codeformat/json"));
//        HttpConnectionManager httpConnectionManager = new HttpConnectionManager();
//        httpConnectionManager.getHtml("http://tool.oschina.net/codeformat/json");
    }
}
