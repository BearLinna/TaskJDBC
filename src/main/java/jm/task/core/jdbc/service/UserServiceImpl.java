package jm.task.core.jdbc.service;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        try (Connection connection = Main.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "lastName VARCHAR(100), " +
                     "age TINYINT" +
                     ");")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Main.getConnection();
             PreparedStatement ps = connection.prepareStatement("DROP TABLE IF EXISTS user;")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Main.getConnection();
             PreparedStatement ps = connection
              .prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?);")) {
            createUsersTable();
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Main.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Main.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user;")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }
}
