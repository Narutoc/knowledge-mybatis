package com.local.naruto.knowledge.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 语言信息模型
 *
 * @author Naruto Chen
 * @date 2022/02/11
 */
@Getter
@Setter
public class ContentModel extends CommonModel implements Serializable {

    @NotNull
    private String contentId;
    @NotNull
    private String objectId;
    private String lang;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
}
