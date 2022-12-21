package game;

import java.sql.*;

public class Database {
    public static void main(String[] args) {
        printUsers();
        printGames();
    }

    public static Statement connect() throws NullPointerException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            return conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw (new NullPointerException());
        }
    }

    public static int addUser(String username, String password) {
        Statement stmt = connect();
        if (stmt == null) {
            return 0;
        }
        String query = "INSERT INTO Players (Username, Password) values('" + username + "','" + password + "');";
        try {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int loginUser(String username, String password) {
        try {
            Statement stmt = connect();
            String query = "SELECT Password FROM Players WHERE Username='" + username + "';";
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                if (resultSet.getString("Password").equals(password)) {
                    return 2;
                }
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static String[] loadGames(String user) {
        try {
            Statement stmt = connect();
            String query = "SELECT GameID,Seed,Score FROM Game WHERE Player='" + user + "' AND Ongoing='TRUE'";
            ResultSet resultSet = stmt.executeQuery(query);
            String results = "";
            while (resultSet.next()) {
                results += resultSet.getString("GameID") + "-" + resultSet.getString("Seed") + "-" + resultSet.getString("Score") + ",";
            }
            return results.split(",");
        } catch (SQLException e) {
            return new String[]{""};
        }
    }

    public static String[] getGames() {
        try {
            Statement stmt = connect();
            String query = "SELECT GameID,Score,Player,Ongoing FROM Game ORDER BY Score DESC";
            ResultSet resultSet = stmt.executeQuery(query);
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
        Statement stmt = connect();
        String query = "INSERT INTO Game (Player, Seed, Score, Ongoing, Health) values('" + user + "','" + seed + "',0,TRUE,10)";
        String getIDQuery = "SELECT GameID FROM Game WHERE Player='" + user + "' AND Seed='" + seed + "' AND Score=0 AND Ongoing=TRUE AND Health=10";
        try {
            stmt.executeUpdate(query);
            ResultSet resultSet = stmt.executeQuery(getIDQuery);
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
        Statement stmt = connect();
        String query = "UPDATE Game SET Score='" + score + "', Ongoing='" + ongoing + "', Health='" + health + "' WHERE GameID='" + id + "'";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ignored) {
        }
    }

    public static int deleteUser(String Username) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            String q3 = "DELETE FROM Players WHERE Username='" + Username + "';";
            return stmt.executeUpdate(q3);
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }

    public static int delete0Games() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            String q3 = "DELETE FROM Game WHERE Score=0;";
            return stmt.executeUpdate(q3);
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
    }

    public static void printUsers() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "SELECT Username, Password FROM Players;";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("Username") + " " + resultSet.getString("Password");
                System.out.println(title);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printGames() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "SELECT * FROM Game;";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4);
                System.out.println(title);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
