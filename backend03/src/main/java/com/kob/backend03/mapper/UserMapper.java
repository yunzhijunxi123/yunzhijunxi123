package com.kob.backend03.mapper;

import com.kob.backend03.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shan
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
