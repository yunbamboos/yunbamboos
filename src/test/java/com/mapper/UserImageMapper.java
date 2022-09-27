package com.mapper;

import com.model.UserImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户头像表(user_image)表数据库访问层
 */
@Repository
public interface UserImageMapper {

    /**
     * 查询用户头像
     * @param userId 用户ID
     * @return 用户头像
     * */
    Optional<UserImage> queryByUserId(@Param("user_id") int userId);

}
