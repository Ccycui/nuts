package com.ccy.display.display.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccy.common.utils.PageUtils;
import com.ccy.display.display.entity.CarEntity;
import com.ccy.display.display.entity.InfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 11:10:02
 */
public interface InfoService extends IService<InfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public List<Integer> getId(String brand,String type,String color,int mile_l,int mile_h);
}

