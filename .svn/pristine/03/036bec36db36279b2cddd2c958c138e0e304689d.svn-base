package com.business.scheduler.base;

import com.business.core.mongo.BaseMongoDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时器具体任务实现任务基类。所有子类需要继承它.
 * <pre>
 *     1. 可以使用{@link #logger}进行写日志
 *     2. 使用{@link #setMemo(String)}可以设置任务结束后备注
 *     3. 当任务出现异常时，会被记录到日志里并标记任务失败。所以任务的异常需要抛出来，不要catch掉不抛出。
 *     4. 记得实现类加{@link org.springframework.stereotype.Service}注解，让它可以被spring扫描到
 * </pre>
 * User: sin
 * Date: 15-8-26
 * Time: 下午9:20
 */
public abstract class BaseJob extends BaseMongoDaoSupport {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 任务执行完之后的备注
     */
    private String memo;

    /**
     * 实现
     */
    public abstract void execute();


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
