package com.business.work.shop.product;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.shop.Product;
import com.business.work.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhangtao on 2016/11/23.
 */
@Service
public class ProductService extends AbstractServiceImpl<Product> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    protected AbstractMapper<Product> getAbstractMapper() {
        return productMapper;
    }



}
