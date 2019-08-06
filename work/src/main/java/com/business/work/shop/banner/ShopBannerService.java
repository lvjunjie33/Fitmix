package com.business.work.shop.banner;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.shop.ShopBanner;
import com.business.work.mapper.ShopBannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2016/11/25.
 */
@Service
public class ShopBannerService extends AbstractServiceImpl<ShopBanner> {

    @Autowired
    private ShopBannerMapper shopBannerMapper;

    @Override
    protected AbstractMapper<ShopBanner> getAbstractMapper() {
        return shopBannerMapper;
    }
}
