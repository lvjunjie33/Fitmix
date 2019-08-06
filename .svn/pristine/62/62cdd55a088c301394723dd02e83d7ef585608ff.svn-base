package com.business.work.base.tag;

/**
 * Created by edward on 2017/2/20.
 */
public class HtmlTextCheckTag extends AbstractTagSupport {

    /**
     * 需要被检测的内容
     */
    private String text;

    @Override
    protected String generateHTML() {
        text = text.replaceAll("<", "&lt;");
        text = text.replaceAll(">", "&gt;");
        text = text.replaceAll("'", "&quot;");
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
