package com.kob.backend03.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shan
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {  //用户
    @TableId(type = IdType.AUTO)  //让Id自增
    private Integer id;
    private String username;
    private String password;
    private String photo;
}