package com.business.work.mix;

import com.alibaba.fastjson.JSON;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.RunMix;
import com.business.core.operations.mixAuthor.MixAuthorCoreService;
import com.business.core.utils.ImageUtil;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 15/4/13.
 */
@Controller
@RequestMapping()
public class MixController extends BaseControllerSupport {

    @Autowired
    private MixService mixService;
    @Autowired
    private MixAuthorCoreService mixAuthorCoreService;

    /**
     * Mix 分页
     * @param page 分页
     */
    @RequestMapping("/mix/mix-list")
    public String mixList(Page<Mix> page) {
        page.convertInt("id", "trackLength", "state", "type");
        page.convertCollInt("scene", "genre");
        mixService.list(page);
        return "mix/mix-list";
    }

    /**
     * Mix 添加页面
     */
    @RequestMapping(value = "/mix/mix-add", method = RequestMethod.GET)
    public String mixAdd(Model model) {
        model.addAttribute("mixAuthorList", mixAuthorCoreService.findAllMixAuthor());
        return "mix/mix-add";
    }



    /**
     * Mix 添加
     * <p>
     *     此 add 不做文件处理。
     *     modify 中统一做
     * </p>
     * @param mix 歌曲信息
     * @param mixMusicStr 歌曲配置
     * @throws IOException
     */
    @RequestMapping(value = "/mix/mix-add",method = RequestMethod.POST)
    public void add(Model model, Mix mix,
                    @RequestParam("mixMusicStr") String mixMusicStr) throws IOException {
        // 设置 uploadEntity
        mix.setMixMusics(JSON.<List<Map<String, Object>>>parseObject(mixMusicStr, Object.class));
        Object[] result = mixService.mixAdd(mix);
        switch ((int)result[0]) {
            case 0:
                model.addAttribute("mix", result[1]);
                break;
            case 1:
                tip(model, 5001, "标识重复");
                break;
        }
    }

    /**
     * Mix 添加进度
     * @param model
     */
    @RequestMapping("/mix/mix-rate")
    @ResponseBody
    public String addRate(Model model, HttpServletRequest request) {
        return JSON.toJSONString(request.getSession().getAttribute("uploadEntity"));
    }

    /**
     * mix 详情
     * @param id 编号
     */
    @RequestMapping("/mix/mix-info")
    public String mixInfo(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("mix", mixService.mixInfo(id));
        return "mix/mix-info";
    }

    /**
     * Mix 跳转更新界面
     */
    @RequestMapping(value = "/mix/mix-modify", method = RequestMethod.GET)
    public String mixModify(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("mix", mixService.mixInfo(id));
        model.addAttribute("mixAuthorList", mixAuthorCoreService.findAllMixAuthor());
        return "mix/mix-modify";
    }


    /**
     * ajax 选中值动态加载歌曲名称和简介
     */
    @RequestMapping(value = "/mix/mix-modify-ajax", method = RequestMethod.GET)
    public String mixModifyAjax(Model model, @RequestParam("id") Integer id,@RequestParam("lan") String lan) {
        model.addAttribute("mix", mixService.mixInfoLan(id,lan));
        model.addAttribute("mixAuthorList", mixAuthorCoreService.findAllMixAuthor());
        return "mix/mix-modify";
    }

    /**
     * Mix 更新
     * @param mix 对象
     */
    @RequestMapping("/mix/mix-modify")
    public void mixModify(Model model, Mix mix,
                          @RequestParam(value = "mixMusicStr", required = false) String mixMusicStr) {
        mix.setMixMusics(JSON.<List<Map<String, Object>>>parseObject(mixMusicStr, Object.class));
        int result = mixService.mixModify(mix);
        switch (result) {
            case 1:
                tip(model, CodeConstants.MIX_IDENTIFICATION_REPEAT, "自定义标识,重复");
                break;
        }
    }

