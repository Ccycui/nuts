package com.ccy.display.display.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccy.common.utils.PageUtils;
import com.ccy.display.display.entity.CarEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 10:24:08
 */
public interface CarService extends IService<CarEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<CarEntity> conditionQuery(String brand,String type,String color,int mile_l,int mile_h);
}

