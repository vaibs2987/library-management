package com.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableAsync
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger("com.library.App");

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		LOGGER.info(ctx.getId() + ":==>" + ctx.getDisplayName());
		LOGGER.info(ctx.getId() + ":==>" + ctx.getDisplayName());
	}
}
