package com.ccy.shopping.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccy.shopping.feign.CarFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.shopping.entity.FavourEntity;
import com.ccy.shopping.service.FavourService;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.R;



/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 16:29:35
 */
@RestController
@RequestMapping("shopping/favour")
public class FavourController {
    @Autowired
    private FavourService favourService;
    @Autowired
    private CarFeignService carFeignService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("shopping:favour:list")
    public R list(){
        List<FavourEntity> list=favourService.list();
        List<Integer> idList=new ArrayList<>();

        for(FavourEntity favourEntity:list) {
            idList.add(favourEntity.getCarId());
        }
        R result=carFeignService.getFavours(idList);
        return result;
    }
    @RequestMapping("/add/{carId}")
    public R add(@PathVariable("carId") Integer carId){
        FavourEntity favourEntity=new FavourEntity();
        favourEntity.setCarId(carId);
        favourEntity.setUsrId(1);
        favourService.save(favourEntity);
        return R.ok();
    }
    @RequestMapping("/delete/{carId}")
    public R delete(@PathVariable("carId") Integer carId){
        favourService.remove(new QueryWrapper<FavourEntity>().eq("car_id",carId));
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{carId}")
    //@RequiresPermissions("shopping:favour:info")
    public R info(@PathVariable("carId") Integer carId){
		FavourEntity favour = favourService.getOne(new QueryWrapper<FavourEntity>().eq("car_id",carId));
        if(favour!=null) return R.ok();
        else return R.error(500,"fail");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("shopping:favour:save")
    public R save(@RequestBody FavourEntity favour){
		favourService.save(favour);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("shopping:favour:update")
    public R update(@RequestBody FavourEntity favour){
		favourService.updateById(favour);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("shopping:favour:delete")
    public R delete(@RequestBody Integer[] favourIds){
		favourService.removeByIds(Arrays.asList(favourIds));

        return R.ok();
    }

}
