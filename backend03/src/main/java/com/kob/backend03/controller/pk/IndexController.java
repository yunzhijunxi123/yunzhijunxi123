package com.kob.backend03.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shan
 * @version 1.0
 */
@Controller
@RequestMapping("/pk/")
public class IndexController {
    @RequestMapping("index/")
    public String index(){
        return "pk/index.html";
    }
}
