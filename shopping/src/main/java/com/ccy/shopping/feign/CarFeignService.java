package com.ccy.shopping.feign;

import com.ccy.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("display")
public interface CarFeignService {
    @RequestMapping("/display/car/getFavours")
    public R getFavours(@RequestParam List<Integer> idlist);
    @RequestMapping("/display/car/getOrders")
    public R getOrders(@RequestParam List<Integer> idlist0,@RequestParam List<Integer> idlist1);
}
