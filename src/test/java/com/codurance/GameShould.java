package com.codurance;


import static com.codurance.Player.O;
import static com.codurance.Player.X;
import static com.codurance.Square.TOP_LEFT;
import static com.codurance.Square.TOP_MIDDLE;
import static com.codurance.Status.GAME_ON;
import static com.codurance.Status.SQUARE_ALREADY_PLAYED;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameShould {

  @Test
  void wait_for_X_to_play_first() {
    Game game = new Game();
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
  }

  @Test
  void wait_for_O_to_play_after_X() {
    Game game = new Game();
    game = game.play(TOP_LEFT);
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, O));
  }

  @Test
  void alternate_the_players() {
    Game game = new Game();
    game = game.play(TOP_LEFT);
    game = game.play(TOP_MIDDLE);
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
  }

  @Test
  void not_permit_square_to_be_played_twice() {
    Game game = new Game();
    game = game.play(TOP_LEFT);
    game = game.play(TOP_MIDDLE);
    game = game.play(TOP_LEFT);
    assertThat(game.state()).isEqualTo(new GameState(SQUARE_ALREADY_PLAYED, X));
  }
}
