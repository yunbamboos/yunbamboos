package com.mapper;

import com.model.User;
import io.github.yunbamboos.model.Page;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 用户表(user)表数据库访问层
 */
@Repository
public interface UserMapper {
    /**
     * 通过主键查询单条数据
     *
     * @param userId 主键 用户标识
     * @return 实例对象
     */
    Optional<User> queryByPrimaryKey(@Param("user_id") int userId);

    /**
     * 通过条件分页查询指定行数据
     *
     * @param page 分页对象
     * @return 对象列表
     */
    List<User> queryByPage(@Param("page") Page page);

    /**
     * 通过条件统计总行数
     *
     * @param page 分页对象
     * @return 总行数
     */
    int countByPage(@Param("page") Page page);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键 用户标识
     * @return 影响行数
     */
    int deleteByPrimaryKey(@Param("user_id") int userId);

    /**
     * 登录调用
     *
     * @param loginName 登录名
     * @return 影响行数
     */
    Optional<User> queryByLoginName(@Param("login_name") String loginName);

    /**
     * 登录调用
     *
     * @param loginName 登录名
     * @param password  密码
     * @return 影响行数
     */
    Optional<User> queryByLoginNameAndPassword(@Param("login_name") String loginName, @Param("password") String password);

    /**
     * 查询最大的用户ID
     * @return 最后生成的用户ID
     */
    int queryMaxUserId();

    /**
     * 通过豆瓣用户ID(md5)查询用户信息
     * @param douBanUserId 豆瓣用户ID
     */
    Optional<User> queryByDouBanUserID(@Param("douban_user_id") String douBanUserId);

    /**
     * 通过豆瓣用户ID(md5)查询用户信息
     * */
    Integer queryUserIdByDouBanUserID(@Param("douban_user_id") String douBanUserId);

    /**
     * 查询范围内的ID列表
     */
    List<User> queryUserIdList(@Param("min_user_id") int minUserId, @Param("max_user_id") int maxUserId);
}