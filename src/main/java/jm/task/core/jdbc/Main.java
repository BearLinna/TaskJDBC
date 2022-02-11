package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {
    private static String DBUrl = null;
    private static String DBUser = null;
    private static String DBPassword = null;
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Mathwey", "DelPercio", (byte)7);
        User user2 = new User("Angela", "Devis", (byte)17);
        User user3 = new User("Vera", "Hadson", (byte)45);
        User user4 = new User("Denis", "Rodman", (byte)55);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
//        userService.removeUserById(2);
        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    public static Connection getConnection() {
        Properties proper = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            proper.load(fis);
            DBUrl = proper.getProperty("db.host");
            DBUser = proper.getProperty("db.user");
            DBPassword = proper.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
