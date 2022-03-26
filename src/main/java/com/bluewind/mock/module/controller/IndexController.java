package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liuxingyu01
 * @date 2022-03-26 20:38
 * @description
 **/
@Controller
public class IndexController extends BaseController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }


}
