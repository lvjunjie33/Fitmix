package com.business.work.gw;



import com.business.core.entity.Page;
import com.business.core.entity.file.File;
import com.business.core.entity.gw.CommonProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by edward on 2018/9/18.
 */
@RequestMapping()
@Controller
public class GwController {
    @Autowired
    private GwService gwService;
    /**
     * 查询常见问题列表
     *
     * @param page 分页对象
     */
    @RequestMapping("/gw/CommonProblem/problem-list")
    public String pageCommonProblemList(Page<CommonProblem> page) {
        page.removeEmptys("problemTitile", "problemContent", "status").convertInt("status");
        gwService.page(page);
        return "gw/commonProblem/problem-list";
    }

    /**
     * 新增常见问题
     *
     * @param commonProblem 常见问题实体
     */
    @RequestMapping("/gw/CommonProblem/problem-add")
    public String commonProblemAdd(Model model,Page<CommonProblem> page, CommonProblem commonProblem,String des,String title) {
        gwService.add(commonProblem,des,title);
        return "gw/commonProblem/problem-list";

    }

    /**
     * 跳转常见问题修改页面
     *
     * @param id 常见问题id
     */
    @RequestMapping("/gw/CommonProblem/problem-update-view")
    public String commonProblemUpdateView(Model model, Long id,String lan) {
        model.addAttribute("commonProblem", gwService.findCommonProblemById(id));
        return "gw/commonProblem/problem-update";
    }

    /**
     * 修改常见问题
     *
     * @param commonProblem 常见问题实体
     */
    @RequestMapping("/gw/CommonProblem/problem-update")
    public String commonProblemUpdate(Page<CommonProblem> page,CommonProblem commonProblem,String des,String title) {
        gwService.modifyFile(commonProblem,des,title);
        return "gw/commonProblem/problem-list";
    }

    /**
     * 删除常见问题
     *
     * @param page 分页对象
     * @param commonProblem 常见问题实体对象
     */
    @RequestMapping("/gw/CommonProblem/problem-delete")
    public String  commonProblemDelete(Page<CommonProblem> page,CommonProblem commonProblem) {
        gwService.commonProblemDelete(commonProblem);
        gwService.page(page);
        return "gw/commonProblem/problem-list";
    }


    /**
     * ajax 选中值动态加载简介
     *
     * @param id 文件编号
     */
    @RequestMapping(value = "/gw/CommonProblem/problem-update-ajax", method = RequestMethod.GET)
    public void watchModifyAjax(Model model, Long id,String lan) {
        CommonProblem commonProblem=gwService.findFileByIdAjax(id,lan);
        if(commonProblem!=null) {
            if ("zh".equals(lan)) {
                commonProblem.setProblemTitle_en("");
                commonProblem.setProblemContent_en("");
            }
            if ("en".equals(lan)) {
                commonProblem.setProblemTitle("");
                commonProblem.setProblemContent("");
            }
            model.addAttribute("commonProblem", commonProblem);
        }

    }
}
