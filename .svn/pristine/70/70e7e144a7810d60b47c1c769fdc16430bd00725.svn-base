package com.business.work.announcement;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.sys.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by edward on 2017/9/25.
 */
@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    /**
     * 公告分页
     *
     * @param page 分页对象
     */
    public void page(Page<Announcement> page) {
        announcementDao.page(page);
    }

    /**
     * 新增公告
     *
     * @param announcement 公告对象
     */
    public void add(Announcement announcement) {
        announcement.setAddTime(System.currentTimeMillis());
        announcement.setStatus(Constants.SWITCH_CLOSE);
        announcementDao.add(announcement);
    }

    /**
     * 查询公告
     *
     * @param id 公告编号
     */
    public Announcement findById(Integer id) {
        return announcementDao.findById(id);
    }

    /**
     * 修改公告
     *
     * @param imgLink 图片
     * @param body 内容
     * @param desc 描述
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param displayNum 显示次数
     */
    public void modify(Integer id, String imgLink, String body, String desc, Long bTime, Long eTime, Integer displayNum, Integer status) {
        Update update = Update.update("body", body).set("desc", desc).set("bTime", bTime).set("eTime", eTime).set("displayNum", displayNum).set("status", status);
        if (!StringUtils.isEmpty(imgLink)) {
            update.set("imgLink", imgLink);
        }
        announcementDao.modify(id, update);
    }
}
