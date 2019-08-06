package com.business.work.gw;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.file.File;
import com.business.core.entity.gw.CommonProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by edward on 2018/9/18.
 */
@Service
public class GwService {
    @Autowired
    private GwDao gwDao;
    public void page(Page<CommonProblem> page) {
        gwDao.page(page);
    }

    public void add(CommonProblem commonProblem,String des,String title) {
        if("zh".equals(commonProblem.getProblem_lan())){
            commonProblem.setProblemTitle(title);
            commonProblem.setProblemContent(des);
            commonProblem.setProblemTitle_en("");
            commonProblem.setProblemContent_en("");
        }
        if("en".equals(commonProblem.getProblem_lan())){
            commonProblem.setProblemTitle_en(title);
            commonProblem.setProblemContent_en(des);
            commonProblem.setProblemTitle("");
            commonProblem.setProblemContent("");
        }
        commonProblem.setAddTime(System.currentTimeMillis());
        commonProblem.setStatus(Constants.STATE_EFFECTIVE);
        gwDao.add(commonProblem);
    }

    public CommonProblem findCommonProblemById(Long id) {
        return gwDao.findCommonProblemById(id);
    }

    public void modifyFile(CommonProblem commonProblem,String des,String title) {
        Update update =new Update();
        if("zh".equals(commonProblem.getProblem_lan())){
            update.set("problemTitle", title).set("problemContent",des);
        }
        if("en".equals(commonProblem.getProblem_lan())){
            update.set("problemTitle_en", title).set("problemContent_en",des);
        }
        update.set("problem_lan",commonProblem.getProblem_lan());
        gwDao.modify(commonProblem.getId(), update);
    }
    public CommonProblem findFileByIdAjax(Long id, String lan) {
        CommonProblem commonProblem = gwDao.findFileAjax(id,lan);
        if(commonProblem==null){
            commonProblem=new CommonProblem();
            commonProblem.setProblem_lan("lan");
            Update update = Update.update("problem_lan", lan);
            gwDao.modify(id, update);
            commonProblem = gwDao.findFileAjax(id,lan);
        }
        return commonProblem;
    }

    public void commonProblemDelete(CommonProblem commonProblem) {
        gwDao.deleteFile(commonProblem.getId());
    }



}
