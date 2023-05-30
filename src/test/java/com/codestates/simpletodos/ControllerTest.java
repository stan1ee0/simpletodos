package com.codestates.simpletodos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void PostTodoTest() throws Exception {
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle("코딩하기");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(todoDto);

        ResultActions actions = mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(todoDto.getTitle()));
    }
}
