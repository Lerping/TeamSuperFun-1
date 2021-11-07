/* Defines the interface used to the for the JPA repository
 * https://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html
 *
 * Gang of Four Repository
 *   https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 */

package com.tsf.oscars_prefill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OscarNominationEntryRepository extends CrudRepository<OscarNominationEntry, Long> {
    // List<Entry> findByLastName(String lastName);

    // Customer findById(long id);
}
