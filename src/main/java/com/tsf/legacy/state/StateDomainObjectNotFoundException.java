/* Indicates when a StateDomainObject has been looked up but not found */

package com.tsf.legacy.state;

class StateDomainObjectNotFoundException extends RuntimeException {

  StateDomainObjectNotFoundException(Long id) {
    super("Could not find BasicDomainObject " + id);
  }
}
