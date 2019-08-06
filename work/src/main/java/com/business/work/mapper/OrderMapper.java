package com.business.work.mapper;


import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.shop.Order;

@MyBatisRepository
public interface OrderMapper extends AbstractMapper<Order> {

}