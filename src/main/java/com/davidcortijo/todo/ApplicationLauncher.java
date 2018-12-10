package com.davidcortijo.todo;

import com.davidcortijo.todo.rest.TodoEndpoint;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ApplicationLauncher {

	/* @GetMapping(value="/todo/web")
	public String todo() {
		return "Hello todo";
	} */
	
	@Bean
  	ResourceConfig resourceConfig() {
		return new ResourceConfig().register(TodoEndpoint.class);
  	}
	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}
}
