package com.tsf.oscars_prefill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long year;
    private String category;
    private Boolean winner;
    private String entity;

    protected Entry() {}

    public Entry(Long year, String category, Boolean winner, String entity) {
        this.year = year;
        this.category = category;
        this.winner = winner;
        this.entity = entity;
    }

    public Long getId() {
        return this.id;
      }

    public Long getYear() {
        return this.year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getWinner() {
        return this.winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
      return String.format("Entry[year=%d,category=%s,winner=%b,entity=%s]",year,category,winner,entity);
    }
}