package com.business.work.wxMenu;

import ch.ethz.ssh2.crypto.cipher.DES;
import com.business.core.entity.wx.WXCustomerService;
import com.business.core.entity.wx.WXMenu;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/10/21.
 */
@Repository
public class WXMenuDao extends BaseMongoDaoSupport {

    /**
     * 获取所有 微信菜单
     * @return 微信菜单
     */
    public List<WXMenu> findAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        return getRoutingMongoOps().find(query, WXMenu.class);
    }

    /**
     * 查询所有父级菜单
     * @return
     */
    public List<WXMenu> findAllParentWXMenu() {
        Query query = new Query(Criteria.where("parent").is(null));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        return getRoutingMongoOps().find(query, WXMenu.class);
    }

    /**
     * 查询微信菜单
     * @param id 菜单编号
     */
    public WXMenu findWXMenuById(Integer id) {
        return getRoutingMongoOps().findOne(Query.query(Criteria.where("id").is(id)), WXMenu.class);
    }

    /**
     * 查询微信子菜单
     * @param parentId 父级编号
     */
    public List<WXMenu> findWXMenuByParent(Integer parentId) {
        Query query = Query.query(Criteria.where("parent").is(parentId));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        return getRoutingMongoOps().find(query, WXMenu.class);
    }

    /**
     * 添加 微信菜单
     * @param wxMenu
     */
    public void insertWXMenu(WXMenu wxMenu) {
        insertToMongo(wxMenu);
    }


    /**
     * 更新 微信菜单信息
     * @param id 菜单编号
     * @param update 更新内容
     */
    public void updateWXMenuById(Integer id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, WXMenu.class);
    }

    /**
     * 删除 微信菜单
     * @param id 编号
     */
    public void removeWXMenuById(Integer id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), WXMenu.class);
    }

    /**
     * 删除 微信菜单
     * @param ids 编号
     */
    public void removeWXMenuByIds(Collection<Integer> ids) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").in(ids)), WXMenu.class);
    }

    /**
     * 查询所有微信客服帐号
     */
    public List<WXCustomerService> findAllCS() {
        return getRoutingMongoOps().findAll(WXCustomerService.class);
    }

    /**
     * 查询客服帐号信息
     */
    public WXCustomerService findCSById(Integer id) {
        return findEntityById(WXCustomerService.class, id);
    }

    /**
     * 保存微信客服
     *
     * @param cs 微信客服
     */
    public void addCS(WXCustomerService cs) {
        insertToMongo(cs);
    }
}
