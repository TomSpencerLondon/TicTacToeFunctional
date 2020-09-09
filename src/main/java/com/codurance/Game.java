package com.codurance;

import static com.codurance.Player.X;
import static com.codurance.Status.GAME_ON;

public class Game {

  private final Status status;
  private final Player nextUp;

  public Game() {
    this.status = GAME_ON;
    this.nextUp = X;
  }
  public Game(Status status, Player nextUp) {
    this.status = status;
    this.nextUp = nextUp;
  }

  public GameState state() {
    return new GameState(status, nextUp);
  }

  public Game play() {
    return new Game(Status.GAME_ON, Player.O);
  }
}
