package com.business.core.abs;


import java.util.List;
import java.util.Map;

/**
 * 抽象dao 实现类
 * modified by: zhangtao
 * Date: 14-3-19
 * Time: 下午5:40
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

    protected abstract AbstractMapper<T> getAbstractMapper();

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return getAbstractMapper().deleteByPrimaryKey(id);
    }


    @Override
    public int insert(T record) {
        return getAbstractMapper().insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return getAbstractMapper().insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return getAbstractMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getAbstractMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getAbstractMapper().updateByPrimaryKey(record);
    }

    @Override
    public List<T> list() {
        return getAbstractMapper().selectAll();
    }


    @Override
    public int deletes(List<String> list) {
        return getAbstractMapper().deletes(list);
    }

    @Override
    public int count(Map<String, Object> params) {
        return getAbstractMapper().count(params);
    }

    /**
     * 分页查询等
     * @param pagerParams
     * @return
     */
    public List<T> selectByParams(Map<String, Object> pagerParams){
        return getAbstractMapper().selectByParams(pagerParams);
    }

}
