package com.davidcortijo.todo.dao;

import java.util.List;

import com.davidcortijo.todo.beans.Task;

import org.springframework.stereotype.Repository;

@Repository
public interface ITaskDAO {
    List<Task> getAllTasks();
    Task getTaskById(int taskId);
    List<Task> getTasksByName(String taskName);
    void addATask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    boolean taskExists(String name);
} 