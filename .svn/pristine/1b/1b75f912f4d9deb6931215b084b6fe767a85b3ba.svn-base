package com.business.core.operations.noticeLog;

import com.business.core.entity.notice.log.NoticeLog;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by sin on 2015/12/9.
 */
@Service
public class NoticeLogCoreService {

    public NoticeLog buildNoticeLog(Integer uid, String name, String idfa, String content) {
        NoticeLog noticeLog = new NoticeLog();
        noticeLog.setUid(uid);
        noticeLog.setName(name);
        noticeLog.setIdfa(idfa);
        noticeLog.setContent(content);
        noticeLog.setAddTime(System.currentTimeMillis());
        noticeLog.setAddDate(new Date());
        return noticeLog;
    }
}
