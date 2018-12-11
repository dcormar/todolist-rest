package com.davidcortijo.todo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.dao.ITaskDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
 
   /* @TestConfiguration
    static class TaskServiceTestContextConfiguration {
  
        @Bean
        public ITaskService taskService() {
            return new TaskService();
        }
    }
    */
    @Autowired
    private ITaskService taskService;
 
    @MockBean
    private ITaskDAO taskDAO;
    
    @Before
    public void setUp() {
        Task task1 = new Task();
        task1.setName("Test task 1");
        task1.setCategory("Test type 1");
        task1.setDescription("Test description 1");
        task1.setTaskId(1);
        taskDAO.addATask(task1);
        List <Task> taskList = new ArrayList<Task>(1);
        taskList.add(task1);

        Mockito.when(taskDAO.getTasksByName(task1.getName()))
        .thenReturn(taskList);
        Mockito.when(taskDAO.getTaskById(task1.getTaskId()))
        .thenReturn(task1);
    }

    @Test
    public void TaskServiceAutowired() {   
        assertThat(taskService).isNotNull();
    }

    @Test
    public void whenValidName_thenTaskListShouldBeFound() {
        String name = "Test task 1";
        List <Task> foundList = taskService.getTasksByName(name);
        assertThat(foundList.size()).isGreaterThanOrEqualTo(1);
        Task found = foundList.get(0);
        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenValidId_thenTaskShouldBeFound() {
        int id = 1;
        Task found = taskService.getTaskById(id);
        assertThat(found.getTaskId()).isEqualTo(id);
    }
}