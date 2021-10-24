package com.ccy.login.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.login.entity.AddrEntity;
import com.ccy.login.service.AddrService;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.R;



/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-16 23:21:06
 */
@RestController
@RequestMapping("login/addr")
public class AddrController {
    @Autowired
    private AddrService addrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("login:addr:list")
    public R list(@RequestParam Map<String, Object> params){

        List<AddrEntity> list=addrService.list();
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{addrId}")
    //@RequiresPermissions("login:addr:info")
    public R info(@PathVariable("addrId") Integer addrId){
		AddrEntity addr = addrService.getById(addrId);

        return R.ok().put("addr", addr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("login:addr:save")
    public R save(@RequestBody AddrEntity addr){
		addrService.save(addr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("login:addr:update")
    public R update(@RequestBody AddrEntity addr){
		addrService.updateById(addr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("login:addr:delete")
    public R delete(@RequestBody Integer[] addrIds){
		addrService.removeByIds(Arrays.asList(addrIds));

        return R.ok();
    }

}
