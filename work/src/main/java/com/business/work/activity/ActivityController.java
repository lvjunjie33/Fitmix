package com.business.work.activity;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.activity.ActivitySignUpMember;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.OfficeUtil;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by sin on 2015/9/11.
 */
@Controller
@RequestMapping("activity")
public class ActivityController extends BaseControllerSupport {

    @Autowired
    private ActivityService activityService;

    /**
     * 活动列表
     *
     * @param page 分页
     * @return 活动
     */
    @RequestMapping("list")
    public String List(Page<Activity> page) {
        page.removeEmptys("themeName", "id", "status", "type", "releaseStatus").convertInt("id", "status", "releaseStatus", "type");
        activityService.list(page);
        return "activity/list";
    }

    /**
     * 去赛事管理 页面
     * @param activityId 赛事编号
     */
    @RequestMapping("to-activity")
    public String toActivity(Model model, @RequestParam("activityId") Integer activityId) {
        model.addAttribute("activity", activityService.getActivity(activityId));
        return "activity/to-activity";
    }

    /**
     * 活动添加
     *
     * @param themeName 主题名
     * @param themeImage 主题图片
     * @param signUpBeginTime 报名开始时间
     * @param signUpEndTime 报名结束时间
     * @param activityBeginTime 活动开始时间
     * @param activityEndTime 活动结束时间
     * @param activityMaxNumber 活动最大人数
     * @param activityFalseNumber 虚假人数
     * @param desc 描述
     * @param type 赛事活动类型
     */
    @RequestMapping("add")
    public void add(Model model, @RequestParam("activityMoney") Double activityMoney,
                    @RequestParam("themeName") String themeName,
                    @RequestParam("themeImage") MultipartFile themeImage,
                    @RequestParam("signUpBeginTime") String signUpBeginTime,
                    @RequestParam("signUpEndTime") String signUpEndTime,
                    @RequestParam("activityBeginTime") String activityBeginTime,
                    @RequestParam("activityEndTime") String activityEndTime,
                    @RequestParam("activityMaxNumber") Integer activityMaxNumber, @RequestParam("activityFalseNumber") Integer activityFalseNumber,
                    @RequestParam("desc") String desc,
                    @RequestParam("type") Integer type) {
        int result = activityService.add(activityMoney, themeName, themeImage,
                activityBeginTime, activityEndTime, signUpBeginTime, signUpEndTime,
                activityMaxNumber, activityFalseNumber, desc, type);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.ACTIVITY_THEME_NAME_REPEAT, "活动主题重复");
                break;
        }
    }

    /**
     * 修改 活动信息
     * @param activityId 活动编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Model model, @RequestParam("activityId") Integer activityId) {
        model.addAttribute("activity", activityService.getActivity(activityId));
        return "activity/modify";
    }

    /**
     * 修改 活动信息
     * @param activityId 活动编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(Model model, @RequestParam("activityId") Integer activityId,
                       @RequestParam("themeName") String themeName,
                       @RequestParam(value = "themeImage", required = false) MultipartFile themeImage,
                       @RequestParam("signUpBeginTime") String signUpBeginTime,
                       @RequestParam("signUpEndTime") String signUpEndTime,
                       @RequestParam("activityBeginTime") String activityBeginTime,
                       @RequestParam("activityEndTime") String activityEndTime,
                       @RequestParam("activityFalseNumber") Integer activityFalseNumber,
                       @RequestParam("activityMaxNumber") Integer activityMaxNumber,
                       @RequestParam("type") Integer type,
                       @RequestParam("desc") String desc,
                       @RequestParam("sort") Integer sort,
                       @RequestParam("releaseStatus") Integer releaseStatus,
                       @RequestParam("status") Integer status) {
        int result = activityService.modifyActivity(activityId, themeName, themeImage, signUpBeginTime, signUpEndTime,
                activityBeginTime, activityEndTime, activityMaxNumber, activityFalseNumber, type, desc, sort, releaseStatus, status);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.ACTIVITY_THEME_NAME_REPEAT, "活动主题重复");
                break;
        }
    }

    @RequestMapping(value = "add-activity-content", method = RequestMethod.GET)
    public String addActivityContent(Model model, Integer activityId) {
        model.addAttribute("activity", activityService.getActivity(activityId));
        return "activity/add-activity-content";
    }

    /**
     * 添加活动展示页面内容
     *
     * @param activityId 活动编号
     * @param htmlContent 活动html内容
     */
    @RequestMapping(value = "add-activity-content", method = RequestMethod.POST)
    public void addActivityContent(Integer activityId, String htmlContent) {
        try {
            //System.out.println(htmlContent);
            //编码后再入库
            htmlContent = URLEncoder.encode(htmlContent,"UTF-8");
            //System.out.println(URLDecoder.decode(htmlContent,"UTF-8"));
            activityService.addActivityContent(activityId, htmlContent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传活动赛事图片
     */
    @RequestMapping("upload-img")
    public void uploadImg(HttpServletRequest request, Model model) {
        List<String> fileImgUrls = FileCenterClient.uploadFiles(request, FileConstants.FILE_TYPE_MIX_BANNER_IMAGE);
        if (!CollectionUtils.isEmpty(fileImgUrls)) {
            model.addAttribute("imgUrl", ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + fileImgUrls.get(0));
        } else {
            tip(model, com.business.core.constants.CodeConstants.MISS, "上传文件失败!!!");
        }
    }

    /**
     * 获取活动内容
     *
     * @param activityId 活动编号
     */
    @RequestMapping("get-activity-content")
    public void getActivityContent(Model model, Integer activityId) {
        String htmlContent = activityService.getActivityContent(activityId);
        model.addAttribute("htmlContent", "");

        if (!StringUtils.isEmpty(htmlContent)) {
            try {
                model.addAttribute("htmlContent", URLDecoder.decode(htmlContent,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 去预览活动
     */
    @RequestMapping(value = "to-preview", method = RequestMethod.GET)
    public String toPreview(Model model, Integer activityId) {
        model.addAttribute("activityId", activityId);
        return "activity/to-preview";
    }

    /**
     * 去添加 活动报名组信息
     * @param activityId 活动编号
     * @param groupName 活动组名称
     * @param needMoney 需要的金额
     * @param memberNumber 报名的人数
     * @param desc 描述
     */
    @RequestMapping("to-add-group")
    public void toAddGroup(Integer activityId, String groupName, Double needMoney, Integer memberNumber, String desc, String memberNames) {
        activityService.toAddGroup(activityId, groupName, needMoney, memberNumber, desc, Arrays.asList(memberNames.split("、")));
    }

    /**
     * 删除活动组信息
     *
     * @param activityId 活动编号
     * @param groupId 组编号
     */
    @RequestMapping("remove-activity-group")
    public String removeActivityGroup(Integer activityId, String groupId) {
        activityService.removeActivityGroup(activityId, groupId);
        return redirect("activity/set-other-info.htm?activityId=" + activityId);
    }

    /**
     * 设置活动报名信息
     */
    @RequestMapping("set-other-info")
    public String setOtherInfo(Model model, Integer activityId) {
        model.addAttribute("activity", activityService.getActivity(activityId));
        return "activity/set-other-info";
    }

    /**
     * 去查看报名信息
     */
    @RequestMapping("to-find-activity-sign-up-info")
    public String toFindSignUpInfo(Model model, Page<ActivitySignUp> page) {
        page.removeEmptys("activityId", "groupId", "mobileName", "mobilePhone", "beginTime", "endTime")
                .convertInt("activityId")
                .convertDate2("beginTime" , "bTime", "yyyy-MM-dd").convertDate2("endTime", "eTime", "yyyy-MM-dd");

        //不合理的访问
        Assert.isTrue(page.getFilter().containsKey("activityId"));

        Integer activityId = Integer.parseInt(page.getFilter().get("activityId").toString());

        activityService.findSignUpInfo(page);
        model.addAttribute("groups", activityService.findActivityGroup(activityId));
        model.addAttribute("activityType", activityService.getActivity(activityId).getType());
        model.addAttribute("activity", activityService.getActivity((Integer)page.getFilter().get("activityId")));
        return "activity/activity-sign-up-info";
    }

    /**
     * 导出赛事报名清单
     */
    @RequestMapping("sign-up-export")
    public void signUpExport(HttpServletResponse response, Page<ActivitySignUp> page) throws IOException {
        page.removeEmptys("activityId", "groupId", "mobileName", "mobilePhone", "beginTime", "endTime")
                .convertInt("activityId")
                .convertDate2("beginTime" , "bTime", "yyyy-MM-dd").convertDate2("endTime", "eTime", "yyyy-MM-dd");
        //不合理的访问
        Assert.isTrue(page.getFilter().containsKey("activityId"));

        final Activity activity = activityService.getActivity(Integer.parseInt(page.getFilter().get("activityId").toString()));
        activityService.signUpExport2(page);

        HSSFWorkbook workbook = OfficeUtil.buildExcel("赛事报名清单", page.getResult(), new OfficeUtil.BuildExcel() {

            @Override
            public void build(HSSFWorkbook book, String sheetName, Object object) {
                HSSFSheet sheet = book.createSheet(sheetName);
                CellStyle cellStyle = OfficeUtil.createStyle1(book);
                List<ActivitySignUp> list = (List<ActivitySignUp>) object;

                {//设置单元格的宽度
                    ImmutableList<Integer> columnWidths = ImmutableList.of(4000, 4000, 6000, 4000 ,8000, 8000);//组序号	身份	姓名	性别	身份证号码	手机号
                    for (int i = 0; i < columnWidths.size(); i ++) {
                        sheet.setColumnWidth(i, columnWidths.get(i));
                    }
                }
                List<Integer> signUpModes = null;
                {
                    switch (activity.getType()) {
                        case Activity.TYPE_COMMON:
                        case Activity.TYPE_INTEGRAL:
                        case Activity.TYPE_NORMAL:
                            signUpModes = activity.getSignUpMode();
                            signUpModes.add(21);
                            signUpModes.add(0);
                            signUpModes.add(22);
                            break;
/*                        case Activity.TYPE_NORMAL:
                            signUpModes = ImmutableList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 20, 21, 22);
                            break;*/
                    }
                }

                {//生成第一行--赛事标题
                    CellRangeAddress region = new CellRangeAddress(0, 0, 0, signUpModes.size() - 1);
                    sheet.addMergedRegion(region);
                    // 这段代码，把B1单元格内容添加上
                    HSSFRow headerRow = sheet.createRow(0);
                    HSSFCell headerCell = headerRow.createCell(0);
                    headerCell.setCellValue(activity.getThemeName());
                    headerCell.setCellStyle(cellStyle);
                }

                {//创建第二行

                    List<String> columnNames = buildTitles(signUpModes);
                    HSSFRow row = sheet.createRow(1);
                    for (int i = 0; i < columnNames.size(); i++) {
                        HSSFCell titleCell = row.createCell(i);
                        titleCell.setCellValue(columnNames.get(i));
                        titleCell.setCellStyle(cellStyle);
                    }
                }

                List<Integer> mergeColumns = getMergeColumns(signUpModes);

                {
                    int currentRow = 2;
                    for (int i = 0; i < list.size(); i++) {
                        ActivitySignUp activitySignUp = list.get(i);
//                        List<ActivitySignUpMember> activitySignUpMembers = activitySignUp.getActivitySignUpMembers();
                        List<List<Object>> rows = buildRowContent(signUpModes, activitySignUp);
                        int memberSize = rows.size(); //成员人数
                        int mergeBegin = currentRow; //开始行
                        int mergeEnd = currentRow + memberSize - 1; //结束行

                        HSSFRow headerRow = sheet.createRow(currentRow);
                        //添加小组编号
                        {//小组编号
                            CellRangeAddress region = new CellRangeAddress(mergeBegin, mergeEnd, 0, 0);
                            sheet.addMergedRegion(region);
                            HSSFCell headerCell = headerRow.createCell(0);
                            headerCell.setCellValue(i);
                            headerCell.setCellStyle(cellStyle);
                        }
                        currentRow+= memberSize;
                        for (int j = 0; j < memberSize; j++) {
                            List<Object> cells = rows.get(j);
                            HSSFRow row = headerRow;
                            if (j != 0) {
                                row = sheet.createRow(mergeBegin + j);
                            }
                            //添加小组成员信息
                            {
                                for (int k = 0; k < cells.size(); k++) {
                                    String cellVal = StringUtils.isEmpty(cells.get(k)) ? "" : cells.get(k).toString();
                                    if (mergeColumns.contains(k)) {
                                        if (j == 0) {// 第一行,联系信息合并
                                            CellRangeAddress region = new CellRangeAddress(mergeBegin, mergeEnd, 1+k, 1+k);
                                            sheet.addMergedRegion(region);
                                            HSSFCell headerCell = row.createCell(1 + k);
                                            headerCell.setCellValue(cellVal);
                                            headerCell.setCellStyle(cellStyle);
                                        }
                                    } else {
                                        HSSFCell groupMemberNameCell = row.createCell(1 + k);
                                        groupMemberNameCell.setCellValue(cellVal);
                                        groupMemberNameCell.setCellStyle(cellStyle);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        CommonUtil.downLoadExcel(response, "赛事报名清单", workbook);
    }

    private List<List<Object>> buildRowContent(List<Integer> signUpModes, ActivitySignUp activitySignUp) {
        List<ActivitySignUpMember> activitySignUpMembers = activitySignUp.getActivitySignUpMembers();

        List<List<Object>> rowCells = new ArrayList<>();//小组所有数据

        for (ActivitySignUpMember activitySignUpMember : activitySignUpMembers) {
            Map<Integer, Object> positions = new HashMap<>();
            for (Integer k : signUpModes) {
                switch (k) {
                    case 0:
                        positions.put(0, activitySignUpMember.getGroupMemberName());//小组身份
                        break;
                    case 1:
                        positions.put(1, activitySignUpMember.getName());//姓名
                        break;
                    case 2:
                        positions.put(2, activitySignUpMember.getGender());//性别
                        break;
                    case 3:
                        positions.put(4, activitySignUpMember.getClothesSize());//衣服尺码
                        break;
                    case 4:
                        positions.put(6, activitySignUpMember.getMemo());//备注
                        break;
                    case 5:
                        positions.put(7, activitySignUpMember.getIdCardType());//证件类型
                        break;
                    case 6:
                        positions.put(8, activitySignUpMember.getIdCard());//证件号码
                        break;
                    case 7:
                        positions.put(11, activitySignUp.getEmail());//邮箱
                        break;
                    case 8:
                        positions.put(12, activitySignUp.getEmergencyName());//紧急联系人姓名
                        break;
                    case 9:
                        positions.put(13, activitySignUp.getEmergencyPhone());//紧急联系人电话
                        break;
                    case 10:
                        positions.put(3, activitySignUpMember.getBloodType());//血型
                        break;
                    case 11:
                        positions.put(14, activitySignUp.getNeedMoney());//参赛金额
                        break;
                    case 14:
                        positions.put(9, activitySignUp.getMobileName());//联系人名称
                        break;
                    case 15:
                        positions.put(16, activitySignUp.getUserTeamName());//团队名称
                        break;
                    case 16:
                        positions.put(17, activitySignUp.getUserTeamSlogan());//团队口号
                        break;
                    case 20:
                        if (StringUtils.isEmpty(activitySignUpMember.getMobile())) {
                            positions.put(5, activitySignUp.getMobilePhone());//成员手机号码
                        } else {
                            positions.put(5, activitySignUpMember.getMobile());//成员手机号码
                        }
                        break;
                    case 21:
                        positions.put(10, activitySignUp.getMobilePhone());//联系人手机号码
                        break;
                    case 22:
                        Integer tradeStatus = activitySignUp.getTradeStatus();
                        String tradeResult;
                        if (tradeStatus == null) {
                            tradeResult = "未支付";
                        } else if (ActivitySignUp.TRADE_STATUS_SUCCESS == tradeStatus) {
                            tradeResult = "支付成功";
                        } else if (ActivitySignUp.TRADE_STATUS_WAIT_APY == tradeStatus) {
                            tradeResult = "未支付";
                        } else {
                            tradeResult = "支付失败";
                        }
                        positions.put(15, tradeResult); // 支付状态
                        break;
                }
            }
            List<Object> cells = new ArrayList<>();

            for (int i = 0; i < 30; i++) {
                if (positions.containsKey(i)) {
                    cells.add(positions.get(i));
                }
            }
            rowCells.add(cells);
        }
        return rowCells;
    }

    /**
     * 构建excel标题
     *
     * @param signUpModes 赛事设置勾选项
     * @return
     */
    private List<String> buildTitles(List<Integer> signUpModes) {
        Map<Integer, String> positions = new HashMap<>();
        for (Integer k : signUpModes) {
            switch (k) {
                case 0:
                    positions.put(0, "小组身份");
                    break;
                case 1:
                    positions.put(1, "姓名");
                    break;
                case 2:
                    positions.put(2, "性别");
                    break;
                case 3:
                    positions.put(4, "衣服尺码");
                    break;
                case 4:
                    positions.put(6, "备注");
                    break;
                case 5:
                    positions.put(7, "证件类型");
                    break;
                case 6:
                    positions.put(8, "证件号码");
                    break;
                case 7:
                    positions.put(11,"邮箱");
                    break;
                case 8:
                    positions.put(12, "紧急联系人姓名");
                    break;
                case 9:
                    positions.put(13, "紧急联系人电话");
                    break;
                case 10:
                    positions.put(3, "血型");
                    break;
                case 11:
                    positions.put(14, "参赛金额");
                    break;
                case 14:
                    positions.put(9, "联系人名称");
                    break;
                case 15:
                    positions.put(16, "团队名称");
                    break;
                case 16:
                    positions.put(17, "团队口号");
                    break;
                case 20:
                    positions.put(5, "成员手机号码");
                    break;
                case 21:
                    positions.put(10, "联系人手机号码");
                    break;
                case 22:
                    positions.put(15, "支付状态");
                    break;
            }
        }
        List<String> titles = new ArrayList<>();
        titles.add("组序号");
        for (int i = 0; i < 30; i++) {
            if (positions.containsKey(i)) {
                titles.add(positions.get(i));
            }
        }
        return titles;
    }

    private List<Integer> getMergeColumns(List<Integer> signUpModes) {
        Map<Integer, Integer> positions = new HashMap<>();
        List<Integer> mergePositions = new ArrayList<>();
        for (Integer k : signUpModes) {
            switch (k) {
                case 0:
                    positions.put(0, 0);//小组身份
                    break;
                case 1:
                    positions.put(1, 1);//姓名
                    break;
                case 2:
                    positions.put(2, 2);//性别
                    break;
                case 3:
                    positions.put(4, 4);//衣服尺码
                    break;
                case 4:
                    positions.put(6, 6);//备注
                    break;
                case 5:
                    positions.put(7, 7);//证件类型
                    break;
                case 6:
                    positions.put(8, 8);//证件号码
                    break;
                case 7:
                    positions.put(11, 11);//邮箱
                    mergePositions.add(11);
                    break;
                case 8:
                    positions.put(12, 12);//紧急联系人姓名
                    mergePositions.add(12);
                    break;
                case 9:
                    positions.put(13, 13);//紧急联系人电话
                    mergePositions.add(13);
                    break;
                case 10:
                    positions.put(3, 3);//血型
                    break;
                case 11:
                    positions.put(14, 14);//参赛金额
                    mergePositions.add(14);
                    break;
                case 14:
                    positions.put(9, 9);//联系人名称
                    mergePositions.add(9);
                    break;
                case 15:
                    positions.put(16, 16);//团队名称
                    mergePositions.add(16);
                    break;
                case 16:
                    positions.put(17, 17);//团队口号
                    mergePositions.add(17);
                    break;
                case 20:
                    positions.put(5, 5);//成员手机号码
                    break;
                case 21:
                    positions.put(10, 10);//联系人手机号码
                    mergePositions.add(10);
                    break;
                case 22:
                    positions.put(15, 15);//支付状态
                    mergePositions.add(15);
                    break;
            }
        }
        List<Integer> titles = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (positions.containsKey(i)) {
                titles.add(positions.get(i));
            }
        }

        List<Integer> mergeCells = new ArrayList<>();
        for (int i = 0; i < titles.size(); i ++) {
            int c = titles.get(i);
            if (mergePositions.contains(c)) {
                mergeCells.add(i);
            }
        }
        return mergeCells;
    }

    /**
     * 导出 积分赛事
     *
     * @param activityId 赛事编号
     */
    @RequestMapping("integral-export")
    public void integralExport(HttpServletResponse response, Integer activityId) {
        List<List<String>> values = activityService.signUpExport(activityId);

        ImmutableList<Integer> columnWidths = ImmutableList.of(10000, 10000, 2000, 4000 ,3000, 3000, 3000, 3000, 3000, 3000, 5000, 7000);
        ImmutableList<String> columnNames = ImmutableList.of("用户信息", "赛事信息", "排名", "统计日期", "总里程", "总步数", "总积分", "当日里程", "当日步数", "当日积分", "联系方式", "证件信息");

        HSSFWorkbook book = OfficeUtil.buildExcel("积分赛事排名", columnWidths, columnNames, values);
        CommonUtil.downLoadExcel(response, "积分赛事排名", book);
    }

    /**
     * 设置报名信息模版
     *
     * @param activityId 活动编号
     */
    @RequestMapping(value = "set-sign-up-mode", method = RequestMethod.GET)
    public String setSignUpMode(Model model, Integer activityId) {
        Activity activity = activityService.getActivity(activityId);
        model.addAttribute("activityId", activity.getId());
        if (!CollectionUtils.isEmpty(activity.getSignUpMode())) {
            for (Integer val : activity.getSignUpMode()) {
                model.addAttribute("signUpMode" + val, Constants.STATE_EFFECTIVE);
            }
        }
        return "activity/set-sign-up-mode";
    }

    @RequestMapping("set-user-team-info")
    public String setUserTeamInfo(Model model, Integer activityId) {
        Activity activity = activityService.getActivity(activityId);
        model.addAttribute("activityId", activity.getId());
        if (!CollectionUtils.isEmpty(activity.getSignUpMode())) {
            for (Integer val : activity.getSignUpMode()) {
                model.addAttribute("signUpMode" + val, Constants.STATE_EFFECTIVE);
            }
        }
        return "activity/set-user-team";
    }

    /**
     * 设置报名信息模版
     *
     * @param activityId 活动编号
     * @param signUpMode 报名填写信息模版
     */
    @RequestMapping(value = "set-sign-up-mode", method = RequestMethod.POST)
    public void setSignUpMode(Integer activityId, Integer[] signUpMode) {
        activityService.modifySignUpMode(activityId, signUpMode);
    }

    /**
     * 设置三方赛事的链接
     *
     * @param activityId 赛事编号
     * @param webLink web链接
     */
    @RequestMapping("set-thread-link")
    public void setThreadLink(Integer activityId, String webLink) {
        activityService.setThreadLink(activityId, webLink);
    }

    /**
     * 设置积分赛事的区域标签
     *
     * @param activityId 赛事编号
     * @param cityTarget 区域标签
     */
    @RequestMapping("set-activity-integral-city")
    public void setActivityIntegralCity(Integer activityId, Integer cityTarget) {
        activityService.setActivityIntegralCity(activityId, cityTarget);
    }

    /**
     * 设置最大报名人数
     *
     * @param activityId 赛事编号
     * @param maxGroupNum 最大组数
     */
    @RequestMapping("set-activity-max-group-num")
    public void setActivityMaxGroupNum(Integer activityId, Integer maxGroupNum) {
        if (StringUtils.isEmpty(maxGroupNum)) {
            return;
        }
        activityService.setActivityMaxGroupNum(activityId, maxGroupNum);
    }

    /**
     * 增加赛事性别检查
     *
     * @param activityId 赛事编号
     * @param checkSex 检查性别
     */
    @RequestMapping("set-activity-check-sex")
    public void setActivityCheckSex(Integer activityId, String checkSex) {
        activityService.setActivityCheckSex(activityId, checkSex);
    }

    /**
     * 设置三方赛事的链接
     *
     * @param activityId 赛事编号
     * @param focusTag 赛事标签
     */
    @RequestMapping("set-focus-tag")
    public void setFocusTag(Integer activityId, String focusTag) {
        activityService.setFocusTag(activityId, focusTag);
    }

    /**
     * 迁移数据 脚本
     */
    @RequestMapping("m-s")
    public void dataMoveScript() {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        List<Integer> all = new ArrayList<>(Activity.SIGN_UP_MODE_ALL.keySet());
        Integer[] a = new Integer[all.size() - 2];
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) == 15 || all.get(i) == 16) {

            } else {
                a[i] = all.get(i);
            }
        }
        defaultDao.modifyMore(Query.query(Criteria.where("type").is(3)), Update.update("signUpMode", a), Activity.class);
    }
}
