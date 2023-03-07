package com.local.naruto.knowledge.common;

import static org.assertj.core.api.Assertions.assertThat;

import com.local.naruto.knowledge.entity.ConditionModel;
import java.util.Map;

import com.local.naruto.knowledge.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonUtilTest {

    private ConditionModel param;

    @BeforeEach
    public void setUp() {
        param = new ConditionModel();
        param.setUserName("Name");
        param.setRealName("Real");
        param.setCurrentPage(1);
        param.setPageSize(10);
    }

    @Test
    void testGetConditionMap() {
        Map<String, Object> condition = CommonUtil.getConditionMap(param);
        assertThat(condition.get("userName")).isEqualTo("name");
    }

}