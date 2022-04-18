package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.qingshan.mall.pay.entity.UserWithdrawCashListEntity;
import com.qingshan.mall.pay.vo.page.UserWithdrawCashListVO;
import com.qingshan.mall.pay.service.UserWithdrawCashListService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 提现记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@RestController
@RequestMapping("userwithdrawcashlist")
@Api(tags = "提现记录表")
public class UserWithdrawCashListController {
    @Autowired
    private UserWithdrawCashListService userWithdrawCashListService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("提现记录表列表")
    // @RequiresPermissions("pay:userwithdrawcashlist:list")
    public R<PageUtils<UserWithdrawCashListEntity>> list(@RequestParam UserWithdrawCashListVO params){
        PageUtils page = userWithdrawCashListService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("提现记录表信息")
    // @RequiresPermissions("pay:userwithdrawcashlist:info")
    public R<UserWithdrawCashListEntity> info(@PathVariable("id") String id){
		UserWithdrawCashListEntity userWithdrawCashList = userWithdrawCashListService.getById(id);

        return R.ok(userWithdrawCashList);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("提现记录表保存")
    // @RequiresPermissions("pay:userwithdrawcashlist:save")
    public R save(@RequestBody UserWithdrawCashListEntity userWithdrawCashList){
		userWithdrawCashListService.save(userWithdrawCashList);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("提现记录表修改")
    // @RequiresPermissions("pay:userwithdrawcashlist:update")
    public R update(@RequestBody UserWithdrawCashListEntity userWithdrawCashList){
		userWithdrawCashListService.updateById(userWithdrawCashList);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("提现记录表删除")
    // @RequiresPermissions("pay:userwithdrawcashlist:delete")
    public R delete(@RequestBody String[] ids){
		userWithdrawCashListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
