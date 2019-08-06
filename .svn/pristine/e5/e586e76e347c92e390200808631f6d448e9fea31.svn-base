package com.business.work.club;

import com.alibaba.fastjson.JSON;
import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.work.user.UserDao;
import com.business.work.userRun.UserRunDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangtao on 2016/4/20.
 */
@Service
public class ClubService {

    @Autowired
    private ClubDao clubDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRunDao userRunDao;

    /**
     * 获取 俱乐部列表
     * @param page 分页
     */
    public void list(Page<Club> page) {
        clubDao.findClubPage(page);
        //获取创建人员信息
        List<User> userList = userDao.findUserByUid(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"),"id","name");
        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
        for(Club club : page.getResult()){
            club.setUser(userMap.get(club.getUid()));
        }
    }

    /**
     * 获取 俱乐部信息
     * @param id
     * @return
     */
    public Club findClubById(Integer id) {
        Club club = clubDao.findClubById(id);
        User user = userDao.findUserByUid(club.getUid());
        club.setUser(user);
        return club;
    }

    /**
     * 根据 club id 获取club成员信息
     * @param page 分页信息
     * @param id 俱乐部ID
     */
    public void findMemberInfoById(Page<ClubMember> page, Integer id) {
        clubDao.findMemberInfoById(page,id);
        //获取创建人员信息
        List<User> userList = userDao.findUserByUid(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"),"id","name");
        //获取用户最后一次运动信息
        List<UserRun> userRunList = userRunDao.findUserRunByUid(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"));

        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
        Map<Integer, UserRun> userRunMap = CollectionUtil.buildMap(userRunList, Integer.class, UserRun.class, "uid");

        for(ClubMember clubMember : page.getResult()){
            if(userMap.containsKey(clubMember.getUid())){
                if(userRunMap.containsKey(clubMember.getUid())){
                    userMap.get(clubMember.getUid()).setLastRun(userRunMap.get(clubMember.getUid()));
                }
                clubMember.setUser(userMap.get(clubMember.getUid()));

            }
        }
    }

    /**
     * 查询俱乐部综合信息
     * 导出俱乐部的部分用户数据
     */
    public List<List<String>> findClubComplexInfo(Page<Club> page) {
        clubDao.findClubPage(page, "name", "memberCount", "desc", "addTime", "status", "uid");

        List<Club> clubs = page.getResult();
        List<User> users = userDao.findUserByUid(CollectionUtil.buildList(clubs, Integer.class, "uid"), "name", "mobile");

        Map<Integer, User> userMaps = CollectionUtil.buildMap(users, Integer.class, User.class, "id");
        List<List<String>> exportData = new ArrayList<>();
        for (Club club : clubs) {
            List<String> row = new ArrayList<>();
            row.add(club.getName());
            row.add(club.getMemberCount() == null ? "0" : club.getMemberCount().toString());
            row.add(DateUtil.formatTimestamp(club.getAddTime(), "yyyy年MM月dd日"));
            row.add(club.getStatus() == 1 ? "可用" : "不可用");
            if (userMaps.containsKey(club.getUid())) {
                User user = userMaps.get(club.getUid());
                row.add(user.getName());
                row.add(user.getMobile());
            } else {
                row.add("");
                row.add("");
            }
            row.add(club.getDesc());
            exportData.add(row);
        }
        return exportData;
    }

    /**
     * 修改俱乐部人数限制
     *
     * @param id 俱乐部编号
     * @param maxMember 俱乐部人数
     */
    public void setMaxMember(Long id, Integer maxMember) {
        Long num = clubDao.findClubMemberCount(id);
        if (num < maxMember) {
            clubDao.modifyClub(id, Update.update("maxMember", maxMember));
        }
    }
}
