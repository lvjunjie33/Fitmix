package com.business.work.mixComment;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixComment;
import com.business.core.operations.mixComment.MixCommentCoreService;
import com.business.work.mix.MixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Controller
@RequestMapping("mix-comment")
public class MixCommentController {

    @Autowired
    private MixService mixService;
    @Autowired
    private MixCommentService mixCommentService;
    @Autowired
    private MixCommentCoreService mixCommentCoreService;

    /**
     * 分页查询
     * @param page
     */
    @RequestMapping("mix-comment-page")
    public String mixCommentPage(Model model,
                                 Page<MixComment> page,
                                 @RequestParam("mid") Integer mid) {
        page.getFilter().put("mid", mid);
        mixCommentService.mixCommentPage(page);
        mixCommentCoreService.buildMixCommentWithUserAndReplayUserInfo(page.getResult());
        model.addAttribute("mix", mixService.mixInfo(mid));
        return "mixComment/mix-comment-page";
    }

    /**
     * 删除 mix歌曲评论
     * @param id 编号
     */
    @RequestMapping("remove-mix-comment")
    public void removeMixComment(@RequestParam("id") Long id) {
        mixCommentService.removeMixComment(id);
    }

    /**
     * 置顶评论
     * @param mid 用户编号
     * @param id 评论编号
     */
    @RequestMapping("top-mix-comment")
    public void topMixComment(@RequestParam("mid") Integer mid, @RequestParam("id") Long id) {
        mixCommentService.topMixComment(mid, id);
    }
}
