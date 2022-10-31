package com.mapper;

import com.model.UserConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户配置表(user_config)表数据库访问层
 */
@Repository
public interface UserConfigMapper {

    List<UserConfig> queryByUserId(@Param("user_id") int userId);
}
