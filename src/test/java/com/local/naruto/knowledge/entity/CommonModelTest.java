package com.local.naruto.knowledge.entity;

import com.local.naruto.utils.DateUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommonModelTest {

    @Test
    void testCommonModel() {
        CommonModel common = new CommonModel();
        common.setStatus("1");
        common.setCreatedUser("created user");
        common.setCreatedDate(DateUtils.getUtcTime());
        common.setLastModifiedUser("modified user");
        common.setLastModifiedDate(DateUtils.getUtcTime());

        assertThat(common.getStatus()).isEqualTo("1");
        assertThat(common.getCreatedUser()).isEqualTo("created user");
        assertThat(common.getCreatedDate()).isNotNull();
        assertThat(common.getLastModifiedUser()).isEqualTo("modified user");
        assertThat(common.getLastModifiedDate()).isNotNull();
    }

}