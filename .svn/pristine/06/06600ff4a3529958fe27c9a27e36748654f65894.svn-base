package com.business.work.logs;

import com.business.core.entity.logs.RequestLog;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by edward on 2017/6/15.
 */
@Controller
@RequestMapping("log")
public class LogController extends BaseControllerSupport{

    private void addCriteria(Integer type, Criteria criteria) {
        Date today = new Date();
        switch(type) {
            case RequestLog.TYPE_DAY:
                criteria.and("addTime").gt(DateUtil.getDayBegin(today).getTime()).lt(DateUtil.getDayEnd(today).getTime());
                break;
            case RequestLog.TYPE_WEEK:
                criteria.and("addTime").gt(DateUtil.getWeekBegin(today).getTime()).lt(DateUtil.getWeekEnd(today).getTime());
                break;
            case RequestLog.TYPE_MONTH:
                criteria.and("addTime").gt(DateUtil.getMonthBegin(today).getTime()).lt(DateUtil.getMonthEnd(today).getTime());
                break;
        }
    }

    /**
     * 统计链接访问量
     * @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/link")
    public String statLink(Model model, Integer type) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<String, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            String link = log.getMatchedPath();
            if(!values.containsKey(link)) {
                values.put(link, 0);
            }
            values.put(link, values.get(link) + 1);
        }
        model.addAttribute("links", values);
        model.addAttribute("type", type);
        return "/logs/stat-link";
    }

    /**
     * 统计某个链接的访问量
     * @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/link/user")
    public String statLinkUser(Model model, Integer type, String link) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = Criteria.where("matchedPath").is(link);
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<Integer, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            Integer uid = log.getUid();
            if (StringUtils.isEmpty(uid)) {
                uid = - 3;
            }
            if (!values.containsKey(uid)) {
                values.put(uid, 0);
            }
            values.put(uid, values.get(uid) + 1);
        }
        model.addAttribute("users", values);
        model.addAttribute("link", link);
        model.addAttribute("type", type);
        return "/logs/stat-link-user";
    }

    /**
     * 统计用户的访问量
     *  @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/user")
    public String statUser(Model model, Integer type) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<Integer, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            Integer uid = log.getUid();
            if (StringUtils.isEmpty(uid)) {
                uid = - 3;
            }
            if (!values.containsKey(uid)) {
                values.put(uid, 0);
            }
            values.put(uid, values.get(uid) + 1);
        }
        model.addAttribute("users", values);
        model.addAttribute("type", type);
        return "/logs/stat-user";
    }

    /**
     * 统计某个用户的链接访问量
     *  @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/user/link")
    public String statUserLink(Model model, Integer type, Integer uid) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        if (uid == -3) {
            Criteria.where("uid").in(null, -1);
        } else {
            Criteria.where("uid").is(uid);
        }
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<String, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            String link = log.getMatchedPath();
            if (!values.containsKey(link)) {
                values.put(link, 0);
            }
            values.put(link, values.get(link) + 1);
        }
        model.addAttribute("links", values);
        model.addAttribute("type", type);
        model.addAttribute("uid", uid);
        return "/logs/stat-user-link";
    }

    /**
     * 统计ip访问量
     *  @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/ip")
    public String statIp(Model model, Integer type) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<String, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            String ip = log.getIp();
            if (StringUtils.isEmpty(ip)) {
                ip = "undefined";
            }
            if (!values.containsKey(ip)) {
                values.put(ip, 0);
            }
            values.put(ip, values.get(ip) + 1);
        }
        model.addAttribute("ips", values);
        model.addAttribute("type", type);
        return "/logs/stat-ip";
    }

    /**
     * 统计某个ip访问链接量
     *  @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/ip/link")
    public String statIpLink(Model model, Integer type, String ip) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        if ("undefined".equals(ip)) {
            criteria.and("ip").is(null);
        } else {
            criteria.where("ip").is(ip);
        }
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<String, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            String link = log.getMatchedPath();
            if (!values.containsKey(link)) {
                values.put(link, 0);
            }
            values.put(link, values.get(link) + 1);
        }
        model.addAttribute("links", values);
        model.addAttribute("type", type);
        model.addAttribute("ip", ip);
        return "/logs/stat-ip-link";
    }

    /**
     * 统计某个ip访问链接量
     *  @param type 1、日，2、周，3、月
     */
    @RequestMapping("/stat/ip/user")
    public String statIpUser(Model model, Integer type, String ip) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Criteria criteria = new Criteria();
        if ("undefined".equals(ip)) {
            criteria.and("ip").is(null);
        } else {
            criteria.where("ip").is(ip);
        }
        addCriteria(type, criteria);
        List<RequestLog> list = defaultDao.query(Query.query(criteria), RequestLog.class);
        Map<Integer, Integer> values = new TreeMap<>();
        for (RequestLog log : list) {
            Integer uid = log.getUid();
            if (StringUtils.isEmpty(uid)) {
                uid = - 3;
            }
            if (!values.containsKey(uid)) {
                values.put(uid, 0);
            }
            values.put(uid, values.get(uid) + 1);
        }
        model.addAttribute("users", values);
        model.addAttribute("type", type);
        model.addAttribute("ip", ip);
        return "/logs/stat-ip-user";
    }
}
