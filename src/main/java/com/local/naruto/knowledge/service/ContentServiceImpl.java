package com.local.naruto.knowledge.service;

import com.local.naruto.common.Constants;
import com.local.naruto.exception.ServiceException;
import com.local.naruto.knowledge.entity.ContentModel;
import com.local.naruto.knowledge.mapper.content.ContentMapper;
import com.local.naruto.knowledge.service.content.ContentService;
import com.local.naruto.utils.DateUtils;
import com.local.naruto.utils.UUIDUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内容信息业务实现
 *
 * @Author Naruto Chen
 * @Date 2022/05/02
 */
@Service
@Slf4j
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentMapper contentMapper;

    /**
     * 批量插入内容信息
     *
     * @param contentList 内容信息列表
     * @throws ServiceException 服务异常
     */
    @Override
    public void batchInsertContent(List<ContentModel> contentList) throws ServiceException {
        try {
            if (CollectionUtils.isNotEmpty(contentList)) {
                for (ContentModel content : contentList) {
                    content.setContentId(UUIDUtils.generateUuid());
                    content.setCreatedDate(DateUtils.getUtcTime());
                    content.setLastModifiedDate(DateUtils.getUtcTime());
                }
                contentMapper.batchInsertContent(contentList);
            }
            return;
        } catch (BindingException bind) {
            log.error("batchInsertContent bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("batchInsertContent exception is " + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "batchInsertContent caught en error");
    }

    /**
     * 批量更新内容信息
     *
     * @param contentList 内容信息列表
     * @throws ServiceException 服务异常
     */
    @Override
    public void batchUpdateContent(List<ContentModel> contentList) throws ServiceException {
        try {
            if (CollectionUtils.isNotEmpty(contentList)) {
                for (ContentModel content : contentList) {
                    content.setLastModifiedDate(DateUtils.getUtcTime());
                }
                contentMapper.batchUpdateContent(contentList);
            }
            return;
        } catch (BindingException bind) {
            log.error("batchUpdateContent bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("batchUpdateContent exception is " + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "batchUpdateContent caught en error");
    }

    /**
     * 根据objectId删除多语言信息
     *
     * @param objectId 对象id
     * @throws ServiceException 服务异常
     */
    @Override
    public void deleteByObjectId(String objectId) throws ServiceException {
        try {
            contentMapper.deleteByObjectId(objectId);
            return;
        } catch (BindingException bind) {
            log.error("deleteByObjectId bindingException is " + bind.getMessage());
        } catch (Exception exception) {
            log.error("deleteByObjectId exception is " + exception.getMessage());
        }
        throw new ServiceException(Constants.INT_500, "deleteByObjectId caught en error");
    }
}
