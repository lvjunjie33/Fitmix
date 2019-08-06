package com.business.work.sysManager;

import com.business.core.entity.Page;
import com.business.core.entity.logs.RequestLog;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.sysManager.SysManagerDao;
import com.business.core.operations.sysManager.SysManagerService;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by edward on 2016/4/18.
 *
 * 系统管理 Controller
 *
 * {1、异常日志管理}
 */
@Controller
@RequestMapping("sys")
public class SysManagerController {

    @Autowired
    private SysManagerService sysManagerService;

    /**
     * 异常列表清单
     */
    @RequestMapping("error-log-list")
    public String findErrorLogList(Page<SysErrorLog> page) {
        page.removeEmptys("sys", "solveStatus", "ip", "beginTime", "endTime", "matchedPath").convertInt("solveStatus", "sys")
                .convertDate2("beginTime", "bTime", "yyyy-MM-dd").convertDate2("endTime", "eTime", "yyyy-MM-dd");
        sysManagerService.findErrorLogList(page);
        return "/sysManager/error-manager";
    }

    /**
     *  修改异常日志处理状态
     *
     * @param id 异常日志编号
     * @param solveStatus 新的处理状态
     */
    @RequestMapping("modify-log-status")
    public void modifySysErrorLog(Integer id, Integer solveStatus) {
        sysManagerService.modifySysErrorLog(solveStatus, id);
    }

    @RequestMapping("r-s")
    public void removeSysLog(String bTime, String eTime) {
        BeanManager.getBean(SysManagerDao.class).removeSysErrorLog(DateUtil.parse(bTime, "yyyy-MM-dd"), DateUtil.parse(eTime, "yyyy-MM-dd"));
    }
}
