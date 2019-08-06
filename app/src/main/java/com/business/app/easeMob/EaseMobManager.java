package com.business.app.easeMob;

import com.business.app.easeMob.api.impl.EasemobAuthToken;
import com.business.app.easeMob.comm.ClientContext;
import com.business.app.easeMob.comm.EasemobRestAPIFactory;
import com.business.app.easeMob.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by edward on 2016/10/17.
 */
public class EaseMobManager {

    private static EasemobRestAPIFactory factory;

    public static void ini() {
        EaseMobManager.factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

        EasemobAuthToken easemobAuthToken = (EasemobAuthToken)factory.newInstance(EasemobRestAPIFactory.TOKEN_CLASS);

        ClientContext clientContext = easemobAuthToken.getContext();

        ResponseWrapper responseWrapper = (ResponseWrapper)easemobAuthToken.getAuthToken(clientContext.getClientId(), clientContext.getClientSecret());
        ObjectNode result = (ObjectNode) responseWrapper.getResponseBody();
        String accessToken = result.get("access_token").textValue();
        String expiresIn = result.get("expires_in").textValue();
        String application = result.get("application").textValue();
        //TODO 缓存环信的AccessToken ，AccessToken  目前用处待定
    }

    /**
     * 获取操作类
     *
     * @param clazz {@link EasemobRestAPIFactory#TOKEN_CLASS} 之类的
     * @param <T> 返回的类型
     */
    public static  <T> T getOperationClass(String clazz) {
        return (T)factory.newInstance(clazz);
    }

}
