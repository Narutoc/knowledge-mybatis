package com.local.naruto.knowledge.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConditionModelTest {

    @Test
    void testConditionModel() {
        ConditionModel condition = new ConditionModel();
        condition.setUserName("userName");
        condition.setRealName("realName");
        condition.setCurrentPage(2);
        condition.setPageSize(10);

        assertThat(condition.getUserName()).isEqualTo("userName");
        assertThat(condition.getRealName()).isEqualTo("realName");
        assertThat(condition.getCurrentPage()).isEqualTo(2);
        assertThat(condition.getPageSize()).isEqualTo(10);
    }

}