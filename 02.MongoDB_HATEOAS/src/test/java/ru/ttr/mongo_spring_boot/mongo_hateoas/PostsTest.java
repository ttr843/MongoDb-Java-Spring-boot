package ru.ttr.mongo_spring_boot.mongo_hateoas;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.ttr.mongo_spring_boot.mongo_hateoas.models.Post;
import ru.ttr.mongo_spring_boot.mongo_hateoas.repositories.PostsRepository;
import ru.ttr.mongo_spring_boot.mongo_hateoas.services.PostsService;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PostsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsService postsService;


    @BeforeEach
    public void setUp() {
        when(postsService.setExpired("5fbed1146eddd4205107cdd0")).thenReturn(expiredPost());
    }

    @Test
    public void postSetExpiredTest() throws Exception {
        mockMvc.perform(put("/posts/5fbed1146eddd4205107cdd0/setExpired")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(expiredPost().getDate()))
                .andExpect(jsonPath("$.description").value(expiredPost().getDescription()))
                .andExpect(jsonPath("$.expired").value(true))
                .andExpect(jsonPath("$.grade").value(expiredPost().getGrade()))
                .andExpect(jsonPath("$.title").value(expiredPost().getTitle()))
                .andDo(document("putSetExpired",responseFields(subsectionWithPath("_links").ignored(),
                        fieldWithPath("date").description("Дата"),
                        fieldWithPath("description").description("Описание"),
                        fieldWithPath("expired").description("Просрочено"),
                        fieldWithPath("grade").description("Оценка"),
                        fieldWithPath("keywords").description("Ключевые слова"),
                        fieldWithPath("title").description("Заголовок"),
                        fieldWithPath("user.timestamp").ignored(),
                        fieldWithPath("user.date").ignored()
                        )));
    }


    private Post expiredPost() {
        return Post.builder()
                ._id("5fbed1146eddd4205107cdd0")
                .date("date")
                .description("desc")
                .expired(true)
                .grade(5)
                .title("ExpiredPost")
                .user(new ObjectId())
                .build();
    }

}
