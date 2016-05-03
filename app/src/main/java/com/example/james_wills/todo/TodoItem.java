package com.example.james_wills.todo;

import com.orm.SugarRecord;

public class TodoItem extends SugarRecord {
  private String name;

  public TodoItem() {
    this.name = "";
  }

  public TodoItem(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name;
  }
}

