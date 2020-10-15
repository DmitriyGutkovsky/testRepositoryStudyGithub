package com.noirix.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum Commands {
  FIND_BY_ID("findById"),
  FIND_ALL("findAll"),
  CREATE("create"), // save?
  DELETE("delete"),
  UPDATE("update"),
  DEFAULT("findAll");


  private String commandName;

  public static Commands findByCommandName(String commandName) {
    if (StringUtils.isNotBlank(commandName)) {
      for (Commands value : Commands.values()) {
        if (value.getCommandName().equalsIgnoreCase(commandName)) {
          return value;
        }
      }
    }
    return DEFAULT;
  }
}
