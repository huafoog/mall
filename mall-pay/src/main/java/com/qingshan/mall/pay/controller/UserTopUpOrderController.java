package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.qingshan.mall.pay.entity.UserTopUpOrderEntity;
import com.qingshan.mall.pay.vo.page.UserTopUpOrderVO;
import com.qingshan.mall.pay.service.UserTopUpOrderService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 充值订单
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@RestController
@RequestMapping("usertopuporder")
@Api(tags = "充值订单")
public class UserTopUpOrderController {
    @Autowired
    private UserTopUpOrderService userTopUpOrderService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("充值订单列表")
    // @RequiresPermissions("pay:usertopuporder:list")
    public R<PageUtils<UserTopUpOrderEntity>> list(@RequestParam UserTopUpOrderVO params){
        PageUtils page = userTopUpOrderService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("充值订单信息")
    // @RequiresPermissions("pay:usertopuporder:info")
    public R<UserTopUpOrderEntity> info(@PathVariable("id") String id){
		UserTopUpOrderEntity userTopUpOrder = userTopUpOrderService.getById(id);

        return R.ok(userTopUpOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("充值订单保存")
    // @RequiresPermissions("pay:usertopuporder:save")
    public R save(@RequestBody UserTopUpOrderEntity userTopUpOrder){
		userTopUpOrderService.save(userTopUpOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("充值订单修改")
    // @RequiresPermissions("pay:usertopuporder:update")
    public R update(@RequestBody UserTopUpOrderEntity userTopUpOrder){
		userTopUpOrderService.updateById(userTopUpOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("充值订单删除")
    // @RequiresPermissions("pay:usertopuporder:delete")
    public R delete(@RequestBody String[] ids){
		userTopUpOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
