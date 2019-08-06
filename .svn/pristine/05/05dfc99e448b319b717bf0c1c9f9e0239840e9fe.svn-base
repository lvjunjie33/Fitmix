package com.business.work.api;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.api.Api;
import com.business.core.entity.api.ApiDetails;
import com.business.core.entity.api.Fields;
import com.business.core.entity.file.*;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import com.business.work.apiDetails.ApiDetailsService;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.file.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Controller
@RequestMapping("api")
public class ApiController extends BaseControllerSupport {

    @Autowired
    private ApiService apiService;
    @Autowired
    private ApiDetailsService apiDetailsService;

    public static ArrayList<File> files = new ArrayList<>();

    @Autowired
    private FileService fileService;
    /**
     * 生成API
     * @param path
     * @throws Exception
     */
    @RequestMapping(value = "api-generate")
    public void generateApi(String path) throws Exception {

        if( path == null) {
            return;
        }

        //删除之前的API
        if( path != null) {
            apiService.removeAll();
            apiDetailsService.removeAll();
        }

        File root = new File(path);
        showAllFiles(root);

        // 属性类型
        HashMap<String, String> typeMap = new HashMap<>();

        //1.添加到Api中
        for(File file : files) {

            Api api = new Api();
            api.setStatus(1);
            api.setAddTime(System.currentTimeMillis());
            api.setModules("app");
            api.setPid(0);
            api.setSort(1);
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "GBK"));
            List<String> list = new ArrayList<>();

            String context = null;

            do{
                context = br.readLine();

                if(context == null) {
                    break;
                }

                if(context.trim().startsWith("@RequestMapping")) {

                    String regex = "\"(\\S*)\"";
                    Pattern pattern = Pattern.compile (regex);
                    Matcher matcher = pattern.matcher(context.trim());
                    if(matcher.find()) {
//                        list.add("方法名称:"+matcher.group(1));
                        api.setUrl(matcher.group(1));
                        api.setApiName(matcher.group(1)+"相关接口");
                        apiService.apiAdd(api);
                        break;
                    }
                }

            } while (context != null);
            br.close();
        }

        //2.生成详情
        List<Map<String,String>> list = new ArrayList<>();

        for(File file : files) {
//            FileReader fl = new FileReader(file);//File name
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);

//             List<String> list = new ArrayList<String>();

