package com.business.app.easeMob.api.impl;

import com.business.app.easeMob.api.ChatMessageAPI;
import com.business.app.easeMob.api.EasemobRestAPI;
import com.business.app.easeMob.comm.constant.HTTPMethod;
import com.business.app.easeMob.comm.helper.HeaderHelper;
import com.business.app.easeMob.comm.wrapper.HeaderWrapper;
import com.business.app.easeMob.comm.wrapper.QueryWrapper;

public class EasemobChatMessage extends EasemobRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
