package com.codurance;


import static com.codurance.Player.O;
import static com.codurance.Player.X;
import static com.codurance.Square.BOTTOM_LEFT;
import static com.codurance.Square.BOTTOM_MIDDLE;
import static com.codurance.Square.BOTTOM_RIGHT;
import static com.codurance.Square.CENTRE_LEFT;
import static com.codurance.Square.CENTRE_MIDDLE;
import static com.codurance.Square.CENTRE_RIGHT;
import static com.codurance.Square.TOP_LEFT;
import static com.codurance.Square.TOP_MIDDLE;
import static com.codurance.Square.TOP_RIGHT;
import static com.codurance.Status.DRAW;
import static com.codurance.Status.GAME_ON;
import static com.codurance.Status.O_HAS_WON;
import static com.codurance.Status.SQUARE_ALREADY_PLAYED;
import static com.codurance.Status.X_HAS_WON;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class GameShould {

  @Test
  void wait_for_X_to_play_first() {
    Game game = new Game();
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
  }

  @Test
  void wait_for_O_to_play_after_X() {
    Game game = play(TOP_LEFT);
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, O));
  }

  @Test
  void alternate_the_players() {
    Game game = play(TOP_LEFT, TOP_MIDDLE);
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
  }

  @Test
  void not_permit_square_to_be_played_twice() {
    Game game = play(TOP_LEFT, TOP_MIDDLE, TOP_LEFT);
    assertThat(game.state()).isEqualTo(new GameState(SQUARE_ALREADY_PLAYED, X));
  }

  // X 0 X
  // X X O
  // O X O
  @Test
  void recognise_a_draw() {
    Game game = play(
        TOP_LEFT,
        TOP_MIDDLE,
        TOP_RIGHT,
        CENTRE_RIGHT,
        CENTRE_LEFT,
        BOTTOM_LEFT,
        CENTRE_MIDDLE,
        BOTTOM_RIGHT,
        BOTTOM_MIDDLE
    );
    assertThat(game.state()).isEqualTo(new GameState(DRAW));
  }

  @ParameterizedTest
  @CsvSource({
      "TOP_LEFT,TOP_MIDDLE,CENTRE_LEFT,CENTRE_MIDDLE,BOTTOM_LEFT",
      "TOP_MIDDLE,TOP_LEFT,CENTRE_MIDDLE,CENTRE_LEFT,BOTTOM_MIDDLE",
      "TOP_RIGHT,TOP_LEFT,CENTRE_RIGHT,CENTRE_LEFT,BOTTOM_RIGHT",
      "TOP_LEFT,CENTRE_LEFT,TOP_MIDDLE,CENTRE_MIDDLE,TOP_RIGHT",
      "CENTRE_LEFT,TOP_LEFT,CENTRE_MIDDLE,TOP_MIDDLE,CENTRE_RIGHT",
      "BOTTOM_LEFT,TOP_LEFT,BOTTOM_MIDDLE,TOP_MIDDLE,BOTTOM_RIGHT",
      "TOP_LEFT,TOP_MIDDLE,CENTRE_MIDDLE,BOTTOM_LEFT,BOTTOM_RIGHT",
      "TOP_RIGHT,TOP_MIDDLE,CENTRE_MIDDLE,BOTTOM_RIGHT,BOTTOM_LEFT"
  })
  void recognise_a_win(Square s1, Square s2, Square s3, Square s4, Square s5) {
    Game game = play(s1, s2, s3, s4, s5);

    assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON));
  }

  @Test
  void recognise_a_win_for_O() {
    Game game = play(
        TOP_RIGHT,
        TOP_LEFT,
        TOP_MIDDLE,
        CENTRE_LEFT,
        CENTRE_MIDDLE,
        BOTTOM_LEFT
    );
    assertThat(game.state()).isEqualTo(new GameState(O_HAS_WON));
  }

  private Game play(Square... squares) {
    return Arrays.stream(squares)
        .reduce(new Game(), Game::play, (a, b) -> null);
  }
}
