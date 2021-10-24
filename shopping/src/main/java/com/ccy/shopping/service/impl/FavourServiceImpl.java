package com.ccy.shopping.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.Query;

import com.ccy.shopping.dao.FavourDao;
import com.ccy.shopping.entity.FavourEntity;
import com.ccy.shopping.service.FavourService;


@Service("favourService")
public class FavourServiceImpl extends ServiceImpl<FavourDao, FavourEntity> implements FavourService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavourEntity> page = this.page(
                new Query<FavourEntity>().getPage(params),
                new QueryWrapper<FavourEntity>()
        );

        return new PageUtils(page);
    }

}