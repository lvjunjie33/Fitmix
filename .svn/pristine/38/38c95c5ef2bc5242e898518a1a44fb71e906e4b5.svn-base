package com.business.work.keyword;

import com.business.core.entity.Page;
import com.business.core.entity.keyword.Keyword;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fenxio on 2016/5/17.
 */
@Controller
@RequestMapping("keyword")
public class KeywordController extends BaseControllerSupport {

    @Autowired
    private KeywordService keywordService;

    /**
     * 关键词列表
     * @param page 分页信息
     * @return
     */
    @RequestMapping("keyword-list")
    public String list(Page<Keyword> page) {
        keywordService.list(page);
        return "keyword/keyword-list";
    }

    /**
     * 跳转关键字添加页面
     * @return
     */
    @RequestMapping(value = "keyword-add", method = RequestMethod.GET)
    public String add() {
        return "keyword/keyword-add";
    }

    /**
     * 添加关键字
     * @param keyword 关键字对象
     * @param model
     */
    @RequestMapping(value = "keyword-add", method = RequestMethod.POST)
    public void add(Keyword keyword, Model model) {
        Object[] result = keywordService.keywordAdd(keyword);
        switch ((Integer) result[0]) {
            case 0:
                model.addAttribute("keyword", result[1]);
                break;
        }
    }

    /**
     * 跳转修改关键字界面
     * @param id  关键字编号
     * @param model
     * @return
     */
    @RequestMapping(value = "keyword-modify", method = RequestMethod.GET)
    public String keywordModify(@RequestParam("id") Integer id, Model model) {
        Keyword keyword = keywordService.findKeywordById(id);
        model.addAttribute("keyword", keyword);
        return "keyword/keyword-modify";
    }

    /**
     * 修改关键字基本信息
     * @param keyword 关键字对象
     */
    @RequestMapping(value = "keyword-modify", method = RequestMethod.POST)
    public void keywordModify(Keyword keyword) {
        keywordService.modifyKeyword(keyword);
    }

    /**
     * 修改 关键字上架状态
     * @param id 编号
     * @param state 上架状态
     */
    @RequestMapping(value = "keyword-modify-state", method = RequestMethod.POST)
    public void keywordModifyState(@RequestParam("id") Long id, @RequestParam("state") Integer state) {
        keywordService.keywordModifyState(id, state);
    }

    /**
     * 修改 关键字排序
     * @param id 编号
     * @param sort 排序
     */
    @RequestMapping(value = "keyword-modify-sort", method = RequestMethod.POST)
    public void keywordModifySort(@RequestParam("id") Long id, @RequestParam("sort") Integer sort) {
        keywordService.keywordModifySort(id, sort);
    }

    /**
     * 删除关键字
     * @param id 编号
     */
    @RequestMapping(value = "keyword-remove", method = RequestMethod.POST)
    public void keywordRemove(@RequestParam("id") Long id) {
        keywordService.removeKeywordById(id);
    }

}