//             list.add("类名："+file.getName());

            String baseUri = "";

            Integer flag = 0;

            String context = null;

            StringBuffer sb = null;

            do{
                context = bf.readLine();

                if(context == null) {
                    break;
                }

                if(context.trim().startsWith("*")) {

                    String regex = "@param\\s([A-Za-z]+)\\s(\\S*)";
                    Pattern pattern = Pattern.compile (regex);
                    Matcher matcher = pattern.matcher (context.trim());
                    if(matcher.find()){
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("fields",matcher.group(1)+":"+matcher.group(2));
                        list.add(map);
//                         list.add("字段名称:"+matcher.group(1));
//                         list.add("字段描述:"+matcher.group(2));
                    }

                    if(!context.trim().contains("@param") && !context.trim().contains("@return")) {
                        Pattern p = Pattern.compile("\\*\\s(\\S*)");
                        Matcher m = p.matcher (context.trim());
                        if(m.find()) {
                            Map<String, String> map = new HashMap<>();
                            map.put("methodDesc",m.group(1));
                            list.add(map);
//                             list.add("方法描述："+m.group(1));
                        }

                    }

                } else if(context.trim().startsWith("@RequestMapping")) {

                    String regex = "\"(\\S*)\"";
                    Pattern pattern = Pattern.compile (regex);
                    Matcher matcher = pattern.matcher (context.trim());
                    if(matcher.find()) {
                        if(flag == 0) {
                            flag++;
                            Map<String,String> map = new HashMap<>();
                            map.put("url",matcher.group(1));
                            list.add(map);
                            baseUri = matcher.group(1);
                        } else {
                            Map<String,String> map = new HashMap<>();
                            map.put("url",baseUri+"=="+matcher.group(1));
                            list.add(map);
                        }
//                         list.add("方法名称:"+matcher.group(1));
                    }
                } else if (context.contains("@RequestParam")) {
                    String[] requestParamStrs = context.split(",");
                    for (String requestParamStr : requestParamStrs) {
                        if(requestParamStr.contains("@RequestParam")) {
                            String[] requestParams = requestParamStr.split(" ");
                            if(requestParams.length >= 2) {
                                typeMap.put(requestParams[requestParams.length-1].trim(), requestParams[requestParams.length-2]);
                            }
                        }
                    }
                }

            } while (context != null);

            bf.close();
            isr.close();
            is.close();
        }

        ArrayList<Integer> index = new ArrayList<>();
        index.add(0);
        int lenght = list.size();
        for(int i = 0; i < lenght; i++) {
            Map<String, String> map = list.get(i);
            if(map.containsKey("url")) {
                index.add(i);
            }
        }

        for(int i=0; i < index.size() - 1; i++) {
            ApiDetails apiDetails = new ApiDetails();
            apiDetails.setAddTime(System.currentTimeMillis());
            apiDetails.setStatus(1);
            apiDetails.setResult("");

            Api api = new Api();
            api.setStatus(1);
            api.setAddTime(System.currentTimeMillis());
            api.setModules("app");
            api.setSort(1);


            int start = index.get(i) + 1;
            int end = index.get(1 + i);
            for(; start <= end; start++) {
                if(list.get(start).containsKey("url")) {
                    String[] urls = list.get(start).get("url").split("==");
                    Api rootApi = apiService.findApiByUrl(urls[0]);
                    if(urls.length == 2) {
                        api.setUrl(urls[1]);
                        apiDetails.setUrl(urls[1]);
                        apiDetails.setName(urls[1] + " 接口");
                        api.setApiName(urls[1] + " 接口");
                    }
                    api.setPid(rootApi.getId());
                }

                if(list.get(start).containsKey("methodDesc")) {
                    apiDetails.setDesc(list.get(start).get("methodDesc"));
                }

                if(list.get(start).containsKey("fields")) {
                    String[] temp = list.get(start).get("fields").split(":");
                    Fields fields = new Fields();
                    fields.setIsNeed(1);
                    fields.setName(temp[0]);
                    String type = typeMap.containsKey(temp[0]) ? typeMap.get(temp[0]) : "string";
                    fields.setType(type);
                    if(temp.length == 2){
                        fields.setDesc(temp[1]);
                    }

                    if(null == apiDetails.getFieldsList()) {
                        List<Fields> fieldsList = new ArrayList<>();
                        fieldsList.add(fields);
                        apiDetails.setFieldsList(fieldsList);
                    }else {
                        apiDetails.getFieldsList().add(fields);
                    }
                }

            }

            apiService.apiAdd(api);
            apiDetails.setApiId(api.getId());

            apiDetailsService.apiDetailsAdd(apiDetails);


        }

    }


    @RequestMapping("api-list")
    public String apiList(String modules, Model model) {
        List<Api> apiList = apiService.findAllApiByModules(modules);

        Map<Integer, List<Api>> map = CollectionUtil.buildMultimap(apiList, Integer.class, Api.class, "pid");

        List<Api> resultList = map.get(0);

        for(Api api : resultList){
            if(map.containsKey(api.getId())) {
                api.setChildren(map.get(api.getId()));
            }
        }

        model.addAttribute("apiList", resultList);
        return "api/api-list";
    }


    /**
     * API接口展示首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("api-index")
    public String apiIndex(Model model, HttpServletRequest request) {

        List<Api> apiList = apiService.findAllApi();
        Map<Integer, List<Api>> apiMap = CollectionUtil.buildMultimap(apiList, Integer.class, Api.class, "pid");
        List<Api> resultList = apiMap.get(0);

        for(Api api : resultList) {
            if(apiMap.containsKey(api.getId())) {
                api.setChildren(apiMap.get(api.getId()));
            }
        }

        request.getSession().setAttribute("apiList", resultList);

        return "api/index";
    }

    /**
     * 跳转接口详情
     * @param apiId 接口编号
     * @param model
     * @return
     */
    @RequestMapping("api-details")
    public String apiDetails(@RequestParam("apiId") Integer apiId, @RequestParam("rootUrl") String rootUrl, Model model) {
        model.addAttribute("apiDetails", apiDetailsService.findApiDetailsByApiId(apiId))
             .addAttribute("rootUrl", rootUrl);
        return "api/api-details";
    }


    /**
     * 循环遍历文件夹下所有文件
     * @param dir
     * @throws Exception
     */
    public static final void showAllFiles(File dir) throws Exception {
        File[] fs = dir.listFiles();
        for(int i = 0; i < fs.length; i++) {
            if(fs[i].isDirectory()) {
                try {
                    showAllFiles(fs[i]);
                } catch (Exception e) {}
            } else {
                if(fs[i].getName().contains("Controller")) {
                    files.add(fs[i]);
                }
            }
        }
    }


    /**
     * 官网手表升级包 分页接口
     *
     * @param page 耳机升级包列表
     */
    @RequestMapping("watch/api/GW-list")
    public String watchApiGWList(Page<com.business.core.entity.file.File> page) {
        page.removeEmptys("title", "fileType", "status").convertInt("status", "fileType");
        if (!page.getFilter().containsKey("fileType")) {
            List<Integer> listin=new ArrayList<Integer>();
            listin.add(com.business.core.entity.file.File.FILE_TYPE_WATCH);
            listin.add(com.business.core.entity.file.File.FILE_TYPE_WATCH_TOOL);
            listin.add(com.business.core.entity.file.File.FILE_TYPE_WATCH_FLASH_TOOL);
            page.getFilter().put("fileType",listin);
        }
        fileService.pageApi(page);
        return "file/watch/list";
    }

    /**
     * PC升级工具获取手表版本信息
     */
    @ApiOperation(value = "PC升级工具获取手表版本信息", notes = "PC升级工具获取手表版本信息", position = 2, httpMethod = "POST")
    @RequestMapping("get/watch/version")
    public void getWatchVersion(HttpServletRequest request,Page<com.business.core.entity.file.File> page,String lan) throws IOException {PrintStream printStream=null;
        String encoding="utf-8";
        String path = request.getSession().getServletContext().getRealPath("/");
        String url="";
        StringBuilder stringHtml = null;
        fileService.getWatchVersion(page,lan);
        List<com.business.core.entity.file.File> fileList=page.getResult();
        for(com.business.core.entity.file.File fileInfo:fileList){
            stringHtml=new StringBuilder();
             url=path+"/WEB-INF/pages/upgrade/"+fileInfo.getOther().get("serial")+".jsp";
            //输入HTML文件内容
            stringHtml.append("<html><head>");
            stringHtml.append("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            stringHtml.append("<title>"+fileInfo.getOther().get("serial")+"</title>");
            stringHtml.append("<style>\n" + "::-webkit-scrollbar {\n" + "width: 12px;\n" + "height: 12px;\n" + "}\n" + "::-webkit-scrollbar-thumb, ::-webkit-scrollbar-thumb:horizontal {\n" + "border-radius: 6px;\n" + "background: #939393;\n" + "}\n" + "::-webkit-scrollbar-button {\n" + "display: none;\n" + "}\n" + "::-webkit-scrollbar-track, ::-webkit-scrollbar-track-piece {\n" + "background: -webkit-gradient(linear,left);\n" + "}\n" + "::-webkit-scrollbar-track-piece {\n" + "border-radius: 6px;\n" + "background: #1C1C1F;\n" + "}\n" + "body {\n" + "scrollbar-face-color: #939393;\n" + "scrollbar-highlight-color: #939393;\n" + "scrollbar-shadow-color: #939393;\n" + "scrollbar-3dlight-color: #939393;\n" + "scrollbar-arrow-color: #240024;\n" + "scrollbar-track-color: #1C1C1F;\n" + "scrollbar-darkshadow-color: #939393;\n" + "scrollbar-base-color: #939393\n" + "}\n" + "</style>");
            stringHtml.append("</head>");
            stringHtml.append("<body style=\'background-color: #2C2D30;color:  #91939D;\';>");
            stringHtml.append("<div>" + fileInfo.getDes() + "</div>");
            stringHtml.append("</body></html>");
            try {
                File file=new File(url);
                if(!file.exists()) {
                    file.createNewFile();
                }
                //打开文件
                printStream = new PrintStream(new FileOutputStream(file),true,encoding);
                //将HTML文件内容写入文件中
                printStream.println(stringHtml.toString());
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            //手表升级包备注信息页面地址
            fileInfo.setFileDesUrl(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/api/watch/des.htm?serial="+fileInfo.getOther().get("serial"));
            //手表升级包下载地址
            fileInfo.setFileLink("http://yyssb.ifitmix.com/"+fileInfo.getFileLink());
            printStream.flush();
            printStream.close();
        }

    }

    /**
     * 访问版本信息备注静态页面（Apollo）
     */
    @RequestMapping("watch/des")
    public String watchDes(String serial)  {
        return "upgrade/"+serial+"";
    }



}
