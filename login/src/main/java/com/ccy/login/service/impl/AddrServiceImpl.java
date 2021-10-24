package com.ccy.login.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.Query;

import com.ccy.login.dao.AddrDao;
import com.ccy.login.entity.AddrEntity;
import com.ccy.login.service.AddrService;


@Service("addrService")
public class AddrServiceImpl extends ServiceImpl<AddrDao, AddrEntity> implements AddrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddrEntity> page = this.page(
                new Query<AddrEntity>().getPage(params),
                new QueryWrapper<AddrEntity>()
        );

        return new PageUtils(page);
    }

}