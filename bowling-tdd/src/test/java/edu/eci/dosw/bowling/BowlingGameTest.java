package edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    @Test
    @DisplayName("A1 - roll(0) no lanza excepcion")
    void rollZeroPins_shouldNotThrowException() {
        BowlingGame game = new BowlingGame();
        assertDoesNotThrow(() -> game.roll(0));
    }
    @Test
    @DisplayName("A2 - roll(-1) lanza IllegalArgumentException")
    void rollNegativePins_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }
    @Test
    @DisplayName("A3 - roll(11) lanza IllegalArgumentException")
    void rollMoreThan10Pins_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        assertThrows(IllegalArgumentException.class, () -> game.roll(11));
    }
    @Test
    @DisplayName("A4 - Dos tiros en un frame suman > 10 lanza IllegalArgumentException")
    void frameSumMoreThan10_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        game.roll(7);
        assertThrows(IllegalArgumentException.class, () -> game.roll(6));
    }
    @Test
    @DisplayName("A5 - roll() cuando el juego ya está completo lanza IllegalStateException")
    void rollWhenGameIsComplete_shouldThrowException() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++) { // 10 frames de 2 tiros normales
            game.roll(0);
        }
        assertThrows(IllegalStateException.class, () -> game.roll(0));
    }
    @Test
    @DisplayName("A6 - roll(10) detecta strike, marca FrameType.STRIKE y avanza frame")
    void strikeRoll_marksFrameAsStrikeAndAdvances() {
        BowlingGame game = new BowlingGame();
        game.roll(10);

        assertFalse(game.getFrames().isEmpty());
        assertEquals(FrameType.STRIKE, game.getFrames().get(0).getType());
    }
    @Test
    @DisplayName("A7 - roll(5) + roll(5) detecta spare y marca FrameType.SPARE")
    void spareRolls_marksFrameAsSpare() {
        BowlingGame game = new BowlingGame();
        game.roll(5);
        game.roll(5);

        assertEquals(FrameType.SPARE, game.getFrames().get(0).getType());
    }

}
