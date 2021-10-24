package com.ccy.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccy.common.utils.PageUtils;
import com.ccy.shopping.entity.FavourEntity;


import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 16:29:35
 */
public interface FavourService extends IService<FavourEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

