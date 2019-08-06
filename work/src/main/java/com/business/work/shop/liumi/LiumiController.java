package com.business.work.shop.liumi;

import com.business.core.constants.OrderStatus;
import com.business.core.entity.Page;
import com.business.core.entity.shop.LiumiOrder;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.OfficeUtil;
import com.business.work.base.support.BaseControllerSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/24.
 */
@Controller
@RequestMapping("shop/liumi")
public class LiumiController extends BaseControllerSupport {

    @Autowired
    private LiumiService liumiService;

    /**
     * 流米订单
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "liumi-order-list", method = RequestMethod.GET)
    public String getLiumiOrderList(Page<LiumiOrder> page, Model model) {
        PageHelper.startPage(page.getPageNo(), Page.DEFAULT_PAGE_SIZE);
        List<LiumiOrder> list = liumiService.selectByParams(page.getFilter());
        page.setResult(list);
        PageInfo<LiumiOrder> pageInfo = new PageInfo<>(list);
        page.setTotal(pageInfo.getTotal());
        model.addAttribute("page", page);
        return "shop/liumi/liumi-order-list";
    }

    /**
     * 订单导出
     * @param startTime
     * @param endTime
     */
    @RequestMapping(value = "liumi-order-export")
    public void liumiOrderExport(@RequestParam("startTime") String startTime,
                                 @RequestParam("endTime") String endTime,
                                 HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startTime", DateUtil.parse(startTime, "yyyy-MM-dd").getTime());
        map.put("endTime", DateUtil.parse(endTime, "yyyy-MM-dd").getTime());
        List<LiumiOrder> list = liumiService.selectByParams(map);

        HSSFWorkbook workbook = OfficeUtil.buildExcel("流米订单列表_" + startTime + "_" + endTime, list, new OfficeUtil.BuildExcel() {

            @Override
            public void build(HSSFWorkbook book, String sheetName, Object object) {
                HSSFSheet sheet = book.createSheet(sheetName);
                CellStyle cellStyle = OfficeUtil.createStyle1(book);
                List<LiumiOrder> list = (List<LiumiOrder>) object;

                {//设置单元格的宽度
                    ImmutableList<Integer> columnWidths = ImmutableList.of(4000, 4000, 8000, 8000 ,8000, 8000);//用户ID	兑换流量套餐名称	手机号码	兑换时间 兑换状态 该次兑换金币量
                    for (int i = 0; i < columnWidths.size(); i ++) {
                        sheet.setColumnWidth(i, columnWidths.get(i));
                    }
                }


                {//创建第二行
                    ImmutableList<String> columnNames = ImmutableList.of("用户ID", "兑换流量套餐名称", "手机号码", "兑换时间", "兑换状态", "该次兑换金币量");
                    HSSFRow row = sheet.createRow(1);
                    for (int i = 0; i < columnNames.size(); i++) {
                        HSSFCell titleCell = row.createCell(i);
                        titleCell.setCellValue(columnNames.get(i));
                        titleCell.setCellStyle(cellStyle);
                    }
                }

                {
                    int currentRow = 2;
                    for (int i = 0; i < list.size(); i++) {
                        LiumiOrder liumiOrder = list.get(i);

                        HSSFRow row = sheet.createRow(currentRow);

                        HSSFCell uidCell = row.createCell(0);
                        uidCell.setCellValue(liumiOrder.getUid());
                        uidCell.setCellStyle(cellStyle);

                        HSSFCell nameCell = row.createCell(1);
                        nameCell.setCellValue(liumiOrder.getPostpackage());
                        nameCell.setCellStyle(cellStyle);

                        HSSFCell mobileCell = row.createCell(2);
                        mobileCell.setCellValue(liumiOrder.getMobile());
                        mobileCell.setCellStyle(cellStyle);

                        HSSFCell addTimeCell = row.createCell(3);
                        addTimeCell.setCellValue(DateUtil.formatTimestamp(liumiOrder.getAddTime(), "yyyy-MM-dd HH:mm:ss"));
                        addTimeCell.setCellStyle(cellStyle);

                        HSSFCell statusCell = row.createCell(4);

                        for (OrderStatus o : OrderStatus.values()) {
                            if(o.toString().equals(liumiOrder.getStatus())) {
                                statusCell.setCellValue(o.desc);
                                statusCell.setCellStyle(cellStyle);
                                break;
                            }
                        }



                        HSSFCell coinCell = row.createCell(5);
                        coinCell.setCellValue(liumiOrder.getCoin());
                        coinCell.setCellStyle(cellStyle);

                        currentRow++;
                    }
                }
            }
        });
        CommonUtil.downLoadExcel(response, "流米订单列表_" + startTime + "_" + endTime, workbook);
    }


}
