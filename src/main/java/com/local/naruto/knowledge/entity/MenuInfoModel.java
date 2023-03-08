package com.local.naruto.knowledge.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单实体
 *
 * @author Naruto Chen
 * @date 2022/02/11
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuInfoModel extends CommonModel implements Serializable {

    @NotNull
    private String menuId;
    private String parentId;
    private String sortNum;
    private List<MenuInfoModel> child;
    private List<ContentModel> menuLanguageList;
}
