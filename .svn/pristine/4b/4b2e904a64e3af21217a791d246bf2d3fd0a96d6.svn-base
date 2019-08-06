package com.business.ugc.content.mix;

import com.business.core.constants.CodeConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAlbum;
import com.business.ugc.base.QxMap;
import com.business.ugc.base.support.BaseControllerSupport;
import com.business.ugc.base.view.WebModel;
import com.business.ugc.content.albums.MixAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by fenxio on 2016/8/30.
 * 节目
 */
@Controller
@RequestMapping("ugc/content")
public class MixController extends BaseControllerSupport {

    @Autowired
    private MixService mixService;
    @Autowired
    private MixAlbumsService mixAlbumsService;

    /**
     * 节目上传 页面
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/mix/upload", method = RequestMethod.GET)
    public String upload(@PathVariable("uid") Long uid,
                         @PathVariable("albumsId") Integer albumsId) {
        return "content/upload";
    }

    /**
     * 跳转节目列表
     * @param uid
     * @param albumsId
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/programs", method = RequestMethod.GET)
    public String programs(@PathVariable("uid") Integer uid, @PathVariable("albumsId") Integer albumsId, Model model) {
        MixAlbum mixAlbum = mixAlbumsService.findById(albumsId);
        model.addAttribute("mixAlbum", mixAlbum);
        return "content/programs";
    }

    /**
     * 获取节目列表
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/mix", method = RequestMethod.GET)
    @ResponseBody
    public WebModel programs(@PathVariable("uid") Integer uid,
                             @PathVariable("albumsId") Integer albumsId,
                             Page<Mix> page) {
        page.getFilter().put("uid", uid);
        page.getFilter().put("mixAlbumsId", albumsId);
        mixService.findByPage(page, new QxMap<>("addTime", Sort.Direction.DESC));
        return new WebModel(CodeConstants.SUCCESS, page);
    }

    /**
     * 上传Mix
     *
     * @param uid
     * @param albumsId
     * @param file
     * @param mix
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/mix", method = RequestMethod.POST)
    @ResponseBody
    public WebModel createMix(@PathVariable("uid") Integer uid,
                              @PathVariable("albumsId") Integer albumsId,
                              @RequestParam("file") MultipartFile file,
                              Mix mix) {
        mix.setUid(uid);
        mix.setMixAlbumsId(albumsId);
//        String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_AUDIO_MIX);
//        mix.setUrl(path);
        String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        mix.setName(name);
        mix.setAddTime(System.currentTimeMillis());
        mixService.insert(mix);
        return new WebModel(CodeConstants.SUCCESS, mix);
    }

    /**
     * 删除 mix
     * @param uid
     * @param albumsId
     * @param mixId
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/mix/{mixId}", method = RequestMethod.DELETE)
    @ResponseBody
    public WebModel delete(@PathVariable("uid") Integer uid,
                           @PathVariable("albumsId") Integer albumsId,
                           @PathVariable("mixId") Integer mixId) {
        mixService.deleteByMap(new QxMap<String, Object>("_id", mixId).add("uid", uid).add("mixAlbumsId", albumsId));
        return new WebModel(CodeConstants.SUCCESS, null);
    }

    /**
     * 更新mix
     * @param uid
     * @param albumsId
     * @param mixId
     * @param mix
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}/mix/{mixId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseBody
    public WebModel update(@PathVariable("uid") Integer uid,
                           @PathVariable("albumsId") Integer albumsId,
                           @PathVariable("mixId") Integer mixId,
                           Mix mix) {
        mix.setId(mixId);
        mixService.updateByIdSelective(mix);
        return new WebModel(CodeConstants.SUCCESS, mix);
    }

}
