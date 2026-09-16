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
    @Test
    @DisplayName("A8 - Frame 10 con strike acepta hasta 3 tiros")
    void tenthFrameWithStrike_acceptsThreeRolls() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 18; i++) { // 9 frames normales
            game.roll(0);
        }
        assertDoesNotThrow(() -> {
            game.roll(10); // Tiro 1 (Strike)
            game.roll(10); // Tiro 2 (Bono)
            game.roll(10); // Tiro 3 (Bono)
        });
    }


    @Test
    @DisplayName("C1 - isComplete() al inicio del juego retorna false")
    void isComplete_atStart_returnsFalse() {
        BowlingGame game = new BowlingGame();
        assertFalse(game.isComplete());
    }

    @Test
    @DisplayName("C2 - isComplete() tras 9 frames completos retorna false")
    void isComplete_after9Frames_returnsFalse() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 18; i++) game.roll(0);
        assertFalse(game.isComplete());
    }

    @Test
    @DisplayName("C3 - 10 frames normales completos retorna true")
    void isComplete_10NormalFrames_returnsTrue() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++) game.roll(1);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("C4 - Spare en frame 10 mas tiro bonus retorna true")
    void isComplete_tenthFrameSpareWithBonus_returnsTrue() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 18; i++) game.roll(0);
        game.roll(5); game.roll(5);
        game.roll(3);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("C5 - Strike en frame 10 mas 2 tiros bonus retorna true")
    void isComplete_tenthFrameStrikeWithTwoBonuses_returnsTrue() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 18; i++) game.roll(0);
        game.roll(10); game.roll(4); game.roll(3);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("C6 - Juego perfecto tras 12º strike retorna true")
    void isComplete_perfectGame_returnsTrue() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 12; i++) game.roll(10);
        assertTrue(game.isComplete());
    }

}
