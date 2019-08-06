package com.business.app.easeMob;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONToken;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.easeMob.api.AuthTokenAPI;
import com.business.app.easeMob.api.EasemobRestAPI;
import com.business.app.easeMob.api.impl.EasemobAuthToken;
import com.business.app.easeMob.api.impl.EasemobIMUsers;
import com.business.app.easeMob.comm.ClientContext;
import com.business.app.easeMob.comm.EasemobRestAPIFactory;
import com.business.app.easeMob.comm.body.IMUserBody;
import com.business.app.easeMob.comm.body.IMUsersBody;
import com.business.app.easeMob.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2016/10/15.
 */
@RequestMapping("tt")
@Controller
public class TestController extends BaseControllerSupport{

    @RequestMapping("aa")
    public void aa() {
        EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        EasemobAuthToken easemobAuthToken = (EasemobAuthToken)factory.newInstance(EasemobRestAPIFactory.TOKEN_CLASS);

        ClientContext clientContext = easemobAuthToken.getContext();

        ResponseWrapper responseWrapper = (ResponseWrapper)easemobAuthToken.getAuthToken(clientContext.getClientId(), clientContext.getClientSecret());
        ObjectNode result = (ObjectNode) responseWrapper.getResponseBody();
        result.get("access_token");
        result.get("expires_in");
        result.get("application");
        System.out.println(result);

        EasemobIMUsers easemobIMUsers= (EasemobIMUsers)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        List<IMUserBody> users = new ArrayList<>();

        for (int i = 3; i < 6; i++) {
            IMUserBody user = new IMUserBody("test123" + i, "test123" + i, null);
            users.add(user);
        }
        ResponseWrapper responseWrapper2 = (ResponseWrapper)easemobIMUsers.createNewIMUserBatch(new IMUsersBody(users));
        ObjectNode result2 = (ObjectNode) responseWrapper2.getResponseBody();
        System.out.println(result2);
    }
}
