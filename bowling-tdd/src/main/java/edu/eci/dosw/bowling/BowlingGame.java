package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene exactamente 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    public void roll(int pins) {
        if (isComplete()) {
            throw new IllegalStateException("El juego ya está completo");
        }
        validatePins(pins);

        Frame current = getCurrentOrCreateFrame();

        if (currentFrame < 9) {
            handleStandardFrame(current, pins);
        } else {
            handleTenthFrame(current, pins);
        }
    }

    private void handleStandardFrame(Frame frame, int pins) {
        List<Integer> rolls = frame.getRolls();
        if (rolls.isEmpty()) {
            frame.addRoll(pins);
            if (pins == 10) {
                frame.setType(FrameType.STRIKE);
                currentFrame++;
            }
        } else {
            if (rolls.get(0) + pins > 10) {
                throw new IllegalArgumentException("La suma de pines en el frame no puede superar 10");
            }
            frame.addRoll(pins);
            if (rolls.get(0) + pins == 10) {
                frame.setType(FrameType.SPARE);
            }
            currentFrame++;
        }
    }

    private void handleTenthFrame(Frame frame, int pins) {
        frame.setType(FrameType.TENTH);
        List<Integer> rolls = frame.getRolls();

        if (rolls.size() == 1 && rolls.get(0) < 10 && rolls.get(0) + pins > 10) {
            throw new IllegalArgumentException("La suma de los dos primeros tiros no puede superar 10");
        }

        frame.addRoll(pins);
        if (isTenthFrameComplete(frame)) {
            currentFrame++;
        }
    }

    private boolean isTenthFrameComplete(Frame frame) {
        List<Integer> rolls = frame.getRolls();
        if (rolls.size() == 3) {
            return true;
        }
        return rolls.size() == 2 && (rolls.get(0) + rolls.get(1) < 10);
    }

    private Frame getCurrentOrCreateFrame() {
        if (frames.size() <= currentFrame) {
            frames.add(new Frame());
        }
        return frames.get(currentFrame);
    }

    private void validatePins(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("Los pines deben estar entre 0 y 10");
        }
    }

    public boolean isComplete() {
        return frames.size() == 10 && isTenthFrameComplete(frames.get(9));
    }

    public int score() {
        return 0;
    }

    public List<Frame> getFrames() {
        return List.copyOf(frames);
    }
}