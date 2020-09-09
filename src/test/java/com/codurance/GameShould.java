package com.codurance;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class GameShould {

  @Test
  void wait_for_X_to_play_first() {
    assertThat(game.state()).isEqualTo(new GameState(Status.GAME_ON, Player.X));
  }
}
