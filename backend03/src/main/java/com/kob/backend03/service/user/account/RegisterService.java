package com.kob.backend03.service.user.account;
import java.util.Map;
/**
 * @author shan
 * @version 1.0
 */
public interface RegisterService { //注册账号
    public Map<String,String> register(String username,String password,String confirmedPassword);
}
