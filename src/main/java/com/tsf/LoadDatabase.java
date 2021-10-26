package com.tsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BasicDomainObjectRepository basicDomainObjectRepository,
        StateDomainObjectRepository stateDomainObjectRepository) {
        return args -> {
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_ONE")));
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_TWO")));
            log.info("Preloading " + basicDomainObjectRepository.save(new BasicDomainObject("TEST_THREE")));

            stateDomainObjectRepository.save(new StateDomainObject("STATE_TEST_ONE", State.START));
            stateDomainObjectRepository.save(new StateDomainObject("STATE_TEST_TWO", State.FINISH));
            stateDomainObjectRepository.findAll().forEach(stateDomainObject -> {
                    log.info("Preloaded " + stateDomainObject);
            });
        };
    }
}
