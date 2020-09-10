package com.cwj.ssm.template.framework;

import java.util.List;

import com.github.pagehelper.Page;

public interface BaseMapper<T> {

    int insert(T bean);
    
    /**
     * 批量插入
     */
    int insertAll(List<T> beans);
    
    T select(T bean);
    
    /**
     * 分页查询
     */
    Page<T> selectAll(T bean);
    
    int updateById(T bean);
    
    int delete(T bean);
    
    int deleteById(Long id);
    
    /**
     * 批量删除
     */
    int deleteAll(List<Long> idList);
}
