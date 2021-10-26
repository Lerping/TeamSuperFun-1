/* This will be the type of object used in the business logic. It remains in
 * the domain of the application.
 */

package com.tsf;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/* JPA annotation to make the object ready for JPA based storage ( H2 ) */
@Entity
class BasicDomainObject {

    /* @Id
     *   Java Persistence API
     *   Indicates that the following attribute is the primary key of the
     *   current object
     *
     * @GeneratedValue
     *   Java Persistence API
     *   Defines the ID generation strategy. If the attribute is not used
     *   the application is responsible for key generation
     */
    private @Id @GeneratedValue Long id;

    /* Attributes of the domain object
     * General data used the in bussiness logic of the application
     */
    private String data;

    BasicDomainObject() {}

        BasicDomainObject(String data) {
            this.data = data;
        }

        public Long getId() {
            return this.id;
        }

        public String getData() {
            return this.data;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
            return true;
            if (!(o instanceof BasicDomainObject))
            return false;

            BasicDomainObject BasicDomainObject = (BasicDomainObject) o;

            return Objects.equals(this.id, BasicDomainObject.id) &&
            Objects.equals(this.data, BasicDomainObject.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id, this.data);
        }

        @Override
        public String toString() {
            return "BasicDomainObject{" + "id=" + this.id + ", data='" + this.data + "'}";
        }
    }
