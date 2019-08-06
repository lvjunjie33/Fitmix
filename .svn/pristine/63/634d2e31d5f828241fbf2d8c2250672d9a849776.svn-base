package com.business.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Office格式的软件的工具类
 * Created by sin on 13-12-31.
 */
public class OfficeUtil {

    /**
     * 自定义构建excel
     */
    public abstract static class BuildExcel{
        public abstract void build(HSSFWorkbook book, String sheetName, Object object);
    }

    /**
     * 正则，用于匹配0串
     */
    private static final Pattern PATTERN_ZEROS = Pattern.compile("[0]*");

    /**
     * 读取Excel，并返回数据字符串数组
     *
     * @param file excel文件
     * @return 数据字符串数组
     * @throws IOException 当file为不支持的文件类型
     */
    public static List<List<String>> readExcel(File file) throws IOException {
        return readExcel(file, null, null);
    }

    public static List<List<String>> readExcel(File file, Integer index) throws IOException {
        return readExcel(file, index, null);
    }

    public static List<List<String>> readExcel(File file, String name) throws IOException {
        return readExcel(file, null, name);
    }

    public static List<List<String>> readExcel(File file, Integer index, String name) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf('.') == -1 ? "" : fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (extension) {
            case "xls":
                return read2003Excel(file, index, name);
            case "xlsx":
                return read2007Excel(file, index, name);
            default:
                throw new IOException("不支持的文件类型");
        }
    }

    /**
     * 读取 office 2003 excel
     */
    private static List<List<String>> read2003Excel(File file, Integer index, String name) throws IOException {
        List<List<String>> list = new LinkedList<>();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = (HSSFSheet) getSheet(hwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }


    /**
     * 读取Office 2007 excel
     */
    private static List<List<String>> read2007Excel(File file, Integer index, String name) throws IOException {
        List<List<String>> list = new LinkedList<>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = (XSSFSheet) getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }

    private static Sheet getSheet(Workbook book, Integer index, String name) {
        if (index != null && name != null) {
            throw new RuntimeException("index,name只能有一个不为空！");
        }
        Sheet sheet;
        if (index != null) {
            sheet = book.getSheetAt(index);
        } else if (name != null) {
            sheet = book.getSheet(name);
        } else {
            sheet = book.getSheetAt(0);
        }
        return sheet;
    }

    /**
     * 特殊方法。将象"123.0"类型的字符串的".0"去掉<br />
     * 若不符合这样的条件，返回null
     *
     * @param value 字符串
     * @return 字符串
     */
    private static String parseInt(String value) {
        String[] strs = value.split("\\.");
        if (strs.length != 2) {
            return null;
        }
        if (StringUtils.isNumeric(strs[0]) && PATTERN_ZEROS.matcher(strs[1]).matches()) {
            return strs[0];
        }
        return null;
    }

    public static XSSFWorkbook buildExcel2007(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<String>> values){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFCellStyle style =  wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        XSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i ++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i ++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i ++) {
            XSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j ++) {
                newRow.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return wb;
    }

    public static HSSFWorkbook buildExcel2003(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style =  wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        HSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i ++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i ++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i ++) {
            HSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j ++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).doubleValue());
                } else {
                    cell.setCellValue(String.valueOf(val));
                }
//                if (val instanceof String) {
//                    newRow.createCell(j).setCellValue((String) val);
//                } else if (val instanceof Double) {
//                    newRow.createCell(j).setCellType();
//                }
            }
        }
        return wb;
    }

    public static HSSFWorkbook buildExcel(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<String>> values){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style =  wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setWrapText(true);
        HSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i ++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i ++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i ++) {
            HSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j ++) {
                newRow.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return wb;
    }

    /**
     * 自定义构建Excel
     *
     * @param sheetName excel名
     * @param buildExcel 构建excel规则
     * @param obj 数据
     */
    public static HSSFWorkbook buildExcel(String sheetName, Object obj, BuildExcel buildExcel) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style =  wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setWrapText(true);
        buildExcel.build(wb, sheetName, obj);
        return wb;
    }

    /**
     * 设置单元格风格一
     * (各种居中)
     * @param workbook
     */
    public static CellStyle createStyle1(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        //cellStyle.setAlignment(CellStyle.ALIGN_JUSTIFY);//水平居中
        //OfficeUtil.setBorder(cellStyle, CellStyle.BORDER_THIN);   //设置单元格边框
        return cellStyle;
    }

    /**
     * 设置单元格边框宽度
     * @param cellStyle
     * @param borderWidth
     */
    private static void setBorder(CellStyle cellStyle,short borderWidth){
        cellStyle.setBorderTop(borderWidth);
        cellStyle.setBorderBottom(borderWidth);
        cellStyle.setBorderLeft(borderWidth);
        cellStyle.setBorderRight(borderWidth);
    }

}
