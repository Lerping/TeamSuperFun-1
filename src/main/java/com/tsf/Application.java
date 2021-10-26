package com.tsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Meta-annotation which includes component scanning, autoconfiguration, and
 * property support. Wrapper of JAX-RS / Jakarta Application.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