    /**
     * 修改歌曲上下架状态
     * <p>
     *     返回信息
     *      0、成功  1、没有歌曲文件
     * </p>
     * @param id 歌曲编号
     * @param state 歌曲状态
     */
    @RequestMapping("/mix/mix-modify-state")
    public void mixModifyState(Model model,
                               @RequestParam("id") Integer id,
                               @RequestParam("state") Integer state) {
        int result = mixService.mixModifyState(id, state);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.MIX_FILE_NOT_EXIST, "歌曲文件不存在");
            break;
        }
    }

    /**
     * 修改歌曲排序顺序
     * @param id 歌曲编号
     * @param sort 排序
     */
    @RequestMapping("/mix/mix-sort")
    public void mixSort (@RequestParam(value = "id", required = true) Integer id,
                         @RequestParam(value = "sort", required = true) Integer sort) {
        mixService.mixSort(id, sort);
    }

    /**
     * 修改 mix 歌曲图片信息
     * @param mid 歌曲编号
     * @param image mix 歌曲图片
     * @throws java.io.IOException
     */
    @RequestMapping("/mix/mix-image-modify")
    public void mixImageModify (@RequestParam("mid") Integer mid, @RequestParam("image") MultipartFile image) throws IOException {
        byte[] albumByte = ImageUtil.compress(
                ImageUtil.resize(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION[0], FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION[1]),
                ImageUtil.buildSuffix(image.getOriginalFilename()));
//        byte[] albumByte_2 = ImageUtil.compress(ImageUtil.resize(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION2[0], FileConstants.FILE_TYPE_AUDIO_MIX_ALBUM_RESOLUTION2[1]));
        String albumUrl = FileCenterClient.upload(albumByte, FileConstants.FILE_TYPE_AUDIO_MIX_IMAGE, image.getOriginalFilename()); // 上传 album
        String albumUrl_2 = FileCenterClient.upload(image.getBytes(), FileConstants.FILE_TYPE_AUDIO_MIX_IMAGE, image.getOriginalFilename()); // 上传 album
        mixService.mixImageModify(mid, albumUrl, albumUrl_2);
    }

    /**
     * 修改 mix 歌曲文件信息
     * @param mid 歌曲编号
     * @param file 文件信息
     * @throws java.io.IOException
     */
    @RequestMapping("/mix/mix-file-modify")
    public void mixFileModify (@RequestParam("mid") Integer mid,
                               @RequestParam("part") Long part,
                               @RequestParam("size") Long size,
                               @RequestParam("name") String name,
                               @RequestParam("partSize") Long partSize,
                               @RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
//        String fileUrl = FileCenterClient.upload(file, FileConstants.FILE_TYPE_AUDIO_MIX); // 上传 mix
        //FileConstants.FILE_TYPE_AUDIO_MIX.toString()
        String fileUrl = AliyunCenterClient.multipartFile(FileConstants.FILE_TYPE_AUDIO_MIX.toString(), file, name, size, partSize, part);
        if (partSize * part >= size) {
            mixService.mixFileModify(mid, fileUrl);
        }
    }

    /**
     * 修改 mix 歌曲文件信息 进度
     * @param request 请求
     * @return 返回上传文件进度信息
     */
    @RequestMapping("/mix/mix-file-modify-rate")
    @ResponseBody
    public String mixFileModifyRate(HttpServletRequest request) {
        return JSON.toJSONString(request.getSession().getAttribute("uploadEntity"));
    }

    /**
     * mix 歌曲删除
     * @param mid 音乐编号
     */
    @RequestMapping("/mix/mix-remove")
    public void mixRemove (@RequestParam("mid") Integer mid) {
        mixService.mixRemove(mid);
    }

    /**
     * 清楚所有 Mix 排序
     */
    @RequestMapping("/mix/clear-sort")
    public void clearSort () {
        mixService.clearSort();
    }

    /**
     * Mix 推荐管理
     */
    @RequestMapping("/mix/recommend-manager")
    public String recommendManager(Model model) {
        model.addAttribute("mixs", mixService.recommendManager());
        return "mix/mix-recommend";
    }

    /**
     * 添加推荐
     *
     * @param mid mix编号
     * @param bTime 开始时间
     * @param eTime 结束时间
     */
    @RequestMapping("/mix/add-recommend")
    public void addRecommend(Integer mid, String bTime, String eTime) {
        mixService.addRecommend(mid, bTime, eTime);
    }

    /**
     * 设置偏移量
     *
     * @param id 编号
     * @param type 类型
     * @param offsetNum 偏移值
     */
    @RequestMapping("/mix/set/offset")
    public void setOffset(Integer id, Integer type, Integer offsetNum) {
        mixService.setOffset(id, type, offsetNum);
    }

    //==================================新版音乐 2018/01/29 begin ==========================================

    /**
     * 运动音乐管理
     */
    @RequestMapping("/run/mix/page")
    public String runMixPage(Page<RunMix> page) {
        page.removeEmptys("id", "duration", "title", "bBpm", "eBpm").convertLong("id").convertLong("duration").convertInt("bBpm", "eBpm");
        mixService.runMixPage(page);
        return "mix/new/page";
    }

    /**
     * 运动音乐添加
     *
     * @param runMix 运动音乐
     */
    @RequestMapping(value = "/run/mix/save", method = RequestMethod.POST)
    public void runMixAdd(RunMix runMix) {

        runMix.setEmotion(RunMix.COLOR_TAG_MAP.get(runMix.getColorTag()));

        runMix.setStatus(Constants.STATUS_YES);
        runMix.setAddTime(System.currentTimeMillis());
        runMix.setRandomVal(0L);
        runMix.setWatch(Constants.SWITCH_CLOSE);
        mixService.runMixAdd(runMix);
    }

    @RequestMapping("/run/mix/info")
    public String runMixInfo(Model model, Long id) {
        RunMix runMix = mixService.runMixFind(id);
        runMix.setImgLink(FileCenterClient.buildUrl(runMix.getImgLink()));
        runMix.setLink(FileCenterClient.buildUrl(runMix.getLink()));
        model.addAttribute("runMix", runMix);
        return "mix/new/info";
    }

    @RequestMapping("/run/mix/img/link/modify")
    public void  modifyRunMixImgLink(Long id, @RequestParam("uploadFile") MultipartFile uploadFile) {
        String imgLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RUN_MIX_IMG_LINK, uploadFile);
        mixService.runMixModify(id, Update.update("imgLink", imgLink));
