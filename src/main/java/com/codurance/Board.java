package com.codurance;

import static com.codurance.Square.BOTTOM_LEFT;
import static com.codurance.Square.BOTTOM_MIDDLE;
import static com.codurance.Square.CENTRE_LEFT;
import static com.codurance.Square.CENTRE_MIDDLE;
import static com.codurance.Square.TOP_LEFT;
import static com.codurance.Square.TOP_MIDDLE;
import static java.util.stream.Stream.of;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Board {

  private Set<Square> takenSquares;

  public Board() {
    this.takenSquares = Collections.emptySet();
  }

  private Board(Set<Square> squares) {
    this.takenSquares = squares;
  }

  public boolean alreadyTaken(Square square) {
    return takenSquares.contains(square);
  }

  public Board take(Square square) {
    Set<Square> newBoard = new HashSet<>(takenSquares);
    newBoard.add(square);
    return new Board(newBoard);
  }

  public boolean isFull() {
    return takenSquares.size() == 9;
  }

  public boolean hasWinningCombination() {
    Stream<Stream<Square>> winnningCombinations = of(
        of(TOP_LEFT, CENTRE_LEFT, BOTTOM_LEFT),
        of(TOP_MIDDLE, CENTRE_MIDDLE, BOTTOM_MIDDLE)
    );
    return winnningCombinations.anyMatch(winningCombination ->
            winningCombination.allMatch(takenSquares::contains)
        );
  }
}
