package com.codurance;

import static com.codurance.Player.O;
import static com.codurance.Player.X;
import static com.codurance.Status.GAME_ON;

public class Game {

  private final Status status;
  private final Player lastPlayer;

  public Game() {
    this.status = GAME_ON;
    this.lastPlayer = null;
  }
  public Game(Status status, Player lastPlayer) {
    this.status = status;
    this.lastPlayer = lastPlayer;
  }

  public GameState state() {
    return new GameState(status, nextPlayer());
  }

  public Game play() {
    return new Game(GAME_ON, nextPlayer());
  }

  private Player nextPlayer(){
    if (lastPlayer == null)
      return X;
    else
      return lastPlayer == X ? O : X;
  }
}
