package com.business.work.channelApp;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.DicConstants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.Page;
import com.business.core.entity.channel.ChannelApp;
import com.business.core.entity.channel.ChannelAppAnalysis;
import com.business.core.entity.channel.ChannelUserStat;
import com.business.core.entity.user.User;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.*;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.dic.DictionaryService;
import com.google.common.collect.ImmutableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/5/9.
 */
@Controller
@RequestMapping("channel-app")
public class ChannelAppController extends BaseControllerSupport {

    @Autowired
    private ChannelAppService channelAppService;

    /**
     * 跳转ChannelApp 列表
     * @param page
     * @return
     */
    @RequestMapping(value = "channel-app-list", method = RequestMethod.GET)
    public String channelAppList(Page<ChannelApp> page){
        page.convertInt("channelId");
        channelAppService.list(page);
        return "channelApp/channel-app-list";
    }

    /**
     * 跳转channelAPP添加页面
     * @return
     */
    @RequestMapping(value = "channel-app-add", method = RequestMethod.GET)
    public String add(Model model){
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        model.addAttribute("dictionaryList", dictionaryList);
        return "channelApp/channel-app-add";
    }

    /**
     * 绑定渠道APP信息
     * @param channelApp
     * @param model
     */
    @RequestMapping(value = "channel-app-add", method = RequestMethod.POST)
    public void add(ChannelApp channelApp, Model model) throws Exception{
        Object[] result = channelAppService.channelAppAdd(channelApp);
        switch ((Integer) result[0]) {
            case 0:
                model.addAttribute("channelApp", result[1]);
                break;
        }
    }

    /**
     * 跳转channelApp 修改信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "channel-app-modify", method = RequestMethod.GET)
    public String channelAppModify(@RequestParam("id") Long id, Model model){
        ChannelApp channelApp = channelAppService.findChannelAppById(id);
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        model.addAttribute("dictionaryList", dictionaryList)
             .addAttribute("channelApp", channelApp);
        return "channelApp/channel-app-modify";
    }


    /**
     * 上传Android 包
     * @param id channelID
     * @param part APK分割下标
     * @param size APK大小
     * @param name APK名称
     * @param partSize 分割大小
     * @param file APK文件
     */
    @RequestMapping(value = "channel-app-apk-modify",method = RequestMethod.POST)
    public void videoFileModify(@RequestParam("id") Long id,
                                @RequestParam("part") Long part,
                                @RequestParam("size") Long size,
                                @RequestParam("name") String name,
                                @RequestParam("partSize") Long partSize,
                                @RequestParam("file") MultipartFile file){
        String fileUrl = AliyunCenterClient.multipartFile(FileConstants.FILE_TYPE_ANDROID_APK.toString(), file, name, size, partSize, part);
        if (partSize * part >= size) {
            channelAppService.channelAppModifyApk(id, fileUrl);
        }

    }

    /**
     * 修改channelApp 基本信息
     * @param channelApp
     * @param model
     */
    @RequestMapping(value = "channel-app-modify", method = RequestMethod.POST)
    public void channelAppModify(ChannelApp channelApp, Model model) {
        channelAppService.channelAppModify(channelApp);
    }

    /**
     * 修改channelApp状态
     * @param id channelApp id
     * @param status channelApp 状态
     */
    @RequestMapping(value = "channel-app-modify-status",method = RequestMethod.POST)
    public void channelAppModifyStatus(@RequestParam("id") Long id,
                                  @RequestParam("status") Integer status){
        channelAppService.channelAppModifyStatus(id,status);
    }

    /**
     * 根据ID 删除渠道绑定信息
     * @param id ID
     */
    @RequestMapping(value = "channel-app-remove",method = RequestMethod.POST)
    public void channelAppRemove(@RequestParam("id") Integer id){
        channelAppService.channelAppRemove(id);
    }

    /**
     * 统计列表
     * @param page 分页信息
     * @return
     */
    @RequestMapping(value = "channel-analysis-list")
    public String getChannelAnalysisList(Page<ChannelAppAnalysis> page, Model model){
        page.convertInt("channelId", "type");
        channelAppService.getChannelAnalysisList(page);
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        model.addAttribute("dictionaryList", dictionaryList);

        //type = 0 跳转 访问统计 type = 1 跳转 下载统计
        if(Integer.parseInt(page.getFilter().get("type").toString()) == 0) {
            return "channelApp/channel-analysis-list";
        } else {
            return "channelApp/channel-download-list";
        }
    }

    /**
     * 新用户统计 用户 注册渠道、运动、下载音乐，信息统计
     */
    @RequestMapping("channel-user-stat-list")
    public String newUserStat(Model model, String bTime, String eTime, Integer channelId) {
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        model.addAttribute("dictionaryList", dictionaryList);

        if (StringUtils.isEmpty(bTime) || StringUtils.isEmpty(eTime) || StringUtils.isEmpty(channelId)) {
            return "channelApp/channel-user-stat-list";
        }
        model.addAttribute("bTime", bTime);
        model.addAttribute("eTime", eTime);
        model.addAttribute("channelId", channelId);
        model.addAttribute("channelUserStats", channelAppService.newUserStat(bTime, eTime, channelId));
        return "channelApp/channel-user-stat-list";
    }

    /**
     * 新用户统计 用户 注册渠道、运动、下载音乐，信息统计 导出
     */
    @RequestMapping("channel-user-stat-list-export")
    public void newUserStatExport(HttpServletResponse response, String bTime, String eTime, Integer channelId) {

        List<List<String>> values = new ArrayList<>();

        ImmutableList<Integer> columnWidths = ImmutableList.of(3000, 3000, 3000, 4000);
        ImmutableList<String> columnNames = ImmutableList.of("注册日期", "注册用户数", "运动用户数", "下载用户数(歌曲)");

        if (StringUtils.isEmpty(bTime) || StringUtils.isEmpty(eTime) || StringUtils.isEmpty(channelId)) {
        } else {
            List<ChannelUserStat> channelUserStats = channelAppService.newUserStat(bTime, eTime, channelId);
            for (ChannelUserStat channelUserStat : channelUserStats) {
                List<String> cells = new ArrayList<>();
                cells.add(channelUserStat.getStatDay().toString());
                cells.add(channelUserStat.getRegisterNum().toString());
                cells.add(channelUserStat.getRunNum().toString());
                cells.add(channelUserStat.getDownloadMusicNum().toString());
                values.add(cells);
            }
        }

        HSSFWorkbook book = OfficeUtil.buildExcel("渠道用户行为统计", columnWidths, columnNames, values);
        CommonUtil.downLoadExcel(response, "渠道用户行为统计", book);
    }


}
