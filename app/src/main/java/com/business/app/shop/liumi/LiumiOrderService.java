package com.business.app.shop.liumi;

import com.alibaba.fastjson.JSONObject;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.excetion.AppBusinessExcetion;
import com.business.app.mapper.*;
import com.business.app.user.UserDao;
import com.business.app.userRun.UserRunDao;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.constants.Constants;
import com.business.core.constants.OrderStatus;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.entity.shop.LiumiOrder;
import com.business.core.entity.shop.Product;
import com.business.core.entity.user.Account;
import com.business.core.entity.user.AccountFlow;
import com.business.core.operations.sysManager.SysManagerDao;
import com.business.core.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zhangtao on 2016/11/21.
 */
@Service
public class LiumiOrderService extends AbstractServiceImpl<LiumiOrder> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LiumiOrderMapper liumiOrderMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AccountFlowMapper accountFlowMapper;
    @Autowired
    private UserRunStatisticsMapper userRunStatisticsMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRunDao userRunDao;
    @Autowired
    private SysManagerDao sysManagerDao;

    @Override
    protected AbstractMapper<LiumiOrder> getAbstractMapper() {
        return liumiOrderMapper;
    }

    @Transactional
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    /**
     * 下订单
     * @param mobile 手机号码
     * @param uid 用户编号
     * @param productId 产品编号
     * @param language 语言
     */
    @Transactional
    public LiumiOrder placeOrder(HttpServletRequest request, String mobile, Integer uid, Integer productId, String language) {
        // 1.获取账号信息
        Account account = accountMapper.selectByUid(uid);
        /*User user = userDao.findUserById(uid, "addTime");
        if (System.currentTimeMillis() - user.getAddTime() < 10 * 24 * 60 * 60 * 1000) {// 如果10天以内(新帐号)
            Long size = userRunDao.findUserRunCount(uid);
            if (account.getCoin() >= 1000 && size < 10) {//一个帐号的金币数大于1000
                saveShopError(com.business.app.base.constants.Constants.SHOP_NEW_USER_ERROR, JSON.toJSONString(account), request);
                throw new AppBusinessExcetion(ShopConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, CodeMessageUtil.format(ShopConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, language));
            }
        } else {
            UserRunStatistics userRunStatistics = userRunStatisticsMapper.selectByUid(uid);
            if (userRunStatistics != null) {
                Integer level = Integer.parseInt(userRunStatistics.getRunLevel().substring(10));
                Map<String, Object> p = new HashMap<>();
                p.put("aid", account.getId());
                p.put("flowType", AccountFlow.FLOW_TYPE_GAIN);
                List<AccountFlow> accountFlows = accountFlowMapper.selectByParams(p);
                for (AccountFlow af : accountFlows) {
                    if (!AccountFlow.levelStr.containsKey(af.getDescription())) {
                        continue;
                    }
                    if(AccountFlow.levelStr.get(af.getDescription()) > level) {//校验跑步等级一致，不一致则直接返回错误
                        saveShopError(com.business.app.base.constants.Constants.SHOP_USER_RUN_LEVEL_ERROR, JSON.toJSONString(account), request);
                        throw new AppBusinessExcetion(ShopConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, CodeMessageUtil.format(ShopConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, language));
                    }
                }
            }
        }*/

        LiumiOrder liumiOrder;
        //0.校验手机号是否合法
        if(!LiumiUtil.isMobile(mobile)) {
            throw new AppBusinessExcetion(CodeConstants.MOBILE_INVALID, CodeMessageUtil.format(CodeConstants.MOBILE_INVALID, language));
        }

        // 2.获取产品信息
        Product product = productMapper.selectByPrimaryKey(productId);

        if (account.getCoin() < product.getCoin()) {
            throw new AppBusinessExcetion(CodeConstants.BALANCE_NOT_ENOUGH, CodeMessageUtil.format(CodeConstants.BALANCE_NOT_ENOUGH, language));
        } else if (Product.STATUS_INVALID.equals(product.getStatus()) || !Product.TYPE_LIUMI.equals(product.getType())) {
            throw new AppBusinessExcetion(CodeConstants.INVALID_GOOD, CodeMessageUtil.format(CodeConstants.INVALID_GOOD, language));
        } else {
            // 3.减库存
            if (productMapper.reduceStocks(productId, 1) == 0) {
                throw new AppBusinessExcetion(CodeConstants.STOCKS_NOT_ENOUGH, CodeMessageUtil.format(CodeConstants.STOCKS_NOT_ENOUGH, language));
            }

            // 4.扣费
            if (accountMapper.reduceBalance(uid, product.getCoin()) == 0) {
                throw new AppBusinessExcetion(CodeConstants.BALANCE_NOT_ENOUGH, CodeMessageUtil.format(CodeConstants.BALANCE_NOT_ENOUGH, language));
            }
            // 账号明细
            AccountFlow accountFlow = new AccountFlow(product.getCoin(), uid, String.format("兑换-%s", product.getName()), account.getId(),
                    AccountFlow.FLOW_TYPE_EXPENSE, System.currentTimeMillis(), System.currentTimeMillis());
            accountFlowMapper.insertSelective(accountFlow);

            // 5.生成订单信息
            String orderNo = PayUtil.createOrderNo();
            String token = RedisUtil.cacheGet(RedisConstants.LIUMI_TOKEN_DB, RedisConstants.LIUMI_TOKEN_KEY);
            if (token == null) {
                JSONObject jsonObject = JSONObject.parseObject(LiumiUtil.getToken());
                if (jsonObject.getString("code").equals("000")) {
                    token = jsonObject.getJSONObject("data").get("token").toString();
                }

                if (token == null) {
                    logger.error("获取流米流量 TOKEN 结果：" + jsonObject.toJSONString());
                    throw new AppBusinessExcetion(CodeConstants.REQUEST_BAD_LIUMI_GET_TOKEN, CodeMessageUtil.format(CodeConstants.REQUEST_BAD_LIUMI_GET_TOKEN, language));
                }

                RedisUtil.cacheSetNx(RedisConstants.LIUMI_TOKEN_DB, RedisConstants.LIUMI_TOKEN_KEY, token, RedisConstants.LIUMI_TOKEN_EXPIRE_TIME);
            }
            // 下单
            String result = LiumiUtil.placeOrder(mobile, orderNo, product.getVirtualKey(), token);

            if (JSONObject.parseObject(result).getString("code").equals("000")) { // 成功
                liumiOrder = new LiumiOrder(uid, productId, JSONObject.parseObject(result).getJSONObject("data").getString("orderNO"),
                        orderNo, product.getCoin(), mobile, product.getVirtualKey(), OrderStatus.CREATED.toString(),
                        result, null, System.currentTimeMillis(), System.currentTimeMillis());
                liumiOrderMapper.insertSelective(liumiOrder);
            } else if(JSONObject.parseObject(result).getString("code").equals("008")) { //token 失效
                // 重新获取 Token
                token = null;
                JSONObject jsonObject = JSONObject.parseObject(LiumiUtil.getToken());
                if (jsonObject.getString("code").equals("000")) {
                    token = jsonObject.getJSONObject("data").get("token").toString();
                }
                if (token == null) {
                    logger.error("获取流米流量 TOKEN 结果：" + jsonObject.toJSONString());
                    throw new AppBusinessExcetion(CodeConstants.REQUEST_BAD_LIUMI_GET_TOKEN, CodeMessageUtil.format(CodeConstants.REQUEST_BAD_LIUMI_GET_TOKEN, language));
                }

                RedisUtil.cacheSetNx(RedisConstants.LIUMI_TOKEN_DB, RedisConstants.LIUMI_TOKEN_KEY, token, RedisConstants.LIUMI_TOKEN_EXPIRE_TIME);
                // 重新下单
                result = LiumiUtil.placeOrder(mobile, orderNo, product.getVirtualKey(), token);
                if (JSONObject.parseObject(result).getString("code").equals("000")) {
                    liumiOrder = new LiumiOrder(uid, productId, JSONObject.parseObject(result).getJSONObject("data").getString("orderNO"),
                            orderNo, product.getCoin(), mobile, product.getVirtualKey(), OrderStatus.CREATED.toString(),
                            result, null, System.currentTimeMillis(), System.currentTimeMillis());
                    liumiOrderMapper.insertSelective(liumiOrder);
                } else {
                    logger.error("提交流米流量订单结果：" + result);
                    throw new AppBusinessExcetion(CodeConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, CodeMessageUtil.format(CodeConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, language));
                }
            } else {
                logger.error("提交流米流量订单结果：" + result);
                throw new AppBusinessExcetion(CodeConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, CodeMessageUtil.format(CodeConstants.REQUEST_BAD_LIUMI_PLACE_ORDER, language));
            }
        }
        return liumiOrder;
    }

    /**
     * 处理回调结果
     * @param flag flag
     * @param liumiOrder LM订单
     * @param result 结果
     */
    @Transactional
    public void callbackHandle(Boolean flag, LiumiOrder liumiOrder, String result) {
        if (flag) {
            liumiOrder.setStatus(OrderStatus.SUCCESS.toString());
            liumiOrder.setCallback(result);
            liumiOrderMapper.updateByPrimaryKeySelective(liumiOrder);
        } else {
            Product product = productMapper.selectByPrimaryKey(liumiOrder.getProductId());
            // 更改状态
            liumiOrder.setStatus(OrderStatus.FAIL.toString());
            liumiOrder.setCallback(result);
            liumiOrderMapper.updateByPrimaryKeySelective(liumiOrder);
            // 记录详细
            Account account = accountMapper.selectByUid(liumiOrder.getUid());
            AccountFlow accountFlow = new AccountFlow(liumiOrder.getCoin(), account.getUid(), String.format("退还金币-%s", product.getName()), account.getId(),
                    AccountFlow.FLOW_TYPE_GAIN, System.currentTimeMillis(), System.currentTimeMillis());
            accountFlowMapper.insertSelective(accountFlow);
            // 增加金币
            accountMapper.augmentBalance(account.getUid(), liumiOrder.getCoin());
            // 增加库存
            productMapper.augmentStocks(product.getId(), 1);
        }
    }

    /**
     * 根据 订单号 查询
     *
     * @param orderNo 订单编号
     */
    public LiumiOrder selectByOrderNo(String orderNo) {
        return liumiOrderMapper.selectByOrderNo(orderNo);
    }

    /**
     * 保存商品异常
     */
    private void saveShopError(String content, String other, HttpServletRequest request) {
        SysErrorLog log = new SysErrorLog();
        log.setException(content);
        log.setUserId(null);
        log.setSys(Constants.SERVER_COMMON);

        log.setRequestParams(other);
        log.setUa(HttpUtil.getUA(request));
        log.setIp(HttpUtil.getIP(request));
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);
    }
}
