package com.local.naruto.knowledge.controller.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.local.naruto.knowledge.TestUtils;
import com.local.naruto.knowledge.entity.ContentModel;
import com.local.naruto.knowledge.entity.MenuInfoModel;
import com.local.naruto.knowledge.service.menu.MenuService;
import com.local.naruto.utils.UUIDUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
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
    void shouldAddMenuInfo() throws Exception {
        menuMockMvc.perform(post("/rest/menu")
                .content(TestUtils.convertObjectToRequestBody(getMenuInfoModel()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    private MenuInfoModel getMenuInfoModel() {
        MenuInfoModel menu = new MenuInfoModel();
        menu.setMenuId("1234567");
        menu.setParentId(null);
        menu.setSortNum("1");
        menu.setChild(getChildMenu("1234567"));
        menu.setMenuLanguageList(getMenuLanguageList());
        return menu;
    }

    private List<MenuInfoModel> getChildMenu(String parentId) {
        List<MenuInfoModel> childMenu = new ArrayList<>();
        MenuInfoModel child1 = new MenuInfoModel();
        child1.setMenuId(UUIDUtils.generateUuid());
        child1.setParentId(parentId);
        child1.setSortNum("1");
        childMenu.add(child1);

        MenuInfoModel child2 = new MenuInfoModel();
        child2.setMenuId(UUIDUtils.generateUuid());
        child2.setParentId(parentId);
        child2.setSortNum("2");
        childMenu.add(child2);
        return childMenu;
    }

    private List<ContentModel> getMenuLanguageList() {
        List<ContentModel> languageList = new ArrayList<>();
        ContentModel language1 = new ContentModel();
        language1.setObjectId("objectId");
        language1.setContentId("contentId");
        language1.setLang("1");
        language1.setContent1("content1");
        languageList.add(language1);
        return languageList;
    }

}