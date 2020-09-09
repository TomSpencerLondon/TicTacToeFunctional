package com.codurance;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {

  private Set<Square> takenSquares;

  public Board(Set<Square> squares) {
    this.takenSquares = squares;
  }

  public Board() {
    this.takenSquares = Collections.emptySet();
  }

  public boolean alreadyTaken(Square square) {
    return takenSquares.contains(square);
  }

  public Board take(Square square) {
    Set<Square> newBoard = new HashSet<>(takenSquares);
    newBoard.add(square);
    return new Board(newBoard);
  }
}