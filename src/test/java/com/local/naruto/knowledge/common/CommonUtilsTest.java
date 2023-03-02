package com.local.naruto.knowledge.common;

import static org.assertj.core.api.Assertions.assertThat;

import com.local.naruto.knowledge.entity.ConditionModel;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonUtilsTest {

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
        Map<String, Object> condition = CommonUtils.getConditionMap(param);
        assertThat(condition.get("userName")).isEqualTo("name");
    }

}