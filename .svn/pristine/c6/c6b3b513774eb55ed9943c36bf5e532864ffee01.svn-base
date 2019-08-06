package com.business.app.notice;

import com.alibaba.fastjson.JSON;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.constants.ViewConstants;
import com.business.core.entity.notice.Notice;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/12/9.
 *
 * TODO 这个类的 api目前不提供
 */
@ApiIgnore
@Controller
@RequestMapping("notice")
public class NoticeController extends BaseControllerSupport {

    @Autowired
    private NoticeService noticeService;


    /**
     * http://ios.api.i4.cn/appactivatecb.xhtml?aisicid=200147&aisi=253438&appid=999239164&rt=1&mac=mac&idfa=idfa
         aisicid=200147
         aisi=253438
         appid=999239164
     * 三方渠道 爱思
     * @param appid appStore 编号
     * @param mac 手机 mac 地址
     * @param idfa 手机特有的 idfa
     * @param openudid 不知道什么鬼
     * @param os 不晓得 爱思个傻逼
     * @param callback 回调个地址
     */
    @RequestMapping("aiSi")
    public void  aiSi(Model model,
                     @RequestParam("appid") String appid,
                     @RequestParam(value = "mac", required = false) String mac,
                     @RequestParam(value = "idfa") String idfa,
                     @RequestParam(value = "openudid", required = false) String openudid,
                     @RequestParam(value = "os", required = false) String os,
                     @RequestParam(value = "callback", required = false) String callback) {
        int result = noticeService.addNotice(idfa, Notice.CHANNEL_AI_SI);
        switch (result) {
            case 0:
                break;
            case 1:
                model.addAttribute(ViewConstants.KEY_CODE, 1);
                model.addAttribute(ViewConstants.KEY_MSG, "重复提交");
                break;
        }
    }


    ///
    /// 点入移动


    @RequestMapping("dianRu")
    @ResponseBody
    public String dianRu(@RequestParam(value = "idfa_list") String idfa_list) {
        List<String> idfaList = CollectionUtil.splitList(idfa_list, ",");
        Object[] result = noticeService.dianRu(idfaList);
        switch ((int)result[0]) {
            case 0:
                return JSON.toJSONString(result[1]);
            default :
                Map<String, Object> paramsMap = new LinkedHashMap<>();
                paramsMap.put(ViewConstants.KEY_CODE, 9002);
                paramsMap.put(ViewConstants.KEY_MSG, "idfa_list is null");
                return JSON.toJSONString(paramsMap);
        }
    }
}
