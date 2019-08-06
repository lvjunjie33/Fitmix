package base;

import com.business.core.constants.Constants;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.user.User;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.DickInfo;
import com.business.core.utils.DickUtil;
import com.business.core.utils.HttpUtil;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/5.
 */
public class BaseTest {

    protected static final String HOST = "http://localhost:8068/app";

    protected static final String K = "xQ33ppeXMppKa4";

    protected static final int VERSION = VersionConstants.IOS.VERSION_0.getVersion();
//    protected static final int VERSION = VersionConstants.Android.VERSION_0.getVersion();

    protected static int TERMINAL = Constants.TERMINAL_ANDROID;
//    protected static int TERMINAL = Constants.TERMINAL_IOS;

    protected static final String SDK = "7.000000";

    protected static final String UDID = "51fa53ac7fb7f35cff33e16c9d80418fb486eac8";


    protected void request(String uri, MultipartEntityBuilder builder) {
        CloseableHttpResponse response = null;
        try {
            // 处理必须带的参数
            builder
                    .addTextBody("k", K)
                    .addTextBody("sdk", SDK)
                    .addTextBody("v", String.valueOf(VERSION))
                    .addTextBody("t", String.valueOf(TERMINAL))
                    .addTextBody("ct", String.valueOf(System.currentTimeMillis()))
                    .addTextBody("udid", UDID)
            ;


            // 请求
            HttpPost post = new HttpPost(HOST + uri);
            System.out.println(HOST + uri);

            post.addHeader("User-Agent", "IOS");
            post.setEntity(builder.build());
            response = HttpUtil.getHttpClient().execute(post);
            int status = response.getStatusLine().getStatusCode();
            String responseText = EntityUtils.toString(response.getEntity());
            if (status == HttpStatus.SC_OK) {
                System.out.println(CommonUtil.jsonFormatter(responseText));
            } else {
                System.err.println("错误的结果：" + status);
                System.err.println(responseText);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    protected void requestGet (String uri, ImmutableMap.Builder<String, String> header) {
        CloseableHttpResponse response = null;
        try {
//            header.put("k", K)
//                    .put("sdk", SDK)
//                    .put("v", String.valueOf(VERSION))
//                    .put("t", String.valueOf(TERMINAL))
//                    .put("ct", String.valueOf(System.currentTimeMillis()))
//                    .put("udid", UDID);

            HttpGet get = new HttpGet(HOST + uri);
            System.out.print(((HOST + uri).indexOf("?")) == -1 ? HOST + uri + "?" : HOST + uri);
            int i = 0;
            for (Map.Entry<String, String> h : header.build().entrySet()) {
                get.setHeader(h.getKey(), h.getValue());
                if (i == 0) {
                    System.out.print(MessageFormat.format("{0}={1}", h.getKey(), h.getValue()));
                    i++;
                }
                else {
                    System.out.print(MessageFormat.format("&{0}={1}", h.getKey(), h.getValue()));
                }
            }
            System.out.println("\r\n");
            response = HttpUtil.getHttpClient().execute(get);
            int status = response.getStatusLine().getStatusCode();
            String responseText = EntityUtils.toString(response.getEntity());
            if (status == HttpStatus.SC_OK) {
                System.out.println(CommonUtil.jsonFormatter(responseText));
            } else {
                System.err.println("错误的结果：" + status);
                System.err.println(responseText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
