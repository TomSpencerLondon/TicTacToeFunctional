package com.codurance;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class GameState {

  private final Status gameOn;
  private final Player nextPlayer;

  public GameState(Status gameOn, Player nextPlayer) {
    this.gameOn = gameOn;
    this.nextPlayer = nextPlayer;
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
