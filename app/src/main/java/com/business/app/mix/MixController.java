package com.business.app.mix;

import com.alibaba.fastjson.JSON;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.constants.Constants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.keyword.KeywordService;
import com.business.core.alipay.util.httpClient.HttpRequest;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.Page;
import com.business.core.entity.keyword.Keyword;
import com.business.core.entity.mix.Mix;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DicUtil;
import com.business.core.utils.HttpUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sin on 15/4/13.
 */
@Controller
@RequestMapping()
public class MixController extends BaseControllerSupport {

    @Autowired
    private MixService mixService;
    @Autowired
    private KeywordService keywordService;

    /**
     * 歌曲分页
     * @param index
     * @param scene
     * TODO 需要去掉
     */
    @Deprecated
    @RequestMapping("/mix/run-mix-page")
    public void runMixPage (Model model,
                            @RequestParam("index") Integer index,
                            @RequestParam("scene") Integer scene,
                            @RequestParam(value = "channel", required = false) String channel,
                            @RequestParam(value = "_v", required = false) Integer version) {
        model.addAttribute("scene",  mixService.runMixPage(scene, index, version, channel));
    }

    /**
     * scene 场景分页 （scene 改成跑速： 快速跑 中速跑 慢速跑 健走)
     * @param index 分页 PageNo
     * @param scene 场景类型
     *  TODO 需要去掉
     */
    @Deprecated
    @RequestMapping("/mix/page-scene")
    public void pageScene (HttpServletRequest request,Model model,
                         @RequestParam(value = "index", required = false) Integer index,
                         @RequestParam(value = "scene",required = false) Integer scene,
                           @RequestParam(value = "_v", required = false) Integer version,
                           @RequestParam(value = "channel", required = false) String channel) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        ImmutableList.Builder<Object> sceneBuilder = ImmutableList.builder();
        if (index == null && scene == null) {
            for (Dictionary dic : DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_SCENE)) {
                List<Mix> sceneList = mixService.pageScene(language,Constants.PAGE_DEFAULT_INDEX, dic.getValue());
                if (!CollectionUtils.isEmpty(sceneList)) {
                    mixService.buildMixFileUrl(sceneList, channel, version);
                    ImmutableMap.Builder<String, Object> sceneBuilderMap = ImmutableMap.builder();
                    sceneBuilderMap.put(Constants.KEY_MIX_SCENE, dic.getValue().toString());
                    sceneBuilderMap.put(Constants.KEY_MIX_ARRAY, sceneList);
                    sceneBuilder.add(new Object[]{sceneBuilderMap.build()});
                }
            }
        } else {
//            List<Mix> sceneList = mixService.pageScene(index, scene);
            List<Mix> sceneList = mixService.searchMix(language,null, scene, null, null, index, null, null, null);
            if (!CollectionUtils.isEmpty(sceneList)) {
                mixService.buildMixFileUrl(sceneList, channel, version);
                ImmutableMap.Builder<String, Object> sceneBuilderMap = ImmutableMap.builder();
                sceneBuilderMap.put(Constants.KEY_MIX_SCENE, scene.toString());
                sceneBuilderMap.put(Constants.KEY_MIX_ARRAY, sceneList);
                sceneBuilder.add(new Object[]{sceneBuilderMap.build()});
            }
            model.addAttribute("index", index);
        }

        model.addAttribute("scene", JSON.toJSON(sceneBuilder.build()));
        model.addAttribute("length", JSON.toJSONString(sceneBuilder.build()).length());
    }

    /**
     * genre 曲风分页
     * @param index 分页 PageNo
     * @param genre 曲风类型 {@link com.business.core.constants.DicConstants#DIC_TYPE_MIX_GENRE}
     *  TODO 需要去掉
     */
    @Deprecated
    @RequestMapping("/mix/page-genre")
    public void pageGenre (HttpServletRequest request,Model model,
                           @RequestParam(value = "index", required = false) Integer index,
                           @RequestParam(value = "genre",required = false) Integer genre,
                           @RequestParam(value = "_v", required = false) Integer version,
                           @RequestParam(value = "channel", required = false) String channel) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        ImmutableList.Builder<Object> genreBuilder = ImmutableList.builder();
        if (index == null && genre == null) {
            for (Dictionary dic : DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_PARENT_GENRE)) {
                List<Mix> genreList = mixService.pageGenre(language,Constants.PAGE_DEFAULT_INDEX, dic.getValue());
                if (!CollectionUtils.isEmpty(genreList)) {
                    mixService.buildMixFileUrl(genreList, channel, version);
                    ImmutableMap.Builder<String, Object> genreBuilderMap = ImmutableMap.builder();
                    genreBuilderMap.put(Constants.KEY_MIX_GENRE, dic.getValue().toString());
                    genreBuilderMap.put(Constants.KEY_MIX_ARRAY, genreList);
                    genreBuilder.add(new Object[]{genreBuilderMap.build()});
                }
            }
        }
        else {
//            List<Mix> genreList= mixService.pageGenre(index, genre);
            List<Mix> genreList = mixService.searchMix(language,null, null, genre, null, index, null, null, null);
            if (!CollectionUtils.isEmpty(genreList)) {
                mixService.buildMixFileUrl(genreList, channel, version);
                ImmutableMap.Builder<String, Object> genreBuilderMap = ImmutableMap.builder();
                genreBuilderMap.put(genre.toString(), genreList);
                genreBuilderMap.put(Constants.KEY_MIX_GENRE, genre.toString());
                genreBuilderMap.put(Constants.KEY_MIX_ARRAY, genreList);
                genreBuilder.add(new Object[]{genreBuilderMap.build()});
            }
            model.addAttribute("index", index);
        }
        model.addAttribute("genre", JSON.toJSON(genreBuilder.build()));
        model.addAttribute("length", JSON.toJSONString(genreBuilder.build()).length());
    }


    /**
     * mix 歌曲搜索
     * @param dicBpm 字典bpm
     * @param scene 场景
     * @param genre 曲风
     * @param dicTrackLength 字典歌曲长度 类型
     * @param index 分页index
     * @param search 搜索内容， bpm,scene,genre,trackLength,name
     */
    @RequestMapping("/mix/search-mix")
    public void searchMix (HttpServletRequest request,Model model, @RequestParam(value = "dicBpm",required = false) Integer dicBpm, @RequestParam(value = "scene",required = false) Integer scene,
                           @RequestParam(value = "genre",required = false) Integer genre, @RequestParam(value = "dicTrackLength",required = false) Integer dicTrackLength,
                           @RequestParam(value = "index",required = true) Integer index, @RequestParam(value = "_v", required = false) Integer version,
                           @RequestParam(value = "search",required = false) String search, @RequestParam(value = "channel", required = false) String channel) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("mix", mixService.searchMix(language,dicBpm, scene, genre, dicTrackLength, index, search, version, channel));
    }

    /**
     * 获取今日推荐歌曲
     */
    @RequestMapping("/mix/recommend-mix")
    public void recommendMix(HttpServletRequest request,Model model) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("currentMix", mixService.recommendMix(language));
    }

    ///
    /// 下载次数统计

    /**
     * mix 下载次数统计
     * @param uid 用户编号
     * @param mid 歌曲编号
     */
    @RequestMapping("/mix/inc-download")
    public void incDownload (String lan,Model model, @RequestParam("uid") Integer uid, @RequestParam("mid") Integer mid) {
        int result = mixService.incDownloadCount(lan,uid, mid);
        switch (result) {
            case 1:
                tip(model, CodeConstants.USER_MIX_COLLECTION_USER_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.USER_MIX_COLLECTION_MIX_EXIST);
                break;
        }
    }

    /**
     * mix 试听次数统计
     * @param uid 用户编号
     * @param mid 歌曲编号
     */
    @RequestMapping("/mix/inc-audition")
    public void incAudition (HttpServletRequest request, Model model, @RequestParam("uid") Integer uid, @RequestParam("mid") Integer mid) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        int result = mixService.incAudition(language,uid, mid);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_MIX_COLLECTION_USER_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.USER_MIX_COLLECTION_MIX_EXIST);
                break;
        }
    }


    /**
     * 查询多个 mix信息
     * @param mids mix 编号
     */
    @RequestMapping("/mix/find-mix-list")
    public void findMixList (HttpServletRequest request, Model model, @RequestParam("mids") String mids,
                             @RequestParam(value = "_v", required = false) Integer version, @RequestParam(value = "channel", required = false) String channel) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("mixList", mixService.findMixList(language,CollectionUtil.convertList(mids, ","), channel, version));
    }


    ///
    /// 2.0 改版后接口

    /**
     * mix 场景列表首页
     * @param type 场景歌曲类型,1、运动歌单，2、电台，3、主播创建
     */
    @RequestMapping("/mix/scene-list")
    public void sceneList(HttpServletRequest request,Model model, @RequestParam(value = "type", required = false) Integer type) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("list", mixService.sceneList(language,type));

    }

    /**
     * 场景 数据分页
     * @param type 类型 场景歌曲类型,1、运动歌单，2、电台，3、主播创建
     * @param sortType 排序
     * @param scene 曲风分页
     * @param index 第几页
     */
    @RequestMapping("/mix/scene-page")
    public void scenePage(HttpServletRequest request, Model model, @RequestParam(value = "type", required = false) Integer type,
                          @RequestParam(value = "sortType", required = false) Integer sortType,
                          @RequestParam("scene") Integer scene,
                          @RequestParam("index") Integer index) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        //1.获取4个封面数据
        List<Mix> mixList = mixService.findMixListBySceneFour(language,scene, type, 4);
        Page<Mix> mixPage = mixService.scenePage(language,type, scene, index, CollectionUtil.buildSet(mixList, Integer.class, "id"), sortType);
        model.addAttribute("page", mixPage);
        if(index == 0) {
            model.addAttribute("list", mixList);
        }
    }

    /**
     * 分享地址
     * @param request 请求
     * @param mid 歌曲编号
     */
    @RequestMapping("/mix/share")
    public void share(Model model, HttpServletRequest request, @RequestParam("mid") Integer mid) {
        String path = HttpUtil.getServerPath(request) + "/mix/share-info.htm?mid=" + mid;
        mixService.shareCountInc(mid);
        model.addAttribute("url", path);
    }

    /**
     * 分享详细
     *
     * @param mid 歌曲编号
     */
    @RequestMapping("/mix/share-info")
    public String shareInfo(HttpServletRequest request,Model model, @RequestParam("mid") Integer mid) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("mix", mixService.shareInfo(language,mid));
        return "mix/share";
    }

    /**
     * 获取今日新歌，不足十首以下载量前10的补至10首
     */
    @RequestMapping("/mix/mix-ranking")
    public String mixRanking1(HttpServletRequest request,Model model) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("mixRanking", mixService.mixRankingShelvesTime(language));
        return "mix/ranking";
    }

    /**
     * 获取热词列表
     * @param uid 用户编号
     */
    @RequestMapping("/mix/get-keyword-list")
    public void getKeywordList(Model model, @RequestParam("uid") Integer uid) {
        List<Keyword> list = keywordService.findAllkeywordList();
        model.addAttribute("result", list);
    }

}
