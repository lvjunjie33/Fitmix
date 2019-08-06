package com.business.app.clubRanking;

import com.business.app.base.support.BaseControllerSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by sin on 2015/11/20.
 */
@Api(value = "club-ranking", description = "俱乐部排名")
@Controller
@RequestMapping("club-ranking")
public class ClubRankingController extends BaseControllerSupport {

    @Autowired
    private ClubRankingService clubRankingService;

    /**
     * 运动排行榜
     *
     * @param clubId 俱乐部编号
     * @param type 排行类型：2、周 3、月 {@link com.business.core.entity.club.ClubRanking}
     */
    @ApiOperation(value = "ranking", notes = "运动排行榜", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("ranking")
    public void ranking(@ApiIgnore Model model,
                        @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                        @ApiParam(required = true, value = "排行类型:2、周 3、月") @RequestParam("type") Integer type) {
        Object[] result = clubRankingService.ranking(clubId, type);
        model.addAttribute("statView", result[1]);
        model.addAttribute("totalCount", result[2]);
    }


    /**
     * 俱乐部用户 排行榜
     *
     * @param clubId 俱乐部编号
     * @param type 类型 2、周 3、月 {@link com.business.core.entity.club.ClubRanking}
     * @param index 下标
     */
    @ApiOperation(value = "user-ranking", notes = "俱乐部用户 排行榜", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("user-ranking")
    public void userRanking(@ApiIgnore Model model,
                            @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                            @ApiParam(required = true, value = "排行类型:2、周 3、月") @RequestParam("type") Integer type,
                            @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        model.addAttribute("userRanking", clubRankingService.userRanking(clubId, type, index));
    }
}
