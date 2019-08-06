package com.business.work.runLevel;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.user.RunLevelInfo;
import com.business.work.base.support.BaseControllerSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/29.
 */
@Controller
@RequestMapping("run-level")
public class RunLevelController extends BaseControllerSupport {

    @Autowired
    private RunLevelService runLevelService;

    @RequestMapping("init")
    public void init() {
        runLevelService.init();
    }

    @RequestMapping("get-run-level-list")
    public String getRunLevelList(Page<RunLevelInfo> page, Model model) {
        PageHelper.startPage(page.getPageNo(), Page.DEFAULT_PAGE_SIZE).setOrderBy("name asc");
        List<RunLevelInfo> list = runLevelService.selectByParams(page.getFilter());
        PageInfo<RunLevelInfo> pageInfo = new PageInfo<>(list);
        page.setTotal(pageInfo.getTotal());
        page.setResult(list);
        model.addAttribute("page", page);
        return "runLevel/run-level-list";
    }
    /**
     * 跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "run-level-modify", method = RequestMethod.GET)
    public String bannerModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("runLevel", runLevelService.selectByPrimaryKey(id));
        return "runLevel/run-level-modify";
    }

    /**
     * 修改banner
     * @param runLevelInfo
     */
    @RequestMapping(value = "run-level-modify", method = RequestMethod.POST)
    public void bannerModify(RunLevelInfo runLevelInfo, MultipartFile file) {
        if(null != file) {
            runLevelInfo.setHonorImage(FileCenterClient.upload(file, FileConstants.FILE_TYPE_RUN_LEVEL));
        }
        runLevelInfo.setModifyTime(System.currentTimeMillis());
        runLevelService.updateByPrimaryKeySelective(runLevelInfo);
    }



}
