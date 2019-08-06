package com.business.core.abs;


import java.util.List;
import java.util.Map;

/**
 * 抽象 mapper接口
 * modified by: zhangtao
 * Date: 14-3-20
 * Time: 下午1:17
 */
public interface AbstractMapper<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectAll();

    /**
     * 分页查询等
     * @param pagerParams
     * @return
     */
    List<T> selectByParams(Map<String, Object> pagerParams);

    int deletes(List<String> ids);

    int count(Map<String, Object> params);

}
