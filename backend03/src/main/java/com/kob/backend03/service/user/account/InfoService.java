package com.kob.backend03.service.user.account;
import java.util.Map;
/**
 * @author shan
 * @version 1.0
 */
public interface InfoService {  //根据令牌返回用户信息
    public Map<String,String> getinfo();
}
