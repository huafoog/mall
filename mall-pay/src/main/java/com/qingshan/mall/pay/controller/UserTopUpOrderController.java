package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qingshan.mall.pay.entity.UserTopUpOrderEntity;
import com.qingshan.mall.pay.service.UserTopUpOrderService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 充值订单
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@RestController
@RequestMapping("pay/usertopuporder")
public class UserTopUpOrderController {
    @Autowired
    private UserTopUpOrderService userTopUpOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("pay:usertopuporder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userTopUpOrderService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("pay:usertopuporder:info")
    public R info(@PathVariable("id") String id){
		UserTopUpOrderEntity userTopUpOrder = userTopUpOrderService.getById(id);

        return R.ok(userTopUpOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("pay:usertopuporder:save")
    public R save(@RequestBody UserTopUpOrderEntity userTopUpOrder){
		userTopUpOrderService.save(userTopUpOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("pay:usertopuporder:update")
    public R update(@RequestBody UserTopUpOrderEntity userTopUpOrder){
		userTopUpOrderService.updateById(userTopUpOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("pay:usertopuporder:delete")
    public R delete(@RequestBody String[] ids){
		userTopUpOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
