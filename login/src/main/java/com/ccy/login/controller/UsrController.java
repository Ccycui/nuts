package com.ccy.login.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ccy.login.entity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccy.login.entity.UsrEntity;
import com.ccy.login.service.UsrService;
import com.ccy.common.utils.PageUtils;
import com.ccy.common.utils.R;



/**
 * 
 *
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 21:50:09
 */
@RestController
@RequestMapping("login/usr")
public class UsrController {
    @Autowired
    private UsrService usrService;

    /**
     * 列表
     */
    @RequestMapping("/login")
    public R login(@RequestBody LogEntity logEntity){
        UsrEntity usrEntity=usrService.login(logEntity);
        if(usrEntity==null)
            return R.error("密码错误或用户名不存在");
        else
            return R.ok().put("Form",usrEntity);
    }

    @RequestMapping("/list")
    //@RequiresPermissions("login:usr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{usrId}")
    //@RequiresPermissions("login:usr:info")
    public R info(@PathVariable("usrId") Integer usrId){
		UsrEntity usr = usrService.getById(usrId);

        return R.ok().put("usr", usr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("login:usr:save")
    public R save(@RequestBody UsrEntity usr){
		usrService.save(usr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("login:usr:update")
    public R update(@RequestBody UsrEntity usr){
		usrService.updateById(usr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("login:usr:delete")
    public R delete(@RequestBody Integer[] usrIds){
		usrService.removeByIds(Arrays.asList(usrIds));

        return R.ok();
    }

}
