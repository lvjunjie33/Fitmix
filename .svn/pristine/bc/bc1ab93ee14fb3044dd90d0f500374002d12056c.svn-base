package com.business.work.codeMessage;

import com.business.core.entity.code.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sin on 2015/11/23.
 */
@Service
public class CodeMessageService {

    @Autowired
    private CodeMessageDao codeMessageDao;

    /**
     * 查询所有 错误信息
     * @return 集合
     */
    public List<CodeMessage> page() {
        return codeMessageDao.findAll();
    }


    /**
     * 添加错误信息
     * @param sys 系统
     * @param code 错误编号
     * @param memo 备注信息
     * @param msgChina 错误信息（中文）
     * @param msgEnglish 错误信息（english）
     * @return
     */
    public int add(String sys, int code, String msgChina, String msgEnglish, String memo) {
        if (codeMessageDao.findCodeMessageByCode(code) != null) {
            return 1;
        }
        CodeMessage codeMessage = new CodeMessage();
        codeMessage.setSys(sys);
        codeMessage.setMemo(memo);
        codeMessage.setCode(code);
        codeMessage.setMsgChina(msgChina);
        codeMessage.setMsgEnglish(msgEnglish);

        codeMessageDao.insertCodeMessage(codeMessage);
        return 0;
    }


    /**
     * 修改错误信息
     * @param id 编号
     * @param sys 系统
     * @param code 错误编号
     * @param memo 备注信息
     * @param msgChina 错误信息（中文）
     * @param msgEnglish 错误信息（english）
     * @return
     */
    public void modify(int id, String sys, int code, String msgChina, String msgEnglish, String memo) {
        codeMessageDao.updateCodeMessageById(id, Update.update("sys", sys).
                set("code", code).set("msgChina", msgChina).set("msgEnglish", msgEnglish).set("memo", memo));
    }

    /**
     * 查询消息码
     * @param id 主键编号
     */
    public CodeMessage findById(Integer id) {
        return codeMessageDao.findById(id);
    }
}
