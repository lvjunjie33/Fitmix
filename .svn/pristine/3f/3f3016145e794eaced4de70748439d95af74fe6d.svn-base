package com.business.ugc.base.tag;

import com.business.core.utils.DicUtil;

/**
 * Created by Administrator on 2015/4/30.
 */
public class DicNameTag extends AbstractTagSupport {

    private Integer type;

    private Integer value;

    @Override
    protected String generateHTML() {
        if (type == null || value == null || !DicUtil.isDictionary(type, value)) {
            return "";
        }
        return DicUtil.getDictionary(type, value).getName();
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
