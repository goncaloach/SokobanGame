package io.goncaloach.application;

import java.time.LocalDate;

public class Score {

    private static final String FIELD_SEPARATOR = " - ";
    private final String playerName;
    private final int score;

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player:" + playerName + FIELD_SEPARATOR +
                "Score:" + score + FIELD_SEPARATOR +
                "Date:" + LocalDate.now();
    }

}
