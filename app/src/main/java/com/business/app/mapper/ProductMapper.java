package com.business.app.mapper;


import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.shop.Product;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface ProductMapper extends AbstractMapper<Product>{

    // 减少库存
    int reduceStocks(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

    int augmentStocks(@Param("productId") Integer productId, @Param("quantity") Integer quantity);
}