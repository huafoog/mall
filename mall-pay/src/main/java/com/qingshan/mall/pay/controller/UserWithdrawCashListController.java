package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qingshan.mall.pay.entity.UserWithdrawCashListEntity;
import com.qingshan.mall.pay.service.UserWithdrawCashListService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 提现记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@RestController
@RequestMapping("pay/userwithdrawcashlist")
public class UserWithdrawCashListController {
    @Autowired
    private UserWithdrawCashListService userWithdrawCashListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("pay:userwithdrawcashlist:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userWithdrawCashListService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("pay:userwithdrawcashlist:info")
    public R info(@PathVariable("id") String id){
		UserWithdrawCashListEntity userWithdrawCashList = userWithdrawCashListService.getById(id);

        return R.ok(userWithdrawCashList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("pay:userwithdrawcashlist:save")
    public R save(@RequestBody UserWithdrawCashListEntity userWithdrawCashList){
		userWithdrawCashListService.save(userWithdrawCashList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("pay:userwithdrawcashlist:update")
    public R update(@RequestBody UserWithdrawCashListEntity userWithdrawCashList){
		userWithdrawCashListService.updateById(userWithdrawCashList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("pay:userwithdrawcashlist:delete")
    public R delete(@RequestBody String[] ids){
		userWithdrawCashListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
