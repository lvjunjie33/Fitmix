package com.business.app.shop.product;

import com.business.app.mapper.ProductMapper;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.shop.Product;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangtao on 2016/11/28.
 */
@Service
public class ProductService extends AbstractServiceImpl<Product> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    protected AbstractMapper<Product> getAbstractMapper() {
        return productMapper;
    }

    /**
     * 获取流米产品
     */
    public Map<String, List<Product>> getLiumiProduct() {
        HashMap<String, List<Product>> resultMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", Product.STATUS_NORMAL);
        map.put("type", Product.TYPE_LIUMI);
        PageHelper.orderBy("sort desc");
        List<Product> list = productMapper.selectByParams(map);
        for(Product product : list) {
            List<Product> temp = resultMap.get(product.getVirtualKey().substring(0, 2));
            if(temp != null) {
                temp.add(product);
            } else {
                temp = new ArrayList<>();
                temp.add(product);
            }
            resultMap.put(product.getVirtualKey().substring(0, 2), temp);
        }
        return resultMap;
    }
}
