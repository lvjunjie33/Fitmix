package com.business.ugc.base.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Administrator on 2015/4/22.
 */
public abstract class AbstractTagSupport extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        try {
            String html = generateHTML();
            out.print(html);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    /**
     * @return HTML
     */
    protected abstract String generateHTML();
}
