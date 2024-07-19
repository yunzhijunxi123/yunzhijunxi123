package com.kob.backend03.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author shan
 * @version 1.0
 */
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getBotInfo/")
    public Map<String,String> getBotInfo(){
        Map<String,String> bot1 = new HashMap<>();
        bot1.put("name","apple");
        bot1.put("rating","1500");
        return bot1;
    }
}
