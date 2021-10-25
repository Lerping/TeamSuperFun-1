/* Indicates when a domainObject has been looked up but not found */

package com.tsf;

class DomainObjectNotFoundException extends RuntimeException {

  DomainObjectNotFoundException(Long id) {
    super("Could not find domainObject " + id);
  }
}
