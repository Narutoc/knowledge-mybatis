package com.local.naruto.knowledge.entity;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MenuInfoModelTest {

    @Test
    void testMenuInfo() {
        MenuInfoModel menu = new MenuInfoModel();
        menu.setMenuId("menuId");
        menu.setParentId("parentId");
        menu.setSortNum("1");
        menu.setChild(new ArrayList<>());
        menu.setMenuLanguageList(new ArrayList<>());

        assertThat(menu.getMenuId()).isEqualTo("menuId");
        assertThat(menu.getParentId()).isEqualTo("parentId");
        assertThat(menu.getSortNum()).isEqualTo("1");
        assertThat(menu.getChild().size()).isEqualTo(0);
        assertThat(menu.getMenuLanguageList().size()).isEqualTo(0);
    }
}