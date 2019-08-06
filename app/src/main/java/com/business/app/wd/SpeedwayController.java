package com.business.app.wd;

import com.business.core.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Random;

/**
 * Created by edward on 2016/7/20.
 */
@Api(value = "SpeedWay", description = "万德智道")
@Controller
@RequestMapping("speedway")
public class SpeedwayController {

    @Autowired
    private SpeedwayService speedwayService;

    /**
     * 获取某个赛道的排名
     *
     * @param speedwayId 赛道编号
     * @param uid        用户编号
     */
    @ApiOperation(value = "获取赛道的排名", notes = "获取赛道的排名", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("rank")
    public String rank(@ApiIgnore Model model,
                       @ApiParam(required = true, value = "赛道编号") Integer speedwayId,
                       @ApiParam(required = true, value = "用户编号") Integer uid) {
        Object[] objects = speedwayService.rank(speedwayId, uid);
        model.addAttribute("speedwayRunInfo", objects[0]);
        model.addAttribute("todayRanks", objects[1]);
        model.addAttribute("historyRanks", objects[2]);
        model.addAttribute("toDay", DateUtil.format(new Date(), "yyyy/MM/dd"));
        return "wd/speedway-run-rank";
    }

    /**
     * 赛道列表
     *
     * @param uid 用户编号
     */
    @ApiOperation(value = "赛道列表", notes = "赛道列表", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("speedway-heed-list")
    public void speedwayHeedList(@ApiIgnore Model model, @ApiParam(required = true, value = "用户编号") Integer uid) {
        model.addAttribute("speedwayHeedList", speedwayService.speedwayHeedList(uid));
    }

    /**
     * 添加万德赛道运动数据
     *
     * @param uid       用户编号
     * @param city      城市
     * @param wayId     赛道编号
     * @param bpm
     * @param bpmMatch
     * @param distance  里程
     * @param calorie   卡路里
     * @param step      步数
     * @param runTime   运动时间
     * @param beginTime 开始时间
     * @param endTime   结束时间
     */
    @ApiOperation(value = "添加万德赛道运动数据", notes = "添加万德赛道运动数据", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add-speedway-run")
    public void addSpeedWayRun(@ApiParam(required = true, value = "用户编号") Integer uid,
                               @ApiParam(required = true, value = "城市") String city,
                               @ApiParam(required = true, value = "赛道编号") String wayId,
                               @ApiParam(required = true, value = "bpm") Long bpm,
                               @ApiParam(required = true, value = "bpm 匹配") Long bpmMatch,
                               @ApiParam(required = true, value = "里程") Long distance,
                               @ApiParam(required = true, value = "卡路里") Long calorie,
                               @ApiParam(required = true, value = "步数") Long step,
                               @ApiParam(required = true, value = "运动时间") Long runTime,
                               @ApiParam(required = true, value = "开始时间") Long beginTime,
                               @ApiParam(required = true, value = "结束时间") Long endTime) {
        if (uid == null || uid <= 0) {
            return;
        }
        speedwayService.addSpeedWayRun(uid, city, wayId, bpm, bpmMatch, distance, calorie, step, runTime, beginTime, endTime);
    }

    @RequestMapping("test")
    public void test() {
        Integer[] uids = new Integer[]{66, 5, 8, 666, 999, 888, 33, 22, 11, 12, 13, 14, 15, 16};
        String city = "长春";
        String wayId = "2";
        Date date = DateUtil.parse("2016-07-23", "yyyy-MM-dd");
        for (int i = 0; i < 4; i++) {
            Long beginTime = DateUtil.getDayBegin(date).getTime();
            Long endTime = DateUtil.getDayEnd(date).getTime();
            for (Integer uid : uids) {
                int offset = new Random().nextInt(1000) + 300;
                Long bpm = 0L + offset;
                Long bpmMatch = 0L + offset;
                Long distance = 0L + offset;
                Long calorie = 0L + offset;
                Long step = 0L + offset;
                Long runTime = 0L + offset;

                speedwayService.addSpeedWayRun(uid, city, wayId, bpm, bpmMatch, distance, calorie, step, runTime, beginTime, endTime);
            }
        }

    }
}
