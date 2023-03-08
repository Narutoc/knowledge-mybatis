package com.local.naruto.knowledge.controller.demo;

import com.local.naruto.common.JsonResult;
import com.local.naruto.knowledge.entity.ConditionModel;
import com.local.naruto.knowledge.util.DateOperationUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo控制层
 *
 * @author Naruto Chen
 * @date 2022/02/11
 */
@RestController
@RequestMapping(value = "/rest/demo")
public class DemoController {

    /**
     * 根据id查询
     *
     * @param id id
     * @return JsonResult<String>
     */
    @GetMapping(value = "/single/{id}")
    public JsonResult<String> getSingle(@PathVariable("id") String id) {
        return new JsonResult<>(id);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return JsonResult<String>
     */
    @DeleteMapping(value = "/{id}")
    public JsonResult<String> deleteDemo(@PathVariable("id") String id) {
        return new JsonResult<>(id);
    }

    /**
     * 新增
     *
     * @param model 新增实体
     * @return JsonResult<ConditionModel>
     */
    @PostMapping
    public JsonResult<ConditionModel> insertDemo(@RequestBody ConditionModel model) {
        return new JsonResult<>(model);
    }

    /**
     * 编辑
     *
     * @param model 编辑实体
     * @return JsonResult<ConditionModel>
     */
    @PutMapping
    public JsonResult<ConditionModel> updateDemo(@RequestBody ConditionModel model) {
        return new JsonResult<>(model);
    }

    @GetMapping(value = "/excel/{date}")
    public int getExcelInfo(@PathVariable("date") String date) {
        return DateOperationUtil.daysCountOfMonth(date);
    }
}