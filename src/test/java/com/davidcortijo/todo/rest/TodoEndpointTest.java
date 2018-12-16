package com.davidcortijo.todo.rest;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.service.ITaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc unit tests are not available on Jackson, you must perform integration tests (=> start up the app server)
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoEndpointTest {
    private static final Logger logger = LoggerFactory.getLogger(TodoEndpointTest.class);	

    private static final String BASE_URL = "/jersey/todo";

    @TestConfiguration
    static class TestRestTemplateAuthenticationConfiguration {

        @Value("${spring.security.user.name}")
        private String userName;
    
        @Value("${spring.security.user.password}")
        private String password;

        @Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication(userName, password);
		}
    }
    

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ITaskService taskService;

    //To convert tasks to JSON, and other way around
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        Task task1 = new Task();
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
        assertThat(restTemplate).isNotNull();
    }
    
    @Test
    public void shouldStartWithOneTestTask() {
        //ResponseEntity <String> entity = restTemplate.withBasicAuth("user", "password").getForEntity(BASE_URL + "/list", String.class);
        ResponseEntity <String> entity = restTemplate.getForEntity(BASE_URL + "/list", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List <Task> returnedTaskList = null;
        String response = entity.getBody();
        logger.info(response);
        try{
            returnedTaskList = mapper.readValue(response, new TypeReference<List<Task>>(){}); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(returnedTaskList).isNotNull();
        assertThat(returnedTaskList).hasSize(1);
        assertThat(returnedTaskList.get(0).getName()).isEqualTo("Test task 1");
    }
}