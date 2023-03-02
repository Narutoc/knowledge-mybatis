package com.local.naruto.knowledge.controller.demo;

import com.local.naruto.knowledge.TestUtils;
import com.local.naruto.knowledge.entity.ConditionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DemoControllerTest {
    MockMvc demoMockMvc;

    @BeforeEach
    void setUp() {
        DemoController demo = new DemoController();
        demoMockMvc = MockMvcBuilders.standaloneSetup(demo).build();
    }

    @Test
    void shouldGetSingle() throws Exception {
        demoMockMvc.perform(get("/rest/demo/single/123456")).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteDemo() throws Exception {
        demoMockMvc.perform(delete("/rest/demo/123456")).andExpect(status().isOk());
    }

    @Test
    void shouldInsertDemo() throws Exception {
        ConditionModel param = new ConditionModel();
        param.setUserName("naruto");
        param.setRealName("ChenXiaoQiang");
        param.setCurrentPage(10);
        param.setPageSize(1);
        demoMockMvc.perform(post("/rest/demo")
                .contentType(TestUtils.APPLICATION_CONTENT)
                .content(TestUtils.convertObjectToRequestBody(param)))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateDemo() throws Exception {
        ConditionModel param = new ConditionModel();
        param.setUserName("naruto");
        param.setRealName("ChenXiaoQiang");
        param.setCurrentPage(10);
        param.setPageSize(1);
        demoMockMvc.perform(put("/rest/demo")
                .contentType(TestUtils.APPLICATION_CONTENT)
                .content(TestUtils.convertObjectToRequestBody(param)))
            .andExpect(status().isOk());
    }
}