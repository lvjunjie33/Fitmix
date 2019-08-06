package com.business.app.joinActivity;

import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.joinActivity.JoinActivity;
import com.business.core.entity.joinActivity.JoinActivityEntered;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Sin on 2016/7/1.
 *
 * 接入 活动赛事
 */
@Api(value = "mix-author", description = "第三方赛事合作接入")
@Controller
@RequestMapping("join_activity")
public class JoinActivityController extends BaseControllerSupport{

    @Autowired
    private JoinActivityService joinActivityService;

    /**
     * 加入新赛事
     *
     themeName	y	String	活动名称
     themeImage	Y	String 	活动封面图地址
     desc	Y	String	活动描述/介绍
     url	Y	String	跳转Url网址（活动改地址）
     activityId	Y	String	第三方活动编号
     activityBeginTime	Y	Long	活动开始时间（MillisTime）
     activityEndTime
     Y	Long	活动结束时间（MillisTime）
     signUpBeginTime
     Y	Long	报名开始时间（MillisTime）
     signUpEndTime
     Y	Long	报名截止时间（MillisTime）
     channel	Y	Integer	渠道号：（根据我们分配）
     activityMoney	Y	Double	报名费用

     */
    @ApiOperation(value = "notice_join", notes = "加入新赛事", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("notice_join")
    public void join(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "赛事信息") @RequestParam("joinActivity") JoinActivity joinActivity) {
        int result = joinActivityService.addJoinActivity(joinActivity);
        tip(model, result);
    }

    /**
     * 对方通知我们，已报名参加
     */
    @ApiOperation(value = "notice_entered", notes = "赛事报名通知", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("notice_entered")
    public void entered(@ApiIgnore Model model,
                        @ApiParam(required = true, value = "报名通知") @RequestParam("joinActivityEntered") JoinActivityEntered joinActivityEntered) {
        int result = joinActivityService.addJoinActivityEntered(joinActivityEntered);
        tip(model, result);
    }
}
