package com.ccy.display.display.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.display.display.entity.CarEntity;
import com.ccy.display.display.service.CarService;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.R;



/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 10:24:08
 */
@RestController
@RequestMapping("display/car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("display:car:list")
    public R list(@RequestParam Map<String, Object> params){

        List<CarEntity> list=carService.list(new QueryWrapper<CarEntity>().eq("car_state",1));

        return R.ok().put("list", list);
    }
    @RequestMapping("/purchase/{carId}")
    public R purchase(@PathVariable("carId") Integer carId){
        CarEntity carEntity=new CarEntity();
        carEntity.setCarId(carId);
        carEntity.setCarState(0);
        carService.updateById(carEntity);
        return R.ok();
    }

    @RequestMapping("/getFavours")
    public R getFavours(@RequestParam List<Integer> idlist){
        List<CarEntity> carlist=carService.listByIds(idlist);
        List<CarEntity> carlist1=new ArrayList<>();
        List<CarEntity> carlist2=new ArrayList<>();

        for(CarEntity carEntity:carlist){
            if(carEntity.getCarState()==1)
                carlist1.add(carEntity);
            else
                carlist2.add(carEntity);
        }

        return R.ok().put("list1",carlist1).put("list2",carlist2);
    }
    @RequestMapping("/getOrders")
    public R getOrders(@RequestParam List<Integer> idlist0,@RequestParam List<Integer> idlist1){

        List<CarEntity> carlist0=carService.listByIds(idlist0);
        List<CarEntity> carlist1=carService.listByIds(idlist1);
        return R.ok().put("list1",carlist0).put("list2",carlist1);
    }

    @RequestMapping("/search")
    public R serach(@RequestParam(value="brand",required = false) String brand,
                  @RequestParam(value="type",required = false) String type,
                  @RequestParam(value="color",required = false) String color,
                  @RequestParam(value="mile_l",required = false,defaultValue = "0") int mile_l,
                  @RequestParam(value="mile_h",required = false,defaultValue= "200000") int mile_h){
        List<CarEntity>  list=carService.conditionQuery(brand,type,color,mile_l,mile_h);
        return R.ok().put("list",list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carId}")
    //@RequiresPermissions("display:car:info")
    public R info(@PathVariable("carId") Integer carId){
		CarEntity car = carService.getById(carId);

        return R.ok().put("car", car);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("display:car:save")
    public R save(@RequestBody CarEntity car){
		carService.save(car);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("display:car:update")
    public R update(@RequestBody CarEntity car){
		carService.updateById(car);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("display:car:delete")
    public R delete(@RequestBody Integer[] carIds){
		carService.removeByIds(Arrays.asList(carIds));

        return R.ok();
    }

}
