package com.local.naruto.knowledge.controller.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.local.naruto.knowledge.service.menu.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MenuControllerTest {

    MockMvc menuMockMvc;
    @Mock
    private MenuService menuService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        MenuController menuController = new MenuController();
        menuMockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
        ReflectionTestUtils.setField(menuController, "menuService", menuService);
    }

    @Test
    void shouldBatchInsertMenu() throws Exception {
        menuMockMvc.perform(post("/rest/menu/batch")).andExpect(status().isOk());
    }

    @Test
    void shouldAddMenuInfo(){

    }
}