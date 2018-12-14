package com.davidcortijo.todo;

import javax.ws.rs.ApplicationPath;

import com.davidcortijo.todo.rest.TodoEndpoint;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ApplicationLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}
}
