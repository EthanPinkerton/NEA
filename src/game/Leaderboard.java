package game;

import javax.swing.*;
import java.awt.*;

public class Leaderboard {
    protected JPanel panel;
    private String[][] allGames;
    private final JTextArea Rank = new JTextArea();
    private final JTextArea ID = new JTextArea();
    private final JTextArea Score = new JTextArea();
    private final JTextArea Username = new JTextArea();
    private final JTextArea Ongoing = new JTextArea();
    private int page;

    public Leaderboard(JFrame jFrame) {
        panel = new JPanel();
        panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        jFrame.add(panel);
        page = 0;
        getGames();
        panel.setLayout(null);
        panel.add(Rank);
        panel.add(ID);
        panel.add(Score);
        panel.add(Username);
        panel.add(Ongoing);
        Rank.setEnabled(false);
        ID.setEnabled(false);
        Score.setEnabled(false);
        Username.setEnabled(false);
        Ongoing.setEnabled(false);
        Rank.setDisabledTextColor(Color.BLACK);
        ID.setDisabledTextColor(Color.BLACK);
        Score.setDisabledTextColor(Color.BLACK);
        Username.setDisabledTextColor(Color.BLACK);
        Ongoing.setDisabledTextColor(Color.BLACK);
        Rank.setBounds(0, 0, 100, jFrame.getHeight());
        ID.setBounds(100, 0, 100, jFrame.getHeight());
        Score.setBounds(200, 0, 100, jFrame.getHeight());
        Username.setBounds(300, 0, 100, jFrame.getHeight());
        Ongoing.setBounds(400, 0, 100, jFrame.getHeight());
        display();
    }

    private void display() {
        Rank.setText(getColumn(0));
        ID.setText(getColumn(1));
        Score.setText(getColumn(2));
        Username.setText(getColumn(3));
        Ongoing.setText(getColumn(4));
    }

    private String getColumn(int index) {
        String rString = "";
        int len = Math.min(allGames[index].length, 10);
        for (int i = 0; i < len; i++) {
            rString += allGames[index][i + page * 10] + "\n";
        }
        return rString;
    }

    private void getGames() {
        String[] games = Database.getGames();

        allGames = new String[5][games.length + 1];

        allGames[0][0] = "Index";
        allGames[1][0] = "ID";
        allGames[2][0] = "Score";
        allGames[3][0] = "Username";
        allGames[4][0] = "Ongoing";

        for (int i = 1; i < games.length + 1; i++) {
            String[] contents = games[i - 1].split("-");
            allGames[0][i] = String.valueOf(i);
            for (int j = 1; j < contents.length; j++) {
                allGames[j][i] = contents[j - 1];
            }
        }
    }

}
