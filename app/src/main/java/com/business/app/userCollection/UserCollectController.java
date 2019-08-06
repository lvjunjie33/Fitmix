package com.business.app.userCollection;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.constants.Constants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.ImageUtil;
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
import java.io.IOException;
import java.util.Map;

/**
 * Created by sin on 2015/4/20.
 * 用户收藏
 */
@Api(value = "UserCollect", description = "用户收藏")
@Controller
@RequestMapping("user-collect")
public class UserCollectController extends BaseControllerSupport {

    @Autowired
    private UserCollectService userCollectService;

    /**
     * 用户收藏
     *
     * @param uid  用户编号
     * @param mids mix 编号
     */
    @ApiOperation(value = "用户收藏", notes = "用户收藏", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("collect")
    public void collect(@ApiIgnore Model model,
                        @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                        @ApiParam(required = true, value = "mix 编号") @RequestParam("mids") String mids) {
        int result = userCollectService.collect(uid, CollectionUtil.convertList(mids, ","));
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_MIX_COLLECTION_USER_EXIST, "用户不存在");
                break;
            case 2:
                tip(model, CodeConstants.USER_MIX_COLLECTION_MIX_EXIST, "mix不存在");
                break;
        }
    }

    /**
     * 用户分页
     *
     * @param uid   用户编号
     * @param index 分页
     */
    @ApiOperation(value = "用户分页", notes = "用户分页", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("page")
    public void page(HttpServletRequest request,@ApiIgnore Model model,
                     @ApiParam(required = true, value = "用户编号") @RequestParam(value = "uid", required = true) Integer uid,
                     @ApiParam(required = true, value = "分页") @RequestParam(value = "index", required = true) Integer index) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        Object[] objects = userCollectService.page(language,uid, index);
        int result = (int) objects[0];
        model.addAttribute("index", index);
        switch (result) {
            case 0:
                model.addAttribute("userCollect", objects[1]);
                break;
            case 1:
                tip(model, CodeConstants.USER_MIX_COLLECTION_USER_EXIST, "用户不存在");
                break;
        }
    }

    /**
     * 用户分享
     *
     * @param uid 用户编号
     */
    @ApiOperation(value = "保存静息心率", notes = "保存静息心率", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("user-share")
    public void userShare(@ApiIgnore HttpServletRequest request,
                          @ApiParam(required = true, value = "用户编号") Integer uid) throws IOException {
        Map<String, MultipartFile> multipartFileMap = FileCenterClient.buildMultipartFile(request);
        MultipartFile multipartFile = multipartFileMap.get("file");
        FileCenterClient.uploadNew(ImageUtil.compress(multipartFile), FileConstants.FILE_TYPE_USER_SHARE_IMAGE, multipartFile.getName());
    }
}
