// LOAD DATABASE
// package com.tsf;
//
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
//
//
// /* Meta-annotation which includes component scanning, autoconfiguration, and
//  * property support. Wrapper of JAX-RS / Jakarta Application.
//  */
// @SpringBootApplication
// public class Application {
//     private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
// 	public static void main(String[] args) {
// 		SpringApplication.run(Application.class, args);
// 	}
//
//     @Bean
//     CommandLineRunner initDatabase(BasicDomainObjectRepository basicDomainObjectRepository,
//             StateDomainObjectRepository stateDomainObjectRepository) {
//         return args -> {
//             log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_ONE")));
//             log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_TWO")));
//             log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_THREE")));
//
//             stateDomainObjectRepository.save(new StateDomainObject("TEST_ONE", State.START));
//             stateDomainObjectRepository.save(new StateDomainObject("TEST_TWO", State.FINISH));
//             stateDomainObjectRepository.save(new StateDomainObject("TEST_THREE", State.START));
//
//             stateDomainObjectRepository.findAll().forEach(stateDomainObject -> {
//                 log.info("Preloaded " + stateDomainObject);
//             });
//         };
//     }
// }

// REST API CONSUMPTION
package com.tsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Consume consume = restTemplate.getForObject(
					"https://quoters.apps.pcfone.io/api/random", Consume.class);
			log.info(consume.toString());
		};
	}
}
