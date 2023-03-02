package com.local.naruto.knowledge.service.user;

import com.local.naruto.common.JsonResult;
import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.entity.ConditionModel;
import com.local.naruto.knowledge.entity.UserInfoModel;
import java.util.List;

/**
 * UserService接口定义
 *
 * @Author Naruto Chen
 * @Date 2022/02/08
 */
public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return list
     * @throws ServiceException 运行时异常
     */
    List<UserInfoModel> getAllUser() throws ServiceException;

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User
     * @throws ServiceException 运行时异常
     */
    UserInfoModel getUserById(String id) throws ServiceException;

    /**
     * 批量插入用户信息
     *
     * @param list 用户信息列表
     * @throws ServiceException 运行时异常
     */
    void batchAddUser(List<UserInfoModel> list) throws ServiceException;

    /**
     * 批量更新用户信息
     *
     * @param list 用户信息列表
     * @throws ServiceException 运行时异常
     */
    void batchUpdateUser(List<UserInfoModel> list) throws ServiceException;

    /**
     * 多条件查询用户信息
     *
     * @param param 查询模型
     * @return JsonResult
     * @throws ServiceException 运行时异常
     */
    JsonResult<List<UserInfoModel>> conditionalQuery(ConditionModel param) throws ServiceException;

    /**
     * 批量删除用户信息
     *
     * @param ids id列表
     * @return int 1-成功
     * @throws ServiceException 运行时异常
     */
    int batchDeleteUser(List<String> ids) throws ServiceException;
}
