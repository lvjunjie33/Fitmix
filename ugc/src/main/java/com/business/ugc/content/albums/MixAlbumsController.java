package com.business.ugc.content.albums;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.MixAlbum;
import com.business.ugc.base.QxMap;
import com.business.ugc.base.support.BaseControllerSupport;
import com.business.ugc.base.view.WebModel;
import com.business.ugc.content.mix.MixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/8/29.
 * 专辑
 */
@Controller
@RequestMapping("ugc/content")
public class MixAlbumsController extends BaseControllerSupport {

    @Autowired
    private MixAlbumsService mixAlbumsService;
    @Autowired
    private MixService mixService;

    /**
     * 专辑列表
     *
     * @param uid 用户编号
     * @return
     */
    @RequestMapping(value = "{uid}/albums", method = RequestMethod.GET)
    public String albums(@PathVariable("uid") Integer uid, Page<MixAlbum> page, Model model) {
        page.getFilter().put("uid", uid);
        mixAlbumsService.findByPage(page, new QxMap<String, Sort.Direction>().add("addTime", Sort.Direction.DESC));
        model.addAttribute("page", page);
        return "content/albums";
    }

    /**
     * 跳转专辑创建页面
     * @param uid
     * @return
     */
    @RequestMapping(value = "{uid}/albums/add", method = RequestMethod.GET)
    public String create(@PathVariable("uid") Integer uid) {
        return "content/albumsAdd";
    }

    /**
     * 改变当前专辑
     * @param uid
     * @return
     */
    @RequestMapping(value = "{uid}/albums/change", method = RequestMethod.POST)
    @ResponseBody
    public WebModel change(@PathVariable("uid") Integer uid, @RequestParam("albumsId") Integer albumsId, HttpServletRequest request) {
        MixAlbum mixAlbum = mixAlbumsService.findById(albumsId);
        if(mixAlbum != null) {
            request.getSession().setAttribute(Constants.CURRENT_MIX_ALBUMS, mixAlbum);
            return new WebModel(CodeConstants.SUCCESS, null);
        } else {
            return new WebModel(CodeConstants.MISS, null, "专辑不存在");
        }
    }

    /**
     * 创建专辑
     *
     * @param uid
     * @param mixAlbum
     * @return
     */
    @RequestMapping(value = "{uid}/albums", method = RequestMethod.POST)
    @ResponseBody
    public WebModel albums(@PathVariable("uid") Integer uid,
                           @RequestParam(value = "backImageFile") String backImageFile,
                           @RequestParam(value = "backImageName") String backImageName,
                           MixAlbum mixAlbum, HttpServletRequest request) {
        mixAlbum.setUid(uid);
        mixAlbum.setAddTime(System.currentTimeMillis());
        mixAlbum.setCheckStatus(Constants.CHECK_STATUS_READY);
        mixAlbum.setType(MixAlbum.TYPE_USER_CREATE);
        mixAlbum.setStatus(MixAlbum.STATUS_NOT_RELEASE);
        //封面
        backImageFile = backImageFile.split(",")[1];
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(backImageFile);
            String path = FileCenterClient.upload(decodedBytes, FileConstants.FILE_TYPE_MIX_ALBUM_IMAGE, backImageName);
            mixAlbum.setBackImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mixAlbumsService.insert(mixAlbum);
        List<MixAlbum> list = (List<MixAlbum>) request.getSession().getAttribute(Constants.MIX_ALBUM_LIST);
        if(null == list) {
            list = new ArrayList<>();
        }
        list.add(mixAlbum);
        request.getSession().setAttribute(Constants.MIX_ALBUM_LIST, list);
        request.getSession().setAttribute(Constants.CURRENT_MIX_ALBUMS, mixAlbum);
        return new WebModel(CodeConstants.SUCCESS, mixAlbum);
    }

    /**
     * 修改专辑
     *
     * @param uid
     * @param mixAlbum
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    @ResponseBody
    public WebModel updateAlbums(@PathVariable("uid") Integer uid,
                                 @PathVariable("albumsId") Integer albumsId,
                                 @RequestParam(value = "backImageFile", required = false) String backImageFile,
                                 @RequestParam(value = "backImageName", required = false) String backImageName,
                                 MixAlbum mixAlbum,
                                 HttpServletRequest request) {
        //封面
        if (null != backImageFile && null != backImageName && !backImageFile.trim().equals("")) {
            backImageFile = backImageFile.split(",")[1];
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] decodedBytes = decoder.decodeBuffer(backImageFile);
                String path = FileCenterClient.upload(decodedBytes, FileConstants.FILE_TYPE_MIX_ALBUM_IMAGE, backImageName);
                mixAlbum.setBackImage(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mixAlbum.setId(albumsId);
        mixAlbumsService.updateByIdSelective(mixAlbum);
        List<MixAlbum> list = (List<MixAlbum>) request.getSession().getAttribute(Constants.MIX_ALBUM_LIST);
        for(MixAlbum each : list){
            if(each.getId() == mixAlbum.getId()) {
                if(mixAlbum.getBackImage() != null) {
                    each.setBackImage(mixAlbum.getBackImage());
                }
                each.setTitle(mixAlbum.getTitle());
                each.setDesc(mixAlbum.getDesc());
            }
        }
        return new WebModel(CodeConstants.SUCCESS, mixAlbum);
    }

    /**
     * 删除专辑信息
     * @param uid
     * @param albumsId
     * @param request
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}", method = RequestMethod.DELETE)
    @ResponseBody
    public WebModel delete(@PathVariable("uid") Integer uid, @PathVariable("albumsId") Integer albumsId, HttpServletRequest request) {
        mixAlbumsService.deleteById(albumsId);
        //删除专辑下 mix
        mixService.deleteByMap(new QxMap<String, Object>("uid", uid).add("mixAlbumsId", albumsId));

        List<MixAlbum> list = (List<MixAlbum>) request.getSession().getAttribute(Constants.MIX_ALBUM_LIST);
        for(MixAlbum mixAlbum : list) {
            if(mixAlbum.getId() == albumsId) {
                list.remove(mixAlbum);
                break;
            }
        }
        request.getSession().setAttribute(Constants.MIX_ALBUM_LIST, list);
        MixAlbum currentMixAlbum = (MixAlbum) request.getSession().getAttribute(Constants.CURRENT_MIX_ALBUMS);
        if(currentMixAlbum.getId() == albumsId) {
            if(list.size() > 0) {
                currentMixAlbum = list.get(0);
            } else {
                currentMixAlbum = new MixAlbum();
            }
            request.getSession().setAttribute(Constants.CURRENT_MIX_ALBUMS, currentMixAlbum);
        }
        return new WebModel(CodeConstants.SUCCESS, null);
    }

    /**
     * 获取专辑详细信息
     *
     * @param uid
     * @param albumsId
     * @param model
     * @return
     */
    @RequestMapping(value = "{uid}/albums/{albumsId}", method = RequestMethod.GET)
    public String albumsInfo(@PathVariable("uid") Integer uid, @PathVariable("albumsId") Integer albumsId, Model model) {
        MixAlbum mixAlbum = mixAlbumsService.findOneByMap(new QxMap<String, Object>().add("uid", uid).add("id", albumsId));
        model.addAttribute("mixAlbum", mixAlbum);
        return "content/albumsInfo";
    }


}
