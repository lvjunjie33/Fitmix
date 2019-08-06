package com.business.work.userRun;

import com.alibaba.fastjson.JSON;
import com.business.core.entity.Page;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by edward on 2016/9/21.
 */
@RequestMapping()
@Controller
public class UserRunController extends BaseControllerSupport{

    @Autowired
    private UserRunService userRunService;
    /**
     * 用户运动数据列表
     *
     * @param page 运动数据分页对象
     */
    @RequestMapping("/user-run/list")
    public String list(Model model, Page<UserRun> page) {
        page.removeEmptys("uid", "type").convertInt("uid", "type");
        userRunService.page(page);
        return "userRun/list";
    }

    @RequestMapping("/user/run/page")
    public String page(Page<UserRun> page) {
        page.removeEmptys("uid", "type", "id", "bTime", "eTime").convertInt("uid", "type", "id")
                .convertDate2("bTime", "beginTime", "yyyy-MM-dd").convertDate2("eTime", "endTime", "yyyy-MM-dd");
        Map<String, Object> filter = page.getFilter();
        if (!filter.containsKey("id") && !filter.containsKey("uid")) {
            if (!filter.containsKey("bTime") || !filter.containsKey("eTime")) {
                return "userRun/page";
            }
        }
        //----start--20180529 暂时去掉分页，方便测试数据分析----------

        //----end--20180529 暂时去掉分页，方便测试数据分析------------
        userRunService.page(page);

        //----start--20180529 暂时去掉分页，方便测试数据分析----------

        //----end--20180529 暂时去掉分页，方便测试数据分析------------
        return "userRun/page";
    }


    /**
     * 获取运动数据总计
     * @param uid 用户编号
     */
    @RequestMapping("/user-run/total")
    public void total(Model model, Integer uid) {
        model.addAttribute("sumUserRun", userRunService.allUserRuns(uid));
    }

    /**
     * 用户总运动数据修复
     */
    @RequestMapping("/user-run/restore/total")
    public void restoreTotal(Integer uid) {
        userRunService.restoreTotal(uid);
    }

    /**
     * 抽样统计某个时间段的用户运动次数
     *
     * @param bTime 开始时间
     * @param eTime 结束时间
     */
    @RequestMapping("/user-run/total-uid")
    public void totalUid(Model model, Long bTime, Long eTime) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        List<UserRun> userRuns = defaultDao.query(Query.query(Criteria.where("addTime").gt(bTime).lt(eTime)), UserRun.class, "uid");
        Set<Integer> uids = new HashSet<>();
        for (UserRun userRun : userRuns) {
            uids.add(userRun.getUid());
        }
        Map<Integer, Long> nums = new HashMap<>();
        for (Integer uid : uids) {
            Long num = defaultDao.count(Query.query(Criteria.where("uid").is(uid)), UserRun.class);
            if (num >= 200) {
                nums.put(uid, num);
            }
        }
        for (Integer u : nums.keySet()) {
//            System.out.println(u + "=========" + nums.get(u));
        }
        model.addAttribute("nums", JSON.toJSONString(nums));
    }

    /**
     * 用户运动时间统计
     *
     * @param page 分页对象
     */
    @RequestMapping("/user/run/time/stat")
    public String userRunTimeStat(Page<UserRunStat> page) {
        page.removeEmptys("uid", "type").convertInt("uid", "type");
        userRunService.userRunTimeStat(page);
        return "userRun/user-run-time-stat";
    }

    /**
     * 运动排行榜
     *
     * @param page 分页对象
     */
    @RequestMapping("/user/run/rank")
    public String rank(Page<UserRunStat> page) {
        page.removeEmptys("level", "type", "time").convertInt("level", "type");
        Integer pageNo = page.getPageNo();
        //默认每页10条
        page.setSize(30);

        Map<String, Object> filter = page.getFilter();

        //默认不查询
        if (!filter.containsKey("type") || !filter.containsKey("time")) {
            return "/userRun/rank";
        }
        filter.put("statTime", filter.get("time"));

        if (UserRunStat.STAT_SUM.equals(filter.get("type"))) {
            filter.put("statTime", "--");
        }
        if (UserRunStat.STAT_YEAR.equals(filter.get("type"))) {
            filter.put("statTime", filter.get("statTime").toString().substring(0, 4) + "--");
        }

        userRunService.runMonthRank(page);
        List<UserRunStat> userRunStats = page.getResult();
        for (int i = 0; i < userRunStats.size(); i++) {
            userRunStats.get(i).setRank(i * pageNo + 1L);
        }
        return "/userRun/rank";
    }

    @RequestMapping("/user/run/remove")
    public void remove() {
        userRunService.remove();
    }


    /*====================================ROC RX=======================================================*/
    @RequestMapping("/user/roc/run/page")
    public String rocPage(Page<UserRun> page) {
        page.removeEmptys("uid", "type", "bTime", "eTime").convertInt("uid", "type")
                .convertDate2("bTime", "beginTime", "yyyy-MM-dd").convertDate2("eTime", "endTime", "yyyy-MM-dd");
        Map<String, Object> filter = page.getFilter();
        if (!filter.containsKey("id") && !filter.containsKey("uid")) {
            if (!filter.containsKey("bTime") || !filter.containsKey("eTime")) {
                return "userRun/page";
            }
        }
        userRunService.page(page);
        return "userRun/roc/page";
    }
}
