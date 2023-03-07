package com.local.naruto.knowledge.controller.menu;

import com.local.naruto.common.JsonResult;
import com.local.naruto.knowledge.common.annotation.LogTrace;
import com.local.naruto.knowledge.entity.MenuInfoModel;
import com.local.naruto.knowledge.service.menu.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制层
 *
 * @Author Naruto Chen
 * @Date 2022/02/11
 */
@LogTrace
@RestController
@RequestMapping(value = "/rest/menu")
@CacheConfig(cacheNames = {"com.local.naruto.knowledge.controller.menu"})
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 新增菜单信息
     *
     * @param model 菜单实体
     * @return string
     */
    @PostMapping
    @Caching(
        put = @CachePut(key = "#model.menuId"),
        evict = @CacheEvict(key = "'allMenu'")
    )
    public JsonResult<String> addMenuInfo(@RequestBody MenuInfoModel model) {
        menuService.addMenuInfo(model);
        return new JsonResult<>(model.getMenuId());
    }

    @PostMapping("/batch")
    public JsonResult<String> batchInsertMenu() {
        String path = "D:\\tmp\\menuInfo_copy.xlsx";
        String userId = "naruto";
        menuService.exportMenuInfoFromExcel(path, userId);
        return new JsonResult<>();
    }

    /**
     * 编辑菜单信息
     *
     * @param model 菜单实体
     * @return string
     */
    @PutMapping
    @Caching(
        put = @CachePut(key = "#model.menuId"),
        evict = @CacheEvict(key = "'allMenu'")
    )
    public JsonResult<String> updateMenuInfo(@RequestBody MenuInfoModel model) {
        menuService.updateMenuInfo(model);
        return new JsonResult<>(model.getMenuId());
    }

    /**
     * 查询所有菜单
     *
     * @return list
     */
    @GetMapping
    @Cacheable(key = "'allMenu'")
    public JsonResult<List<MenuInfoModel>> getAllMenu() {
        return new JsonResult<>(menuService.getAllMenu());
    }

    /**
     * 查询单个菜单
     *
     * @param id 菜单id
     * @return MenuModel
     */
    @Cacheable(value = "menuCache", key = "#id", unless = "#result == null ")
    @GetMapping(value = "/{id}")
    public JsonResult<MenuInfoModel> getMenuById(@PathVariable String id) {
        return new JsonResult<>(menuService.getMenuById(id));
    }

    /**
     * 删除单个菜单
     *
     * @param id 菜单id
     * @return MenuModel
     */
    @Caching(
        evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "'allMenu'")}
    )
    @DeleteMapping(value = "/{id}")
    public JsonResult<String> deleteByMenuId(@PathVariable String id) {
        menuService.deleteByMenuId(id);
        return new JsonResult<>(id);
    }
}
