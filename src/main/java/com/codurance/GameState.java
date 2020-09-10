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

  public GameState(Status status) {
    this.status = status;
    this.nextPlayer = null;
  }

  @Override
  public String toString() {
    String next = nextPlayer == null ? "" : ", next up: " + nextPlayer;

    return "Status: " + status + next;
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
