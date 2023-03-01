package com.local.naruto.knowledge.mapper.content;

import com.local.naruto.knowledge.entity.ContentModel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 内容信息dao层
 *
 * @Author Naruto Chen
 * @Date 2022/05/02
 */
@Mapper
public interface ContentMapper {

    /**
     * 批量插入内容信息
     *
     * @param contentList 内容信息列表
     */
    void batchInsertContent(@Param("contentList") List<ContentModel> contentList);

    /**
     * 批量更新内容信息
     *
     * @param contentList 内容信息列表
     */
    void batchUpdateContent(@Param("contentList") List<ContentModel> contentList);

    /**
     * 根据objectId删除多语言信息
     *
     * @param objectId 对象id
     */
    void deleteByObjectId(@Param("objectId") String objectId);
}
