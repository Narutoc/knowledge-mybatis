package com.local.naruto.knowledge.service.content;

import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.entity.ContentModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 内容信息操作接口定义
 *
 * @author Naruto Chen
 * @date 2022/05/02
 */
public interface ContentService {

    /**
     * 批量插入内容信息
     *
     * @param contentList 内容信息列表
     * @throws ServiceException 服务异常
     */
    void batchInsertContent(@Param("contentList") List<ContentModel> contentList)
        throws ServiceException;

    /**
     * 批量更新内容信息
     *
     * @param contentList 内容信息列表
     * @throws ServiceException 服务异常
     */
    void batchUpdateContent(@Param("contentList") List<ContentModel> contentList)
        throws ServiceException;

    /**
     * 根据objectId删除多语言信息
     *
     * @param objectId 对象id
     * @throws ServiceException 服务异常
     */
    void deleteByObjectId(@Param("objectId") String objectId) throws ServiceException;
}
