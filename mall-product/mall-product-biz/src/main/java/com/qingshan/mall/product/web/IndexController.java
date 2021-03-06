package com.qingshan.mall.product.web;

import com.qingshan.common.core.constant.AuthServerConstant;
import com.qingshan.common.core.dto.member.MemberDTO;
import com.qingshan.mall.product.entity.CategoryEntity;
import com.qingshan.mall.product.service.CategoryService;
import com.qingshan.mall.product.vo.Catelog2Vo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 * @author qingshan
 */
@Controller
@AllArgsConstructor
public class IndexController {
    private final CategoryService categoryService;

    @GetMapping(value = {"/","index.html"})
    private String indexPage(Model model, HttpSession session) {
        MemberDTO user = (MemberDTO)session.getAttribute(AuthServerConstant.LOGIN_USER);
        //1、查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categories",categoryEntities);
        model.addAttribute("user",user);
        return "index";
    }

    //index/json/catalog.json
    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();

        return catalogJson;

    }
}
