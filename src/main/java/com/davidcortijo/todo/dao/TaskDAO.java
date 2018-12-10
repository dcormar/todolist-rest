package com.davidcortijo.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davidcortijo.todo.beans.Task;

@Transactional
@Repository
public class TaskDAO implements ITaskDAO {
    @PersistenceContext	
    private EntityManager entityManager;
    
    @Override
    public List<Task> getAllTasks() {
        String hql = "FROM Task as t";
		return (List<Task>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Task getTaskById(int taskId){
        return entityManager.find(Task.class, taskId);
    }

    @Override
    public List<Task> getTasksByName(String taskName){
        String hql = "FROM Task where name = :taskName";
        Query query = entityManager.createQuery(hql);
        query.setParameter("taskName", taskName);
        return (List<Task>) query.getResultList();
    }

    @Override
    public void addATask(Task task){
        entityManager.persist(task);
    }

    @Override
    public void updateTask(Task task){
        Task artcl = getTaskById(task.getTaskId());
		artcl.setName(task.getName());
		artcl.setCategory(task.getCategory());
		entityManager.flush();
    }

    @Override
    public void deleteTask(int taskId){
        entityManager.remove(getTaskById(taskId));
    }

    @Override
    public boolean taskExists(String name){
        String hql = "FROM Task as t WHERE t.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name).getResultList().size();
		return count > 0 ? true : false;
    }
} 