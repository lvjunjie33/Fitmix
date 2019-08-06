package com.business.core.mongo;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.utils.ReflectUtil;
import com.google.common.collect.ImmutableMap;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mongo数据库路由实现.使用实体类上的{@link com.business.core.annotation.MongoDocumentDB}来映射
 */
public class RoutingMongoOperations extends AbstractRoutingMongoOperations {

    /**
     * 实体路径
     */
    private String entityPath;
    /**
     * 实体类与操作的绑定
     */
    private Map<Class<?>, MongoOperations> clazzOperations;
    /**
     * 类与主键泛型类型缓存
     */
    public static Map<String, String> classRawTypeCache = null;

    /**
     * 初始化类与数据库的映射
     */
    @PostConstruct
    public void init() {
        Assert.notNull(entityPath, "实体路径不能为空");

        Set<Class<?>> classSet = ReflectUtil.scanClass(entityPath, Document.class);
        ImmutableMap.Builder<Class<?>, MongoOperations> builder = ImmutableMap.builder();
        ImmutableMap.Builder<String, String> classRawTypeBuilder = ImmutableMap.builder();
        for (Class<?> clazz : classSet) {
            String className = clazz.getName();
            MongoDocumentDB db = clazz.getAnnotation(MongoDocumentDB.class);
            Assert.notNull(db, String.format("[%s]未添加@MongoDocumentDB", className));
            MongoOperations operations = getMongoOps(db.value());
            Assert.notNull(operations, String.format("[%s]配置的MongoDocumentDB不存在", className));
            builder.put(clazz, operations);
            Type mySuperClass = clazz;
            for (int i = 0; i < 10; i++) { // 自低向上获得10次，防止死循环
                mySuperClass = ((Class) mySuperClass).getGenericSuperclass();
                if (mySuperClass == null) {
                    break;
                }
                if (mySuperClass instanceof ParameterizedType && ((ParameterizedType) mySuperClass).getRawType() == IncIdEntity.class) {
                    classRawTypeBuilder.put(clazz.getName(), ((ParameterizedType) mySuperClass).getActualTypeArguments()[0].toString().substring(6));
                    break;
                }
            }
        }
        classRawTypeCache = classRawTypeBuilder.build();
        clazzOperations = builder.build();
    }

    /**
     * 设置需要扫描的实体包路径
     *
     * @param entityPath 实体包路径
     */
    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    /**
     * 根据实体类获得其数据库操作
     *
     * @param clazz 实体类
     * @return 数据库操作
     */
    public MongoOperations getMongoOps(Class<?> clazz) {
        MongoOperations operations = clazzOperations.get(clazz);
//        for (int i = 0; i < 3; i++) {
//            try {
//                operations.getCollection(clazz.getAnnotation(Document.class).collection()).getCount();
//                break;
//            } catch (MongoException e) {
//                // skip
//            }
//        }
        return operations;
    }

    // ========== mongo操作 ==========

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#getCollectionName(Class)
     */
    public DBCollection getCollection(Class<?> clasz) {
        MongoOperations operations = getMongoOps(clasz);
        return operations.getCollection(operations.getCollectionName(clasz));
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#insert(Object)
     */
    public void insert(Object objectToSave) {
        getMongoOps(objectToSave.getClass()).insert(objectToSave);
    }

    public void insert(String collName, Object objectToSave) {
        getMongoOps(objectToSave.getClass()).insert(objectToSave, collName);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#insert(java.util.Collection, Class)
     */
    @SuppressWarnings("TypeParameterExplicitlyExtendsObject")
    public void insert(Collection<? extends Object> batchToSave, Class<?> entityClass) {
        getMongoOps(entityClass).insert(batchToSave, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#findAndModify(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public <T> T findAndModify(Query query, Update update, Class<T> entityClass) {
        return getMongoOps(entityClass).findAndModify(query, update, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#findAndModify(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * org.springframework.data.mongodb.core.FindAndModifyOptions,
     * Class)
     */
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass) {
        return getMongoOps(entityClass).findAndModify(query, update, options, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#upsert(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public WriteResult upsert(Query query, Update update, Class<?> entityClass) {
        return getMongoOps(entityClass).upsert(query, update, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#updateMulti(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public WriteResult updateMulti(Query query, Update update, Class<?> entityClass) {
        return getMongoOps(entityClass).updateMulti(query, update, entityClass);
    }
    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#updateMulti(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public WriteResult updateMulti(Query query, Update update, Class<?> entityClass, String collName) {
        return getMongoOps(entityClass).updateMulti(query, update, collName);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#findOne(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public <T> T findOne(Query query, Class<T> entityClass) {
        return getMongoOps(entityClass).findOne(query, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#updateFirst(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public WriteResult updateFirst(Query query, Update update, Class<?> entityClass) {
        if (update.getUpdateObject().toMap().size() > 0) {
            return getMongoOps(entityClass).updateFirst(query, update, entityClass);
        }
        return null;
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#updateFirst(org.springframework.data.mongodb.core.query.Query,
     * org.springframework.data.mongodb.core.query.Update,
     * Class)
     */
    public WriteResult updateFirst(Query query, Update update, Class<?> entityClass, String collName) {
        return getMongoOps(entityClass).updateFirst(query, update, collName);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#count(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public long count(Query query, Class<?> entityClass) {
        return getMongoOps(entityClass).count(query, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#count(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public long count(Query query, Class<?> entityClass, String collName) {
        return getMongoOps(entityClass).count(query, collName);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#find(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public <T> List<T> find(Query query, Class<T> entityClass) {
        return getMongoOps(entityClass).find(query, entityClass);
    }
    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#find(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public <T> List<T> find(Query query, Class<T> entityClass, String collName) {
        return getMongoOps(entityClass).find(query, entityClass, collName);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#findAll(Class)
     */
    public <T> List<T> findAll(Class<T> entityClass) {
        return getMongoOps(entityClass).findAll(entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#remove(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public <T> void remove(Query query, Class<T> entityClass) {
        getMongoOps(entityClass).remove(query, entityClass);
    }

    /**
     * @see org.springframework.data.mongodb.core.MongoOperations#findAndRemove(org.springframework.data.mongodb.core.query.Query,
     * Class)
     */
    public <T> T findAndRemove(Query query, Class<T> entityClass) {
        return getMongoOps(entityClass).findAndRemove(query, entityClass);
    }
}