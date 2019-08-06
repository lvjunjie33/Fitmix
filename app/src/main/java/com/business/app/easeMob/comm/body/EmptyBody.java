package com.business.app.easeMob.comm.body;

import com.business.app.easeMob.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * Created by edward on 2016/10/24.
 */
public class EmptyBody implements BodyWrapper {

    public static final String EMPTY = "empty";

    @Override
    public ContainerNode<?> getBody() {
        return JsonNodeFactory.instance.objectNode().put("empty", "empty");
    }

    @Override
    public Boolean validate() {
        return true;
    }
}
