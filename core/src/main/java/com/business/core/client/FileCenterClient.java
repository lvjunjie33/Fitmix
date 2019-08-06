package com.business.core.client;

import com.business.core.constants.ApplicationConfig;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.HttpPostUtil;
import com.business.core.utils.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by sin on 15/4/14.
 *
 * 文件中心－工具
 *
 */
public class FileCenterClient {

    /**
     * 请求 - 上传文件地址
     */
    private static final String REQUEST_UPLOAD = "/upload.json";
    /**
     * 请求 - 上传文件地址
     */
    private static final String REQUEST_REMOVE = "/remove.json";
    /**
     * 请求 - 上传文件参数 - 文件
     */
    private static final String PARAMETER_FILE = "file";
    /**
     * 请求 - 上传文件参数 - 文件分类
     */
    private static final String PARAMETER_CATEGORY_ID = "categoryId";

    /**
     * JPG压缩率
     */
    private static final Float JPEG_QUALITY_DEFAULT = 0.8f;

    /**
     * 图片压缩格式
     */
    private static final String imageFormat = "jpg";

    /**
     * 上传文件到文件中心
     * @param categoryId 文件类型
     * @return 文件服务器路径
     * @throws RuntimeException 当上传文件失败时
     */
    public static String upload (MultipartFile file, int categoryId) {
        try {
            return upload(file.getBytes(), categoryId, file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传文件到文件中心
     * @param categoryId 文件类型
     * @param is         输入
     * @return 文件服务器路径
     * @throws RuntimeException 当上传文件失败时
     */
    public static String upload(InputStream is, int categoryId, String fileName) {
        try {
            byte[] fileBytes = IOUtils.toByteArray(is);
            return upload(fileBytes, categoryId, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 上传文件到文件中心
     * @param fileBytes 文件 二进制
     * @param categoryId 分类编号
     * @param fileName 文件名
     * @return  当上传文件失败时
     */
    public static String upload(byte[] fileBytes, int categoryId, String fileName) {
        return uploadNew(fileBytes, categoryId, fileName);
    }

    public static String buildUrl (String uri) {
        if (StringUtils.isEmpty(uri)) {
            return uri;
        }
        if (uri.indexOf("http:") > -1 || uri.indexOf("https:") > -1) {
            return uri;
        }
        return ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + uri;
    }

    @Deprecated
    public static String buildUrlDiscard(String uri) {
        if (StringUtils.isEmpty(uri)) {
            return uri;
        }
        return "http://fs.igeekery.com/" + uri;
    }

    public static String uploadNew (byte[] fileBytes, int categoryId, String fileName) {

        /// 所有文件 现在走 aliyun
        if (false) {
            String uName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            try {
                HttpPostUtil httpPostUtil = new HttpPostUtil(ApplicationConfig.FILECENTER_REQUEST_URI_PREFIX + REQUEST_UPLOAD);
                httpPostUtil.addFileParameter(uName, fileBytes);
                httpPostUtil.addTextParameter(PARAMETER_CATEGORY_ID, categoryId + "");
                byte[] b = httpPostUtil.send();
                String result = new String(b);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/" + categoryId + "/" + uName;
        }
        /// 走 aliyun
        return AliyunCenterClient.putFile(String.valueOf(categoryId), fileName, fileBytes);
    }

    /**
     * app upload
     * @param request 请求
     * @param fileType 文件类型
     * @return 上传后的文件地址
     */
    public static List<String> uploadApp (HttpServletRequest request, Integer fileType) {
        List<String> fileUrl = new ArrayList<>();
        try {
            Map<String, MultipartFile> fileMap = buildMultipartFile(request);
            if (!CollectionUtils.isEmpty(fileMap)) {
                for (Map.Entry<String, MultipartFile> multipartFile :fileMap.entrySet()) {
                    MultipartFile file = multipartFile.getValue();
                    /// 所有文件 现在走 aliyun
                    if (false) {
                        fileUrl.add(upload(file.getBytes(), fileType, file.getOriginalFilename()));  // 转发文件服务器
                    }
                    /// 走 aliyun
                    fileUrl.add(AliyunCenterClient.putFile(fileType.toString(), file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileUrl;
        }
    }

    /**
     * app upload
     * @param request 请求
     * @param fileType 文件类型
     * @return 上传后的文件地址
     */
    public static List<String> uploadFiles (HttpServletRequest request, Integer fileType) {
        List<String> fileUrl = new ArrayList<>();
        try {
            MultiValueMap<String, MultipartFile> multiValueMap = buildMultipartFiles(request);
            for (Map.Entry<String, List<MultipartFile>> entry : multiValueMap.entrySet()) {
                for (MultipartFile multipartFile : entry.getValue()) {
                    /// 所有文件 现在走 aliyun
                    if (false) {
                        fileUrl.add(upload(multipartFile.getBytes(), fileType, multipartFile.getOriginalFilename()));  // 转发文件服务器
                    }
                    /// 走 aliyun
                    fileUrl.add(AliyunCenterClient.putFile(fileType.toString(), multipartFile));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileUrl;
        }
    }


    /**
     * 构建 MultipartFile
     * @param request 请求
     * @return 构建好的 MultipartFile Map
     */
    public static MultiValueMap<String, MultipartFile> buildMultipartFiles (HttpServletRequest request) {
        return ((DefaultMultipartHttpServletRequest) request).getMultiFileMap();
    }

    /**
     * 构建 MultipartFile
     * @param request 请求
     * @return 构建好的 MultipartFile Map
     */
    public static Map<String, MultipartFile> buildMultipartFile (HttpServletRequest request) {
        Map<String, MultipartFile> fileMap = new LinkedHashMap<>(0);
        if (request instanceof MultipartHttpServletRequest) {
            fileMap = ((MultipartHttpServletRequest) request).getFileMap();
        }
        return fileMap;
    }

    public static boolean removeFile (String url) {
        if (StringUtils.isEmpty(url)) {
            return true;
        }
        /// 所有文件 现在走 aliyun
        if (false) {
            CloseableHttpResponse response = null;
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("url", url);
            try {
                // 请求
                HttpPost post = new HttpPost(ApplicationConfig.FILECENTER_REQUEST_URI_PREFIX + REQUEST_REMOVE);
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
            return true;
        }
        /// 走 aliyun
        return AliyunCenterClient.deleteFile(url);
    }
}
