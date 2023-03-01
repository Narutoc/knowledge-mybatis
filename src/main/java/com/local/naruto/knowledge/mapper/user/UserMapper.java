package com.local.naruto.knowledge.mapper.user;

import com.local.naruto.knowledge.entity.UserModel;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息dao层
 *
 * @Author Naruto Chen
 * @Date 2022/02/08
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return list
     */
    List<UserModel> getAllUser();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User
     */
    UserModel getUserById(@Param("id") String id);

    /**
     * 批量插入用户信息
     *
     * @param list 用户信息列表
     */
    void batchAddUser(List<UserModel> list);

    /**
     * 批量更新用户信息
     *
     * @param list 用户信息列表
     */
    void batchUpdateUser(List<UserModel> list);

    /**
     * 多条件查询用户信息
     *
     * @param param 查询条件
     * @return list
     */
    List<UserModel> conditionalQuery(Map<String, Object> param);

    /**
     * 多条件查询用户信息总数
     *
     * @param param 查询条件
     * @return int
     */
    int conditionalQueryCount(Map<String, Object> param);

    /**
     * 批量删除用户信息
     *
     * @param ids id列表
     * @return int 1-成功
     */
    int batchDeleteUser(@Param("ids") List<String> ids);
}
