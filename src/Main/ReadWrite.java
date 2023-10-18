package Main;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWrite {

    public static List<Score> readScore() {
        List<Score> list = new ArrayList<Score>();
        SokobanGame soko = SokobanGame.getInstance();
        int moves = soko.getPlayer().getMoves();
        try {
            Scanner scan = new Scanner(new File("scores//score" + soko.getLevel() + ".txt"));
            String[] info;
            while (scan.hasNextLine()) {
                info = scan.nextLine().split("-");
                info[0] = info[0].trim();
                info[0] = info[0].substring(7);
                info[1] = info[1].replaceAll("\\D+", "");
                int n = Integer.parseInt(info[1]);
                list.add(new Score(info[0], n));
            }
            scan.close();
            if (list.size() > 4) {
                if (list.get(4).getScore() > moves) {
                    list.add(new Score(insertName(), moves));
                    list.remove(4);
                }
            } else {
                list.add(new Score(insertName(), moves));
            }
            list.sort((a, b) -> a.getScore() - b.getScore());
        } catch (Exception e) {
            System.err.println("erro ao ler");
        }
        return list;
    }


    public static void writeScore() {
        SokobanGame soko = SokobanGame.getInstance();
        List<Score> list = readScore();
        try {
            PrintWriter pw = new PrintWriter(new File("scores//score" + soko.getLevel() + ".txt"));
            for (Score s : list) {
                pw.println(s);
            }
            pw.close();
        } catch (Exception e) {
            System.err.println("erro ao escrever");
        }
    }


    public static String insertName() {
        String nome = "";
        Scanner keyscan = new Scanner(System.in);
        System.out.println("Nickname:");
        nome = keyscan.nextLine();
        return nome;
    }


}
