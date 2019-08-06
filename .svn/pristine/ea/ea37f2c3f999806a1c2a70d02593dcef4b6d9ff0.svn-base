package com.business.app.easeMob.api.impl;

import com.business.app.easeMob.api.EasemobRestAPI;
import com.business.app.easeMob.api.SendMessageAPI;
import com.business.app.easeMob.comm.wrapper.BodyWrapper;
import com.business.app.easeMob.comm.constant.HTTPMethod;
import com.business.app.easeMob.comm.helper.HeaderHelper;
import com.business.app.easeMob.comm.wrapper.HeaderWrapper;

public class EasemobSendMessage extends EasemobRestAPI implements SendMessageAPI {
    private static final String ROOT_URI = "/messages";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object sendMessage(Object payload) {
        String  url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }
}
