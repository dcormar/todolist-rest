package com.davidcortijo.todo;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.dao.ITaskDAO;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Condition;
import org.junit.Before;
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

Task task1;
Task task2;

@Before
public void setUp() {
    task1 = new Task();
    task1.setName("Test task 1");
    task1.setCategory("Test type 1");
    task1.setDescription("Test description 1");
    taskDAO.addATask(task1);

    task2 = new Task();
    task2.setName("Test task 2");
    task2.setCategory("Test type 2");
    task2.setDescription("Test description 2");
    taskDAO.addATask(task2);
}


@Test
public void whenFindById_thenReturnTask() { 
    Task retrieved = taskDAO.getTaskById(task1.getTaskId());
    assertThat(retrieved.getName()).isEqualTo(task1.getName());
}

@Test
public void whenFindAll_thenReturnListOfTasks() { 
    List <Task> retrievedList = taskDAO.getAllTasks();
    assertThat(retrievedList.size()).isGreaterThanOrEqualTo(2);
}

@Test
public void whenSaveTask_thenCanBeFound() { 
    Task task3 = new Task();
    task3.setName("Test task 3");
    task3.setCategory("Test type 3");
    task3.setDescription("Test description 3");
    taskDAO.addATask(task3);
    
    Condition<Task> isTrue = new Condition<>(task-> true, "This condition is always true");
    assertThat(taskDAO.getTasksByName(task3.getName())).areAtLeast(1, isTrue);

    //Condition <Task> isTask3 = new Condition<>(((Task)value).getName.equals(task3.getName()), "this is task3");
    assertThat(taskDAO.getTasksByName(task3.getName())).allSatisfy(task -> {
        assertThat(task.getName()).isEqualTo(task3.getName ());
        assertThat(task.getCategory()).isEqualTo(task3.getCategory());
    });
    
    assertThat(taskDAO.getTasksByName(task3.getName())).areAtLeast(1, isTrue);
}
    
}