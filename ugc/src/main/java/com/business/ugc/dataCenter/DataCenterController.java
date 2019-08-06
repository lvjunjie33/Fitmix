package com.business.ugc.dataCenter;

import com.business.core.constants.Constants;
import com.business.core.entity.mix.MixAlbum;
import com.business.ugc.base.support.BaseControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fenxio on 2016/8/19.
 */
@Controller
@RequestMapping("ugc/data")
public class DataCenterController extends BaseControllerSupport {

    /**
     * 数据统计
     * @param uid
     * @param type
     * @param request
     * @return
     */
    @RequestMapping("/{uid}/type/{type}")
    public String getUserData(@PathVariable("uid") Long uid, @PathVariable("type") Integer type, HttpServletRequest request) {
        List<MixAlbum> list  = (List<MixAlbum>) request.getSession().getAttribute(Constants.MIX_ALBUM_LIST);
        if(null == list || list.size() == 0) {
            return redirect("ugc/content/"+uid+"/albums/0/programs");
        }
        return "dataCenter/overview";
    }

}
