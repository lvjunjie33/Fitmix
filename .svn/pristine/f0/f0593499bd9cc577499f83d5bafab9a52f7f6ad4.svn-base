package com.business.work.base.tag;

import com.business.core.entity.Page;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;

public class BuildPageFilterTag extends AbstractTagSupport
{
    private Page page;

    public void setPage(Page page)
    {
        this.page = page;
    }

    protected String generateHTML()
    {
        return buildParameter();
    }

    public String buildParameter() {
        StringBuilder parameter = new StringBuilder();
        LinkedHashMap<String, Object> filter = this.page.getFilter();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                parameter.append("&filter['").append(key).append("']=").append(val);
            }
        }
        parameter.append("&pageNo=").append(this.page.getPageNo());
        return StringEscapeUtils.escapeHtml4(parameter.toString());
    }
}