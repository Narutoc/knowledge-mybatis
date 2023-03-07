package com.local.naruto.knowledge.service;

import com.local.naruto.common.JsonResult;
import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.util.CommonUtil;
import com.local.naruto.knowledge.entity.ConditionModel;
import com.local.naruto.knowledge.entity.UserInfoModel;
import com.local.naruto.knowledge.mapper.user.UserMapper;
import com.local.naruto.knowledge.service.user.UserService;
import com.local.naruto.utils.DateUtils;
import com.local.naruto.utils.UUIDUtils;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService业务实现
 *
 * @Author Naruto Chen
 * @Date 2022/02/08
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 查询所有用户信息
     *
     * @return list
     * @throws ServiceException 运行时异常
     */
    @Override
    public List<UserInfoModel> getAllUser() throws ServiceException {
        try {
            return userMapper.getAllUser();
        } catch (BindingException bind) {
            log.error("getAllUser bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("getAllUser exception is" + exception.getMessage());
        }
        throw new ServiceException("getAllUser caught en error");
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User
     * @throws ServiceException 运行时异常
     */
    @Override
    public UserInfoModel getUserById(String id) throws ServiceException {
        try {
            return userMapper.getUserById(id);
        } catch (BindingException bind) {
            log.error("getUserById bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("getUserById exception is" + exception.getMessage());
        }
        throw new ServiceException("getUserById caught en error");
    }

    /**
     * 批量插入用户信息
     *
     * @param list 用户信息列表
     * @throws ServiceException 运行时异常
     */
    @Override
    public void batchAddUser(List<UserInfoModel> list) throws ServiceException {
        try {
            if (CollectionUtils.isNotEmpty(list)) {
                for (UserInfoModel single : list) {
                    single.setUserId(UUIDUtils.generateUuid());
                    single.setCreatedDate(DateUtils.getUtcTime());
                    single.setLastModifiedDate(DateUtils.getUtcTime());
                }
                userMapper.batchAddUser(list);
            }
            return;
        } catch (BindingException bind) {
            log.error("batchAddUser bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("batchAddUser exception is" + exception.getMessage());
        }
        throw new ServiceException("batchAddUser caught en error");
    }

    /**
     * 批量更新用户信息
     *
     * @param list 用户信息列表
     * @throws ServiceException 运行时异常
     */
    @Override
    public void batchUpdateUser(List<UserInfoModel> list) throws ServiceException {
        try {
            if (CollectionUtils.isNotEmpty(list)) {
                for (UserInfoModel single : list) {
                    single.setLastModifiedDate(DateUtils.getUtcTime());
                }
            }
            userMapper.batchUpdateUser(list);
            return;
        } catch (BindingException bind) {
            log.error("batchUpdateUser bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("batchUpdateUser exception is" + exception.getMessage());
        }
        throw new ServiceException("batchUpdateUser caught en error");
    }

    /**
     * 多条件查询用户信息
     *
     * @param param 查询条件
     * @return int
     * @throws ServiceException 运行时异常
     */
    @Override
    public JsonResult<List<UserInfoModel>> conditionalQuery(ConditionModel param)
        throws ServiceException {
        try {
            Map<String, Object> map = CommonUtil.getConditionMap(param);
            List<UserInfoModel> list = userMapper.conditionalQuery(map);
            int num = userMapper.conditionalQueryCount(map);
            return new JsonResult<>(list, num);
        } catch (BindingException bind) {
            log.error("conditionalQuery bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("conditionalQuery exception is" + exception.getMessage());
        }
        throw new ServiceException("conditionalQuery caught en error");
    }

    /**
     * 批量删除用户信息
     *
     * @param ids id列表
     * @throws ServiceException 运行时异常
     */
    @Override
    public int batchDeleteUser(List<String> ids) throws ServiceException {
        try {
            return userMapper.batchDeleteUser(ids);
        } catch (BindingException bind) {
            log.error("batchDeleteUser bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("batchDeleteUser exception is" + exception.getMessage());
        }
        throw new ServiceException("batchDeleteUser caught en error");
    }
}