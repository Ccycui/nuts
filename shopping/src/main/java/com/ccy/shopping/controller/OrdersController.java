package com.ccy.shopping.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccy.shopping.entity.FavourEntity;
import com.ccy.shopping.feign.CarFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.shopping.entity.OrdersEntity;
import com.ccy.shopping.service.OrdersService;
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
@RequestMapping("shopping/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CarFeignService carFeignService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("shopping:orders:list")
    public R list(){
        List<OrdersEntity> list=ordersService.list();
        List<Integer> idList0=new ArrayList<>();
        List<Integer> idList1=new ArrayList<>();

        for(OrdersEntity orderEntity:list) {
            if(orderEntity.getExpress()==1)
                idList1.add(orderEntity.getCarId());
            else
                idList0.add(orderEntity.getCarId());
        }
        return carFeignService.getOrders(idList0,idList1);
    }
    @RequestMapping("/add")
    public R add(@RequestParam(name="carId") Integer carId,
                 @RequestParam(name="express") Integer express,
                 @RequestParam(name="address",required = false,defaultValue = " ") String address
                 ){

        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setCarId(carId);
        ordersEntity.setExpress(express);
        ordersEntity.setUsrId(1);
        if(express==1) {
            ordersEntity.setState(1);
            ordersEntity.setAddress(address);
        }
        else{
            ordersEntity.setState(3);
            ordersEntity.setAddress(" ");
        }

        ordersService.save(ordersEntity);
        return R.ok();
    }
//    @RequestMapping("/delete/{carId}")
//    public R delete(@PathVariable("carId") Integer carId){
//        favourService.remove(new QueryWrapper<FavourEntity>().eq("car_id",carId));
//        return R.ok();
//    }

    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    //@RequiresPermissions("shopping:orders:info")
    public R info(@PathVariable("orderId") Integer orderId){
		OrdersEntity orders = ordersService.getById(orderId);

        return R.ok().put("orders", orders);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("shopping:orders:save")
    public R save(@RequestBody OrdersEntity orders){
		ordersService.save(orders);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("shopping:orders:update")
    public R update(@RequestBody OrdersEntity orders){
		ordersService.updateById(orders);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("shopping:orders:delete")
    public R delete(@RequestBody Integer[] orderIds){
		ordersService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
