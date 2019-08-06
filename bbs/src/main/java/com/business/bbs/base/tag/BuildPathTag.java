package com.business.bbs.base.tag;

import com.business.core.client.FileCenterClient;

/**
 * Created by Administrator on 2015/5/14.
 */
public class BuildPathTag extends AbstractTagSupport{

    private String url;

    @Override
    protected String generateHTML() {
        return FileCenterClient.buildUrl(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
