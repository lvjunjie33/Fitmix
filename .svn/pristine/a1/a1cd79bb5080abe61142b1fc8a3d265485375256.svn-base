package com.business.app.search;

import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.stat.MapRunSearchStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by edward on 2017/7/26.
 */
@Controller
@RequestMapping("search")
public class SearchController extends BaseControllerSupport{

    @RequestMapping("to/all")
    public String toAll() {
        return "/search/map-search";
    }

    @RequestMapping("get/target")
    public void getSearchTarget(Model model) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        List<MapRunSearchStat> searchStats = defaultDao.findAll(MapRunSearchStat.class);
        model.addAttribute("searchStats", searchStats);
    }
}
