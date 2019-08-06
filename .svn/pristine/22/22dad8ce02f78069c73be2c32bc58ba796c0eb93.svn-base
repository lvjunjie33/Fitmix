package com.business.ugc.base.tag;

import com.business.core.utils.DateUtil;

/**
 * Created by Administrator on 2015/5/14.
 */
public class ParseDate extends com.business.ugc.base.tag.AbstractTagSupport {

    private static final Long DEFAULT_LONG_VALUE = 0L;

    private Long time;

    private String pattern;

    @Override
    protected String generateHTML() {
        return time.equals(DEFAULT_LONG_VALUE) ? "" : DateUtil.format(DateUtil.parse(time), pattern);
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
