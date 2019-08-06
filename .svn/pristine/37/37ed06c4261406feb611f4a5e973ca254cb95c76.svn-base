package com.business.ugc.base.tag;

import com.business.core.utils.DicUtil;

/**
 * Created by Administrator on 2015/4/30.
 */
public class DicNameArrayTag extends AbstractTagSupport {

    private Integer type;

    private Integer[] value;

    private String split = "&nbsp;";

    @Override
    protected String generateHTML() {
        StringBuffer str = new StringBuffer();
        if (type == null || value == null) {
            return str.toString();
        }
        for (int i = 0; i < value.length; i ++) {
            str.append(DicUtil.getDictionary(type, value[i]).getName());
            if (i != value.length - 1) {
                str.append(split);
            }
        }
        return str.toString();
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setValue(Integer[] value) {
        this.value = value;
    }

    public void setSplit(String split) {
        this.split = split;
    }
}
