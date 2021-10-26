package com.tsf;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE_DOMAIN_OBJECT")
class StateDomainObject {

    private @Id @GeneratedValue Long id;

    private String data;
    private State state;

    StateDomainObject() {}

        StateDomainObject(String data, State state) {
            this.data = data;
            this.state = state;
        }

        public Long getId() {
            return this.id;
        }

        public String getData() {
            return this.data;
        }

        public State getState() {
            return this.state;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setState(State state) {
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o)
                return true;

            if (!(o instanceof StateDomainObject))
                return false;

            StateDomainObject stateDomainObject = (StateDomainObject) o;

            return Objects.equals(this.id, stateDomainObject.id) &&
                   Objects.equals(this.data, stateDomainObject.data) &&
                   this.state == stateDomainObject.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id, this.data, this.state);
        }

        @Override
        public String toString() {
            return "StateDomainObject{" + "id=" + this.id + ", data='" + this.data + '\'' + ", state=" + this.state + '}';
        }
    }
