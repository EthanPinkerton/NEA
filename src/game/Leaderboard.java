package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Leaderboard {
    protected JPanel panel;
    private String[][] allGames;
    private final JTextArea Rank = new JTextArea();
    private final JTextArea ID = new JTextArea();
    private final JTextArea Score = new JTextArea();
    private final JTextArea Username = new JTextArea();
    private final JTextArea Ongoing = new JTextArea();
    private final JTextField searchBar = new JTextField();
    private String displayUsername = "t";
    private String displayOngoing = "t";
    private int page;
    private final ActionListener previousListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (page != 0) {
                page -= 1;
                display();
            }
        }
    };

    private final ActionListener nextListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (page != (allGames[0].length - 1) / 25) {
                page += 1;
                display();
            }
        }
    };

    private final ActionListener searchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] text = searchBar.getText().toLowerCase().split(":");
            switch (text[0]) {
                case "rank":
                    if (Integer.parseInt(text[1]) > 0 && Integer.parseInt(text[1]) < allGames[0].length) {
                        page = (Integer.parseInt(text[1]) - 1) / 25;
                        display();
                    }
                    break;
                case "id":
                    displaySearch(allGames[1],text[1]);
                    break;
                case "score":
                    displaySearch(allGames[2],text[1]);
                    break;
                case "username":
                    displayUsername = text[1];
                    page = 0;
                    display();
                    break;
                case "ongoing":
                    displayOngoing = text[1];
                    page = 0;
                    display();
                    break;
            }
        }
    };

    public Leaderboard(JFrame jFrame, ActionListener backListener) {
        panel = new JPanel();
        panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        format(backListener);
        jFrame.add(panel);
        page = 0;
        getGames();
        display();
    }

    private void format(ActionListener backListener) {
        JButton search = new JButton("Search");
        JButton previous = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton back = new JButton("Back");
        panel.setLayout(null);
        panel.add(Rank);
        panel.add(ID);
        panel.add(Score);
        panel.add(Username);
        panel.add(Ongoing);
        panel.add(searchBar);
        panel.add(search);
        panel.add(back);
        panel.add(next);
        panel.add(previous);
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
        Rank.setBounds(0, 0, 50, 441);
        ID.setBounds(50, 0, 50, 441);
        Score.setBounds(100, 0, 100, 441);
        Username.setBounds(200, 0, 240, 441);
        Ongoing.setBounds(440, 0, 60, 441);
        searchBar.setBounds(70, 442, 140, 20);
        search.setBounds(210, 442, 90, 20);
        back.setBounds(0, 442, 70, 20);
        previous.setBounds(300, 442, 100, 20);
        next.setBounds(400, 442, 100, 20);
        search.addActionListener(searchListener);
        back.addActionListener(backListener);
        next.addActionListener(nextListener);
        previous.addActionListener(previousListener);
    }

    public void displaySearch(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                page = i / 25;
                display();
                return;
            }
        }
    }

    public void removePanel(JFrame jFrame) {
        jFrame.remove(panel);
    }

    private void display() {
        Rank.setText(getColumn(0));
        ID.setText(getColumn(1));
        Score.setText(getColumn(2));
        Username.setText(getColumn(3));
        Ongoing.setText(getColumn(4));
        panel.repaint();
    }

    private String getColumn(int index) {
        String rString = allGames[index][0] + "\n\n";
        int len = Math.min(allGames[index].length - 1 - page * 25, 25);
        if (displayOngoing.equals("t") && displayUsername.equals("t")) {
            for (int i = 1; i < len + 1; i++) {
                rString += allGames[index][i + page * 25] + "\n";
            }
        } else {
            int i = page * 25;
            int count = 0;
            while (count < page * 25 + 25 && i < allGames[0].length) {
                if ((displayUsername.equals("t") || allGames[3][i].toLowerCase().equals(displayUsername)) && (displayOngoing.equals("t") || allGames[4][i].toLowerCase().equals(displayOngoing))) {
                    rString += allGames[index][i] + "\n";
                    count++;
                }
                i++;
            }
        }
        return rString;
    }

    private void getGames() {
        String[] games = Database.getGames();

        allGames = new String[5][games.length + 1];

        allGames[0][0] = "Rank";
        allGames[1][0] = "ID";
        allGames[2][0] = "Score";
        allGames[3][0] = "Username";
        allGames[4][0] = "Ongoing";

        for (int i = 1; i < games.length + 1; i++) {
            String[] contents = games[i - 1].split("-");
            for (int j = 0; j < contents.length + 1; j++) {
                if (j == 0) {
                    allGames[0][i] = String.valueOf(i);
                } else if (j == 4) {
                    if (contents[j - 1].equals("TRUE")) {
                        allGames[j][i] = "Yes";
                    } else if (contents[j - 1].equals("FALSE")) {
                        allGames[j][i] = "No";
                    }
                } else {
                    allGames[j][i] = contents[j - 1];
                }
            }
        }
    }

}
