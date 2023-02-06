package game;

import java.sql.*;

public class Database {
    public static void main(String[] args) {
        printUsers();
        printGames();
    }

    public static void connect() throws NullPointerException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
        } catch (SQLException | ClassNotFoundException e) {
            throw (new NullPointerException());
        }
    }

    public static ResultSet selectQuery(String query) throws NullPointerException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw (new NullPointerException());
        }
    }

    public static int updateQuery(String query) throws NullPointerException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw (new NullPointerException());
        }
    }

    public static int addUser(String username, String password) {
        return updateQuery("INSERT INTO Players (Username, Password) values('" + username + "','" + password + "');");
    }

    public static int loginUser(String username, String password) {
        ResultSet resultSet = selectQuery("SELECT Password FROM Players WHERE Username='" + username + "';");
        try {
            if (resultSet.next()) {
                if (resultSet.getString("Password").equals(password)) {
                    return 2;
                }
                return 1;
            }
        } catch (SQLException ignored) {}
        return 0;
    }

    public static String[] loadGames(String user) {
        try {
            ResultSet resultSet = selectQuery("SELECT GameID,Seed,Score FROM Game WHERE Player='" + user + "' AND Ongoing='TRUE'");
            String results = "";
            while (resultSet.next()) {
                results += resultSet.getString("GameID") + "-" + resultSet.getString("Seed") + "-" + resultSet.getString("Score") + ",";
            }
            return results.split(",");
        } catch (SQLException e) {
            return new String[]{""};
        }
    }

    public static String[] getGameStuffs(int id) {
        try {
            ResultSet resultSet = selectQuery("SELECT Seed,Score,Health FROM Game WHERE GameID=" + id);
            if (resultSet.next()) {
                String results = resultSet.getString("Seed") + "-" + resultSet.getString("Score") + "-" + resultSet.getString("Health");
                return results.split("-");
            } else {
                return new String[]{""};
            }
        } catch (SQLException e) {
            return new String[]{""};
        }
    }

    public static String[] getGames() {
        try {
            ResultSet resultSet = selectQuery("SELECT GameID,Score,Player,Ongoing FROM Game ORDER BY Score DESC");
            String results = "";
            while (resultSet.next()) {
                results += resultSet.getString("GameID") + "-" + resultSet.getString("Score") + "-" + resultSet.getString("Player") + "-" + resultSet.getString("Ongoing") + ",";
            }
            return results.split(",");
        } catch (SQLException e) {
            return new String[]{""};
        }
    }

    public static int newGame(String user, String seed) {
        try {
            updateQuery("INSERT INTO Game (Player, Seed, Score, Ongoing, Health) values('" + user + "','" + seed + "',0,TRUE,10)");
            ResultSet resultSet = selectQuery("SELECT GameID FROM Game WHERE Player='" + user + "' AND Seed='" + seed + "' AND Score=0 AND Ongoing=TRUE AND Health=10");
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("GameID"));
            } else {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public static void updateGame(int id, int score, String ongoing, double health) {
        updateQuery("UPDATE Game SET Score='" + score + "', Ongoing='" + ongoing + "', Health='" + health + "' WHERE GameID='" + id + "'");
    }

    public static int deleteUser(String Username) {
        return updateQuery("DELETE FROM Players WHERE Username='" + Username + "';");
    }

    public static int delete0Games() {
        return updateQuery("DELETE FROM Game WHERE Score=0;");
    }

    public static void printUsers() {
        try {
            ResultSet resultSet = selectQuery("SELECT Username, Password FROM Players;");
            while (resultSet.next()) {
                String title = resultSet.getString("Username") + " " + resultSet.getString("Password");
                System.out.println(title);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void printGames() {
        try {
            ResultSet resultSet = selectQuery("SELECT * FROM Game;");
            while (resultSet.next()) {
                String title = "";
                for (int i = 0; i < 6; i++) {
                    title += resultSet.getString(i+1) + " ";
                }
                System.out.println(title);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
