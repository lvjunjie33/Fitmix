package com.business.work.bbs.advertisement;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.bbs.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fenxio on 2016/9/19.
 */
@Controller
@RequestMapping("bbs/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 广告 首页
     * @return
     */
    @RequestMapping("advertisement-list")
    public String bannerList(Model model) {
        model.addAttribute("advertisementList", advertisementService.getAllAdvertisement());
        return "bbs/advertisement/advertisement-list";
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping(value = "advertisement-add", method = RequestMethod.GET)
    public String bannerAdd() {
        return "bbs/advertisement/advertisement-add";
    }

    /**
     * 添加广告
     * @param advertisement
     */
    @RequestMapping(value = "advertisement-add", method = RequestMethod.POST)
    public void add(Advertisement advertisement, @RequestParam("file") MultipartFile file) {
        if(file != null) {
            String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_BANNER);
            advertisement.setImageUrl(path);
        }
        advertisementService.saveAdvertisement(advertisement);
    }

    /**
     * 跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "advertisement-modify", method = RequestMethod.GET)
    public String bannerModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("advertisement", advertisementService.findAdvertisementById(id));
        return "bbs/advertisement/advertisement-modify";
    }

    /**
     * 修改 广告
     * @param advertisement
     */
    @RequestMapping(value = "advertisement-modify", method = RequestMethod.POST)
    public void bannerModify(Advertisement advertisement, MultipartFile file) {
        if(null != file) {
            advertisement.setImageUrl(FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_BANNER));
        }
        advertisementService.modifyAdvertisementById(advertisement);
    }

    /**
     * 删除banner
     * @param id
     */
    @RequestMapping("advertisement-remove")
    public void bannerRemove(@RequestParam("id") Integer id) {
        advertisementService.removeAdvertisementById(id);
    }

}
