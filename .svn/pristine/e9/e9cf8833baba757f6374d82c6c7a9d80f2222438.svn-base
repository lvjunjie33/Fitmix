package com.business.work.dic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.SysParam;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.sort.SortFactory;
import com.business.core.utils.DicUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2015/4/29.
 */
@Controller
@RequestMapping("dic")
public class DictionaryController extends BaseControllerSupport {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 所有 dictionary
     */
    @RequestMapping("all-dictionary")
    public String allDictionary (Model model) {
        List<Dictionary> dictionaryList = dictionaryService.findAll();
        Collections.sort(dictionaryList, SortFactory.DICTIONARY_SORT);
        model.addAttribute("refreshUrl", ApplicationConfig.APP_DIC_URL);
        model.addAttribute("allDictionary", dictionaryList);
        return "dic/dictionary";
    }

    /**
     * dictionary 添加
     * @param type 类型
     * @param value 值
     * @param name 名称
     * @param sort 排序
     */
    @RequestMapping("add-dictionary")
    public void addDictionary (Model model,
                               @RequestParam("type") Integer type,
                               @RequestParam("value") Integer value,
                               @RequestParam("name") String name,
                               @RequestParam(value = "nameEn", required = false) String nameEn,
                               @RequestParam("sort") Integer sort,
                                @RequestParam("des") String des) {
        dictionaryService.addDictionary(type, value, name, nameEn, sort, des);
    }

    /**
     * modify dictionary
     * @param id 编号
     * @param value 值
     * @param name 名称
     * @param sort 排序
     */
    @RequestMapping("modify-dictionary")
    public void modifyDictionary (@RequestParam("id") Integer id,
                                   @RequestParam("value") Integer value,
                                   @RequestParam("name") String name,
                                   @RequestParam("nameEn") String nameEn,
                                   @RequestParam("sort") Integer sort, @RequestParam("des") String des) {
        dictionaryService.modifyDictionary(id, value, name, nameEn, sort, des);
    }

    /**
     * 删除 dictionary
     * @param id 编号
     */
    @RequestMapping("remove-dictionary")
    public void removeDictionary (@RequestParam("id") Integer id) {
        dictionaryService.removeDictionary(id);
    }


    ///
    /// dictionary other


    /**
     * 设置 other 信息
     */
    @RequestMapping(value = "other", method = RequestMethod.GET)
    public String other(Model model) {
        model.addAttribute("refreshUrl", ApplicationConfig.APP_DIC_URL);
        List<Dictionary> dicList = new ArrayList<>();
        dicList.addAll(DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_SCENE));
        dicList.addAll(DicUtil.getDictionary(DicConstants.DIC_TYPE_RADIO_SCENE));
        Collections.sort(dicList, SortFactory.DICTIONARY_SORT);
        model.addAttribute("dic", dicList);
        return "dic/dictionary-other";
    }

    /**
     * 设置 other 信息
     * @param value 字典信息
     * @param image other 信息
     */
    @RequestMapping(value = "other", method = RequestMethod.POST)
    public void other(Model model,
                        @RequestParam("id") Integer id,
                        @RequestParam("type") Integer type,
                        @RequestParam("value") Integer value,
                        @RequestParam("image") MultipartFile image) {

        model.addAttribute("fileUrl", FileCenterClient.buildUrl(dictionaryService.other(id, type, value, image)));
    }

    /**
     * 设置 other 其他详细信息
     * @param id 编号
     * @param paceSpeed 配速
     * @param rhythm 节拍
     * @param desc 描述
     */
    @RequestMapping("other-info")
    public void otherInfo(Model model,
                            @RequestParam("id") Integer id,
                            @RequestParam("paceSpeed") String paceSpeed,
                            @RequestParam("rhythm") String rhythm,
                            @RequestParam("desc") String desc) {
        dictionaryService.otherInfo(id, paceSpeed, rhythm, desc);
    }

    /**
     * 设置
     *
     * @param id 编号
     * @param appDisplay 是否显示
     */
    @RequestMapping("modify/app/display")
    public void modifyAppDisplay(Integer id, Integer appDisplay) {
        dictionaryService.modifyAppDisplay(id, appDisplay);
    }
}
