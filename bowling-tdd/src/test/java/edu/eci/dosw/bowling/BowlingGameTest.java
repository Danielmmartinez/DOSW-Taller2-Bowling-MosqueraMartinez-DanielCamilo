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

}
