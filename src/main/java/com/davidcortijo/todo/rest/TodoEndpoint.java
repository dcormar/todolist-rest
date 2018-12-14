package com.davidcortijo.todo.rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.davidcortijo.todo.beans.Task;
import com.davidcortijo.todo.service.ITaskService;

import java.net.URI;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/todo")
public class TodoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(TodoEndpoint.class);	

    public TodoEndpoint () {
        logger.info("Created Jersey resource TodoEndpoint");
    }

    @Autowired
	private ITaskService taskService;
  
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList() {
        logger.debug("Request received for resource 'list'");
        List <Task> tasks = taskService.getAllTasks();
        return Response.ok(tasks).build();
    }

    @GET
    @Path("/details/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskDetails(@PathParam("taskId") int taskId) {
        logger.debug("Request received for resource 'details'");
        return Response.ok(taskService.getTaskById(taskId)).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)	
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTask(Task task) {
        logger.debug("Request received for resource 'add'");
        boolean isAdded = taskService.addTask(task);
        if (!isAdded) {
        logger.info("Task " + task.getName() + " already exits.");
        return Response.status(Status.CONFLICT).build();
        }
        return Response.created(URI.create("/rest/details/"+ task.getTaskId())).build();
    }
}