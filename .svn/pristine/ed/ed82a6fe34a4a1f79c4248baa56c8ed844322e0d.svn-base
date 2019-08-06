package com.business.push.android;

import com.business.push.AndroidNotification;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}

}
