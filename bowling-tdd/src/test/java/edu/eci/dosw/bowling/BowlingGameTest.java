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

}
