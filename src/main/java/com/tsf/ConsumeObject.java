package com.tsf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumeObject {

  private String type;
  private Value value;

  public ConsumeObject() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Value getValue() {
    return value;
  }

  public void setValue(Value value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ConsumeObject{" +
        "type='" + type + '\'' +
        ", value=" + value +
        '}';
  }
}
