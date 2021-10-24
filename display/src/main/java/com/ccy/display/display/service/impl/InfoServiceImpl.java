package com.ccy.display.display.service.impl;

import com.ccy.display.display.entity.CarEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.Query;

import com.ccy.display.display.dao.InfoDao;
import com.ccy.display.display.entity.InfoEntity;
import com.ccy.display.display.service.InfoService;


@Service("infoService")
public class InfoServiceImpl extends ServiceImpl<InfoDao, InfoEntity> implements InfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoEntity> page = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>()
        );

        return new PageUtils(page);
    }
    public List<Integer> getId(String brand,String type,String color,int mile_l,int mile_h){
        QueryWrapper<InfoEntity> queryWrapper=new QueryWrapper<>();
        if(brand!=null)
            queryWrapper.eq("car_brand",brand);
        if(type!=null)
            queryWrapper.eq("car_type",type);
        if(color!=null)
            queryWrapper.eq("car_color",color);
        queryWrapper.between("car_mile",mile_l,mile_h);

        List<InfoEntity> list=this.list(queryWrapper);
        List<Integer> result=new ArrayList<>();
        for(InfoEntity i:list){
            result.add(i.getCarId());
        }
        return result;
    }

}