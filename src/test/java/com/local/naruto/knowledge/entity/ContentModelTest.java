package com.local.naruto.knowledge.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ContentModelTest {

    @Test
    void testContentModel() {
        ContentModel content = new ContentModel();
        content.setContentId("contentId");
        content.setObjectId("objectId");
        content.setLang("lang");
        content.setContent1("content1");
        content.setContent2("content2");
        content.setContent3("content3");
        content.setContent4("content4");

        assertThat(content.getContentId()).isEqualTo("contentId");
        assertThat(content.getObjectId()).isEqualTo("objectId");
        assertThat(content.getContent1()).isEqualTo("content1");
        assertThat(content.getContent2()).isEqualTo("content2");
        assertThat(content.getContent3()).isEqualTo("content3");
        assertThat(content.getContent4()).isEqualTo("content4");
        assertThat(content.getLang()).isEqualTo("lang");
    }

}