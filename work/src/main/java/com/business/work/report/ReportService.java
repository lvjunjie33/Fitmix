package com.business.work.report;

import com.business.core.entity.Page;
import com.business.core.entity.report.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by edward on 2017/6/28.
 */
@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    /**
     * 查询某个手表测试报告
     * @param id 编号
     */
    public Watch findWatchById(Integer id) {
        return reportDao.findWatchById(id);
    }

    /**
     * 手表测试报告分页
     *
     * @param page 分页对象
     */
    public void watchPage(Page<Watch> page) {
        reportDao.watchPage(page);
    }
}
