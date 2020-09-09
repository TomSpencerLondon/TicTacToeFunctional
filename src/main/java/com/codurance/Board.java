package com.codurance;

public class Board {

  private Square takenSquare;

  public Board(Square square) {
    this.takenSquare = square;
  }

  public Board() {
    this.takenSquare = null;
  }

  public boolean alreadyTaken(Square square) {
    return takenSquare == square;
  }

  public Board take(Square square) {
    return new Board(square);
  }
}
