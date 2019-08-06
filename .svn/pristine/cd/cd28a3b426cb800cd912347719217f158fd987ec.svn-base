package com.business.work.codeMessage;

import com.business.core.entity.Page;
import com.business.core.entity.code.CodeMessage;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sin on 2015/11/23.
 */
@Controller
@RequestMapping("code-message")
public class CodeMessageController extends BaseControllerSupport {

    @Autowired
    private CodeMessageService codeMessageService;

    /**
     * 查询所有 错误信息
     * @return 集合
     */
    @RequestMapping("page")
    public String page(Model model) {
        model.addAttribute("codeMessage", codeMessageService.page());
        return "codeMessage/page";
    }

    /**
     * 添加错误信息
     * @param sys 系统
     * @param code 错误编号
     * @param memo 备注信息
     * @param msgChina 错误信息（中文）
     * @param msgEnglish 错误信息（english）
     */
    @RequestMapping("add")
    public void add(Model model,
                    @RequestParam("sys") String sys,
                    @RequestParam("code") Integer code,
                    @RequestParam("memo") String memo,
                    @RequestParam("msgChina") String msgChina,
                    @RequestParam("msgEnglish") String msgEnglish) {
        int result = codeMessageService.add(sys, code, msgChina, msgEnglish, memo);

        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.CODE_MESSAGE_CODE_REPEAT, "code 重复");
                break;
        }
    }

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Model model, Integer id) {
        model.addAttribute("codeMessage", codeMessageService.findById(id));
        return "codeMessage/modify";
    }


    /**
     * 修改错误信息
     * @param id 编号
     * @param sys 系统
     * @param code 错误编号
     * @param memo 备注信息
     * @param msgChina 错误信息（中文）
     * @param msgEnglish 错误信息（english）
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(Model model,
                    @RequestParam("id") int id,
                    @RequestParam("sys") String sys,
                    @RequestParam("code") int code,
                    @RequestParam("memo") String memo,
                    @RequestParam("msgChina") String msgChina,
                    @RequestParam("msgEnglish") String msgEnglish) {
        codeMessageService.modify(id, sys, code, msgChina, msgEnglish, memo);
    }
}
