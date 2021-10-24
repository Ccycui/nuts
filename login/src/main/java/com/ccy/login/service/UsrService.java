package com.ccy.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccy.common.utils.PageUtils;
import com.ccy.login.entity.LogEntity;
import com.ccy.login.entity.UsrEntity;

import java.util.Map;

/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 21:50:09
 */
public interface UsrService extends IService<UsrEntity> {

    PageUtils queryPage(Map<String, Object> params);
    UsrEntity login(LogEntity log);
}

