//LOAD DATABASE
package com.tsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.tsf.state.*;
import com.tsf.basic.*;
import com.tsf.consume.*;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    CommandLineRunner initDatabase(BasicDomainObjectRepository basicDomainObjectRepository,
        StateDomainObjectRepository stateDomainObjectRepository) {

        // Cheap way of preloading data
        return args -> {
            // BasicDomainObject
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_ONE")));
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_TWO")));
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_THREE")));

            // StateDomainObject
            stateDomainObjectRepository.save(new StateDomainObject("TEST_ONE", State.START));
            stateDomainObjectRepository.save(new StateDomainObject("TEST_TWO", State.FINISH));
            stateDomainObjectRepository.save(new StateDomainObject("TEST_THREE", State.START));

            stateDomainObjectRepository.findAll().forEach(stateDomainObject -> {
                log.info("Preloaded " + stateDomainObject);
            });
        };
    }
}
