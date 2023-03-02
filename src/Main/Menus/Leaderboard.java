package Main.Menus;

import Main.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
    private int highlightLine;

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
            if (page != (allGames[0].length - 1) / 20) {
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
                        page = (Integer.parseInt(text[1]) - 1) / 20;
                        display();
                    }
                    break;
                case "id":
                    System.out.println(Arrays.toString(sortArray(allGames[1])));
                    displaySearch(sortArray(allGames[1]), text[1]);
                    break;
                case "score":
                    displaySearch(allGames[2], text[1]);
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
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        format(backListener);
        jFrame.add(panel);
        page = 0;
        highlightLine = -1;
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
        Rank.setBounds(0, 0, 50, 350);
        ID.setBounds(50, 0, 50, 350);
        Score.setBounds(100, 0, 100, 350);
        Username.setBounds(200, 0, 240, 350);
        Ongoing.setBounds(440, 0, 60, 350);
        searchBar.setBounds(0, 392, 400, 30);
        search.setBounds(400, 392, 100, 30);
        back.setBounds(210, 443, 80, 20);
        previous.setBounds(0, 443, 100, 20);
        next.setBounds(400, 443, 100, 20);
        searchBar.addActionListener(searchListener);
        search.addActionListener(searchListener);
        back.addActionListener(backListener);
        next.addActionListener(nextListener);
        previous.addActionListener(previousListener);
    }

    public void displaySearch(String[] array, String item) {
        highlightLine = search(array, item, array.length / 2);
        page = highlightLine / 20;
        display();
    }

    private String[] sortArray(String[] array) {
        if (array.length == 1) {
            return array;
        } else {
            String[] array1 = sortArray(Arrays.copyOfRange(array, 0, array.length / 2));
            String[] array2 = sortArray(Arrays.copyOfRange(array, array.length / 2, array.length));
            return merge(array1, array2);
        }
    }

    private String[] merge(String[] array1, String[] array2) {
        String[] sorted = new String[array1.length + array2.length];
        int a1Pointer = 0;
        int a2Pointer = 0;
        int count = 0;

        while (a1Pointer < array1.length && a2Pointer < array2.length) {
            if (array1[a1Pointer].compareTo(array2[a2Pointer]) < 0) {
                sorted[count] = array2[a2Pointer];
                a2Pointer++;
            } else {
                sorted[count] = array1[a1Pointer];
                a1Pointer++;
            }
            count++;
        }

        while (a1Pointer < array1.length) {
            sorted[count] = array1[a1Pointer];
            a1Pointer++;
            count++;
        }

        while (a2Pointer < array2.length) {
            sorted[count] = array2[a2Pointer];
            a2Pointer++;
            count++;
        }

        return sorted;
    }

    //recursive binary search algorithm
    private int search(String[] array, String item, int pointer) {
        if (Integer.parseInt(array[array.length / 2]) == Integer.parseInt(item)) {
            return pointer;
        } else if (array.length == 1) { //if the number cannot be found
            return pointer;
        } else if (Integer.parseInt(array[array.length / 2]) > Integer.parseInt(item)) {
            return search(Arrays.copyOfRange(array, array.length / 2, array.length), item, pointer + array.length / 4);
        } else if (Integer.parseInt(array[array.length / 2]) < Integer.parseInt(item)) {
            return search(Arrays.copyOfRange(array, 0, array.length / 2), item, pointer - array.length / 4);
        } else {
            return -1;
        }
    }

    public void removePanel(JFrame jFrame) {
        jFrame.remove(panel);
    }

    //redraw components
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
        int len = Math.min(allGames[index].length - 1 - page * 20, 20);
        if (displayOngoing.equals("t") && displayUsername.equals("t")) {
            for (int i = 1; i < len + 1; i++) {
                if (i == highlightLine) {
                    rString += allGames[index][i + page * 20] + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^\n";
                } else {
                    rString += allGames[index][i + page * 20] + "\n";
                }
            }
        } else {
            int i = page * 20;
            int count = 0;
            while (count < page * 20 + 20 && i < allGames[0].length) {
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
