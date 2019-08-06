package com.business.work.club;

import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.user.User;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.OfficeUtil;
import com.business.work.base.support.BaseControllerSupport;
import com.google.common.collect.ImmutableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 2016/4/20.
 */
@Controller
@RequestMapping("club")
public class ClubController extends BaseControllerSupport {

    @Autowired
    private ClubService clubService;

    /**
     * club 分页
     * @param page 分页信息
     * @return
     */
    @RequestMapping("club-list")
    public String clubList(Page<Club> page){
        page.convertInt("id","uid");
        clubService.list(page);
        return "club/club-list";
    }

    /**
     * club 详细信息
     * @param model
     * @param id 俱乐部 ID
     * @param page 分页信息
     * @return
     */
    @RequestMapping("club-info")
    public String clubInfo(Model model, @RequestParam("id") Integer id, Page<ClubMember> page){
        clubService.findMemberInfoById(page, id);
        model.addAttribute("club",clubService.findClubById(id));
        return "club/club-info";
    }

    /**
     * 导出俱乐部清单
     */
    @RequestMapping("club-export")
    public void clubExport(HttpServletResponse response, Page<Club> page) throws IOException {
        List<List<String>> values = clubService.findClubComplexInfo(page);

        ImmutableList<Integer> columnWidths = ImmutableList.of(4000,2000, 5000, 8000 ,5000, 5000, 5000, 10000);
        ImmutableList<String> columnNames = ImmutableList.of("俱乐部名称", "俱乐部人数", "创建时间", "俱乐部当前状态", "俱乐部创建者", "创建者手机号码", "描述");

        HSSFWorkbook book = OfficeUtil.buildExcel("俱乐部清单", columnWidths, columnNames, values);
        CommonUtil.downLoadExcel(response, "俱乐部清单", book);
    }

    /**
     * 修改俱乐部人数限制
     *
     * @param id 俱乐部编号
     * @param maxMember 俱乐部人数限制
     */
    @RequestMapping("set-max-member")
    public void setMaxMember(Long id, Integer maxMember) {
        clubService.setMaxMember(id, maxMember);
    }

}
