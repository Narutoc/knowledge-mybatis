package com.local.naruto.knowledge.service.menu;

import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.entity.MenuModel;
import java.util.List;

/**
 * 菜单操作接口定义
 *
 * @Author Naruto Chen
 * @Date 2022/02/11
 */
public interface MenuService {

    /**
     * 新增菜单信息
     *
     * @param model 菜单实体
     * @throws ServiceException 异常
     */
    void addMenuInfo(MenuModel model) throws ServiceException;

    /**
     * 批量插入菜单信息
     *
     * @param path   文件路径
     * @param userId 操作人id
     * @throws ServiceException 异常
     */
    void exportMenuInfoFromExcel(String path, String userId) throws ServiceException;

    /**
     * 查询所有菜单
     *
     * @return List<MenuModel>
     * @throws ServiceException 异常
     */
    List<MenuModel> getAllMenu() throws ServiceException;

    /**
     * 查询单个菜单
     *
     * @param menuId 菜单id
     * @return MenuModel
     * @throws ServiceException 异常
     */
    MenuModel getSingleMenu(String menuId) throws ServiceException;

    /**
     * 编辑菜单信息
     *
     * @param model 菜单实体
     * @throws ServiceException 异常
     */
    void updateMenuInfo(MenuModel model) throws ServiceException;
}
