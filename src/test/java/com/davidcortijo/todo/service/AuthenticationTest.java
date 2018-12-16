package com.davidcortijo.todo.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuthenticationTest {
    private static final String BASE_URL = "/todo"; //unit tests with @WebMvcTest does not load application.properties

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITaskService taskService;

    @Before
    public void setUp() {
    }

    @Test
    //@WithMockUser(username = "admin")
    public void shouldReturn401WhenSendingRequestToControllerWithoutUser() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", password="password")
    public void shouldReturn200WhenSendingRequestToControllerWithRightUser() throws Exception {
        mockMvc.perform(get(BASE_URL + "/hello")).andExpect(status().isOk());
    }
}