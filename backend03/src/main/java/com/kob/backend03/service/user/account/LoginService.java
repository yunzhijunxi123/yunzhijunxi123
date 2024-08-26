package com.kob.backend03.service.user.account;
import java.util.Map;
/**
 * @author shan
 * @version 1.0
 */
public interface LoginService {  //登录账号，写API，1.controller用来调用service,2.service里写接口,3.service里的impl写接口的实现
    public Map<String, String> getToken(String username, String password);
}
