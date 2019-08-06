package com.business.app.message;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.info.UserGroup;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by edward on 2016/4/25.
 * 移动端消息获取接口
 */
@Controller
@RequestMapping()
public class MessageController extends BaseControllerSupport {

    @Autowired
    private MessageService messageService;

    /**
     * 消息id
     * @param msgId 消息编号
     */
    @RequestMapping("/message/get-message-body")
    public void getMessageBody(Model model,
                               @ApiParam(required = true, value = "消息编号") @RequestParam("msgId") Long msgId) {
        //检查该用户有该消息
//        model.addAttribute("body", messageService.findMessageById(msgId).getBody());
    }

    /**
     * 修改已读消息状态
     *
     * @param msgId 消息编号
     * @param uid 用户编号
     */
    @RequestMapping("/message/read-message2")
    public void readMessage(@ApiParam(required = true, value = "消息编号") @RequestParam("msgId") Long msgId,
                            @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Long uid) {
        if (StringUtils.isEmpty(uid)) {
            return;
        }
//        messageService.updateMessageTaskRecordStatus(msgId, uid.toString());
    }

    /**
     * 修改已读消息状态
     *
     * @param pushType 推送类型 详细类型
     * @param businessId 业务编号 clubId, activityId,等等
     */
    @RequestMapping("/message/read-message")
    public void readMessage(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Long uid,
                            @ApiParam(required = true, value = "推送类型") @RequestParam("pushType") Integer pushType,
                            @ApiParam(required = true, value = "业务编号") @RequestParam(value = "businessId", required = false) String businessId) {
//        messageService.readMessage(uid, pushType, businessId);
    }

    /**
     * 拉取用户消息记录
     * @param uid 用户编号
     */
    @RequestMapping("/message/pull-message")
    public void pullMessage(@ApiIgnore Model model,
                            @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid) {
//        model.addAttribute("bodys", messageService.pullMyMessage(uid));
    }

    //======================================end 上面的接口已经废弃====================================================


    //==========================================用户私信============================================

    /**
     * 发送私信
     *
     * @param uid me(我)
     * @param targetUid her/his(目标用户)
     * @param content 内容
     */
    @RequestMapping("/send/private/msg")
    public void sendUserPrivateMsg(Model model, Integer uid, Integer targetUid, String content) {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(targetUid) || StringUtils.isEmpty(content)) {
            tip(model, CodeConstants.USER_PRIVATE_MSG_ERROR);
            return;
        }
        Object[] objects = messageService.sendPrivateMsg(uid, targetUid, content);
        Integer code = (Integer) objects[0];
        if (code == 0) {
            model.addAttribute("groupId", objects[1]);
            model.addAttribute("message", objects[2]);
        } else if (code == 1) {
            tip(model, CodeConstants.USER_PRIVATE_MSG_TARGET_REJECT_TRUE);
            return;
        } else if(code == 2 || code == 3) {
            tip(model, CodeConstants.USER_PRIVATE_MSG_FREQUENT);
            return;
        } else if (code == 4) {
            tip(model, CodeConstants.USER_PRIVATE_MSG_FROM_REJECT_TRUE);
            return;
        }
    }

    /**
     * 查询私信列表
     *
     * @param page 分页对象
     * @param uid 用户编号
     */
    @RequestMapping("/get/private/msg/list")
    public void getUserPrivateMsgList(Page<UserGroup> page, Integer uid) {
        if (StringUtils.isEmpty(uid)) {
            return;
        }
        page.removeEmptys("type", "uid");
        page.getFilter().put("type", UserGroup.TYPE_USER_PRIVATE);
        page.getFilter().put("uid", uid);
        messageService.getUserPrivateMsg(page, uid);
    }

    /**
     * 查询私信详细
     * @param groupId 群组编号
     * @param uid 用户编号
     */
    @RequestMapping("/get/private/msg/info")
    public void getUserPrivateMsgInfo(Page<Message> page, Long groupId, Integer uid) {
        page.removeEmptys("groupId", "msgId");
        page.getFilter().put("groupId", groupId.toString());
        page.getFilter().put("selectChannel", MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG);
        messageService.getUserPrivateMsgInfo(page, groupId, uid);
    }

    /**
     * 私信关系设置(黑名单)
     *
     * @param uid 用户编号
     * @param groupId 群组编号
     * @param handleType 关系类型,1、黑名单，0、正常关系
     */
    @RequestMapping("/set/private/msg/reject")
    public void setUserPrivateMsgReject(Integer uid, Long groupId, Integer handleType) {
        messageService.setUserPrivateMsgReject(uid, groupId, handleType);
    }

    /**
     * 删除私信
     *
     * @param uid 用户编号
     * @param groupId 群组编号
     */
    @RequestMapping("/del/private/msg")
    public void deleteUserPrivateMsg(Integer uid, Long groupId) {
        messageService.deleteUserPrivateMsg(uid, groupId);
    }


    //===========================================通知==============================================================

    /**
     * 获取用户通知列表
     *
     * @param uid 用户编号
     */
    @RequestMapping("get/user/notice")
    public void getUserNotice(Model model, Integer uid) {
        List<Message> messages = messageService.getUserNotice(uid);
        model.addAttribute("notices", messages);
    }

    /**
     * 读取消息
     *
     * @param id 消息编号
     * @param uid 用户编号
     */
    @RequestMapping("read/user/msg")
    public void readUserMsg(Long id, Integer uid) {
        messageService.readUserMsg(id, uid);
    }

}
