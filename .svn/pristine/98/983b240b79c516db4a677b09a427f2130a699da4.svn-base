package com.business.core.abs;



import java.util.List;
import java.util.Map;

/**
 * 抽象公共service
 * modified by: zhangtao
 * Date: 14-3-19
 * Time: 下午5:37
 */
public interface AbstractService<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> list();

    int deletes(List<String> list);

    int count(Map<String, Object> pagerParams);

    /**
     * 分页查询等
     * @param pagerParams
     * @return
     */
    List<T> selectByParams(Map<String, Object> pagerParams);
}
