package com.kob.backend03.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend03.mapper.UserMapper;
import com.kob.backend03.pojo.User;
import com.kob.backend03.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

/**
 * @author shan
 * @version 1.0
 */

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //传入username.通过传入的username返回用户名和密码
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);  //用户名相同
        User user = userMapper.selectOne(queryWrapper);  //查询user
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
