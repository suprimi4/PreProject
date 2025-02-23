package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getJDBCConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists users (id SERIAL PRIMARY KEY, first_name varchar(200), last_name varchar(200), age smallint)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("insert into users (first_name, last_name, age) values (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery("select * from users")) {

            while (set.next()) {
                usersList.add(new User(set.getString(2), set.getString(3), set.getByte(4)));
            }

            return usersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("truncate table users");
        } catch (SQLException e) {
            throw new RuntimeException("Error while cleaning users table", e);
        }
    }
}
