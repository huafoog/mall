package com.qingshan.mall.order.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("{page}.html")
    public String listPage(@PathVariable("page") String page){

        return page;
    }
}
