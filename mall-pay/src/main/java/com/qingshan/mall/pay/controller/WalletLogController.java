package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.qingshan.mall.pay.entity.WalletLogEntity;
import com.qingshan.mall.pay.vo.page.WalletLogVO;
import com.qingshan.mall.pay.service.WalletLogService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 用户钱包流水记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@RestController
@RequestMapping("walletlog")
@Api(tags = "用户钱包流水记录表")
public class WalletLogController {
    @Autowired
    private WalletLogService walletLogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("用户钱包流水记录表列表")
    // @RequiresPermissions("pay:walletlog:list")
    public R<PageUtils<WalletLogEntity>> list(@RequestParam WalletLogVO params){
        PageUtils page = walletLogService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("用户钱包流水记录表信息")
    // @RequiresPermissions("pay:walletlog:info")
    public R<WalletLogEntity> info(@PathVariable("id") String id){
		WalletLogEntity walletLog = walletLogService.getById(id);

        return R.ok(walletLog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("用户钱包流水记录表保存")
    // @RequiresPermissions("pay:walletlog:save")
    public R save(@RequestBody WalletLogEntity walletLog){
		walletLogService.save(walletLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("用户钱包流水记录表修改")
    // @RequiresPermissions("pay:walletlog:update")
    public R update(@RequestBody WalletLogEntity walletLog){
		walletLogService.updateById(walletLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("用户钱包流水记录表删除")
    // @RequiresPermissions("pay:walletlog:delete")
    public R delete(@RequestBody String[] ids){
		walletLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
