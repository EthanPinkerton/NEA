package game;

import java.sql.*;

public class Database {
    public static void main(String args[]){
        penis();
    }

    public static void penis(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\My Documents\\Computer science\\Programming\\NEA\\src\\game\\Game1.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String query = "select Username from Players";
            String q2 = "insert into Players (`Username`,`password`) values(`pp`,`pp`)";
            int resultSet2 = stmt.executeUpdate(q2);
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
