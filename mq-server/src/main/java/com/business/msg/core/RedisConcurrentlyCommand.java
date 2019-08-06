package com.business.msg.core;

/**
 * Created by edward on 2017/4/25.
 * 并发消费者命令
 * 尽量减少在mq中的具体业务逻辑
 * 保证消息队列的功能单一、稳定、以及处理效率
 */
public interface RedisConcurrentlyCommand {

    /**
     * 执行命令
     * (说明: 实现了该接口的类，在该方法中自己处理异常)
     *
     * @param msg MQ消息
     * @return 执行结果
     */
    void execute(String msg);
}
