package com.mandarin_mate.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mandarin_mate.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mandarin_mate.pojo.dto.UserDTO;
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
    User selectByOpenId(String openid);


    /**
     * 更新用户的bookId
     */
    void updateInfo(Object object);

    /**
     * 获取用户信息
     */
    UserDTO getAllByUserId(@Param("userId") Long userId);

    /**
     * 获取所有注册过的邮箱
     * @return
     */
    List<String> selectUserMail();

    /**
     * 获取所有注册过的邮箱
     * @return
     */
    List<String> selectOpenId();

}




