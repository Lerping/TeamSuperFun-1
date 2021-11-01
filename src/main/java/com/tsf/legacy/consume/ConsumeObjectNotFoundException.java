/* Indicates when a BasicDomainObject has been looked up but not found */

package com.tsf.legacy.consume;

class ConsumeObjectNotFoundException extends RuntimeException {

  ConsumeObjectNotFoundException() {
    super("Could not find ConsumeObject");
  }
}
