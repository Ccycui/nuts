package com.ccy.display.display.service.impl;

import com.ccy.display.display.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.Query;

import com.ccy.display.display.dao.CarDao;
import com.ccy.display.display.entity.CarEntity;
import com.ccy.display.display.service.CarService;


@Service("carService")
public class CarServiceImpl extends ServiceImpl<CarDao, CarEntity> implements CarService {
    @Autowired
    InfoService infoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CarEntity> page = this.page(
                new Query<CarEntity>().getPage(params),
                new QueryWrapper<CarEntity>()
        );

        return new PageUtils(page);
    }
    public List<CarEntity> conditionQuery(String brand, String type, String color, int mile_l, int mile_h){
        List<Integer> idList=infoService.getId(brand,type,color,mile_l,mile_h);
        if(idList.size()==0)
            return new ArrayList<>();
        return this.list(new QueryWrapper<CarEntity>().eq("car_state",1).in("car_id",idList));
    }

}