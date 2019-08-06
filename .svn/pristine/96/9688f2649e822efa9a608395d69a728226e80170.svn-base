package com.business.work.surprise;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.surprise.Surprise;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fenxio on 2016/5/19.
 */
@Controller
@RequestMapping("surprise")
public class SurpriseController extends BaseControllerSupport {

    @Autowired
    private SurpriseService surpriseService;

    /**
     * 彩蛋列表
     * @param page 分页信息
     * @return
     */
    @RequestMapping("surprise-list")
    public String surpriseList(Page<Surprise> page) {
        page.convertLong("id");
        surpriseService.list(page);
        return "surprise/surprise-list";
    }

    /**
     * 添加彩蛋页面
     * @return
     */
    @RequestMapping(value = "surprise-add", method = RequestMethod.GET)
    public String surpriseAdd() {
        return "surprise/surprise-add";
    }

    /**
     * 添加彩蛋信息
     * @param surprise 彩蛋对象
     * @param model
     */
    @RequestMapping(value = "surprise-add", method = RequestMethod.POST)
    public void surpriseAdd(Surprise surprise, Model model) {
        Object[] result = surpriseService.surpriseAdd(surprise);
        switch ((Integer) result[0]) {
            case 0:
                model.addAttribute("surprise", result[1]);
                break;
        }
    }

    /**
     * 彩蛋修改页面
     * @param id 编号
     * @param model
     * @return
     */
    @RequestMapping(value = "surprise-modify", method = RequestMethod.GET)
    public String surpriseModify(@RequestParam("id") Long id, Model model) {
        model.addAttribute("surprise", surpriseService.findSurpriseById(id));
        return "surprise/surprise-modify";
    }

    /**
     * 修改彩蛋基本信息
     * @param surprise 彩蛋对象
     */
    @RequestMapping(value = "surprise-modify", method = RequestMethod.POST)
    public void surpriseModify(Surprise surprise) {
        surpriseService.surpriseModify(surprise);
    }

    /**
     * 修改图片信息
     * @param id 编号
     * @param image 图片
     * @throws Exception
     */
    @RequestMapping(value = "surprise-modify-image", method = RequestMethod.POST)
    public void surpriseModifyImage(@RequestParam("id") Long id,
                                    @RequestParam("image") MultipartFile image) throws Exception {
        String imageUrl = FileCenterClient.upload(image.getBytes(), FileConstants.FILE_TYPE_SURPRISE, image.getOriginalFilename());
        surpriseService.surpriseModifyImage(id, imageUrl);
    }

    /**
     * 修改上架状态
     * @param id 编号
     * @param state 上架状态
     */
    @RequestMapping(value = "surprise-modify-state", method = RequestMethod.POST)
    public void surpriseModifyState(@RequestParam("id") Long id,
                                    @RequestParam("state") Integer state, Model model) {
        Integer result = surpriseService.surpriseModifyState(id, state);
        switch (result) {
            case 0:
                tip(model, 0, "success");
                break;
            case 1:
                tip(model, 1, "上架时间冲突");
                break;
        }
    }

    /**
     * 删除彩蛋信息
     * @param id 编号
     */
    @RequestMapping(value = "surprise-remove", method = RequestMethod.POST)
    public void surpriseRemove(@RequestParam("id") Long id) {
        surpriseService.surpriseRemoveById(id);
    }

    /**
     * 彩蛋详情
     * @param id 编号
     * @param model
     * @return
     */
    @RequestMapping(value = "surprise-info", method = RequestMethod.GET)
    public String surpriseInfo(@RequestParam("id") Long id, Model model) {
        model.addAttribute("surprise", surpriseService.findSurpriseById(id));
        return "surprise/surprise-info";
    }

}
