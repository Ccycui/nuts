package com.ccy.display.display.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.display.display.entity.InfoEntity;
import com.ccy.display.display.service.InfoService;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.R;



/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 11:10:02
 */
@RestController
@RequestMapping("display/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("display:info:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{carId}")
    //@RequiresPermissions("display:info:info")
    public R info(@PathVariable("carId") Integer carId){
		InfoEntity info = infoService.getById(carId);

        return R.ok().put("info", info);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("display:info:save")
    public R save(@RequestBody InfoEntity info){
		infoService.save(info);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("display:info:update")
    public R update(@RequestBody InfoEntity info){
		infoService.updateById(info);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("display:info:delete")
    public R delete(@RequestBody Integer[] carIds){
		infoService.removeByIds(Arrays.asList(carIds));

        return R.ok();
    }

}
