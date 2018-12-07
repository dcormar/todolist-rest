package com.davidcortijo.todo.service;

import java.util.List;
import com.davidcortijo.todo.beans.Task;

public interface ITaskService {
     List<Task> getAllTasks();
     Task getTaskById(int taskId);
     boolean addTask(Task task);
     void updateTask(Task task);
     void deleteTask(int taskId);
} 