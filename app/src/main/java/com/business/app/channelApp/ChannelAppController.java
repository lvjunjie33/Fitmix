package com.business.app.channelApp;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.channel.BurnFatFighting;
import com.business.core.entity.channel.ChannelApp;
import com.business.core.entity.channel.ChannelAppDownload;
import com.business.core.entity.channel.ChannelAppStatistics;
import com.business.core.entity.file.File;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.schedulerValue.SchedulerValueCoreService;
import com.business.core.utils.BeanManager;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.JSSdkUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by fenxio on 2016/5/10.
 */
@Api(value = "channel-app", description = "app 渠道")
@Controller
@RequestMapping("channel-app")
public class ChannelAppController {

    @Autowired
    private ChannelAppService channelAppService;

    /**
     * 跳转 燃脂战场页面
     * @param model
     * @return
     */
    @RequestMapping("burn-fat-fighting")
    public String burnFatFighting(Model model, HttpServletRequest request) {
        //1.统计点击数
        ChannelAppStatistics channelAppStatistics = new ChannelAppStatistics();
        channelAppStatistics.setAddTime(System.currentTimeMillis());
        channelAppStatistics.setChannelId(57);
        channelAppStatistics.setIp(HttpUtil.getIP(request));
        channelAppStatistics.setOpenStatus(ChannelAppStatistics.OPENSTATUS_SUCCESS);
        channelAppService.channelAppStatisticsAdd(channelAppStatistics);

        model.addAttribute("easy", channelAppService.getCountGroupByType(BurnFatFighting.TYPE_EASY));
        model.addAttribute("middle", channelAppService.getCountGroupByType(BurnFatFighting.TYPE_MIDDLE));
        model.addAttribute("hard", channelAppService.getCountGroupByType(BurnFatFighting.TYPE_HARD));
        model.addAttribute("random", UUID.randomUUID().toString());
        return "channelApp/burn-fat-fighting";
    }

    /**
     * 分享页面 签名 统计选项
     * @param url
     * @param model
     */
    @RequestMapping("get-signature")
    public void getSignature(@RequestParam("url") String url,
                             BurnFatFighting burnFatFighting,
                             HttpServletRequest request,
                             Model model) {
        String jsapiTicket = RedisConstants.getJsapiTicket();
//        String jsapiTicket = "kgt8ON7yVITDhtdwci0qeY58QbSL2ik8NVXzop0hbwgByq_qnMX7W06mgO13-0lEg_Nb1AXawKl4l0_aw2RuLQ";
        burnFatFighting.setIp(HttpUtil.getIP(request));
        channelAppService.addBurnFatFighting(burnFatFighting);
        Map<String, String> resultMap = JSSdkUtil.sign(jsapiTicket, url);
        model.addAttribute("appid", Constants.WX_APPID);
        model.addAttribute("url", resultMap.get("url"));
        model.addAttribute("nonceStr", resultMap.get("nonceStr"));
        model.addAttribute("timestamp", resultMap.get("timestamp"));
        model.addAttribute("signature", resultMap.get("signature"));
    }


    /**
     * 推广页
     */
    @RequestMapping("generalize")
    public String generalize(@RequestParam("channelId") Integer channelId, HttpServletRequest request, Model model) {

        ChannelApp channelApp = channelAppService.findChannelAppByChannelId(channelId);

        //1.统计点击数
        ChannelAppStatistics channelAppStatistics = new ChannelAppStatistics();
        channelAppStatistics.setAddTime(System.currentTimeMillis());
        channelAppStatistics.setChannelId(channelId);
        channelAppStatistics.setIp(HttpUtil.getIP(request));
        channelAppStatistics.setOpenStatus(ChannelAppStatistics.OPENSTATUS_FAIL);
        channelAppService.channelAppStatisticsAdd(channelAppStatistics);

        model.addAttribute("channelApp", channelApp)
                .addAttribute("channelAppStatistics", channelAppStatistics);
        if (channelId == 55) {
            return "channelApp/generalize";
        } else if (channelId == 56) {
            return "channelApp/beats-generalize";
        }
        return "channelApp/beats-generalize";
    }


    /**
     * APP下载页面
     *
     * @param channelId 渠道编号
     */
    @ApiOperation(value = "download", notes = "APP下载页面", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("download")
    public String download(@ApiIgnore Model model, HttpServletRequest request,
                           @ApiParam(required = true, value = "渠道编号") @RequestParam("channelId") Integer channelId) {
        ChannelApp channelApp = channelAppService.findChannelAppByChannelId(channelId);

        //1.统计点击数
        ChannelAppStatistics channelAppStatistics = new ChannelAppStatistics();
        channelAppStatistics.setAddTime(System.currentTimeMillis());
        channelAppStatistics.setChannelId(channelId);
        channelAppStatistics.setIp(HttpUtil.getIP(request));
        channelAppStatistics.setOpenStatus(ChannelAppStatistics.OPENSTATUS_FAIL);
        channelAppService.channelAppStatisticsAdd(channelAppStatistics);

        model.addAttribute("channelApp", channelApp)
                .addAttribute("channelAppStatistics", channelAppStatistics);
        return "channelApp/download";
    }


    /**
     * 下载统计
     *
     * @param channelId 渠道ID
     * @param type      类型
     */
    @ApiOperation(value = "download-statistics", notes = "下载统计", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "download-statistics", method = RequestMethod.POST)
    public void downloadStatistics(@ApiParam(required = true, value = "渠道编号") @RequestParam("channelId") Integer channelId,
                                   @ApiParam(required = true, value = "类型") @RequestParam("type") String type, HttpServletRequest request) {
        ChannelAppDownload channelAppDownload = new ChannelAppDownload();
        channelAppDownload.setIp(HttpUtil.getIP(request));
        channelAppDownload.setChannelId(channelId);
        channelAppDownload.setStatus(0);
        channelAppDownload.setType(type);
        channelAppDownload.setAddTime(System.currentTimeMillis());
        channelAppService.channelAppDownloadAdd(channelAppDownload);
    }


    /**
     * 统计成功打开页面
     *
     * @param id 渠道编号
     */
    @ApiOperation(value = "modify-open-status", notes = "统计成功打开页面", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "modify-open-status", method = RequestMethod.POST)
    public void modifyOpenStatus(@ApiParam(required = true, value = "渠道编号") @RequestParam("id") Integer id) {
        channelAppService.modifyOpenStatus(id, ChannelAppStatistics.OPENSTATUS_SUCCESS);
    }

    /**
     * Apk下载页面
     */
    @RequestMapping("apk-download")
    public String apkDownload(Model model) {
        File file = BeanManager.getBean(DefaultDao.class).findById(File.class, 5, "fileLink");
        model.addAttribute("fileLink", FileCenterClient.buildUrl(file.getFileLink()));
        return "channelApp/apkDownLoad";
    }
}
