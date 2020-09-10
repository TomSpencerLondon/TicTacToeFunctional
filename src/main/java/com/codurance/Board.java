package com.codurance;

import static com.codurance.Square.BOTTOM_LEFT;
import static com.codurance.Square.BOTTOM_MIDDLE;
import static com.codurance.Square.BOTTOM_RIGHT;
import static com.codurance.Square.CENTRE_LEFT;
import static com.codurance.Square.CENTRE_MIDDLE;
import static com.codurance.Square.CENTRE_RIGHT;
import static com.codurance.Square.TOP_LEFT;
import static com.codurance.Square.TOP_MIDDLE;
import static com.codurance.Square.TOP_RIGHT;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Board {

  private Map<Square, Player> takenSquares;

  public Board() {
    this.takenSquares = Collections.emptyMap();
  }

  private Board(Map<Square, Player> takenSquares) {
    this.takenSquares = takenSquares;
  }

  public boolean alreadyTaken(Square square) {
    return takenSquares.keySet().contains(square);
  }

  public Board take(Square square, Player player) {
    Map<Square, Player> newBoard = new HashMap<>(takenSquares);
    newBoard.put(square, player);
    return new Board(newBoard);
  }

  public boolean isFull() {
    return takenSquares.size() == 9;
  }

  public boolean hasWinningCombination(Player player) {
    Stream<Stream<Square>> winnningCombinations = of(
        of(TOP_LEFT, CENTRE_LEFT, BOTTOM_LEFT),
        of(TOP_MIDDLE, CENTRE_MIDDLE, BOTTOM_MIDDLE),
        of(TOP_RIGHT, CENTRE_RIGHT, BOTTOM_RIGHT),
        of(TOP_LEFT,TOP_MIDDLE,TOP_RIGHT),
        of(CENTRE_LEFT,CENTRE_MIDDLE,CENTRE_RIGHT),
        of(BOTTOM_LEFT,BOTTOM_MIDDLE,BOTTOM_RIGHT),
        of(TOP_LEFT,CENTRE_MIDDLE,BOTTOM_RIGHT),
        of(TOP_RIGHT,CENTRE_MIDDLE,BOTTOM_LEFT)
    );
    return winnningCombinations.anyMatch(winningCombination ->
            winningCombination.allMatch(squaresTakenBy(player)::contains)
        );
  }

  private Set<Square> squaresTakenBy(Player player) {
    return takenSquares.entrySet().stream()
        .filter(entry -> entry.getValue() == player)
        .map(entry -> entry.getKey())
        .collect(toSet());
  }
}
