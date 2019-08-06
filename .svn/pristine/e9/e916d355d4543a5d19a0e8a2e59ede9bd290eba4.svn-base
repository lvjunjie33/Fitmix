package com.business.work.notice;

import com.business.core.entity.Page;
import com.business.core.entity.notice.Notice;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.OfficeUtil;
import com.google.common.collect.ImmutableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sin on 2016/1/5.
 */
@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("page")
    public String page(Page<Notice> page) {
        noticeService.page(page);
        return "notice/page";
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response, Page<Notice> page) {

        if (StringUtils.isEmpty(page.getFilter().get("beginTime"))) {
            return;
        }

        List<Notice> noticeList = noticeService.export(page);

        ImmutableList<Integer> columnWidths = ImmutableList.of(50000);

        List<List<String>> list = new ArrayList<>();
        list.add(ImmutableList.of("idfa"));

        for (Notice notice : noticeList) {
            list.add(ImmutableList.of(notice.getIdfa()));
        }
        HSSFWorkbook wb = OfficeUtil.buildExcel("idfa", columnWidths, Collections.<String>emptyList(), list);
        CommonUtil.downLoadExcel(response, "idfa", wb);
    }
}
