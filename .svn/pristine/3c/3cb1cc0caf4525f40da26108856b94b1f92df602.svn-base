package com.business.work.notice;

import com.business.core.entity.Page;
import com.business.core.entity.notice.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sin on 2016/1/5.
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    public void page(Page<Notice> page) {
        noticeDao.page(page);
    }

    public List<Notice> export(Page<Notice> page) {
        return noticeDao.export(page);
    }
}
