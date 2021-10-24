package com.ccy.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccy.common.utils.PageUtils;
import com.ccy.login.entity.AddrEntity;

import java.util.Map;

/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-16 23:21:06
 */
public interface AddrService extends IService<AddrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

