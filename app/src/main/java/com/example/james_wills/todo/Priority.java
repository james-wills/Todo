package com.example.james_wills.todo;

import android.graphics.Color;

public enum Priority {

  VERY_LOW, LOW, MEDIUM, HIGH, VERY_HIGH;

  private int color;
  private String name;
  private int value;

  static {
    VERY_LOW.color = Color.parseColor("#ffbdbd");
    LOW.color = Color.parseColor("#ff8585");
    MEDIUM.color = Color.parseColor("#ff5c5c");
    HIGH.color = Color.parseColor("#ff0f0f");
    VERY_HIGH.color = Color.parseColor("#db0000");

    VERY_LOW.name = "very low";
    LOW.name = "low";
    MEDIUM.name = "medium";
    HIGH.name = "high";
    VERY_HIGH.name = "very high";

    VERY_LOW.value = 0;
    LOW.value = 1;
    MEDIUM.value = 2;
    HIGH.value = 3;
    VERY_HIGH.value = 4;
  }

  public int getColor() {
    return color;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name;
  }

  public static String[] getPriorityNames() {
    return new String[] {
        VERY_LOW.name,
        LOW.name,
        MEDIUM.name,
        HIGH.name,
        VERY_HIGH.name,
    };
  }

  public static Priority[] getPriorities() {
    return new Priority[] {
        VERY_LOW,
        LOW,
        MEDIUM,
        HIGH,
        VERY_HIGH,
    };
  }
}
