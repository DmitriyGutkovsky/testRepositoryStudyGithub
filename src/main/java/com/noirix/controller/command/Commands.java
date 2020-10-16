package com.noirix.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum Commands {
  FIND_BY_ID("findById"),
  FIND_ALL("findAll"),
  CREATE("create"),
  DELETE("delete"),
  UPDATE("update"),
  DEFAULT("findAll"),

  CAR_FIND_BY_ID("carFindById"),
  CAR_FIND_ALL("carFindAll"),
  CAR_CREATE("carCreate"),
  CAR_DELETE("carDelete"),
  CAR_UPDATE("carUpdate");


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
