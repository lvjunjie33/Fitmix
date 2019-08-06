package com.business.app.easeMob.api;

import com.business.app.easeMob.comm.wrapper.BodyWrapper;
import com.business.app.easeMob.comm.wrapper.HeaderWrapper;
import com.business.app.easeMob.comm.wrapper.QueryWrapper;
import com.business.app.easeMob.comm.wrapper.ResponseWrapper;

import java.io.File;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header, QueryWrapper query);
}
