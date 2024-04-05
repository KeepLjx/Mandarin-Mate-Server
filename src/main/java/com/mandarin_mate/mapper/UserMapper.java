package com.mandarin_mate.mapper;

import com.mandarin_mate.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2023-10-27 23:02:06
* @Entity com.mandrain_mate.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户微信登录的openid
     * @return
     */
    User selectByOpenid(String openid);



}




