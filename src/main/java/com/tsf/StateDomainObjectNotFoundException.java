/* Indicates when a StateDomainObject has been looked up but not found */

package com.tsf;

class StateDomainObjectNotFoundException extends RuntimeException {

  StateDomainObjectNotFoundException(Long id) {
    super("Could not find BasicDomainObject " + id);
  }
}
