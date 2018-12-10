package com.davidcortijo.todo;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.dao.ITaskDAO;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDAOTest {
  
@Autowired
private ITaskDAO taskDAO;
    
@Test
public void whenFindById_thenReturnTask() {
    Task task = new Task();
    task.setName("Test task");
    task.setCategory("Test type");
    task.setDescription("Test description");
    taskDAO.addATask(task);
 
    Task retrieved = taskDAO.getTaskById(task.getTaskId());
 
    assertThat(retrieved.getName()).isEqualTo(task.getName());
}

    
}