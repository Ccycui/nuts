package com.ccy.login.service.impl;

import com.ccy.login.entity.LogEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.Query;

import com.ccy.login.dao.UsrDao;
import com.ccy.login.entity.UsrEntity;
import com.ccy.login.service.UsrService;


@Service("usrService")
public class UsrServiceImpl extends ServiceImpl<UsrDao, UsrEntity> implements UsrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsrEntity> page = this.page(
                new Query<UsrEntity>().getPage(params),
                new QueryWrapper<UsrEntity>()
        );

        return new PageUtils(page);
    }

    public UsrEntity login(LogEntity log){
        return this.getOne(new QueryWrapper<UsrEntity>().eq("acc",log.getAcc()).eq("pwd",log.getPwd()));
    }

}