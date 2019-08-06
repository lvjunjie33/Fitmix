package com.business.work.auth;

import com.business.core.entity.auth.Admin;
import com.business.core.entity.auth.Resource;
import com.business.core.entity.auth.Role;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/4/21.
 */
@Repository
public class AuthDao extends BaseMongoDaoSupport {


    ///
    /// admin 管理员

    /**
     * 添加 admin 信息
     * @param admin 管理员信息
     */
    public void insertAdmin (Admin admin) {
        insertToMongo(admin);
    }

    /**
     * 查询所有 admin 信息
     * @return 所有 admin 信息
     */
    public List<Admin> findAllAdmin () {
        return getRoutingMongoOps().findAll(Admin.class);
    }

    /**
     * 根据 id 查询 admin
     * @param id 编号
     * @param fields 列
     * @return admin 信息
     */
    public Admin findAdminById (Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Admin.class);
    }

    /**
     * 根据 loginName 查询 admin
     * @param loginName 登录名
     * @param fields 列
     * @return admin 信息
     */
    public Admin findAdminByLoginName (String loginName, String...fields) {
        Query query = new Query(Criteria.where("loginName").is(loginName));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Admin.class);
    }

    /**
     * 更新 admin 信息
     * @param update 更新的信息
     */
    public void updateAdminById (Integer id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Admin.class);
    }

    ///
    ///

    /**
     * 添加 role
     * @param role 角色信息
     */
    public void insertRole (Role role) {
        insertToMongo(role);
    }

    /**
     * 根据 id 查询 role
     * @param id 编号
     * @param fields 列
     * @return Role 对象
     */
    public Role findRoleById(Integer id, String... fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Role.class);
    }

    /**
     * 根据 id 查询 role
     * @param name 名称
     * @param fields 列
     * @return Role 对象
     */
    public Role findRoleByName(String name, String... fields) {
        Query query = new Query(Criteria.where("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Role.class);
    }

    /**
     * 查询所有 role 信息, 添加时间 DESC
     * @return 返回 Role List
     */
    public List<Role> findAllRole (String...fields) {
        Query query = new Query();
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, Role.class);
    }

    /**
     * 更新 role 信息
     * @param id 编号
     * @param update 更新的信息
     */
    public void updateRoleById (Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, Role.class);
    }

    ///
    /// Resource 资源

    /**
     * 添加 Resource
     * @param resource 资源信息
     */
    public void insertResource (Resource resource) {
        insertToMongo(resource);
    }

    /**
     * 根据 id 查询 Resource
     * @param id 编号
     * @param fields 列
     * @return Resource 信息
     */
    public Resource findResourceById (Integer id, String...fields) {
        return findEntityById(Resource.class, id, fields);
    }

    /**
     * 根据 name 查询 Resource
     * @param name 名称
     * @param fields 列
     * @return Resource 信息
     */
    public Resource findResourceByName (String name, String...fields) {
        Query query = new Query(Criteria.where("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Resource.class);
    }

    /**
     * 根据 extensionName 查询 Resource
     * @param extensionName 名称
     * @param fields 列
     * @return Resource 信息
     */
    public Resource findResourceByExtensionName (String extensionName, String...fields) {
        Query query = new Query(Criteria.where("extensionName").is(extensionName));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Resource.class);
    }

    /**
     * 根据 roles 查询 资源
     * @param roles 角色集合
     * @param fields 列
     * @return resource 资源集合
     */
    public List<Resource> findResourceByRoles (List<String> roles, String...fields) {
        Query query = new Query(Criteria.where("roles").in(roles));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Resource.class);
    }

    /**
     * 查询所有 Resource, addTime DESC
     * @return Resource 集合
     */
    public List<Resource> findAllResource () {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "sort", "addTime"));
        return getRoutingMongoOps().find(query, Resource.class);
    }

    public List<Resource> findResourceAndType (Collection<Integer> types) {
        Query query = new Query(Criteria.where("type").in(types));
        query.with(new Sort(Sort.Direction.ASC, "sort", "addTime"));
        return getRoutingMongoOps().find(query, Resource.class);
    }

    /**
     * 更新 Resource 信息
     * @param ids 编号集合
     * @param update 更新信息
     */
    public void updateResourceByIds (List<Integer> ids, Update update) {
        updateEntitiesFieldsByIds(Resource.class, ids, update);
    }

    /**
     * 更新 Resource 信息
     * @param ids 编号集合
     * @param update 更新信息
     */
    public void updateResourceByNotIds (List<Integer> ids, Update update) {
        Query query = new Query(Criteria.where("id").nin(ids));
        getRoutingMongoOps().updateMulti(query, update, Resource.class);
    }

    /**
     * 更新 Resource 信息
     * @param id 编号
     * @param update 更新信息
     */
    public void updateResourceById (Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, Resource.class);
    }
}
