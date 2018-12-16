package com.davidcortijo.todo.rest;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.service.ITaskService;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@WebMvcTest
//@AutoConfigureMockMvc
public class TodoEndpointMVCTest {
    private static final Logger logger = LoggerFactory.getLogger(TodoEndpointMVCTest.class);	

    private static final String BASE_URL = "/todo";

    //These are Unit Tests for Spring Web MVC REST service "TodoEndpointMVC"

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ITaskService taskService;

    private Task task1;

    @Before
    public void setUp() {
        task1 = new Task();
        task1.setName("Test task 1");
        task1.setCategory("Test type 1");
        task1.setDescription("Test description 1");
        task1.setTaskId(1);
        List <Task> taskList = new ArrayList <Task> (1);
        taskList.add(task1);

        Mockito.when(taskService.getTaskById(task1.getTaskId()))
        .thenReturn(task1);
        Mockito.when(taskService.getAllTasks())
        .thenReturn(taskList);
    }

    @Test
    public void contextLoads() {
        assertThat(mvc).isNotNull();
    }

    
    @Test
    @WithMockUser(username = "user", password="password")
    public void shouldStartWithOneTestTask() throws Exception {
        MvcResult result = mvc.perform(get(BASE_URL + "/details/" + task1.getTaskId()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        //.andExpect(jsonPath("$", hasSize(1))) it is just a task, not a list of tasks
        .andExpect(jsonPath("$.name", is("Test task 1")))
        .andReturn();
    }

}