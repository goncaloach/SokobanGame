package io.goncaloach.application;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ScorePrinterWriter {

    private static final int SCORES_SIZE = 5;

    public static void writeScore() {
        try (PrintWriter writer = new PrintWriter(getScorePathFile(SokobanGame.getInstance().getLevel()))) {
            readScores().forEach(writer::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Score> readScores() {
        List<Score> storedScores = new ArrayList<>();
        SokobanGame sokoban = SokobanGame.getInstance();
        int newScore = sokoban.getPlayer().getMoves();
        try (Scanner scanner = new Scanner(new File(getScorePathFile(sokoban.getLevel())))) {
            while (scanner.hasNextLine()) {
                storedScores.add(buildScoreFromLine(scanner));
            }
            addNewScoreConditionally(storedScores, newScore);
            storedScores.sort(Comparator.comparingInt(Score::getScore));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return storedScores;
    }

    private static String getScorePathFile(int level) {
        return "scores//score_" + level + ".txt";
    }

    private static void addNewScoreConditionally(List<Score> playersScores, int newScore) {
        if (!areScoresFull(playersScores)) {
            playersScores.add(new Score(readPlayerName(), newScore));
        } else if (isNewScoreBetterThanOneStored(playersScores, newScore)) {
            playersScores.set(SCORES_SIZE - 1, new Score(readPlayerName(), newScore));
        }
    }

    private static boolean isNewScoreBetterThanOneStored(List<Score> playersScores, int newScore) {
        return playersScores.get(SCORES_SIZE - 1).getScore() > newScore;
    }

    private static boolean areScoresFull(List<Score> playersScores) {
        return playersScores.size() == SCORES_SIZE;
    }

    private static Score buildScoreFromLine(Scanner scanner) {
        String[] info = scanner.nextLine().split(Score.FIELD_SEPARATOR);
        String playerName = info[0].substring(7);
        int playerScore = Integer.parseInt(info[1].substring(6));
        LocalDate playerDate = LocalDate.parse(info[2].substring(5));
        return new Score(playerName, playerScore, playerDate);
    }

    private static String readPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nickname:");
        return scanner.nextLine();
    }

}
