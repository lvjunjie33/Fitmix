package com.business.work.language;

import com.business.core.entity.Page;
import com.business.core.entity.language.CharTable;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by edward on 2017/8/17.
 */
@Controller
@RequestMapping("char/table")
public class CharTableController extends BaseControllerSupport{

    @Autowired
    private CharTableService charTableService;

    @RequestMapping("add")
    public void addCharTable(String strCN) {
        charTableService.addCharTable(strCN);
    }

    @RequestMapping("to/compared")
    public String toCompared(Integer table) {
        if (StringUtils.isEmpty(table)) {
            return redirect("char/table/manager.htm");
        }
        charTableService.toCompared(table);
        return redirect("char/table/manager.htm");
    }

    @RequestMapping("manager")
    public String manager(Page<CharTable> page) {
        page.removeEmptys("strCN", "isEmpty", "sort").convertInt("isEmpty", "sort");
        charTableService.page(page);
        return "language/list";
    }

    @RequestMapping("edit/en")
    public void editEn(String strCN, String strEN) {
        charTableService.editEn(strCN, strEN);
    }

    public void remove() {
        BeanManager.getBean(CharTableDao.class).delete();
    }

}
