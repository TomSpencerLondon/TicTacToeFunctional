package com.codurance;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameShould {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void wait_for_X_to_play_first() {
    assertThat(game.state()).isEqualTo(new GameState(Status.GAME_ON, Player.X));
  }
}
