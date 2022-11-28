package game;

import java.sql.*;

public class Database {
    public static void main(String args[]){
        p();
    }

    public static int addUser(String username, String password){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\My Documents\\Computer science\\Programming\\NEA\\src\\game\\Game1.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            String query = "insert into Players (Username, Password) values('"+username+"','"+password+"');";
            int execute = stmt.executeUpdate(query);
            return execute;
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
            return 0;
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
