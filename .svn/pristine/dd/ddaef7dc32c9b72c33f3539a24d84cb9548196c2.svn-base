package com.business.app.userMusicGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.user.UserMusic;
import com.business.core.entity.user.UserMusicGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/11/18.
 * 歌曲分组
 */
@Api(value = "MusicGroup", description = "歌曲分组")
@Controller
@RequestMapping("music-group")
public class UserMusicGroupController extends BaseControllerSupport {

    @Autowired
    private UserMusicGroupService userMusicGroupService;

    /**
     * 添加 歌曲分组
     *
     * @param uid   用户编号
     * @param name  名称
     * @param desc  描述
     * @param image 背景图
     */
    @ApiOperation(value = "添加歌曲分组", notes = "添加歌曲分组", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add-group")
    public void addGroup(@ApiIgnore Model model,
                         @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                         @ApiParam(required = true, value = "名称") @RequestParam("name") String name,
                         @ApiParam(required = true, value = "描述") @RequestParam("desc") String desc,
                         @ApiParam(required = false, value = "背景图") @RequestParam(value = "image", required = false) MultipartFile image) {
        model.addAttribute("musicGroup", userMusicGroupService.addGroup(uid, name, desc, image));
    }


    /**
     * 添加 歌曲分组
     *
     * @param uid   用户编号
     * @param name  名称
     * @param desc  描述
     */
    @ApiOperation(value = "添加歌曲分组", notes = "添加歌曲分组", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add-group-musics")
    public void addGroupMusics(HttpServletRequest request, @ApiIgnore Model model,
                               @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                               @ApiParam(required = true, value = "名称") @RequestParam("name") String name,
                               @ApiParam(required = true, value = "描述") @RequestParam("desc") String desc,
                               @ApiParam(required = true, value = "歌曲编号") @RequestParam("musics") String musics) {

//        @ApiParam(required = false, value = "背景图") @RequestParam(value = "image", required = false) MultipartFile image,

        Map<String, MultipartFile> multipartFile = FileCenterClient.buildMultipartFile(request);
        MultipartFile image = null;
        if (null != multipartFile.get("image")) {
            image = multipartFile.get("image");
        }
        UserMusicGroup userMusicGroup = userMusicGroupService.addGroup(uid, name, desc, image);
        List<UserMusic> userMusics = userMusicGroupService.addMusic(uid, userMusicGroup.getId(), JSON.parseArray(musics, JSONObject.class));
        model.addAttribute("musics", userMusics);
        model.addAttribute("musicGroup", userMusicGroup);
    }


    /**
     * <p>
     * 0、正常 1、歌单不存在
     * </p>
     * 修改 自定义歌单（歌曲分组）
     *
     * @param uid   用户编号
     * @param name  名称
     * @param desc  描述
     * @param image 图片  没有则不修改
     */
    @ApiOperation(value = "修改自定义歌单", notes = "修改自定义歌单", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("modify-group")
    public void modifyGroup(Model model,
                            @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                            @ApiParam(required = true, value = "名称") @RequestParam("name") String name,
                            @ApiParam(required = true, value = "描述") @RequestParam("desc") String desc,
                            @ApiParam(required = true, value = "图片") @RequestParam(value = "image", required = false) MultipartFile image) {
        Object[] result = userMusicGroupService.modifyGroup(uid, name, desc, image);

        switch ((int) result[0]) {
            case 0:
                model.addAttribute("group", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.USER_CUSTOM_MUSIC_GROUP_NOT_EXIST);
                break;
        }
    }

    /**
     * 删除 歌曲分组
     *
     * @param id  分组编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "删除歌曲分组", notes = "删除歌曲分组", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove-group")
    public void removeGroup(@ApiParam(required = true, value = "分组编号") @RequestParam("id") Long id,
                            @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid) {
        userMusicGroupService.removeGroup(id, uid);
    }


    ///
    /// 歌曲操作


    /**
     * 添加 歌曲单
     * <p>
     * music json 格式数据
     * 格式：key name
     * key author
     * [
     * {"name":"歌曲名1", "author":"作者1"}，
     * {"name":"歌曲名2", "author":"作者2"}，
     * ]
     * </p>
     *
     * @param uid    用户编号
     * @param musics 歌曲
     */
    @ApiOperation(value = "添加歌曲", notes = "添加歌曲", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add-music")
    public void addMusic(Model model,
                         @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                         @ApiParam(required = true, value = "歌曲组编号") @RequestParam("groupId") Long groupId,
                         @ApiParam(required = true, value = "歌曲") @RequestParam("musics") String musics) {
        userMusicGroupService.addMusic(uid, groupId, JSON.parseArray(musics, JSONObject.class));
    }

    /**
     * 删除 UserMusic 信息
     * <p>
     * 根据歌曲名称 所在分组 和用户编号 确认信息
     * </p>
     *
     * @param uid     用户编号
     * @param groupId 组
     * @param name    歌曲名称
     */
    @ApiOperation(value = "删除歌曲分组信息", notes = "删除歌曲分组信息", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove-music")
    public void removeMusic(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                            @ApiParam(required = true, value = "组编号") @RequestParam("groupId") Long groupId,
                            @ApiParam(required = true, value = "歌曲名称") @RequestParam("name") String name) {
        userMusicGroupService.removeMusic(uid, groupId, name);
    }

    /**
     * 删除多个 UserMusic 信息
     * <p>
     * 根据歌曲名称 所在分组 和用户编号 确认信息
     * </p>
     *
     * @param uid        用户编号
     * @param groupId    组
     * @param groupNames 歌曲名称
     */
    @ApiOperation(value = "删除多个歌曲分组信息", notes = "删除多个歌曲分组信息", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove-musics")
    public void removeMusics(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                             @ApiParam(required = true, value = "组编号") @RequestParam("groupId") Long groupId,
                             @ApiParam(required = true, value = "组名称") @RequestParam("groupNames") List<String> groupNames) {
        userMusicGroupService.removeMusics(uid, groupId, groupNames);
    }

    /**
     * 移动歌曲，将 UserMusic 移至新的分组
     * <p>
     * 根据歌曲名称 所在分组 和用户编号 确认信息
     * 将歌曲移至 新的分组
     * </p>
     *
     * @param uid        用户编号
     * @param groupId    组
     * @param groupNames 歌曲名称
     */
    @ApiOperation(value = "移动歌曲，将歌曲移至新的分组", notes = "移动歌曲，将歌曲移至新的分组", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("move-musics")
    public void moveMusicsByNames(Model model,
                                  @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                                  @ApiParam(required = true, value = "组名称") @RequestParam("groupNames") List<String> groupNames,
                                  @ApiParam(required = true, value = "组编号") @RequestParam("groupId") Long groupId,
                                  @ApiParam(required = true, value = "目标组编号") @RequestParam("moveGroupName") String moveGroupName) {
        int result = userMusicGroupService.moveMusicsByNames(uid, groupId, groupNames, moveGroupName);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_CUSTOM_MUSIC_GROUP_NOT_EXIST, "歌单不存在");
                break;
        }
    }
}
