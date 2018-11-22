package domain.db;

import domain.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbSQL implements PersonDb {
    Properties properties;
    String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX31?currentSchema=r0612365";

    public PersonDbSQL(Properties properties) throws ClassNotFoundException {
        this.properties = new Properties();
        this.properties.setProperty("user", "local_r0612365");
        this.properties.setProperty("password", "hrO-0TTFLpWgYKye");
        this.properties.setProperty("ssl", "true");
        this.properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        Class.forName("org.postgresql.Driver");
    }

    @Override
    public Person get(String personId) {
        Person person = new Person();
        String sql = "SELECT * FROM person WHERE userid = ?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");

                person = new Person(personId, firstname, lastname, email);
            }
            return person;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Person> getAll() {
        String sql = "SELECT * FROM person";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            List<Person> persons = new ArrayList<>();

            while(result.next()) {
                String userid = result.getString("userid");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");

                Person person = new Person(userid, firstname, lastname, email);
                persons.add(person);
            }
            statement.executeQuery();
            return persons;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void add(Person person) {
        String sql = "INSERT INTO person (userid, firstname, lastname, email, password)" +" VALUES(?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getUserid());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
}

    @Override
    public void update(Person person){
        String sql = "UPDATE rO612365.Person SET firstname='" + person.getFirstName() + "', lastname='"
                + person.getLastName() + "', email='" + person.getEmail() + "', password='" + person.getPassword()
                + "' WHERE userid=" + person.getUserid();
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getUserid());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    @Override
    public void delete(String personId){
        String sql = "DELETE FROM person WHERE userid=?" ;
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, personId);
            statement.execute();
        } catch (SQLException E) {
            throw new DbException(E.getMessage(), E);
        }

    }

    @Override
    public void sort(String personId) {
        String sql = "ORDER BY FROM person WHERE userid=?";
        try(Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1,personId);
            statement.execute();
        }catch(SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }
}
