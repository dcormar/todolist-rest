package com.davidcortijo.todo.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import javax.ws.rs.ApplicationPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
//@ApplicationPath("/jersey") -> overridden by application.properties
public class JerseyConf extends ResourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(JerseyConf.class);

	public JerseyConf () {
		logger.info("creating Jersey resources");
		this.register(TodoEndpoint.class);
	}
}