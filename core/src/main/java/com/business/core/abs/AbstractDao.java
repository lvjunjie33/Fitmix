package com.business.core.abs;

import java.util.List;
import java.util.Map;

/**
 * 抽象dao 接口
 * modified by: zhangtao
 * Date: 14-3-19
 * Time: 下午5:39
 */
public interface AbstractDao<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> list();

    int deletes(List<String> ids);

    int count(Map<String, Object> pagerParams);

    /**
     * 分页查询等
     * @param pagerParams
     * @return
     */
    List<T> selectByParams(Map<String, Object> pagerParams);

}
