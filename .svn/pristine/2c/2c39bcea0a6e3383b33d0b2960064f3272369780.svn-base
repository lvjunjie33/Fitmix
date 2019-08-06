package com.business.app.easeMob.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import org.apache.commons.lang3.StringUtils;

import com.business.app.easeMob.comm.constant.MsgType;

import java.util.Map;

public class AudioMessageBody extends MessageBody {
	private String url;
	private String filename;
	private String secret;
	private Long length;

	public AudioMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long length) {
		super(targetType, targets, from, ext);
		this.url = url;
		this.filename = filename;
		this.secret = secret;
		this.length = length;
	}

	public String getUrl() {
		return url;
	}

	public String getFilename() {
		return filename;
	}

	public String getSecret() {
		return secret;
	}

	public Long getLength() {
		return length;
	}

	public ContainerNode<?> getBody() {
		if(!this.isInit()){
            this.getMsgBody().put("type", MsgType.AUDIO);
            this.getMsgBody().put("url", url);
            this.getMsgBody().put("filename", filename);
            this.getMsgBody().put("secret", secret);
            this.getMsgBody().put("length", length);
            this.setInit(true);
		}

		return this.getMsgBody();
	}

	@Override
	public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(filename) && StringUtils.isNotBlank(secret) && null != length;
	}
}
