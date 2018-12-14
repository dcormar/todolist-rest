package com.davidcortijo.todo.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.service.ITaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/todo")
public class TodoEndpointMVC {
    private static final Logger logger = LoggerFactory.getLogger(TodoEndpointMVC.class);	
    
    @Autowired
    private ITaskService taskService;
    
    public TodoEndpointMVC () {
        logger.info("Created Spring MVC resource TodoEndpointMVC");
    }

    @GetMapping(value="/details/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskDetails (@PathVariable int id) {
        logger.debug("MVC Request received for resource 'list'");
        return taskService.getTaskById(id);
    }

    @GetMapping(value="/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)  
    public List <Task> allTasks() {
        logger.debug("MVC Request received for resource 'list'");
        return taskService.getAllTasks();
    }

    @GetMapping(value="/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        logger.debug("MVC Request received for resource 'hello'");
        return "hello world";
    }
}