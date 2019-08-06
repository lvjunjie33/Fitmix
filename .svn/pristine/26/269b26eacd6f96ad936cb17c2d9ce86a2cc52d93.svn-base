package com.business.work.mixAlbum;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixAlbum;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Sin on 2016/3/9.
 */
@Controller
@RequestMapping("mix-album")
public class MixAlbumController {

    @Autowired
    private MixAlbumService mixAlbumService;

    @RequestMapping("list")
    public String list(Page<MixAlbum> page) {
        page.convertInt("id", "status");
        mixAlbumService.list(page);
        return "mixAlbum/list";
    }

    @RequestMapping("info")
    public String info(Model model,
                       @RequestParam("albumId") Integer albumId) {
        model.addAttribute("data", mixAlbumService.info(albumId));
        return "mixAlbum/info";
    }

    @RequestMapping("add")
    public void add(@RequestParam("file") MultipartFile file,
                    @RequestParam("title") String title,
                    @RequestParam("desc") String desc,
                    @RequestParam("mixIds") String mixIds) {
        List<Integer> mixList = CollectionUtil.convertList(mixIds, ",");
        mixAlbumService.add(file, title, desc, mixList);
    }

    @RequestMapping("modify")
    public void modify(@RequestParam("albumId") Integer albumId,
                       @RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam("title") String title,
                       @RequestParam("status") Integer status,
                       @RequestParam("desc") String desc,
                       @RequestParam("mixIds") String mixIds) {
        mixIds = mixIds.replace("[", "").replace("]", "").trim();
        List<Integer> mixList = CollectionUtil.convertList(mixIds, ",");
        mixAlbumService.modify(albumId, file, title, status, desc, mixList);
    }
}
