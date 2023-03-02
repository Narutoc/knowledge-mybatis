package com.local.naruto.knowledge.mapper.menu;

import com.local.naruto.knowledge.entity.MenuInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 菜单信息dao层
 *
 * @Author Naruto Chen
 * @Date 2022/02/11
 */
@Mapper
public interface MenuMapper {

    /**
     * 新增菜单信息
     *
     * @param model 菜单实体
     */
    void addMenuInfo(MenuInfoModel model);

    /**
     * 批量新增菜单信息
     *
     * @param list 菜单列表
     */
    void batchInsertMenu(List<MenuInfoModel> list);

    /**
     * 根据菜单id查询信息
     *
     * @param menuId 菜单id
     * @return MenuModel
     */
    MenuInfoModel getSingleMenu(@Param("menuId") String menuId);

    /**
     * 查询所有菜单
     *
     * @return List<MenuModel>
     */
    List<MenuInfoModel> getAllMenu();

    /**
     * 编辑菜单信息
     *
     * @param model 菜单实体
     */
    void updateMenuInfo(MenuInfoModel model);
}
