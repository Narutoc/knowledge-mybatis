package com.local.naruto.knowledge.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 *
 * @Author Naruto Chen
 * @Date 2022/02/08
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends CommonModel {

    private String userId;
    private String userName;
    private String password;
    private String realName;
}
