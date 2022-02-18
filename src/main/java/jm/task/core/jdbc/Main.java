package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
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

        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            if (Util.con != null) {
                Util.con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
