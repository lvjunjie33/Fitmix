package com.business.app.activity;

import com.alibaba.fastjson.JSON;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.joinActivity.JoinActivityService;
import com.business.app.payOrder.PayOrderDao;
import com.business.core.constants.Constants;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.activity.ActivityUser;
import com.business.core.entity.joinActivity.JoinActivityViewLog;
import com.business.core.entity.payOrder.PayOrder;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRealInfo;
import com.business.core.operations.activity.ActivityCoreDao;
import com.business.core.utils.DateUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.PayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by sin on 2015/9/10.
 * <p/>
 * app活动
 */
@Api(value = "activity", description = "赛事活动管理")
@Controller
@RequestMapping()
public class ActivityController extends BaseControllerSupport {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityCoreDao activityCoreDao;
    @Autowired
    private PayOrderDao payOrderDao;
    @Autowired
    private JoinActivityService joinActivityService;

    /**
     * 活动列表
     * 此接口是app web接口,已.htm访问
     *
     * @param page 分页索引
     */
    @RequestMapping("/activity/list")
    public String list(HttpServletRequest request, @ApiIgnore Model model,
                       @ApiParam(required = false, value = "Activity分页对象") Page<Activity> page,
                       @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid) {

        if (uid != null) {
            String ua = HttpUtil.getUA(request);
            if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPad")) {//如果是app用户，将userId放入session中
                User user = RedisConstants.cacheGetOnlineUser(uid);
                request.getSession().setAttribute("activityUid", uid);
                request.getSession().setAttribute("appUserInfo", user);
            }
        } else {
            uid = request.getSession().getAttribute("activityUid") == null ? null : (int)request.getSession().getAttribute("activityUid");
//            if (uid != null) {
//                user = RedisConstants.cacheGetOnlineUser(uid);
//            }
        }

