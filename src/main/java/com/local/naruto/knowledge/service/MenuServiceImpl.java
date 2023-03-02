package com.local.naruto.knowledge.service;

import com.local.naruto.common.Constants;
import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.entity.ContentModel;
import com.local.naruto.knowledge.entity.MenuInfoModel;
import com.local.naruto.knowledge.mapper.menu.MenuMapper;
import com.local.naruto.knowledge.service.content.ContentService;
import com.local.naruto.knowledge.service.menu.MenuService;
import com.local.naruto.knowledge.util.ExportExcelUtil;
import com.local.naruto.utils.DateUtils;
import com.local.naruto.utils.UUIDUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单业务实现
 *
 * @Author Naruto Chen
 * @Date 2022/02/11
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    private static final String EMPTY_ID = "menu id is empty";

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    ContentService contentService;

    /**
     * 新增菜单信息
     *
     * @param model 菜单实体
     * @throws ServiceException 异常
     */
    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void addMenuInfo(MenuInfoModel model) throws ServiceException {
        try {
            model.setMenuId(UUIDUtils.generateUuid());
            model.setCreatedDate(DateUtils.getUtcTime());
            model.setLastModifiedDate(DateUtils.getUtcTime());
            if (StringUtils.isNotEmpty(model.getParentId())) {
                MenuInfoModel parent = menuMapper.getSingleMenu(model.getParentId());
                if (parent == null) {
                    log.error("parent menu does not exist");
                    throw ServiceException.paramException("parent menu does not exist");
                }
            }
            handleMenuContent(model.getMenuLanguageList(), model.getMenuId());
            menuMapper.addMenuInfo(model);
            return;
        } catch (BindingException bind) {
            log.error("addMenuInfo bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("addMenuInfo exception is " + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "addMenuInfo caught en error");
    }

    /**
     * 查询所有菜单
     *
     * @return List<MenuModel>
     * @throws ServiceException 异常
     */
    @Override
    public List<MenuInfoModel> getAllMenu() throws ServiceException {
        try {
            return menuMapper.getAllMenu();
        } catch (BindingException bind) {
            log.error("getAllMenu bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("getAllMenu exception is" + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "getAllMenu caught en error");
    }


    /**
     * 查询单个菜单
     *
     * @param menuId 菜单id
     * @return MenuModel
     * @throws ServiceException 异常
     */
    @Override
    public MenuInfoModel getSingleMenu(String menuId) throws ServiceException {
        try {
            if (StringUtils.isEmpty(menuId)) {
                log.error(EMPTY_ID);
                throw new ServiceException(Constants.INT_400, EMPTY_ID);
            }
            return menuMapper.getSingleMenu(menuId);
        } catch (BindingException bind) {
            log.error("getSingleMenu bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("getSingleMenu exception is" + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "getSingleMenu caught en error");
    }

    /**
     * 编辑菜单信息
     *
     * @param model 菜单实体
     * @throws ServiceException 异常
     */
    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void updateMenuInfo(MenuInfoModel model) throws ServiceException {
        try {
            if (StringUtils.isEmpty(model.getMenuId())) {
                log.error(EMPTY_ID);
                throw new ServiceException(Constants.INT_400, EMPTY_ID);
            }
            MenuInfoModel single = menuMapper.getSingleMenu(model.getMenuId());
            if (single == null) {
                log.error("updated menu does not exist");
                throw new ServiceException(Constants.INT_400, "updated menu does not exist");
            }
            // 多语言信息处理：先删除，再新增
            handleMenuContent(model.getMenuLanguageList(), model.getMenuId());
            model.setLastModifiedDate(DateUtils.getUtcTime());
            menuMapper.updateMenuInfo(model);
            return;
        } catch (BindingException bind) {
            log.error("updateMenuInfo bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("updateMenuInfo exception is" + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "updateMenuInfo caught en error");
    }

    /**
     * 批量插入菜单信息
     *
     * @param path 文件路径
     * @param userId 操作人id
     * @throws ServiceException 异常
     */
    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void exportMenuInfoFromExcel(String path, String userId) throws ServiceException {
        try {
            List<MenuInfoModel> menuList = ExportExcelUtil.readInfo(path, userId);
            // 批量插入菜单基础信息
            menuMapper.batchInsertMenu(menuList);
            List<ContentModel> allMenuLanguage = new ArrayList<>();
            for (MenuInfoModel menu : menuList) {
                List<ContentModel> menuLanguageList = menu.getMenuLanguageList();
                allMenuLanguage.addAll(menuLanguageList);
            }
            // 批量插入菜单语言信息
            contentService.batchInsertContent(allMenuLanguage);
            return;
        } catch (BindingException bind) {
            log.error("exportMenuInfoFromExcel bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("exportMenuInfoFromExcel exception is" + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "exportMenuInfoFromExcel caught en error");
    }

    private void handleMenuContent(List<ContentModel> contentList, String menuId) {
        if (CollectionUtils.isNotEmpty(contentList)) {
            // 先删除菜单语言信息
            contentService.deleteByObjectId(menuId);
            for (ContentModel content : contentList) {
                content.setObjectId(menuId);
            }
            // 再新增菜单语言信息
            contentService.batchInsertContent(contentList);
        }
    }
}
