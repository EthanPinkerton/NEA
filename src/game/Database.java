package game;

import java.sql.*;

public class Database {
    public static void main(String[] args){
        printUsers();
        printGames();
    }

    public static Statement connect(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + GetResource.getFile("Game1.accdb"));//Establishing Connection
            return conn.createStatement();
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    public static int addUser(String username, String password){
        Statement stmt = connect();
        if(stmt == null){
            return 0;
        }
        String query = "insert into Players (Username, Password) values('"+username+"','"+password+"');";
        try{
            return  stmt.executeUpdate(query);
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    public static int loginUser(String username, String password){
        try {
            Statement stmt = connect();
            String query = "select Password from Players where Username='"+username+"';";
            ResultSet resultSet = stmt.executeQuery(query);
            if(resultSet.next()){
                if(resultSet.getString("Password").equals(password)){
                    return 2;
                }
                return 1;
            }
            return 0;
        } catch (SQLException | NullPointerException e){
            System.out.println(e);
            return 0;
        }
    }

    public static int newGame(String user, String seed){
        Statement stmt = connect();
        String query = "insert into Game (Player, Seed, Score, Ongoing, Health) values('"+user+"','"+seed+"',0,Yes,10)";
        try{
            return stmt.executeUpdate(query);
        }catch (SQLException | NullPointerException e){
            System.out.println(e);
            return 0;
        }
    }

    public static int updateGame(int id,int score,String ongoing,int health){
        Statement stmt = connect();
        String query = "insert into Game (GameID,Score,Ongoing,Health) values("+id+","+score+",'"+ongoing+"',"+health+")";
        try {
            return stmt.executeUpdate(query);
        }catch (SQLException | NullPointerException e){
            System.out.println(e);
            return 0;
        }
    }

    public static int deleteUser(String Username){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            String q3 = "delete from Players Where Username='"+Username+"';";
            return stmt.executeUpdate(q3);
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
            return 0;
        }
    }

    public static void printUsers(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "select Username, Password from Players;";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                String title = resultSet.getString("Username") + " " + resultSet.getString("Password");
                System.out.println(title);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void printGames(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+GetResource.getFile("Game1.accdb"));//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "select * from Game;";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                String title = resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4);
                System.out.println(title);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void p(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\My Documents\\Computer science\\Programming\\NEA\\src\\game\\Game1.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "select Username from Players;";
            //String q2 = "insert into Players (Username,Password) values('abc','zxy');";
            String q3 = "delete from Players Where Username='';";
            int reselutset3 = stmt.executeUpdate(q3);
            //int resultSet2 = stmt.executeUpdate(q2);
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                String title = resultSet.getString("Username");
                System.out.println(title);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
