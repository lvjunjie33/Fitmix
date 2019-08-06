package com.business.work.report;

import com.business.core.entity.Page;
import com.business.core.entity.report.Watch;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by edward on 2017/6/28.
 */
@RequestMapping("report")
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    ///=========================== 手表测试报告 ===========================================
    @RequestMapping("watch/manager")
    public String watchManager(Page<Watch> page) {
        reportService.watchPage(page);
        return "/report/watch/manager";
    }

    /**
     * 添加手表测试报告
     *
     * @param title 标题
     * @param des 描述
     * @param testTime 测试时间
     */
    @RequestMapping("watch/add")
    public void watchAdd(String title, String des, String testTime) {
        Watch watch = new Watch();
        watch.setTitle(title);
        watch.setDes(des);
        watch.setTestTime(DateUtil.parse(testTime, "yyyy-MM-dd").getTime());
        watch.setAddTime(System.currentTimeMillis());
        BeanManager.getBean(DefaultDao.class).save(watch);
    }

    /**
     * 去编辑手表测试报告
     * @param id 编号
     */
    @RequestMapping(value = "watch/modify",method = RequestMethod.GET)
    public String watchModify(Model model, Integer id) {
        model.addAttribute("watch", reportService.findWatchById(id));
        return "report/watch/modify";
    }

    /**
     * 编辑测试报告
     *
     * @param id 测试报告编号
     * @param key 修改的字段
     * @param val 字段的值
     */
    @RequestMapping(value = "watch/modify", method = RequestMethod.POST)
    public void watchModify(Integer id, String key, String val) {
        if (key.contains("testTime")) {
            BeanManager.getBean(DefaultDao.class).modifyFirst(Query.query(Criteria.where("id").is(id)), Update.update(key, DateUtil.parse(val, "yyyy-MM-dd").getTime()), Watch.class);
        } else {
            BeanManager.getBean(DefaultDao.class).modifyFirst(Query.query(Criteria.where("id").is(id)), Update.update(key, val), Watch.class);
        }
    }

}