//        return redirect("run/mix/info.htm?id="+id);
    }

    @RequestMapping("/run/mix/link/modify")
    public void modifyRunMixLink(Long id, @RequestParam("uploadFile") MultipartFile uploadFile) {
        String link = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RUN_MIX_LINK, uploadFile);
        mixService.runMixModify(id, Update.update("link", link));
    }

    @RequestMapping("/run/mix/modify/title")
    public void runMixModifyTitle(Long id, String title) {
        mixService.runMixModify(id, Update.update("title", title));
    }

    @RequestMapping("/run/mix/modify/des")
    public void runMixModifyDes(Long id, String des) {
        mixService.runMixModify(id, Update.update("des", des));
    }

    @RequestMapping("/run/mix/modify/bpm")
    public void runMixModifyBpm(Long id, Integer bpm, Integer bpmIsDouble) {
        mixService.runMixModify(id, Update.update("bpm", bpm).set("bpmIsDouble", bpmIsDouble));
    }

    @RequestMapping("/run/mix/modify/duration")
    public void runMixModifyDuration(Long id, Integer duration) {
        mixService.runMixModify(id, Update.update("duration", duration));
    }

    @RequestMapping("/run/mix/modify/status")
    public void runMixModifyStatus(Long id, Integer status) {
        mixService.runMixModify(id, Update.update("status", status));
    }

    @RequestMapping("/run/mix/modify/watch")
    public void runMixModifyWatch(Long id, Integer watch) {
        mixService.runMixModify(id, Update.update("watch", watch));
    }

    @RequestMapping("/run/mix/modify/colorTag")
    public void runMixModifyColorTag(Long id, String colorTag) {
        mixService.runMixModify(id, Update.update("colorTag", colorTag).set("emotion", RunMix.COLOR_TAG_MAP.get(colorTag)));
    }

    @RequestMapping("/run/mix/modify/energyLevel")
    public void runMixModifyEnergyLevel(Long id, Integer energyLevel) {
        mixService.runMixModify(id, Update.update("energyLevel", energyLevel));
    }

    @RequestMapping("/run/mix/modify/songStyle")
    public void runMixModifySongStyle(Long id, Integer songStyle) {
        mixService.runMixModify(id, Update.update("songStyle", songStyle));
    }

    @RequestMapping("/get/song/list")
    public String getSongList(Model model, Integer bpm, Long duration, Integer emotion) {
        if (StringUtils.isEmpty(bpm) || StringUtils.isEmpty(duration) || StringUtils.isEmpty(emotion)) {
            model.addAttribute("message", "bpm/能量级别/时长 为空");
            return "/mix/new/share";
        }
        List<RunMix> runMixList = mixService.getSongList(bpm, duration, emotion);
        if (CollectionUtils.isEmpty(runMixList)) {
            model.addAttribute("message", "生成失败，曲库数量不足");
            return "/mix/new/share";
        }
        mixService.updateRandomVal(runMixList);
        model.addAttribute("list", runMixList);
        return "/mix/new/share";
    }

    @RequestMapping("/run/mix/remove")
    public void remove() {
        mixService.remove();
    }

}
