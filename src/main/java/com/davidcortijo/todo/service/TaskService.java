package com.davidcortijo.todo.service;

import java.util.List;
import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.dao.ITaskDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskDAO taskDAO;
     
    public List<Task> getAllTasks(){
        return taskDAO.getAllTasks();
     }
    public Task getTaskById(int taskId){
        return taskDAO.getTaskById(taskId);
     }
    public boolean addTask(Task task){
        taskDAO.addATask(task);
        return true;
     }
    public void updateTask(Task task){
        taskDAO.updateTask(task);
     }
    public void deleteTask(int taskId){
        taskDAO.deleteTask(taskId);
     }
} 