        activityService.list(page, uid);
        model.addAttribute("SYSTIME",System.currentTimeMillis());//系统当前时间
        return "activity/list";
    }

    /**
     * 新版赛事 获取活动列表数据
     *
     * 此接口是app 本地化接口,已.json访问
     *
     * @param page 分页
     * @param uid 用户ID
     */
    @RequestMapping(value = "/activity/get-activity-list")
    public void getActivityList(@ApiIgnore Model model,
                                @ApiParam(required = false, value = "Activity分页对象") Page<Activity> page,
                                @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid){
        activityService.list(page, uid);
        model.addAttribute("SYSTIME",System.currentTimeMillis());//系统当前时间
    }

    /**
     * 去活动页面
     * 此接口是app web,已.htm访问
     *
     * @param activityId 活动编号
     * @param userId 用户编号 由于赛事列表客户端本地化了，所以此接口必须传uid
     * @param enforceToDetailedPage 强制去详细页 1强制去详情页
     * @return 页面
     */
    @RequestMapping("/activity/to-activity")
    public String toActivity(HttpServletRequest request, @ApiIgnore Model model,
                             @RequestParam("activityId") Integer activityId,
                             @RequestParam(value = "uid", required = false) Integer userId,
                             @RequestParam(value = "enforce", required = false, defaultValue = "0") Integer enforceToDetailedPage) {
        User user = null;
        if (userId != null) {
            String ua = HttpUtil.getUA(request);
            if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPad")) {//如果是app用户，将userId放入session中
                user = RedisConstants.cacheGetOnlineUser(userId);
                request.getSession().setAttribute("activityUid", userId);
                request.getSession().setAttribute("appUserInfo", user);
            }
        } else {
            userId = request.getSession().getAttribute("activityUid") == null ? null : (int)request.getSession().getAttribute("activityUid");
            if (userId != null) {
                user = RedisConstants.cacheGetOnlineUser(userId);
            }
        }

        //赛事信息
        Activity activity = activityService.getActivity(activityId);

        //增加浏览量
        activityService.incViewCount(activityId);

        // 第三方赛事
        if(Activity.TYPE_OUTER == activity.getType()) {
            JoinActivityViewLog joinActivityViewLog = new JoinActivityViewLog();
            joinActivityViewLog.setUid(userId);
            joinActivityViewLog.setActivityId(activity.getId());
            joinActivityViewLog.setOutActivityId(activity.getOutActivityId());
            joinActivityViewLog.setChannel(activity.getChannel());
            joinActivityViewLog.setIp(HttpUtil.getIP(request));
            joinActivityViewLog.setAddTime(System.currentTimeMillis());
            joinActivityService.addJoinActivityViewLog(joinActivityViewLog);
            return redirectOutside(activity.getUrl() + "/channel/" + activity.getChannel() + "/uid/" + userId + "/&uid=" + userId);
        }

        if (user == null) {// 没有用户信息的去赛事界面>>>考虑到赛事分享链接，允许访问，但没有报名入口
            setActivityContent(model, activity, null);
            return "activity/to-activity";
        }

        Long currentTime = System.currentTimeMillis();
        //不再报名时间内、不允许报名，直接去赛事浏览页面
        if (currentTime < activity.getSignUpBeginTime() ||  currentTime > activity.getSignUpEndTime() || currentTime > activity.getActivityEndTime()) {
            if (Activity.TYPE_INTEGRAL != activity.getType()) {
                setActivityContent(model, activity, null);
                return "activity/to-activity";
            }
        }

        //查询用户的报名信息
        ActivitySignUp signUp = activityService.getActivitySignUp(activityId, user.getId());

        if (enforceToDetailedPage == 1 && signUp != null) {//强制去赛事浏览页>>不提供报名入口>>>因为已经报名了
            setActivityContent(model, activity, signUp.getMobilePhone());
            return "activity/to-activity";
        }

        if (signUp != null) { //已经报名，去报名页面
            //判断是否是积分赛事
            if (activity.getType() != null && Activity.TYPE_INTEGRAL == activity.getType() && activity.getActivityBeginTime() < currentTime) {
                String url = toIntegralPage(model, user.getId(), activity);
                if (url != null) {
                    return url;
                }
            }
            setSignUpModel(model, activity);
            model.addAttribute("activitySignUp", signUp);
            return "activity/activity-sign-up";
        }

        setSignUpModel(model, activity);

        model.addAttribute("groups", activity.getGroups());
        setActivityContent(model, activity, null);
        return "activity/to-activity";
    }

    /**
     * 设置赛事内容
     *
     * @param activity 赛事
     */
    private void setActivityContent(Model model, Activity activity, String mobile){
        try {
            if (!StringUtils.isEmpty(mobile)) {
                model.addAttribute("mobile", mobile);
            }
            model.addAttribute("themeName", activity.getThemeName());
            //存储页面
            model.addAttribute("activityId", activity.getId());
            model.addAttribute("htmlContent", URLDecoder.decode(activity.getActivityContent(),"UTF-8"));
            model.addAttribute("activityType", StringUtils.isEmpty(activity.getType()) ? "" : activity.getType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户活动报名信息
     *
     * @param activityId 用户活动报名id
     * @param mobile 手机号码
     * @return 页面
     */
    @RequestMapping(value = "/activity/find-activity-sign-up", method = RequestMethod.GET)
    public String findActivityUser(HttpServletRequest request, Model model,
                                   @RequestParam("activityId") Integer activityId,
                                   @RequestParam(value = "mobile", required = false) String mobile) {
        Activity activity = activityService.getActivity(activityId);

        model.addAttribute("mobile", mobile);

        Integer uid = request.getSession().getAttribute("activityUid") == null ? null : (Integer) request.getSession().getAttribute("activityUid");
        //判断是否是积分赛事
        if (activity.getType() != null && Activity.TYPE_INTEGRAL == activity.getType() && activity.getActivityBeginTime() < System.currentTimeMillis()) {
            String url = toIntegralPage(model, uid, activity);
            if (url != null) {
                return url;
            }
        }
        if (!StringUtils.isEmpty(mobile)) {
            ActivitySignUp signUp = activityService.getActivitySignUp(activityId, mobile);
            model.addAttribute("activitySignUp", signUp);
        }
        setSignUpModel(model, activity);
        return "activity/activity-sign-up";
    }

    //设置自定义报名模版
    private void setSignUpModel(Model model, Activity activity) {
        model.addAttribute("activityName", activity.getThemeName());
        model.addAttribute("activityId", activity.getId());

        //第三方赛事没有报名模版 -- 只有积分赛事和普通赛事允许自定义报名模版
        if (Activity.TYPE_OUTER != activity.getType()) {
            if (!CollectionUtils.isEmpty(activity.getSignUpMode())) {
                for (Integer val : activity.getSignUpMode()) {
                    model.addAttribute("signUpMode" + val, Constants.STATE_EFFECTIVE);
                }
            }
        }

        if (activity.getSignUpEndTime() < System.currentTimeMillis()) {//关闭支付入口,活动报名结束了
            model.addAttribute("closeSignUp", 1);
        } else {
            model.addAttribute("closeSignUp", 0);
        }

    }

    /**
     * 积分赛事去积分页
     * @param uid 用户编号
     * @param activity 赛事活动
     */
    private String toIntegralPage(Model model, Integer uid, Activity activity) {
        model.addAttribute("activityId", activity.getId());
        if (uid != null) {
            Date currentDate = new Date();
            if (activity.getActivityEndTime() < System.currentTimeMillis()) {
                currentDate = DateUtil.parse(activity.getActivityEndTime());
            }
            Object[] result = activityService.findActivityStat(activity.getId(), uid, currentDate, activity);
            if (result[0].equals(1)) {
                model.addAttribute("myIntegralInfo", result[1]);
                model.addAttribute("allIntegralInfo", result[2]);
                model.addAttribute("toDay", DateUtil.format(new Date(), "yyyy/MM/dd"));
                return "activity/activity-integral";
            }
        }
        return null;
    }

    /**
     * 保存活动报名信息
     *
     * @param activityId 活动编号
     * @param groupId 活动组编号
     * @param email 邮箱
     * @param mobileName 联系人名字
     * @param mobilePhone 联系人电话
     * @param emergencyName 紧急联系人名字
     * @param emergencyPhone 紧急联系人电话
     * @param signUpMembersInfo 报名人员信息
     * @param userTeamName 团队名称
     * @param userTeamSlogan 团队口号
     */
    @RequestMapping("/activity/save-activity-sign-up")
    public void saveActivitySignUp(HttpServletRequest request, @ApiIgnore Model model,
                                   @RequestParam("activityId") Integer activityId,
                                   @RequestParam("groupId") String groupId,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "mobileName", required = false) String mobileName,
                                   @RequestParam("mobilePhone") String mobilePhone,
                                   @RequestParam(value = "emergencyName", required = false)String emergencyName,
                                   @RequestParam(value = "emergencyPhone", required = false) String emergencyPhone,
                                   @RequestParam(value = "userTeamName", required = false) String userTeamName,
                                   @RequestParam(value = "userTeamSlogan", required = false) String userTeamSlogan,
                                   @RequestParam("signUpMembersInfo") String signUpMembersInfo) {
        String ua = HttpUtil.getUA(request);
        Integer userId = null;
        if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPad")) {//如果是app用户，则取userId
            userId = (Integer) request.getSession().getAttribute("activityUid");
        }
        List<Map<String, Object>> k = (List<Map<String, Object>>) JSON.parse(signUpMembersInfo);
        int code = activityService.saveActivitySignUp(userId, activityId, groupId, email, mobileName, mobilePhone, emergencyName, emergencyPhone, k, userTeamName, userTeamSlogan);
        model.addAttribute("code", code);
        if (CodeConstants.ACTIVITY_SIGN_UP_SUCCESS.equals(code)) {
            model.addAttribute("msg", "亲！活动即将来到，请做好准备哦!!!");
            model.addAttribute("redirect", "activity/find-activity-sign-up.htm?activityId="+activityId + "&mobile=" + mobilePhone);
        } else if(CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_MOBILE.equals(code)) {
            model.addAttribute("msg", "亲！报名失败(您的手机号码已经参加报名了)");
            model.addAttribute("redirect", "activity/find-activity-sign-up.htm?activityId="+activityId + "&mobile=" + mobilePhone);
        } else if(CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_ID_CARD.equals(code)){
            model.addAttribute("msg", "(亲！您填写的参赛成员的身份证已经参与报名了哦)");
        } else if(CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_SEX.equals(code)){
            model.addAttribute("msg", "报名失败(参赛成员性别不符合赛事要求)");
        } else if (CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_FULL.equals(code)) {
            model.addAttribute("msg", "报名失败(总参数组已满)");
        } else if (CodeConstants.ACTIVITY_SIGN_UP_ERROR_DOUBLE_MOBILES.equals(code)) {
            model.addAttribute("msg", "报名失败(重复填写了手机号码)");
        } else if (CodeConstants.ACTIVITY_SIGN_UP_ERROR_DOUBLE_ID_CARD.equals(code)) {
            model.addAttribute("msg", "报名失败(重复填写了身份证信息)");
        } else if (CodeConstants.ACTIVITY_SIGN_UP_ERROR.equals(code)) {
            model.addAttribute("msg", "赛事信息有误");
        } else {
            model.addAttribute("msg", "未知错误!!!");
        }
    }

    @RequestMapping("/activity/to-activity-sm")
    public String toActivitySM() {
        //说明文件可以弄成静态html资源文件
        return "activity/sm";
    }

    /**
     * 去活动支付
     *
     * @param activityId 活动编号
     * @param mobile 手机号码
     */
    @RequestMapping("/activity/to-activity-pay")
    public String toActivityPay(HttpServletRequest request, @RequestParam("activityId") Integer activityId, @RequestParam(required = false) String mobile) {
        String ua = HttpUtil.getUA(request);
        String orderNo = PayUtil.createOrderNo(); //交易订单号
        Activity activity = activityService.getActivity(activityId);
        ActivitySignUp signUp = activityService.getActivitySignUp(activityId, mobile);// 报名信息
        signUp.setTradeNo(orderNo);

        if (ua.contains("MicroMessenger")) {
            //记录支付信息
            activityService.saveActivityPayInfo(activityId, mobile, ActivitySignUp.TRADE_TYPE_WEI_CHAR_PAY,
                    orderNo, signUp.getNeedMoney(), ActivitySignUp.TRADE_STATUS_WAIT_APY);
            String url = "we_chat/pay/public_number_pay.htm?orderNo=" + orderNo + "&money=" + signUp.getNeedMoney() + "&payName=" + activity.getThemeName();
            try {
                return redirect(URLEncoder.encode(url,"UTF-8"));
            }catch (Exception e) {
                //记录支付信息
                return redirect(url);
            }
        } else {// (ua.indexOf("Android") > -1 || ua.indexOf("iPhone") > -1 || ua.indexOf("iPad") > -1)
            //记录支付信息
            activityService.saveActivityPayInfo(activityId, mobile, ActivitySignUp.TRADE_TYPE_WEI_CHAR_PAY,
                    orderNo, signUp.getNeedMoney(), ActivitySignUp.TRADE_STATUS_WAIT_APY);

            // 支付宝支付
            String url = "ali-pay/h5Pay.htm?tradeNo=" + orderNo +
                    "&subject=" + activity.getThemeName() +
                    "&totalFee=" + signUp.getNeedMoney() +
                    "&returnUrl=" + HttpUtil.getServerPath(request) + "activity/to-activity.htm?activityId=" + activity.getId();
            try {
                return redirect(url);
            }catch (Exception e) {
                //记录支付信息
                return redirect(url);
            }
        }
    }

    /* =============================新版活动 end=================================== */

    /**
     * 活动报名
     *
     * @param activityId      活动编号
     * @param uid             用户id
     */
    @Deprecated
    @RequestMapping("/activity/register")
    public String register(@RequestParam Integer activityId, @RequestParam Integer uid) {
        return "404";
    }

    /**
     * 京东跑 临时赛事活动
     *
     */
    @RequestMapping("/activity/register-jd")
    public String registerJd() {
        return "event/jd/run";
    }

    @RequestMapping("/activity/register-jd-data")
    public String register(Model model,
                           @RequestParam("mobilePhone") String mobilePhone,
                           @RequestParam("emergencyPhone") String emergencyPhone,
                           @RequestParam("emergencyName") String emergencyName,
                           @RequestParam("clothesSize") String clothesSize,
                           @RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("age") Integer age,
                           @RequestParam("idCard") String idCard,
                           @RequestParam("detail") String detail) {
        Activity activity = activityCoreDao.findActivityById(22);

        // 您的手机号已报名
        ActivityUser activityUserBase = activityCoreDao.findActivityUserByActivityIdAndMobile(22, mobilePhone);
        if(activityUserBase != null) {
            PayOrder payOrder = payOrderDao.findPayOrderByOrderNo(activityUserBase.getTradeNo());
            if (payOrder != null && payOrder.getPayState().equals(PayOrder.PAY_STATE_SUCCESS)) {
                model.addAttribute("altMsg", "您的手机号已报名");
                return redirect("event/supplementary-user-info.htm");
            }
        }

        String orderNO = PayUtil.createOrderNo();
        ActivityUser activityUser = new ActivityUser();
        activityUser.setActivityId(22);
        activityUser.setNeedMoney(activity.getActivityMoney());
        activityUser.setTradeNo(orderNO);
        activityUser.setAddTime(System.currentTimeMillis());

        UserRealInfo userRealInfo = new UserRealInfo();
        userRealInfo.setName(name);
        userRealInfo.setAge(age);
        userRealInfo.setIdCard(idCard);
        userRealInfo.setClothesSize(clothesSize);

        userRealInfo.setDetail(detail);
        userRealInfo.setMobilePhone(mobilePhone);
        userRealInfo.setEmail(email);
        userRealInfo.setEmergencyName(emergencyName);
        userRealInfo.setEmergencyPhone(emergencyPhone);

        activityUser.setUserRealInfo(userRealInfo);

        activityCoreDao.insertActivityUser(activityUser);
        return redirect("we_chat/pay/public_number_pay.htm?orderNo=" + orderNO + "&money=" + 100 + "&payName=jd");
    }

    /***
     * 支付宝页面跳转
     * <p/>
     * 功能：支付宝服务器异步通知页面
     * 版本：3.3
     * 日期：2012-08-17
     * 说明：
     * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
     * <p/>
     * //***********页面功能说明***********
     * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
     * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
     * 该页面调试工具请使用写文本函数logResult，该函数在com.Alipay.util文件夹的AlipayNotify.java类文件中
     * 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
     *
     * @param request
     * @return
     */
    @RequestMapping("/activity/notify_url")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request) {

        String returnStr = "NOTIFY_URL_RETURN";
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            System.out.println(name + " : " + valueStr);
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

        //商户订单号
        String out_trade_no = params.get("out_trade_no");
        //支付宝交易号
        String trade_no = params.get("trade_no");
        //交易状态
        String trade_status = params.get("trade_status");

        if (params.get("trade_status").equals("TRADE_FINISHED") || params.get("trade_status").equals("TRADE_SUCCESS")) {

            if (params.get("trade_status").equals("TRADE_FINISHED")) {
                /*
                退款的消息 目前可以不考虑
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                * */
            } else if (params.get("trade_status").equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的

                //如果有做过处理，不执行商户的业务程序
                //修改用户订单状态
                activityService.notifyActivityUser(out_trade_no, trade_no, ActivityUser.TRADE_STATUS_SUCCESS);
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            request.setAttribute(returnStr, "success");//请不要修改或删除
        } else {//验证失败
            activityService.notifyActivityUser(out_trade_no, trade_no,ActivityUser.TRADE_STATUS_ERROR);
            request.setAttribute(returnStr, "fail");//请不要修改或删除
        }

        return "success";
    }

    /***
     * 功能：支付宝页面跳转同步通知页面
     * 版本：3.2
     * 日期：2011-03-17
     * 说明：
     * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
     * <p/>
     * //***********页面功能说明***********
     * 该页面可在本机电脑测试
     * 可放入HTML等美化页面的代码、商户业务逻辑程序代码
     * TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
     * TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
     *
     * @param request
     * @return
     */
    @RequestMapping("/activity/return_url")
    public String returnUrl(HttpServletRequest request) {

        String returnStr = "RETURN_URL_RETURN";
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(name + " : " + valueStr);
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = params.get("out_trade_no");
        //支付宝交易号
        String trade_no = params.get("trade_no");
        //交易状态
        String trade_status = params.get("trade_status");

        String uid = out_trade_no.substring(out_trade_no.lastIndexOf("x") + 1);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        //计算得出通知验证结果
        if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序
            request.setAttribute(returnStr, "验证成功");
        } else {
            request.setAttribute(returnStr, "验证失败");
        }
        return redirect("activity/list.htm?uid="+uid);
    }

}
