package io.goncaloach.application;

import java.time.LocalDate;

public class Score {

    public static final String FIELD_SEPARATOR = " - ";
    private final String playerName;
    private final int score;
    private final LocalDate date;

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.date = LocalDate.now();
    }

    public Score(String playerName, int score, LocalDate date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player:" + playerName + FIELD_SEPARATOR +
                "Score:" + score + FIELD_SEPARATOR +
                "Date:" + date;
    }

}
