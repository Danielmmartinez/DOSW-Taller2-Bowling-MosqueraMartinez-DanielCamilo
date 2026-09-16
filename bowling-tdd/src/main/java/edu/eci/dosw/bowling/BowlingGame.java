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
        if (isComplete()) {
            throw new IllegalStateException("El juego ya está completo");
        }
        validatePins(pins);

        Frame current = getCurrentOrCreateFrame();

        if (pins == 10 && current.getRolls().isEmpty()) { // Strike
            current.addRoll(pins);
            current.setType(FrameType.STRIKE);
            currentFrame++;
        } else if (current.getRolls().size() == 1) { // Segundo tiro
            if (current.getRolls().get(0) + pins > 10) {
                throw new IllegalArgumentException("La suma de pines en el frame no puede superar 10");
            }
            current.addRoll(pins);
            currentFrame++;
        } else { // Primer tiro (normal)
            current.addRoll(pins);
        }
    }

    private Frame getCurrentOrCreateFrame() {
        if (frames.size() <= currentFrame) {
            frames.add(new Frame());
        }
        return frames.get(currentFrame);
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        return frames.size() == 10 && (
                frames.get(9).getType() == FrameType.STRIKE || frames.get(9).getRolls().size() == 2
        );
    }
    private void validatePins(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("Los pines deben estar entre 0 y 10");
        }
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        // TODO: implementar con TDD
        return 0;
    }
    public List<Frame> getFrames() { return List.copyOf(frames); }
}