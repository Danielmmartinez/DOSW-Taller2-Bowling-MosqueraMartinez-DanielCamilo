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
    @DisplayName("B3 - Spare en frame 1 mas tiro de 3 suma 13 en frame 1")
    void spareBonus_addsNextRoll() {
        BowlingGame game = new BowlingGame();
        game.roll(5); game.roll(5); // Frame 1: Spare
        game.roll(3); game.roll(0); // Frame 2: 3 + 0
        for (int i = 0; i < 16; i++) game.roll(0); // Resto 0

        // Frame 1 (10 + 3) + Frame 2 (3) = 16
        assertEquals(16, game.score());
    }
    @Test
    @DisplayName("B6 - Todos spares con ultimo tiro a 5 score == 150")
    void allSpares_scores150() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            game.roll(5); game.roll(5);
        }
        game.roll(5); // Tiro bonus frame 10

        assertEquals(150, game.score());
    }
    @Test
    @DisplayName("B8 - score() antes de completar el juego lanza IllegalStateException")
    void scoreIncompleteGame_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        assertThrows(IllegalStateException.class, game::score);
    }

}