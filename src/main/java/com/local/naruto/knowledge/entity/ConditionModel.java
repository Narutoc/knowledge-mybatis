package com.local.naruto.knowledge.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 条件查询模型
 *
 * @author Naruto Chen
 * @date 2022/02/09
 */
@Getter
@Setter
public class ConditionModel {

    private String userName;
    private String realName;
    private int currentPage;
    private int pageSize;
}
