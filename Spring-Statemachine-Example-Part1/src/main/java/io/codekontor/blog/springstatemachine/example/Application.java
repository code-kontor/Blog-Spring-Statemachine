package io.codekontor.blog.springstatemachine.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.codekontor.blog.springstatemachine.example.service.IExampleService;

/**
 * Example application to illustrate the usage of the Spring Statemachine Framework.
 * 
 * @author Gerd W&uuml;therich (gw@code-kontor.io)
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	/** the logger instance */
	private final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private IExampleService _service;

	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("Starting...");

		// first start... 
		_service.start();

		// this fails as the service already has been started
		try {
			_service.start();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		// stop the service
		_service.stop();

		// start the service again
		_service.start();

		// stop the service again
		_service.stop();

		// finally terminate the service
		_service.terminate();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// simply run the spring application
		SpringApplication.run(Application.class, args);
	}
}
