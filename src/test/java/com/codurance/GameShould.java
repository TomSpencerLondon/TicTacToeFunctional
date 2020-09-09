package com.codurance;


import static com.codurance.Player.O;
import static com.codurance.Player.X;
import static com.codurance.Status.GAME_ON;
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
    game = game.play();
    assertThat(game.state()).isEqualTo(new GameState(GAME_ON, O));
  }
}
