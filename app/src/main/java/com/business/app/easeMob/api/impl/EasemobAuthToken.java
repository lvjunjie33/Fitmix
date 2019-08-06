package com.business.app.easeMob.api.impl;

import com.business.app.easeMob.api.AuthTokenAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.app.easeMob.api.EasemobRestAPI;
import com.business.app.easeMob.comm.wrapper.BodyWrapper;
import com.business.app.easeMob.comm.constant.HTTPMethod;
import com.business.app.easeMob.comm.helper.HeaderHelper;
import com.business.app.easeMob.comm.wrapper.HeaderWrapper;
import com.business.app.easeMob.comm.body.AuthTokenBody;

public class EasemobAuthToken extends EasemobRestAPI implements AuthTokenAPI {
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(EasemobAuthToken.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
