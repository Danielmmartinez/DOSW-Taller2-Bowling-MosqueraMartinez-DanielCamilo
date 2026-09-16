package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;
    private int firstRollInFrame = -1;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    /** Registra pinos derribados. Lanza IllegalArgumentException si pines < 0 o pines > 10.
     *  Lanza IllegalStateException si el juego ya termino. */
    public void roll(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("Los pines deben estar entre 0 y 10");
        }
        if (firstRollInFrame != -1) {
            if (firstRollInFrame + pins > 10) {
                throw new IllegalArgumentException("La suma de pines en el frame no puede superar 10");
            }
            firstRollInFrame = -1;
        } else {
            firstRollInFrame = pins;
        }
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        // TODO: implementar con TDD
        return 0;
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        // TODO: implementar con TDD
        return false;
    }

    public List<Frame> getFrames() { return List.copyOf(frames); }
}