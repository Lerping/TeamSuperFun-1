/* Defines the interface used to the for the JPA repository
 * https://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html
 */

package com.tsf;

import org.springframework.data.jpa.repository.JpaRepository;

interface DomainObjectRepository extends JpaRepository<DomainObject, Long> {
/* Queries would be placed here
 * @NamedQuery for specific method
 * @Query for native queries
 */
}
