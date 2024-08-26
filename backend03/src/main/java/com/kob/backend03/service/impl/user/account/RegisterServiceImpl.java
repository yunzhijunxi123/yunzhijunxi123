package com.kob.backend03.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend03.mapper.UserMapper;
import com.kob.backend03.pojo.User;
import com.kob.backend03.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shan
 * @version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map =new HashMap<>();
        if(username == null){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null){
            map.put("error_message","密码不能为空");
            return map;
        }
        username = username.trim();//删掉首尾空白字符
        if(username.isEmpty()){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password.isEmpty()||confirmedPassword.isEmpty()){
            map.put("error_message","密码不能为空");
            return map;
        }
        if(username.length()>100){
            map.put("error_message","用户名长度不能大于100");
            return map;
        }
        if(password.length()>100||confirmedPassword.length()>100){
            map.put("error_message","密码长度不能大于100");
            return map;
        }
        if(!password.equals(confirmedPassword)){
            map.put("error_message","两次的密码不能重复");
            return map;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();  //查询数据库里面是否有用户名为username的用户
        queryWrapper.eq("username",username);
        List<User> users =userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message","用户名已存在");
            return map;
        }
        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/362845_lg_8f83b9574f.jpg";
        User user = new User(null,username,encodedPassword,photo);
        userMapper.insert(user);

        map.put("error_message","success");
        return map;
    }
}
