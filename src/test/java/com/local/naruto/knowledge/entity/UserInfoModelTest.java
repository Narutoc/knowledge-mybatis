package com.local.naruto.knowledge.entity;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserInfoModelTest {

    @Test
    void testUserInfo() {
        UserInfoModel user = new UserInfoModel();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setPassword("password");
        user.setRealName("realName");

        assertThat(user.getUserId()).isEqualTo("userId");
        assertThat(user.getUserName()).isEqualTo("userName");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getRealName()).isEqualTo("realName");
    }

}