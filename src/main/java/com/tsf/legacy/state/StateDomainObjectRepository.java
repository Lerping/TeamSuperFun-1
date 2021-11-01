/* Defines the interface used to the for the JPA repository
 * https://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html
 *
 * Gang of Four Repository
 *   https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 */

package com.tsf.legacy.state;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateDomainObjectRepository extends JpaRepository<StateDomainObject, Long> {
/* Queries would be placed here
 * @NamedQuery for specific method
 * @Query for native queries
 */
}
