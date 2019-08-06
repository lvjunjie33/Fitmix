package com.business.work.base.tag;

import com.business.core.entity.Page;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/22.
 */
public class PageTag extends AbstractTagSupport {


private static final int VIEW_PAGE_SIZE = 3;
/**
 *  两种显示方式，大于 7 和 小于 7
 */
private static final int VIEW_PAGE_BUTTON = 7;
/**
 * uk 选中后的 class 样式
 */
private static final String VIEW_PAGE_ACTIVE = "uk-active";

///
/// 一下是 buildHtml 类型

private static final int BUILD_HTML_TYPE_ROOT_HEAD = -1;
private static final int BUILD_HTML_TYPE_ROOT_TAIL = -2;
private static final int BUILD_HTML_TYPE_BODY_PARENT = 1;
private static final int BUILD_HTML_TYPE_BODY_NEXT = 2;
private static final int BUILD_HTML_TYPE_BODY_PAGE = 3;
private static final int BUILD_HTML_TYPE_BODY_DISABLED = 4;
private static final int BUILD_HTML_TYPE_BODY_PARENT_DISABLED = 5;
private static final int BUILD_HTML_TYPE_BODY_NEXT_DISABLED = 6;
private static final int BUILD_HTML_TYPE_BODY_TOTAL_COUNT = 7;


private Page page;

private String href;

public void setPage(Page page) {
    this.page = page;
}

public void setHref(String href) {
    this.href = href;
}

@Override
protected String generateHTML() {

    String filterUrl = buildParameter();

    StringBuilder html = new StringBuilder();
    html.append(buildHtml(BUILD_HTML_TYPE_ROOT_HEAD));
    if (page.getPageNo() > 1) {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_PARENT, buildPageNo(page.getPageNo() - 1) + filterUrl, ""));
    }
    else {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_PARENT_DISABLED));
    }
    // 时候满足 7 页
    if (page.getTotalPages() < VIEW_PAGE_BUTTON) {
        for (long l = 1; l <= page.getTotalPages(); l++) {
            isActiveAppend(l, html, filterUrl);
        }
    }
    else { // 满足 7 页
        long gt = page.getPageNo() - 1;
        long eq = page.getPageNo() + 1;
        if (page.getPageNo() <= 1) {
            gt = 1;
        }
        if (page.getPageNo() + 5 > page.getTotalPages()) {
            gt = page.getTotalPages() - 5;
            eq = gt + 2;
        }
        // 前三位
        for (long l = gt; l <= eq; l++) {
            isActiveAppend(l, html, filterUrl);
        }
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_DISABLED, "", "..."));
        // 后三位
        for (long l = page.getTotalPages() - 2; l <= page.getTotalPages(); l++) {
            isActiveAppend(l, html, filterUrl);
        }
    }
    if (page.getPageNo() < page.getTotalPages()) {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_NEXT, buildPageNo(page.getPageNo() + 1) + filterUrl, ""));
    }
    else {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_NEXT_DISABLED));
    }
    html.append(buildHtml(BUILD_HTML_TYPE_BODY_TOTAL_COUNT, page.getTotal(), page.getTotalPages()));
    return html.toString();
}


public void isActiveAppend (long index, StringBuilder html, String filterUrl) {
    if (index == page.getPageNo()) {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_DISABLED, VIEW_PAGE_ACTIVE, index));
    }
    else {
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_PAGE, buildPageNo((int) index) + filterUrl, index));
    }
}

@SuppressWarnings("varargs")
public String buildHtml (int type, Object...parameter) {
    String result = "";
    switch (type){
        case -1:
            result = "<ul class=\"uk-pagination\">";
            break;
        case -2:
            result = "</ul>";
            break;
        case 1:
            result = String.format("<li><a href=\"%s\" style=\"font-size: 10px;\" %s>上一页</a></li>", parameter);
            break;
        case 2:
            result = String.format("<li><a href=\"%s\" style=\"font-size: 10px;\" %s>下一页</a></li>", parameter);
            break;
        case 3:
            result = String.format("<li><a href=\"%s\">%s</a></li>", parameter);
            break;
        case 4:
            result = String.format("<li class=\"%s\"><span>%s</span></li>", parameter);
            break;
        case 5:
            result = String.format("<li><span>上一页</span></li>", parameter);
            break;
        case 6:
            result = String.format("<li><span>下一页</span></li>", parameter);
            break;
        case 7:
            result = String.format("<li style=\"float:left;\">条数：%s &nbsp;&nbsp; 页数：%s</li>", parameter);
            break;
    }
    return result;
}

public String buildParameter () {
    StringBuilder parameter = new StringBuilder();
    @SuppressWarnings("unchecked") LinkedHashMap<String, Object> filter = page.getFilter();
    if (filter != null && !filter.isEmpty()) {
        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            parameter.append("&filter['").append(key).append("']=").append(val);
        }
    }
    return StringEscapeUtils.escapeHtml4(parameter.toString());
}

public String buildPageNo (int pageNo) {
    return href + (href.contains("?") ? '&' : '?') + "pageNo=" + pageNo;
}
}
