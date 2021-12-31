package com.qingshan.mall.product.service.impl;

import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;

import com.qingshan.mall.product.dao.CategoryDao;
import com.qingshan.mall.product.entity.CategoryEntity;
import com.qingshan.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> getList() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        List<CategoryEntity> level1Menus = categoryEntities.stream().filter((entities) -> entities.getParentCid() == 0).map((menu)->{
            menu.setChildren(getChildren(menu,categoryEntities));
            return menu;
        }).sorted(Comparator.comparingInt(CategoryEntity::getSort)).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        // TODO 1.删除时查看依赖
        baseMapper.deleteBatchIds(catIds);
    }

    /**
     * 递归获取所有菜单的子菜单
     * @param root 当前菜单
     * @param all 所有菜单
     * @return 当前菜单的所有子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity root,List<CategoryEntity> all){
        return all.stream().filter((o)-> o.getParentCid().equals(root.getCatId()))
                .map((menu)->{
                    menu.setChildren(getChildren(menu,all));
                    return menu;
                }).sorted((menu1,menu2)->(menu1.getSort() == null ? 0 :menu1.getSort())-(menu2.getSort() == null ? 0 : menu2.getSort() ))
                .collect(Collectors.toList());
    }

}