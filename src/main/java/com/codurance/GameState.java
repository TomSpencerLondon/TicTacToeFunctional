package com.codurance;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class GameState {

  private final Status status;
  private final Player nextPlayer;

  public GameState(Status status, Player nextPlayer) {
    this.status = status;
    this.nextPlayer = nextPlayer;
  }

  @Override
  public String toString() {
    return "Status: " + status +  ", next up: " + nextPlayer;
  }

  @Override
  public boolean equals(Object other) {
    return reflectionEquals(this, other);
  }

  @Override
  public int hashCode() {
    return reflectionHashCode(this);
  }
}
