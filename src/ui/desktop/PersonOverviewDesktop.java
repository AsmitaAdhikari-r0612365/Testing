package ui.desktop;

import domain.db.DbException;
import domain.model.Person;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;

public class PersonOverviewDesktop {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX31?currentSchema=r0612365";
        properties.setProperty("user", "local_r0612365");
        properties.setProperty("password", "hrO-0TTFLpWgYKye");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

        String userid = JOptionPane.showInputDialog("What is your userid?");
        String firstname =  JOptionPane.showInputDialog("Firstname?");
        String lastname =  JOptionPane.showInputDialog("Lastname?");
        String email =  JOptionPane.showInputDialog("Email?");
        String password =  JOptionPane.showInputDialog("Password?");

        Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM r0612365.person");

        //statement.close();

        try(Connection conn = DriverManager.getConnection(url, properties);
            Statement state = connection.createStatement();){

            state.execute("INSERT INTO r0612365.person (userid, firstname, lastname, email, password) " +
                    "VALUES('"+userid+"',  '"+firstname+"' , '"+ lastname +"' , '"+ email +"' , '"+ password +"')");
            ResultSet res = statement.executeQuery("SELECT * FROM person");

        while (res.next()) {
            String userid1 = res.getString("userid");
            String firstname1 = res.getString("firstname");
            String lastname1 = res.getString("lastname");
            String email1 = res.getString("email");
            // validation of data stored in database
            Person person = new Person(userid1, firstname1, lastname1, email1);
            System.out.println(person.toString());
        }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
}
}

