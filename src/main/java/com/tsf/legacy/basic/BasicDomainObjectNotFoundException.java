/* Indicates when a BasicDomainObject has been looked up but not found */

package com.tsf.legacy.basic;

class BasicDomainObjectNotFoundException extends RuntimeException {

  BasicDomainObjectNotFoundException(Long id) {
    super("Could not find BasicDomainObject " + id);
  }
}
