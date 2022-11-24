package org.seheon.com.web.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@RunWith(SpringRunner.class)
@WebMvcTest // only for controller annotation
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
    @Test
    public void return_user() throws Exception {
        String id = "user id";
        String pw = "user pw";
        mvc.perform(get("/hello/user")
                .param("id", id)
                .param("password", pw))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(id)))
                .andExpect((ResultMatcher) jsonPath("$.password", is(pw)));

    }
}
