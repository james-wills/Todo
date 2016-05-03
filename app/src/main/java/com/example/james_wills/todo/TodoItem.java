package com.example.james_wills.todo;

import com.orm.SugarRecord;

import java.util.Date;

public class TodoItem extends SugarRecord {
  private String name;
  private Priority priority;

  public TodoItem() {
    this.name = "";
    this.priority = Priority.MEDIUM;
  }

  public TodoItem(String name) {
    this.name = name;
    this.priority = Priority.MEDIUM;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setPriority(Priority p) {
    this.priority = p;
  }

  public Priority getPriority() {
    return priority;
  }

  public String toString() {
    return name;
  }
}

