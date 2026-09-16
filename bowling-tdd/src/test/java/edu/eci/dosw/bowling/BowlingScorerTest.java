package edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BowlingScorerTest {

    private final BowlingScorer scorer = new BowlingScorer();
    @Test
    @DisplayName("B1 - Juego con todos los tiros a 0 score == 0")
    void allZeros_scoresZero() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++) game.roll(0);
        assertEquals(0, scorer.calculate(game.getFrames()));
    }

    @Test
    @DisplayName("B2 - Juego sin strikes ni spares suma directa de pinos")
    void gameWithoutBonuses_scoresSumOfPins() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++) game.roll(1); // 20 tiros de 1 pino = 20 puntos
        assertEquals(20, scorer.calculate(game.getFrames()));
    }
    @Test
    @DisplayName("B8 - score() antes de completar el juego lanza IllegalStateException")
    void scoreIncompleteGame_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        assertThrows(IllegalStateException.class, game::score);
    }

}