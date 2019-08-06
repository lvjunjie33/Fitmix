package com.business.work.base.tag;

import com.business.core.constants.Constants;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.Page;
import com.business.core.sort.SortFactory;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by edward on 2017/9/9.
 */
public class PageSimpleTag extends AbstractTagSupport{

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
    private static final int BUILD_HTML_TYPE_BODY_TOTAL_COUNT = 7;


    private Page<? extends IncIdEntity> page;

    private String href;

    public void setPage(Page<? extends IncIdEntity> page) {
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
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_PARENT, buildPageNo(Constants.PRE_PAGE) + filterUrl, ""));
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_NEXT, buildPageNo(Constants.NEXT_PAGE) + filterUrl, ""));
        html.append(buildHtml(BUILD_HTML_TYPE_BODY_TOTAL_COUNT, page.getTotal(), page.getSize()));
        return html.toString();
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
            case 7:
                result = String.format("<li style=\"float:left;\">条数：%s &nbsp;&nbsp; 每页&nbsp; %s &nbsp;条</li>", parameter);
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

    public String buildPageNo (int offset) {
        String h = href + (href.contains("?") ? '&' : '?') + "offset=" + offset;
        if (!StringUtils.isEmpty(page.getBeginId())) {
            h += "&beginId=" + page.getBeginId() ;
        }
        if (!StringUtils.isEmpty(page.getEndId())) {
            h += "&endId=" + page.getEndId();
        }
        return h;
    }
}
