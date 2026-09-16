package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        List<Integer> rolls = new ArrayList<>();
        for (Frame frame : frames) {
            rolls.addAll(frame.getRolls());
        }

        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rolls, rollIndex)) {
                score += 10 + rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
                rollIndex++;
            } else if (isSpare(rolls, rollIndex)) {
                score += 10 + rolls.get(rollIndex + 2);
                rollIndex += 2;
            } else {
                score += rolls.get(rollIndex) + rolls.get(rollIndex + 1);
                rollIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(List<Integer> rolls, int index) {
        return rolls.get(index) == 10;
    }

    private boolean isSpare(List<Integer> rolls, int index) {
        return rolls.get(index) + rolls.get(index + 1) == 10;
    }
}